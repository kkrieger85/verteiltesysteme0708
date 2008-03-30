package project.network.discovery;

import java.net.*;
import java.io.*;
import java.rmi.*;
import java.rmi.registry.*;

import project.helperclasses.DDLogger;
import project.network.IPList;
import project.network.ServerDataObject;

/**
 * RMILookup-Klasse
 * Stellt Server bereit der auf valide Nachfragen wartet um Discovery zu erfüllen.
 *
 * @author Andreas Kuntz
 * 
 * @version 0.2 (28.03.2008)
 * 
 */
public class RMILookup implements Runnable {

        /**
         * reference to RMI server
         */
        private Remote _service;

        /**
         * Interfaces implemented by RMI server
         */
        private Class<DiscoveryServerInterface> [] _serviceInterface;

        /**
         * Unique name of RMI server: used to distinguish between multiple instances
         */
        private String _serviceName;
        
        /**
         * cache of threads created: used in stop() to interrupt
         */
        private static java.util.List<Thread> _threadList = new java.util.ArrayList<Thread>();
        
        private RMILookup(Remote service, String serviceName){
            _service = service;
            _serviceName = serviceName;
            parseInterfaces();
        }
        
        @SuppressWarnings("unchecked")
		private void parseInterfaces() {
            Class c = _service.getClass();
            _serviceInterface = c.getInterfaces();
        }
        
        /**
         * Stops all mulitcast listeners.
         * The JVM will not exit unless you explicity unexport the remote ref
         * e.g UnicastRemoteObject.unexport( remote, true );
         * 
         * @since 1
         */
        public static synchronized void stop() {
            //an alternative here would be to store the RMILookup instances
            //which could have a MulticastSocket field, we could then close that socket
            //forcing run() to exit if we were in receive()
        	DDLogger.getLogger().createLog("RMI lookup: stopping listeners...", DDLogger.DEBUG);
            
            Object [] lookup = _threadList.toArray();
            for(int i = 0; i < lookup.length; i++){
                ((Thread)lookup[i]).interrupt();
                DDLogger.getLogger().createLog("Interrupting thread " + lookup[i], DDLogger.DEBUG);
            }
            _threadList = new java.util.ArrayList<Thread>();
            System.gc();
        }

        /**
         * Create a lookup service to listener for requests for the interface implemented by remote and matching name
         * Also bind to rmiregistry (if running) on default port
         *
         * @param service RMI server - used to match interface requests and to serialize back to disciverer
         * @param serviceName Unique name associated with this service. This parameter is used to distinguish between multiple intances of the service
         */
        public static void bind(Remote service, String serviceName) {
            bind(service, serviceName, Registry.REGISTRY_PORT);
        }
        
        /**
         * Create a lookup service to listener for requests for the interface implemented by remote and matching name
         * Also bind to rmiregistry (if running) on specified port
         *
         * @param service RMI server - used to match interface requests and to serialize back to disciverer
         * @param serviceName Unique name associated with this service. This parameter is used to distinguish between multiple intances of the service
         * @param port registry port to bind to (if running)
         */
        public static void bind(Remote service, String serviceName, int port) {
            try {
                //check Discovery properties for naming convention
                //then attempt to bind to the rmi-reg
                String nameToReg = DiscoveryProp.getRegistyUrlPrefix() + serviceName;
                Registry reg = LocateRegistry.getRegistry(port);
                reg.rebind(nameToReg, service);
                
            } catch(Exception ex) {
            	DDLogger.getLogger().createLog("RMI lookup: Warning: can't locate rmi registry. Reason: " + ex.getMessage(), DDLogger.ERROR);
            }
            DDLogger.getLogger().createLog("RMI lookup: Starting up for " + serviceName, DDLogger.DEBUG);
            
            //now initiate the lookup service
            RMILookup lookup = new RMILookup(service, serviceName); 
            Thread t = new Thread(lookup, "RMI lookup");
            t.setDaemon(true);
            t.start();
            _threadList.add(t);
        }

        /**
         * Create multicast listener then loop listening for valid requests
         */
        @SuppressWarnings({ "unchecked", "static-access" })
		public void run() {
            try {
            	DDLogger.getLogger().createLog("RMI lookup: listening....", DDLogger.DEBUG);
                
                MulticastSocket socket = new MulticastSocket(DiscoveryProp.getMulticastPort());
                InetAddress address = DiscoveryProp.getMulticastAddress();
                DDLogger.getLogger().createLog("RMI lookup: Multicast address=" + address, DDLogger.DEBUG);
            	
            	socket.joinGroup(address);

                while(!Thread.currentThread().isInterrupted()) {
                    
                    //set timeout on socket to allow for interrupts to be detected
                    socket.setSoTimeout(10000);
                    byte[] buf = new byte[512];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    try {
                        socket.receive(packet);
                    } catch(IOException ex) {
                    	DDLogger.getLogger().createLog(ex.getMessage(), DDLogger.ERROR);
                        //timeout on socket, so let's go around again
                        continue;
                    }
                    //NOTE: we are not using a thread per request here as a
                    //blocking strategy if okay. This works because clients send
                    //requests multiple times. It also appears that requests are 
                    //getting buffered someone so concurrent requests still get processed
                    
                    String msg = new String(packet.getData()).trim();
                    DDLogger.getLogger().createLog("RMI lookup: Recieved messaged " + msg, DDLogger.DEBUG);
                    if(msg.startsWith(DiscoveryProp.getProtocolHeader()) && IPList.getInstance().isIsroot()) {
                        
                        //request in format
                        //<header><delim><port><delim><interface><delim><serviceName>
                        
                        String [] params = null;
                        try {
                            params = parseMsg(msg);
                        } catch(Exception ex) {
                        	DDLogger.getLogger().createLog("RMI lookup: bad packet format " + ex.getMessage(), DDLogger.ERROR);
                            continue;
                        }
                        InetAddress repAddress = packet.getAddress();
                        int repPort = Integer.parseInt(params[0]);
                        String interfaceName = params[1];
                        String serviceName = params[2];
                        
    					IPList.getInstance().addObject(new ServerDataObject(repAddress.toString(), String.valueOf(Registry.REGISTRY_PORT), 0));
                        
                        DDLogger.getLogger().createLog("RMI lookup: Service name=" + serviceName, DDLogger.DEBUG);
                        DDLogger.getLogger().createLog("RMI lookup: Service interface=" + interfaceName, DDLogger.DEBUG);
                        DDLogger.getLogger().createLog("RMI lookup: Reply address=" + repAddress, DDLogger.DEBUG);
                        DDLogger.getLogger().createLog("RMI lookup: Reply port=" + repPort, DDLogger.DEBUG);
                        
                        //if the Discovery.ANY flag has been passed in or a valid name doesn't match this name
                        //let's go 'round again
                        if(!serviceName.equals(DiscoveryProp.ANY) && !serviceName.equals(_serviceName)) {
                        	DDLogger.getLogger().createLog(getClass() + " service name mismatch", DDLogger.DEBUG);
                            continue;
                        }
                        //now check that the service implements the interface
                        //the discoverer is looking for
                        boolean match = false;
                        for(int i = 0; !match && i < _serviceInterface.length; i++) {
                            match = _serviceInterface[i].getName().equals(interfaceName);
                        }
                        if(match) {
                            try {
                                //okay now do a unicast request back to the discoverer
                                Socket sock = new Socket(repAddress, repPort);
                                ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
                                oos.writeObject(new MarshalledObject(_service));
                                oos.flush();
                                oos.close();
                            //must catch all here otherwise listener dies    
                            } catch(Throwable ex) {
                                //Connection refused will occur when the client
                                //has already got a proxy(from another listener) and has shutdown its listener
                            	DDLogger.getLogger().createLog("RMI lookup: discoverer(client) listener may have shutdown: " + ex.getMessage(), DDLogger.DEBUG);
                            }
                        } else {
                        	DDLogger.getLogger().createLog("RMI lookup: No matching interface", DDLogger.DEBUG);
                        }
                    }
                }
                socket.leaveGroup(address);
                socket.close();         
            } catch(Exception ex) {
                ex.printStackTrace(System.err);
            }
            DDLogger.getLogger().createLog("RMI Lookup: listener exiting", DDLogger.DEBUG);
        }
        
        /**
         * 
         * @param msg
         * @return
         */
        private String [] parseMsg(String msg){
            //request in format
            //<header><delim><port><delim><interface><delim><serviceName>
            java.util.StringTokenizer tok = new java.util.StringTokenizer(msg, DiscoveryProp.getProtocolDelim());
            tok.nextToken(); //msg header
            String [] strArray = new String[3];
            strArray[0] = tok.nextToken();//reply port
            strArray[1] = tok.nextToken();//interface name
            strArray[2] = tok.nextToken();//service name
            
            return strArray;            
        }
}

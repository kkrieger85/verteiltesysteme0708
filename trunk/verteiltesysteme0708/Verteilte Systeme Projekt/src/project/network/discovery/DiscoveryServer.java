package project.network.discovery;

import java.rmi.*;
import java.rmi.server.*;
import java.util.LinkedList;

import project.network.IPList;
import project.network.ServerDataObject;

/**
 * RMI-Server
 * nutzt RMILookup
 *
 * @author Andreas Kuntz
 * 
 * @version 0.1 (17.03.2008)
 *
 */
public class DiscoveryServer extends UnicastRemoteObject implements DiscoveryServerInterface {

	/**
	 * 
	 * @param args
	 */
    public static void main(String [] args) {
        work();
    }
    
    public static void work() {
    	try {
            checkCodebase();
             
            System.setSecurityManager(new RMISecurityManager());
            
            //specify where the properties file is located
            //here it's in the directory that you run java from
            //you can set the properties on the cmd line if you prefer
            //Alt: you can also use setProperties(Properties props);
            DiscoveryProp.setProperties("saved_files/discovery.properties");
             
            //create service instance 
            DiscoveryServer server = new DiscoveryServer();
            
            //create a jini like lookup service for the RMI service
            //with the service instance and its name
            RMILookup.bind(server, "Discovery");
            //NOTE: bind() will also try to bind to the RMIRegistry if it is running
            //This doesn't affect the multicast listener.
            //if the registry isn't running then a warning message if printed
             
         } catch(Exception ex) {
             ex.printStackTrace();
         }
    }
    
    /**
     * 
     * @throws RemoteException
     */
    public DiscoveryServer() throws RemoteException {
        super();
    }
    
    /**
     * 
     * @param word
     * @return
     * @throws RemoteException
     */
    public String test() throws RemoteException {
        return "OK!";
    }
    
    public LinkedList<ServerDataObject> getIPList() {
    	return IPList.getInstance().getIPList();
    }
    
    public void setIPList(LinkedList<ServerDataObject> list){
    	IPList.getInstance().setIPList(list);
    }

    /**
     * 
     */
    private static void checkCodebase() {
        String codebase = System.getProperty("java.rmi.server.codebase");
            
        if(codebase == null) {
            String msg = "Codebase not set. Use" + "-Djava.rmi.server.codebase=";
            throw new RuntimeException(msg);
        } else {
            System.out.println(codebase);
        }
    }
}

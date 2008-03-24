package project.network.discovery;

import java.rmi.*;
import java.rmi.server.*;

/**
 *
 *
 * @author Andreas Kuntz
 * 
 * @version 0.1 (17.03.2008)
 *
 */
public class DiscoveryServer extends UnicastRemoteObject implements DiscoveryServerInterface {

	private java.util.HashMap<String, String> _dictionary = new java.util.HashMap<String, String>();
    
	/**
	 * 
	 * @param args
	 */
    public static void main(String [] args) {
        try {
           checkCodebase();
            
           System.setSecurityManager(new RMISecurityManager());
           
           //specify where the properties file is located
           //here it's in the directory that you run java from
           //you can set the properties on the cmd line if you prefer
           //Alt: you can also use setProperties(Properties props);
           DiscoveryProp.setProperties("discovery.properties");
            
           //create service instance 
           DiscoveryServer server = new DiscoveryServer();
           
           //create a jini like lookup service for the RMI service
           //with the service instance and its name
           RMILookup.bind(server, "Spanish");
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
        init();
    }
    
    /**
     * 
     * @param word
     * @return
     * @throws RemoteException
     */
    public String translateWord(String word) throws RemoteException {
        Object result = _dictionary.get(word.toLowerCase());
        if(result == null) {
            throw new RemoteException(word + " not found");
        }
        
        System.out.println(getClass() + " translateWord(" + word + ")");
        
        return result.toString();
    }
    
    /**
     * //add some words to the example dictionary
     */
    private void init() {
        _dictionary.put("hello","hola");
        _dictionary.put("goodbye","Adiós");
        _dictionary.put("happy","feliz");
        _dictionary.put("sad","triste");
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

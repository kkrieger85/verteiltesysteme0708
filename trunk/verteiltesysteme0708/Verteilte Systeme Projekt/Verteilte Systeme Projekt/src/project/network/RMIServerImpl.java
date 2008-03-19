/**
 * 
 */
package project.network;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import project.helperclasses.DDLogger;
import project.helperclasses.NetworkHelper;


/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *	RMI Serverimplementierung !!!
 */
public class RMIServerImpl {
	
	/**
	 * Konstruktor 
	 * Ruft einfach nur den Server auf und startet ihn. 
	 */
	public RMIServerImpl(){
	DDLogger ddl = DDLogger.getLogger(); 
	String host = null ;
	NetworkHelper networkHelper = new NetworkHelper(); 
	host = networkHelper.getOwnIPAdress(); 
	
    try {
	      LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
	      ddl.createLog("Server wird gestartet ... Listening Port: " + Registry.REGISTRY_PORT , DDLogger.INFO); 
	    }	    
	    catch (RemoteException ex) {
	      System.out.println(ex.getMessage());
	    }
	    try {
	      Naming.rebind("rmi://"+host+"/server", new RMINetworkImpl());
	    }
	    catch (MalformedURLException ex) {
	    	ddl.createLog(ex.getMessage() , DDLogger.FATAL); 
	    }
	    catch (RemoteException ex) {
	    	ddl.createLog(ex.getMessage() , DDLogger.FATAL); 
	    }
	}
}


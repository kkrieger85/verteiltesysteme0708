/**
 * 
 */
package konzept.spielwiese;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class RMITestServer {

	/**
	 * Standard Main Methode zum Starten des Servers 
	 * @param args
	 */
	public static void main(String[] args) {
		
		String host = "localhost"; 
		if (args.length != 0){
			if (args[0]!= null)
			{
				host = args[0]; 
			}
		} 

	    try {
		      LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
		    }	    
		    catch (RemoteException ex) {
		      System.out.println(ex.getMessage());
		    }
		    try {
		      Naming.rebind("rmi://"+host+"/server", new RMITestImpl());
		    }
		    catch (MalformedURLException ex) {
		      System.out.println(ex.getMessage());
		    }
		    catch (RemoteException ex) {
		      System.out.println(ex.getMessage());
		      ex.printStackTrace(); 
		    }
	}

}

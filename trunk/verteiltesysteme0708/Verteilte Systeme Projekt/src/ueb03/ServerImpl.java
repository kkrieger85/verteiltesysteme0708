/**
 * 
 */
package ueb03;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implementierung des Interfaces zum Testen der RMI Geschichte 
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 * @version 1.00
 */
public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

	/**
	 * Static Serial 
	 */
	private static final long serialVersionUID = 1L;

	public ServerImpl() throws RemoteException {
		super();
	}
	/* (non-Javadoc)
	 * @see ueb03.ServerInterface#getInfo()
	 */
	public String getInfo() throws RemoteException {
		return "Admin;Admin;Blubbnummer";
	}

	/* (non-Javadoc)
	 * @see ueb03.ServerInterface#login()
	 */
	public boolean login(String name, String pass) throws RemoteException {
		 
		if (name.compareTo("Admin") == 0 && pass.compareTo("Admin")==0){
			System.out.println("Login as: " + name);
			return true; 
		}
		System.out.println("Wrong Login as: " + name);
		return false; 
	}

	/* (non-Javadoc)
	 * @see ueb03.ServerInterface#logout()
	 */
	public boolean logout() throws RemoteException {
		return true; 
	}
	
}

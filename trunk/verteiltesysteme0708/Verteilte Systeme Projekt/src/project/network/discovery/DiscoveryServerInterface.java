package project.network.discovery;

import java.rmi.*;
import java.util.LinkedList;

import project.network.ServerDataObject;

/**
 *
 *
 * @author Andreas Kuntz
 * 
 * @version 0.1 (20.03.2008)
 *
 */
public interface DiscoveryServerInterface extends Remote {

	/**
	 * 
	 * @param word
	 * @return
	 * @throws RemoteException
	 */
	public String test() throws RemoteException;
	
	public LinkedList<ServerDataObject> getIPList();
	
	public void setIPList(LinkedList<ServerDataObject> list);
}

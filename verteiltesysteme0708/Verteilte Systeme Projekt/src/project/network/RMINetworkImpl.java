/**
 * 
 */
package project.network;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

import project.helperclasses.DDLogger;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class RMINetworkImpl extends UnicastRemoteObject implements
		RMINetworkInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5498171438169548681L;

	/**
	 * Auto-generated Code 
	 * @throws RemoteException
	 */
	public RMINetworkImpl() throws RemoteException {
	}

	/**
	 * Auto-generated Code 
	 * @param arg0
	 * @throws RemoteException
	 */
	public RMINetworkImpl(int arg0) throws RemoteException {
		super(arg0);
	}

	/**
	 * Auto-generated Code 
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws RemoteException
	 */
	public RMINetworkImpl(int arg0, RMIClientSocketFactory arg1,
			RMIServerSocketFactory arg2) throws RemoteException {
		super(arg0, arg1, arg2);
	}

	/* (non-Javadoc)
	 * @see project.network.RMINetworkInterface#getIPList()
	 */
	@Override
	public LinkedList<ServerDataObject> getIPList() throws RemoteException {
		DDLogger ddl = DDLogger.getLogger(); 
		ddl.createLog("Liste wird abgerufen!", DDLogger.INFO); 
		IPList list = IPList.getInstance(); 
		
		return list.getIPList(); 
	}
	
	/**
	 * Mit dieser Funktion kann man eine IPListe zu einem anderen Server schicken!!
	 */
	@Override
	public boolean pushIPList(LinkedList<ServerDataObject> list)
			throws RemoteException {
		boolean returnvalue = false; 
		DDLogger ddl = DDLogger.getLogger(); 
		ddl.createLog("Liste wird geschickt!", DDLogger.INFO); 
		
		IPList iplist = IPList.getInstance(); 
		returnvalue = iplist.setIPList(list); 
		return returnvalue;
	}
	
	

}

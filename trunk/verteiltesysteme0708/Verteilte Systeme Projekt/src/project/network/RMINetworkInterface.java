/**
 * 
 */
package project.network;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *	RMI Interface zum Nutzen der RMI Funktionalit�t 
 */
public interface RMINetworkInterface extends Remote {

	/**
	 * Anfordern einer IPListe 
	 * @return Gibt eine IPListe in Form von einer LinkedList zur�ck!
	 * @throws RemoteException
	 */
	public LinkedList<ServerDataObject> getIPList() throws RemoteException;
}

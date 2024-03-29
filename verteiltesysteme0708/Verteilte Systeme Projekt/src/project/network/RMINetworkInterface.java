/**
 * 
 */
package project.network;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

import project.data.dataSendingWrapper;

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
	
	/**
	 * Pusht die IP Liste einem Computer 
	 * @param list
	 * @return Gibt ein Bool zur�ck ob das ganze geklappt hat
	 * @throws RemoteException
	 */
	public boolean pushIPList(LinkedList<ServerDataObject> list) throws RemoteException;
	
	/**
	 * Routine soll einen String in einem Verzeichnis auf einem anderen PC suchen
	 * @param searchString
	 * @return
	 * @throws RemoteException
	 */
	public LinkedList<SearchResult> startSearchRoutine(String searchString) throws RemoteException; 

	/**
	 * Routine soll eine Datei vom Server downloaden, dazu der String mit dem Dateinamen!!!
	 * @param filename
	 * @return
	 * @throws RemoteException
	 */
	public dataSendingWrapper getFile(String filename) throws RemoteException; 
}

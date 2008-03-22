/**
 * 
 */
package project.network;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

import project.data.DocumentMetadata;
import project.data.DocumentWrapper;
import project.exception.NetworkFacadeException;
import project.exception.ServerDataObjectException;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class NetworkFacade {

	/** 
	 * Standardkonstruktor !!!
	 */
	public NetworkFacade(){
		
	}
	
	/** 
	 * Dient dazu die IP Liste abzurufen 
	 * @return Gibt die LinkedList mit den Serverdaten zurück 
	 */
	public LinkedList<ServerDataObject> getIPList(){ 
		LinkedList<ServerDataObject> list = new LinkedList<ServerDataObject>(); 
		list = IPList.getInstance().getIPList(); 
		return list; 
	}
	
	/**
	 * Schickt die Datei dwrap an die entsprechenden IP Adressen in der LIste !!!
	 * @param dwrap
	 * @param iplist
	 * @throws NetworkFacadeException
	 */
	public void pushFile(DocumentWrapper dwrap, LinkedList<ServerDataObject> iplist)
		throws NetworkFacadeException {
		
		throw new NetworkFacadeException(NetworkFacadeException.SERVICENOTIMPLEMENTED); 		
	}
	
	/**
	 * Verschickt eine Datei an die angegebenen Daten 
	 * @param ipaddress
	 * @param port
	 * @param dwrap
	 * @throws NetworkFacadeException
	 */
	public void pushFile(String ipaddress, String port, DocumentWrapper dwrap )
		throws NetworkFacadeException {
		
		throw new NetworkFacadeException(NetworkFacadeException.SERVICENOTIMPLEMENTED); 		
	}
	
	/**
	 * Verschickt Metadaten zu einer Datei im Netz !!
	 * @param dmeta
	 * @param iplist
	 * @throws NetworkFacadeException
	 */
	public void pushMetaDate(DocumentMetadata dmeta, LinkedList<ServerDataObject> iplist)
		throws NetworkFacadeException {
		throw new NetworkFacadeException(NetworkFacadeException.SERVICENOTIMPLEMENTED); 		
	}
	
	/**
	 * Startet eine Suchroutine nach dem angegebenen Namen 
	 * In der Hashmap 
	 * @param fileName
	 * @param iplist
	 * @throws NetworkFacadeException
	 */
	public void startSearchRoutine(String fileName, LinkedList<ServerDataObject> iplist)
		throws NetworkFacadeException {
		ThreadCreator tc = ThreadCreator.getInstance(); 
		HashMap<String,Object> hm = new HashMap<String,Object>(); 
		hm.put("searchString", fileName); 
		tc.createThreads(this.getIPList(), ThreadObject.SEARCHFILE, hm); 
		
		// throw new NetworkFacadeException(NetworkFacadeException.SERVICENOTIMPLEMENTED); 		
	}
	
	/** 
	 * Startet eine Suche nach bestimmten Metainformationen 
	 * Über eine Hashmap können Informationen angegeben werden nach denen gesucht wird. 
	 * @param metainfo
	 * @param iplist
	 * @throws NetworkFacadeException
	 */
	public void startSearchRoutineMeta(HashMap<String,String> metainfo, LinkedList<ServerDataObject> iplist)
		throws NetworkFacadeException {
		throw new NetworkFacadeException(NetworkFacadeException.SERVICENOTIMPLEMENTED); 		
	}	
	
	/**
	 * Lädt eine bestimmte Datei von einem bestimmten Server herunter 
	 * @param filename
	 * @param ipaddress
	 * @throws ServerDataObjectException 
	 */
	public void downloadFileFromServer(String filename, String ipaddress, String Port)
		throws NetworkFacadeException, ServerDataObjectException {
		
		if (filename == null || ipaddress == null){
			throw new NetworkFacadeException(NetworkFacadeException.VALUEISNULL);
		}
		
		// Threadcreator laden 
		ThreadCreator tc = ThreadCreator.getInstance(); 
		
		// ServerDataObject erstellen 
		ServerDataObject sdo = new ServerDataObject(ipaddress,Port,0);
		LinkedList<ServerDataObject> ll = new LinkedList<ServerDataObject>(); 
		ll.add(sdo); 
		
		// InformationHash zusammenbauen 
		// Was muss rein ? Dateiname nur 
		HashMap<String,Object> hash = new HashMap<String,Object>(); 
		hash.put("filename", filename); 
		tc.createThreads(ll, ThreadObject.DOWNLOADFILE, hash); 
		
			
	}
}

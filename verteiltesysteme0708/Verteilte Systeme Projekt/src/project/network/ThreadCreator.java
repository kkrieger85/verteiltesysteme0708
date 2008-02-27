/**
 * 
 */
package project.network;

import java.util.HashMap;
import java.util.LinkedList;

import project.exception.ThreadObjectException;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *	Baut die Threads zu diversen Teilen zusammen !! 
 *	Wurde als Singleton umgesetzt 
 */
public class ThreadCreator {
	
	private static ThreadCreator threadcreator = new ThreadCreator(); 
	private static ThreadObserver threadobserver; 
	
	/**
	 *  Konstruktor 
	 *  setzt die Variable threadobserver 
	 */
	private ThreadCreator() {
		ThreadCreator.threadobserver = ThreadObserver.getInstance(); 
	}
	
	/**
	 * Singletonimplementierung 
	 * @return
	 */
	public static ThreadCreator getInstance(){
		if (ThreadCreator.threadcreator != null){
			return ThreadCreator.threadcreator; 
		} else 
			return null; 
	}
	
	/**
	 * Mittels dieses Aufrufs kann man verschiedene Threads direkt erstellen 
	 * hierzu wird die IPListe benötigt, der Threadtyp und die Hashmap mit weiteren 
	 * Informationen für den späteren Verlauf 
	 * @param iplist
	 * @param type
	 * @param informationHash
	 * @return
	 */
	public boolean createThreads(LinkedList<ServerDataObject> iplist, int type, HashMap<String,String> informationHash){
		
		for (int i = 0; i< iplist.size(); i++ ){
			ServerDataObject sdo = iplist.get(i); 
			try {
				ThreadObject tobj = new ThreadObject(sdo.getAddress(),sdo.getPort(),type,informationHash);
				tobj.addObserver(threadobserver); 
				threadobserver.addThread(tobj); 			
			} catch (ThreadObjectException e) {
				// TODO  Catch ordentlich bearbeiten !!! 
			} 		
		}		
		return true; 
	}
	

}

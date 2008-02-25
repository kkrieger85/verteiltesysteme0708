/**
 * 
 */
package project.network;

import java.util.LinkedList;

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
	 * @return Gibt die LinkedList mit den Serverdaten zur�ck 
	 */
	public LinkedList<ServerDataObject> getIPList(){ 
		// TODO Funktion aus der Klasse IPList richtig einbinden 
		LinkedList<ServerDataObject> list = new LinkedList<ServerDataObject>(); 
		IPList iplist = new IPList(); 
		list = iplist.testIPList(); 
		return list; 
	}
	
	
}

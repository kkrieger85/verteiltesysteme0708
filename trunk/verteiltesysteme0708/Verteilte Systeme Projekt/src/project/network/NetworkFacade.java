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
	 * @return Gibt die LinkedList mit den Serverdaten zurück 
	 */
	public LinkedList<ServerDataObject> getIPList(){ 
		LinkedList<ServerDataObject> list = new LinkedList<ServerDataObject>(); 
		list = IPList.getInstance().getIPList(); 
		return list; 
	}
	
	
	
}

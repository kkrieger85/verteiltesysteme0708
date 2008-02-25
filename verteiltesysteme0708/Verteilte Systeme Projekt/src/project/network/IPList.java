/**
 * 
 */
package project.network;

import java.io.Serializable;
import java.util.LinkedList;

import project.exception.ServerDataObjectException;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class IPList implements Serializable {

	private static String file = "iplist.xml"; 	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5049195550478153165L;

	/**
	 * Private da Singleton
	 */
	public IPList() {
	}
	
	/** 
	 * XML Datei laden 
	 */
	private void loadXML(){
		// TODO Liste aus XML Datei generieren !!! 
	}
	
	/** 
	 * XML Datei speichern 
	 */
	public void saveXML(){
		// TODO Liste in XML Datei speichern !!! 
	}
	
	/**
	 * Testfunktion 
	 * TODO Testfunktion sp‰ter rausschmeiﬂen !!! 
	 * @return
	 */
	public LinkedList<ServerDataObject> testIPList(){
		LinkedList<ServerDataObject> ll = new LinkedList<ServerDataObject>(); 
		try {
			ServerDataObject sdo = new ServerDataObject("192.168.2.101", "1099");
			ll.add(sdo);
			sdo = new ServerDataObject("192.168.2.100", "1099");
			ll.add(sdo);
			sdo = new ServerDataObject("192.168.2.102", "1099");
			ll.add(sdo);
			sdo = new ServerDataObject("192.168.2.103", "1099");
			ll.add(sdo);
		} catch (ServerDataObjectException e) {
			e.printStackTrace();
		} 
		return ll; 
	}
	
	

}

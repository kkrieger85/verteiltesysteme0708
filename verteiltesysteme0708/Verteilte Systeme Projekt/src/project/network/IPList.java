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
			ServerDataObject sdo = new ServerDataObject("localhost", "3333");
			ll.add(sdo);
			sdo = new ServerDataObject("localhost", "4444");
			ll.add(sdo);
			sdo = new ServerDataObject("localhost", "5555");
			ll.add(sdo);
			sdo = new ServerDataObject("localhost", "6666");
			ll.add(sdo);
		} catch (ServerDataObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return ll; 
	}
	
	

}

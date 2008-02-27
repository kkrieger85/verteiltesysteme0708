/**
 * 
 */
package project.classtests.network;

import project.helperclasses.DDLogger;
import project.network.IPList;
import project.network.ThreadObject;
import project.network.ThreadObserver;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class getIPListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		DDLogger ddl = new DDLogger(DDLogger.ALL); 
		// Zuerst die IP Liste anlegen !! 
		IPList iplist = IPList.getInstance(); 
		// Daten in die ipliste reinschreiben 
		// iplist.testIPList(); 
		// Datei speichern 
		iplist.saveXML(); 
		// Datei auslesen und ausgeben 
		iplist.loadXML(); 
		
		ThreadObserver tobs = ThreadObserver.getInstance(); 
		String address = "192.168.2.100"; 
		String port = "1099"; 
		int type = ThreadObject.GETIPLISTACTION; 
		try {
			ThreadObject to = new ThreadObject(address,port,type); 
			to.addObserver(tobs); 
			tobs.addThread(to); 
		} catch (Exception ex){
		
		}
	}

}

/**
 * 
 */
package project.classtests.network;

import project.exception.ServerDataObjectException;
import project.helperclasses.DDLogger;
import project.network.*;
/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class IPListXMLTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DDLogger ddl = new DDLogger(DDLogger.ALL); 
		// Zuerst die IP Liste anlegen !! 
		IPList iplist = IPList.getInstance(); 
		// Daten in die ipliste reinschreiben 
		// iplist.testIPList(); 
		// Datei speichern 
		iplist.saveXML(); 
		// Datei auslesen und ausgeben 
		iplist.loadXML(); 
		ddl.createLog(iplist.toString(), DDLogger.DEBUG); 
		ServerDataObject sdo;
		try {
			sdo = new ServerDataObject("192.168.2.103", "1099", 100);
			iplist.addObject(sdo); 
		} catch (ServerDataObjectException e) {
			e.printStackTrace();
		}
		System.out.println(iplist); 
	}

}

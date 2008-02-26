/**
 * 
 */
package project.classtests;

import java.util.HashMap;

import project.helperclasses.DDLogger;
import project.network.IPList;
import project.network.ThreadCreator;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class ThreadCreatorTest {

	/**
	 * 
	 */
	public ThreadCreatorTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Logger holen 
		DDLogger ddl = new DDLogger(); 
		ddl = DDLogger.getLogger(); 
		ddl.createLog("Test gestartet ... ", DDLogger.DEBUG); 
		// ThreadCreator holen 
		ThreadCreator tc = ThreadCreator.getInstance();
		
		// IPListe holen !!! 
		IPList iplist = IPList.getInstance(); 
		
		// Variable festlegen für Testfälle !! 
		int type = 0; 
		
		HashMap<String, String> hm = new HashMap<String, String>(); 
		hm.put("blubb", "Das hier ist dann die Blubbnachricht !!!"); 
		
		tc.createThreads(iplist.getIPList(), type, hm); 
	}

}

/**
 * 
 */
package project.classtests;

import java.util.LinkedList;

import project.helperclasses.NetworkHelper;
import project.network.*;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class NetworkFacadeTestcasesOne {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Eigene IP herausfinden:
		NetworkFacadeTestcasesOne NFTO = new NetworkFacadeTestcasesOne(); 
		NFTO.getOwnIP(); 
		
		// IPAdressliste abrufen 
		NetworkFacade nf = new NetworkFacade(); 
		LinkedList<ServerDataObject> list; 
		list = nf.getIPList(); 
		System.out.println("---------Test 1 ------ LinkedList auslesen"); 
		System.out.println(list.toString());
		
		
		// TODO IPAdressliste aus XML Datei auslesen lassen 		
		// TODO IPAdressliste speichern lassen 
		// TODO IPAdressliste aus XML Datei auslesen lassen 
	}
	
	public void getOwnIP(){
		System.out.println("---------Test Sonstige ------ Eigene IP Adresse auslesen!! -------"); 
		NetworkHelper nh = new NetworkHelper(); 
		System.out.println("IPAdresse: " + nh.getOwnIPAdress()) ; 
	}

}

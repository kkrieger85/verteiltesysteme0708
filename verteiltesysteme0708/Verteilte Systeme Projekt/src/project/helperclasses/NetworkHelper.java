/**
 * 
 */
package project.helperclasses;

import java.net.InetAddress;


/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert,
 *         reichert.sascha@googlemail.com</a> Kleiner Helfer der ein paar
 *         Netzwerkfunktionen beinhaltet !!!
 */
public class NetworkHelper {

	/**
	 * Konstruktor noch ohne Bedeutung!
	 */
	public NetworkHelper() {

	}

	/**
	 * Soll die eigene IP Adresse herausfinden
	 * 
	 * @return
	 */
	public String getOwnIPAdress() {
		// TODO Exception werfen falls keine IP Adresse vorhanden ist!!! 
		String IPAddress = null; 
		try {		
			IPAddress = InetAddress.getLocalHost().getHostAddress().toString(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return IPAddress; 
	}

	/**
	 * Hiermit kann man den eigenen Port abrufen
	 * 
	 * @return
	 */
	public String getOwnOpenPort() {
		// StandardRMI Port ist immer 1099 
		return "1099";
	}
}

/**
 * 
 */
package project.network;

import java.io.Serializable;

/**
 * Klasse dient als Rückgabe von Suchroutinen 
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class SearchResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4246437713684261354L;
	private String serverIP; 
	private String fileName; 
	// Weitere Informationen mit einbauen !!! 
	
	/**
	 * 
	 */
	public SearchResult(String serverIP, String fileName) {
		// TODO Exception werfen falls etwas nicht funzt !!! 
		this.serverIP = serverIP;
		this.fileName = fileName; 
	}

	/**
	 * @return the serverIP
	 */
	public synchronized String getServerIP() {
		return serverIP;
	}

	/**
	 * @param serverIP the serverIP to set
	 */
	public synchronized void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	/**
	 * @return the fileName
	 */
	public synchronized String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public synchronized void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the serialVersionUID
	 */
	public static synchronized long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	/**
	 * Einfach toString methode die den Inhalt des Elements wiedergibt 
	 */
	public String toString(){
		return "Datei: " + this.fileName + "  IP: " + this.serverIP; 
	}

}

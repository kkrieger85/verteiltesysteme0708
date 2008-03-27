
package project.data;

import java.util.Date;
import java.util.Vector;

/**
 * Kapselt alle Metadaten des Dokuments
 * 
 * @author ab
 *
 */
public class DocumentMetadata {
	
	private String beschreibung;
	private Vector<String> rolle;

	private String sperrender = null;
	private Computer sperrhost = null;
	private Date sperrzeit = null;
	
	/**
	 * Konstruktor der Metadaten
	 */
	public DocumentMetadata(){
		rolle = new Vector<String>();
	}
	
	/**
	 * Gibt die enthaltene Beschreibung zurï¿½ck
	 * @return Beschreibung
	 */
	
	public String getBeschreibung() {
		return beschreibung;
	}
	
	/**
	 * Setzt die Beschreibung auf den Wert des ï¿½bergebenen Strings
	 * @param beschreibung neue Beschreibung
	 */

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	/**
	 * Gibt die enthaltene Rolle zurï¿½ck
	 * @return Enthaltene Rolle
	 */
	
	public Vector<String> getRolle(){
		return rolle;
	}
	
	
	/**
	 * Setzt Rolle auf den Wert des übergebenen Strings
	 * @param rolle neue Rolle
	 */
	public void setRolle(String rolle){
		this.rolle.add(rolle);
	}
	
	/**
	 * Ersetzt den Vector Rolle mit dem übergebenen Vector
	 * @param rollen neuer Rollen-Vector
	 */
	
	public void setRolle(Vector<String> rollen){
		this.rolle = rollen;
	}
	
	/**
	 * Gibt eine String Representation des Objektes zurück.
	 */
	public String toString() {
		String tmp = "(" + beschreibung + rolle; 
		if (sperrender != null)
			tmp += "Sperrender: " + sperrender;
		if (sperrhost != null)
			tmp += "Sperrhost: " + sperrhost;
		if (sperrzeit != null)
			tmp += "Sperrzeit: " + sperrzeit;
		
		return  tmp + ")";
	}
	
	public String getSperrender() {
		return sperrender;
	}

	public void setSperrender(String sperrender) {
		this.sperrender = sperrender;
	}

	public Computer getSperrhost() {
		return sperrhost;
	}

	public void setSperrhost(Computer sperrhost) {
		this.sperrhost = sperrhost;
	}

	public Date getSperrzeit() {
		return sperrzeit;
	}

	public void setSperrzeit(Date sperrzeit) {
		this.sperrzeit = sperrzeit;
	}
}

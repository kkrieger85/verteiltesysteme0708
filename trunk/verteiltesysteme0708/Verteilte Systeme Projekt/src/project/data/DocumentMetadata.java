
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
	}
	
	/**
	 * Gibt die enthaltene Beschreibung zur�ck
	 * @return Beschreibung
	 */
	
	public String getBeschreibung() {
		return beschreibung;
	}
	
	/**
	 * Setzt die Beschreibung auf den Wert des �bergebenen Strings
	 * @param beschreibung neue Beschreibung
	 */

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	/**
	 * Gibt die enthaltene Rolle zur�ck
	 * @return Enthaltene Rolle
	 */
	
	public Vector<String> getRolle(){
		return rolle;
	}
	
	
	/**
	 * Setzt Rolle auf den Wert des �bergebenen Strings
	 * @param rolle neue Rolle
	 */
	public void setRolle(String rolle){
		this.rolle.add(rolle);
	}
	
	/**
	 * Gibt eine String Representation des Objektes zur�ck.
	 */
	public String toString() {
		//ToDo String-Methode
		return "(" + beschreibung + rolle + ")";
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


package project.data;

import java.util.Date;
import java.util.Vector;

import project.network.ServerDataObject;

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
	private ServerDataObject sperrhost = null;
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
	
	/**
	 * Gibt den Namen des Benutzers zurück, der das Dokument gesperrt hat.
	 * @return String der den Namen des Benutzers enthält. Null wenn Dokument nicht gesperrt.
	 */
	
	public String getSperrender() {
		return sperrender;
	}
	
	/**
	 * Setzt den Sperrenden auf den Wert des übergebenen Strings.
	 * @param sperrender der neue Sperrende. Null wenn Dokument entsperrt werden soll.
	 */

	public void setSperrender(String sperrender) {
		this.sperrender = sperrender;
	}
	
	/**
	 * Gibt den Host des Sperrenden zurück
	 * @return Objekt das den Host des Sperrenden enthält. Null wenn Dokument nicht gesperrt.
	 */

	public ServerDataObject getSperrhost() {
		return sperrhost;
	}
	
	/**
	 * Setzt den Host des Sperrenden auf "sperrhost"
	 * @param sperrhost neuer Sperrhost. Null wenn Dokument entsperrt werden soll.
	 */

	public void setSperrhost(ServerDataObject sperrhost) {
		this.sperrhost = sperrhost;
	}
	
	/**
	 * Gibt die Zeit zurück, zu der das Dokument gesperrt wurde.
	 * @return Date Objekt mit dem Datum, wann das Dokument gesperrt wurde. Null wenn Dokument nicht gesperrt
	 */

	public Date getSperrzeit() {
		return sperrzeit;
	}
	
	/**
	 * Setzt die Sperrzeit auf "sperrzeit".
	 * @param sperrzeit Neue Sperrzeit. Null wenn Dokument entsperrt werden soll.
	 */

	public void setSperrzeit(Date sperrzeit) {
		this.sperrzeit = sperrzeit;
	}
}

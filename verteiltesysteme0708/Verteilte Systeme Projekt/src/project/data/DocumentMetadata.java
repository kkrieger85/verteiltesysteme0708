
package project.data;

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
	
	
	/**
	 * Konstruktor der Metadaten
	 * 
	 *  Es muss mindestens der Dateinamen ausgef�llt sein,
	 *  alle anderen Parameter k�nnen optional gesetzt werden
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

}

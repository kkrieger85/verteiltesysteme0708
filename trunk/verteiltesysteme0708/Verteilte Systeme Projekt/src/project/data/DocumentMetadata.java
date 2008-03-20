
package project.data;

/**
 * Kapselt alle Metadaten des Dokuments
 * 
 * @author ab
 *
 */
public class DocumentMetadata {
	
	private String beschreibung;
	private String rolle;
	
	
	/**
	 * Konstruktor der Metadaten
	 * 
	 *  Es muss mindestens der Dateinamen ausgefï¿½llt sein,
	 *  alle anderen Parameter kï¿½nnen optional gesetzt werden
	 */
	public DocumentMetadata(){
	}
	
	/**
	 * Gibt die enthaltene Beschreibung zurück
	 * @return Beschreibung
	 */
	
	public String getBeschreibung() {
		return beschreibung;
	}
	
	/**
	 * Setzt die Beschreibung auf den Wert des übergebenen Strings
	 * @param beschreibung neue Beschreibung
	 */

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	/**
	 * Gibt die enthaltene Rolle zurück
	 * @return Enthaltene Rolle
	 */
	
	public String getRolle(){
		return rolle;
	}
	
	/**
	 * Setzt Rolle auf den Wert des übergebenen Strings
	 * @param rolle neue Rolle
	 */
	public void setRolle(String rolle){
		this.rolle = rolle;
	}
	
	/**
	 * Gibt eine String Representation des Objektes zurück.
	 */
	public String toString() {
		//ToDo String-Methode
		return "(" + beschreibung + rolle + ")";
	}

}

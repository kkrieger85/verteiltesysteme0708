/**
 * 
 */
package project.data;

/**
 * Kapselt alle Metadaten des Dokuments
 * 
 * @author ab
 *
 */
public class DocumentMetadata {
	
	// KANN-Feld: Beschreibung
	private String beschreibung;
	
	// ...
	
	/**
	 * Konstruktor der Metadaten
	 * 
	 *  Es muss mindestens der Dateinamen ausgefüllt sein,
	 *  alle anderen Parameter können optional gesetzt werden
	 */
	public DocumentMetadata(){
	}
	
	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	/*
	 * toString()
	 */
	public String toString() {
		//ToDo String-Methode
		return "(" + beschreibung + ")";
	}

}

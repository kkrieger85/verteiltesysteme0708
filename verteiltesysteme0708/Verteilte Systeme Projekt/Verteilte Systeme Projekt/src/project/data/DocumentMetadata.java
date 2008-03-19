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
	private String rolle;
	
	// ...
	
	/**
	 * Konstruktor der Metadaten
	 * 
	 *  Es muss mindestens der Dateinamen ausgef�llt sein,
	 *  alle anderen Parameter k�nnen optional gesetzt werden
	 */
	public DocumentMetadata(){
	}
	
	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	public String getRolle(){
		return rolle;
	}
	/*
	 * toString()
	 */
	public String toString() {
		//ToDo String-Methode
		return "(" + beschreibung + ")";
	}

}

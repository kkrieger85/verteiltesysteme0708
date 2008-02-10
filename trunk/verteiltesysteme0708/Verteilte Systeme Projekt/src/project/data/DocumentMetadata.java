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

	// MUSS-Feld: Dateiname
	private String filename;
	
	// KANN-Feld: Beschreibung
	private String beschreibung;
	
	// ...
	
	/**
	 * Konstruktor der Metadaten
	 * 
	 *  Es muss mindestens der Dateinamen ausgefüllt sein,
	 *  alle anderen Parameter können optional gesetzt werden
	 */
	public DocumentMetadata(String filename) throws Exception {
		if (filename == null)
			throw new Exception("Dateinamen muss angegeben werden");
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
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
		return "(" + filename + "," + beschreibung + ")";
	}

}

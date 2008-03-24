package project.data;

/**
 * Wrapperklasse für dateispezifische Informationen
 * 
 * @author Christian Schwerdtfeger
 *
 */
public class DocumentFile {

	private String filepath;
	
	/**
	 * Konstruktor dem der Dateiname übergeben wird
	 * @param filepath Dateiname zu dem die Metadaten gehören
	 */
	
	public DocumentFile(String filepath)
	{
		this.filepath = filepath;
	}
	
	/**
	 * Ändert den Filepath in filepath
	 * @param filepath neuer Filepath
	 */
	
	public void setFilepath(String filepath)
	{
		this.filepath = filepath;
	}
	/**
	 * Gibt eine String-Representation des DocumentFile-Objektes zurück
	 */
	public String toString() {
		return filepath;
	}

	public String getFilepath() {
		return filepath;
	}
	
}

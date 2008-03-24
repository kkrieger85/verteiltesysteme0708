package project.data;

/**
 * Wrapperklasse f�r dateispezifische Informationen
 * 
 * @author Christian Schwerdtfeger
 *
 */
public class DocumentFile {

	private String filepath;
	
	/**
	 * Konstruktor dem der Dateiname �bergeben wird
	 * @param filepath Dateiname zu dem die Metadaten geh�ren
	 */
	
	public DocumentFile(String filepath)
	{
		this.filepath = filepath;
	}
	
	/**
	 * �ndert den Filepath in filepath
	 * @param filepath neuer Filepath
	 */
	
	public void setFilepath(String filepath)
	{
		this.filepath = filepath;
	}
	/**
	 * Gibt eine String-Representation des DocumentFile-Objektes zur�ck
	 */
	public String toString() {
		return filepath;
	}

	public String getFilepath() {
		return filepath;
	}
	
}

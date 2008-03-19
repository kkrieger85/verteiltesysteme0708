package project.data;

/**
 * @author ab
 *
 */
public interface Document {

	/**
	 * isLocal
	 * 
	 * @return	ist die Datei lokal und g�ltig, also sind mindestens Informationen
	 * 			�ber Speicherort sowie Metadaten vorhanden ?
	 */
	public boolean isValid();

	public DocumentVersion getVersion();
	public void setVersion(DocumentVersion dversion);

	public DocumentDistribution getDistribution();
	public void setDistribution(DocumentDistribution dbackup);
	
	public DocumentFile getFile();

	public DocumentMetadata getMetadata();




}

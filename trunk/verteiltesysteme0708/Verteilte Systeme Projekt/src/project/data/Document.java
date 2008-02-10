package project.data;

/**
 * @author ab
 *
 */
public interface Document {

	/**
	 * isLocal
	 * 
	 * @return	ist die Datei lokal und gültig, also sind mindestens Informationen
	 * 			über Speicherort sowie Metadaten vorhanden ?
	 */
	public boolean isValid();

	public DocumentVersion getVersion();
	public void setVersion(DocumentVersion dversion);

	public DocumentDistribution getDistribution();
	public void setDistribution(DocumentDistribution dbackup);
	
	public DocumentFile getFile();

	public DocumentMetadata getMetadata();




}

/**
 * 
 */
package dd.data;

/**
 * Implementiert ein Dokument in allen Formen, wie es vorkommen kann
 * 
 * - lokales Dokument
 * - netzwerkverteiltes Dokument
 * - Backup Dokument
 * ...
 * 
 * @author ab
 *
 */
public class DocumentWrapper implements Document {


	// Informationen über physikalischen Speicherort
	private DocumentFile dfile 				= null;

	// Metadaten des Dokuments
	private DocumentMetadata dmeta 			= null;
	
	// Versionsinformationen des Dokuments
	private DocumentVersion dversion 		= null;
	
	// Verteilungs- (Backup-) Informationen über das Dokument 
	private DocumentDistribution dbackup 	= null;
	
	

	/**
	 * Konstruktor, um ein neues lokales Dokument anzulegen.
	 * 
	 * Es wird überprüft, ob das Dokument lokal vorliegt
	 * (lokal vorliegen == f ist nicht null)
	 * 
	 * @param f 	DocumentFile		physikalischer Ort der Metadaten, muss in diesem Fall lokal sein	
	 * @param m		DocumentMetadata	Metadaten des Dokuments
	 */
	public DocumentWrapper(DocumentFile f, DocumentMetadata m) throws Exception {
		if (f == null)
			throw new Exception("Dokument muss lokal vorliegen");
		if (m == null)
			throw new Exception("Dokument muss mindestens minimale Metainformationen haben");
		this.dfile = f;
		this.dmeta = m;
	}
	

	/**
	 * isLocal
	 * 
	 * @return	ist die Datei lokal und gültig, also sind mindestens Informationen
	 * 			über Speicherort sowie Metadaten vorhanden ?
	 */
	public boolean isValid() {
		return (dfile != null && dmeta != null);
	}


	public DocumentVersion getVersion() {
		return dversion;
	}

	public void setVersion(DocumentVersion dversion) {
		this.dversion = dversion;
	}

	public DocumentDistribution getDistribution() {
		return dbackup;
	}

	public void setDistribution(DocumentDistribution dbackup) {
		this.dbackup = dbackup;
	}
	
	public DocumentFile getFile() {
		return dfile;
	}

	public DocumentMetadata getMetadata() {
		return dmeta;
	}

	/*
	 * toString()
	 */
	public String toString() {
		String s = dfile + ", " + dmeta + ", " + dversion.getVersion() + ", " + dbackup;
		return s;
	}
}

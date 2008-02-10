package diverse;

// dieses Objekt bildet eine Datei ab
// d.h. dies sind die Metadaten

public class Document {

	// wer ist Moderator fuer das Dokument;
	Computer moderator;
	
	// Liste aller Computer mit Backup dieser Datei
	Computer[] backups = new Computer[Configuration.numOfBackups];

	// wo liegt die physische Datei?
	String file;
	
	// wie groﬂ ist die physische Datei?
	int fileSize;
	
}

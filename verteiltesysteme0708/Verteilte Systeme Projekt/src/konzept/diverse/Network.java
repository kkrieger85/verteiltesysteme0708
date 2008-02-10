package konzept.diverse;

// hier ist alles Netzwerkzeug implementiert dass wir brauchen

public class Network {

	// Broadcast senden, wer kann eine Datei die so groß ist bei sich
	// backupen ?
	public Computer[] whoCanTakeAFileThisBig(int size) { return null; }
	
	// Datei zu einem anderen Rechner uebertragen
	// metadatenOnly = false -> Datei + Metadaten uebertragen
	// 				 = true  -> nur Metadaten uebertragen (updaten)
	public boolean transferFile(Computer to, Document doc, boolean metadatenOnly) { return true; }
	
	
	//Metadaten zu einer Datei von einem bestimmten Rechner erfragen
	//benötigt falls Owner-Change zum erfragen des neuen Owners.
	//docId = Ident-Number für das jeweilige Dokument.
	public Document requestMetadata(Computer from, long docId){return new Document(); }
	
}

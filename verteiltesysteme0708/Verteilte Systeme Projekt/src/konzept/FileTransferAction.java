package konzept;

public class FileTransferAction extends Action {

	Document doc;
	Computer destination;
	
	//Konstruktor mit Ziel-Computer und dem zu sendenden Dokument
	
	public FileTransferAction(Computer to, Document doc){
		doc = this.doc;
		destination = to;
	}
	
	//Methode zum ausf�hren der Aktion.
	
	public void run(){
		Network net = new Network();
		if(net.transferFile(destination, doc, false)) // sendet Datei an "destination"
			return; // wenn erfolg --> Beenden
		else
			triggerTime = System.currentTimeMillis() + Configuration.retryTime; // wenn kein Erfolg --> Sp�ter erneut probieren.
	}
}

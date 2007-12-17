package konzept;

public class ReceiveToUpdateAction extends Action {

	//erhaltenes Dokument
	Document doc;
	
	//Methode die aufgerufen wird wenn das Dokument erhalten wurde.
	
	public void receive (Document doc){
		doc = this.doc;
		markInGui();
	}
	
	//Methode die aufgerufen wird, wenn die Änderung bestätigt und ins Netz
	//publiziert werden soll.
	
	public void committed(){
		for (int i = 0; i < Configuration.numOfBackups; i++) // für Anzahl der Backup-Rechner tue
		{
			Configuration.actionQ.add(new FileTransferAction(doc.backups[i], doc)); //Erstelle neues FileTransferAction-Object
																					//welches die Datei an Backup-Rechner "i" versendet.
		}
	}
	
	//Methode die aufgerufen wird, wenn die Änderung nicht übernommen wird.
	//In diesem Fall wird das Objekt zerstört.
	
	public void notCommitted(){
		try
		{
			this.finalize();
		}catch(Throwable e)
		{
		}
	}
	
	//Schnittstelle zur GUI, in der das Dokument als geändert markiert werden soll
	
	public void markInGui(){
		//Markiere in Gui, das Document zur Änderung vorliegt.
	}
}

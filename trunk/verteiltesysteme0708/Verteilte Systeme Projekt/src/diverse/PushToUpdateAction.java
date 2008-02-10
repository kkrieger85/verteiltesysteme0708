package diverse;

public class PushToUpdateAction extends Action {
	
	
	//geändertes Dokument, welches nun zum bestätigen an den Moderator gesendet werden soll
	Document doc;
	//falls eine Zeitschaltung vorgesehen ist, wird hier die Wartezeit in Sekunden eingetragen
	
	//Konstruktor für den Fall das nur daszu pushende Dokument übergeben werden muss
	
	public PushToUpdateAction (Document doc){
		doc = this.doc;
		triggerTime = 0;
	}
	
	//Konstruktor dem sowohl das Dokument als auch eine Wartezeit übergeben wird
	
	public PushToUpdateAction (Document doc, int timeinsecs){
		doc = this.doc;
		triggerTime = timeinsecs * 1000;
	}
	
	//Hauptmethode der Klasse.
	
	public void run(){
		try
		{
			Thread.sleep(triggerTime); //Warte timeInSecs-Sekunden bis weitermachen
		}catch (Exception e)
		{
		}
		Network net = new Network();
		Computer temp;
		if (net.transferFile(doc.moderator, doc, false)) //versuche die Datei an den Moderator zu senden
		{
			return; // wenn Erfolg, dann fertig.
		}
		else
		{
			// wenn kein Erfolg wird geschaut ob neuer Moderator
			boolean newMod = false;
			for (int i = 0; i < Configuration.numOfBackups; i++)
			{
				temp = (net.requestMetadata(doc.backups[i], 1L)).moderator; // anfordern der Metadaten zu dem Dokument
				if (temp.name != doc.moderator.name); //Überprüfen ob neuer Moderator
				{
					newMod = true;
					doc.moderator.name = temp.name; //Flag wird gesetzt und Name in den eigenen Metadaten geändert
				}
			}
			if (newMod)
			{
				PushToUpdateAction newJob = new PushToUpdateAction(doc);
				Configuration.actionQ.add(newJob); // Es wird ein neuer Job erstellt, der sofort versucht an neuen Mod. zu senden
			}
			else
			{
				PushToUpdateAction newJob = new PushToUpdateAction(doc, 3600);
				Configuration.actionQ.add(newJob); // Es wird ein neuer Job erstellt, der in einer Stunde versucht an den alten mod zu senden
			}
		}
	}

}

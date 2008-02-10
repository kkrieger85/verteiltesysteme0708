package diverse;

public class FilePushAction extends Action {

	Document doc;

	// bereits gefundene Backup-Rechner
	// wird u.U. ueber mehrere "runs" gefüllt
	// wenn im ersten Schritt nicht genügend gefunden werden konnten
	Computer[] backups = new Computer[Configuration.numOfBackups];
	int backupsCount = 0;
	
	public void run() {
		
		// unser Job ist es ein neu angelegtes Dokument
		// zu verschicken
		
		// fragen: wer kann alles die Datei aufnehmen
		Computer[] possibleBackups = Configuration.network.whoCanTakeAFileThisBig(doc.fileSize);
		
		for (int i=0; i < possibleBackups.length; i++) {
			// wenn die Datei erfolgreich auf einen Host
			// uebertragen werden konnte wird dieser vorgemerkt
			// damit er spaeter in die Metadaten eingetragen werden kann
			// -> d.h. im ersten Schritt werden die Metadaten nur LEER
			// an alle Rechner uebertragen
			if (Configuration.network.transferFile(possibleBackups[i], doc, false))
				backups[backupsCount++] = possibleBackups[i]; 
			
			// wenn wir genug Backup-Rechner gefunden haben dann raus
			if (backupsCount == Configuration.numOfBackups)
				break;
		}
		
		if (backupsCount < Configuration.numOfBackups) {
			// noch nicht alle Backup-Rechner gefunden
			// also wird die Task zu einem späteren Zeitpunkt nochmal
			// wiederholt
			triggerTime = System.currentTimeMillis() + Configuration.retryTime;
			Configuration.actionQ.add(this);
		} else {
			for (int i=0; i < backups.length; i++)
				// super! wir haben alle Backup-Eintraege gefunden
				// datenn koennen wir sie in den Metadaten eintragen
				doc.backups[i] = backups[i];

			// dann im zweiten schritt schieben wir die nun
			// vollstaendig ausgefuellten metadaten auf alle
			// betroffenen Rechner
			for (int i=0; i < doc.backups.length; i++)
				Configuration.actionQ.add(new MetaDatenPushAction(doc.backups[i], doc));
		}
		
	}
	
}

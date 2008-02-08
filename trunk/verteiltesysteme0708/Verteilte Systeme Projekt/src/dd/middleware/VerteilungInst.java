package dd.middleware;

import dd.*;
import dd.data.*;

/**
 * @author ab
 *
 */
public class VerteilungInst implements Verteilung {

	/**
	 * Dokument jetzt letztendlich im Netzwerk verteilen
	 * 
	 */
	public void distributeDocument(Document doc) throws Exception {

		// zuerstmal prüfen, ob das Dokument lokal vollständig ist
		// also gültig ist und auch schon eine Version hat
		if (!doc.isValid() && doc.getVersion() != null)
			throw new Exception("Dokument muss erst vollständig und versioniert sein, bevor es verteilt werden kann");
		
		// wir schnappen uns einfach mal den ersten Rechner aus unserer IP-Liste ...
		Computer comp = dd.Main.network.getIPListe().elementAt(0); 
		
		// eventuell hier noch unterscheiden ob bereits Backup-Rechner
		// eingetragen sind ...
		
		// ... und tragen ihn als Backup-Rechner ein ...
		doc.setDistribution(new DocumentDistribution(comp));

		System.out.println("Verteilung: 1 Backup-Rechner ausgewählt, Auftrag ans Netzwerk geben");
		
		// ... und verschicken das Dokument an ihn ...
		Main.network.sendDocument(comp, doc);
	
	}
	
}

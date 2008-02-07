package dd.network;

import java.util.Vector;
import dd.data.Computer;
import dd.data.Document;

/**
 * @author ab
 *
 */
public interface Network {

	/**
	 * Liste aller bekannter PC's im Netzwerk zurückliefern
	 * 
	 * @return Liste aller Computer als Vector<Computer>
	 */
	public Vector<Computer> getIPListe();

	/**
	 * Ein Dokument mit allen Metadaten an einen anderen Teilnehmer
	 * im System schicken (mit Threads, Q und allem Schnickschnack) 
	 * 
	 * @param comp	Computer, an den das Dokument geschickt werden soll
	 * @param doc	Dokument, das verschickt werden soll
	 * 
	 */
	public void sendDocument(Computer comp, Document doc);
	
}

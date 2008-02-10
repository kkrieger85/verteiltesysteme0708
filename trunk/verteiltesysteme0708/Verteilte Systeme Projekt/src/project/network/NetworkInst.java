package project.network;

import java.util.Vector;

import project.data.*;


/**
 * Diese Klasse implementiert alle Client-seitigen Netzwerkfunktionen.
 * 
 *  Warteschlange, Threadcontroller (TC), ...
 * 
 * @author ab
 *
 */
public class NetworkInst implements Network {

	
	private Vector<Computer> IPListe = new Vector<Computer>();
	
	/**
	 * Konstruktor
	 * 
	 * Dieses Programmteil wird als erstes hochgefahren, d.h.
	 * hier sind alle weiteren Teile (Server, GUI) noch nicht funktionsbereit.
	 * 
	 * Ist aber nicht schlimm, da hier normalerweise nur auf Anfrage etwas
	 * passiert. Oder? 
	 */
	public NetworkInst() {
		// initialisieren
		System.out.println("Client: Starte Netzwerk-Client");

		// Broadcast und IP-Liste simulieren
		System.out.println("Client: Fülle IP-Liste ... 1 Client gefunden");
		IPListe.add(new ComputerWrapper("1.2.3.4"));
	}

	/**
	 * Ein Dokument mit allen Metadaten an einen anderen Teilnehmer
	 * im System schicken (mit Threads, Q und allem Schnickschnack) 
	 * 
	 * @param comp	Computer, an den das Dokument geschickt werden soll
	 * @param doc	Dokument, das verschickt werden soll
	 * 
	 */
	public void sendDocument(Computer comp, Document doc) {
		System.out.println("Netzwerk: Versenden der Datei" + doc); // + " verschickt an " + comp);
	}
	
	/**
	 * Liste aller bekannter PC's im Netzwerk zurückliefern
	 * 
	 * @return Liste aller Computer als Vector<Computer>
	 */
	public Vector<Computer> getIPListe() {
		return IPListe;
	}
	
}

package dd.middleware;

import dd.data.*;

/**
 * @author ab
 *
 */
public class MiddlewareInst implements Middleware {

	/**
	 * High-Level Funktion, um ein (lokal) neu erstelltes Dokument zu verteilen
	 *
	 * Die Middleware ist daf�r verantwortlich, alle n�tigen Operationen mit den anderen
	 * Teilmodulen durchzuf�hren.   
	 * 
	 * @param Document 	Dokument-Objekt, dass alle Informationen die bereits �ber das
	 * 					Dokument bekannt sind enth�lt. Dies m�ssen insbesondere der DocumentFile
	 * 					Bereich und notwendige Teile der Metadaten sein.
	 * @see	Document
	 */
	public void createDocument(Document doc) throws Exception {
		
		// zuerst �berpr�fen, ob das gelieferte Dokument korrekt vorliegt und so
		// verteilt werden kann
		if (!doc.isValid())
			throw new Exception("Dokument liegt nicht in g�ltiger Fassung lokal vor");

		// bei Rechteverwaltung pr�fen, ob Rechte ok sind
		if (!dd.Main.rechte.can_createDocument(doc))
			throw new Exception("Dokument darf nicht verteilt werden");
		
		// Document versionieren
		dd.Main.vers.newDocument(doc);
		
		// Dokument verteilen
		dd.Main.vert.distributeDocument(doc);
			
	}

	
}

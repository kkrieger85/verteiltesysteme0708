package project.middleware;

import project.data.Document;

/**
 * @author ab
 *
 */
public interface Middleware {

	
	/**
	 * High-Level Funktion, um ein (lokal) neu erstelltes Dokument zu verteilen
	 * 
	 * @param Document 	Dokument-Objekt, dass alle Informationen die bereits über das
	 * 					Dokument bekannt sind enthält. Dies müssen insbesondere der DocumentFile
	 * 					Bereich und notwendige Teile der Metadaten sein.
	 * @see	Document
	 */
	public void createDocument(Document doc, String comment) throws Exception; 
	
}

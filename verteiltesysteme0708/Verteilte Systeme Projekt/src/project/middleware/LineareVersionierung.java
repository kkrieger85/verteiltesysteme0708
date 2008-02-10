package project.middleware;

import project.data.*;

/**
 * @author ab
 *
 */
public class LineareVersionierung implements Versionierung {

	/**
	 * Neues Dokument versionieren
	 * 
	 * @param doc	neues Dokoument
	 */
	public void newDocument(Document doc) throws Exception {
		// zuerst prüfen, ob wirklich ein neues Dokument
		// (also ohne bisherige Versionsinformation) übergeben wurde
		if (doc.getVersion() != null)
			throw new Exception("Dokument zum Veteilen muss noch ohne Version sein");
		
		// Versionierung durchführen ... 
		System.out.println("Versionierung: neues Dokument versioniert");
		doc.setVersion(new DocumentVersion(0));
	}

	
}

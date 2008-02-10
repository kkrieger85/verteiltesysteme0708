package project.middleware;

import project.data.Document;

/**
 * @author ab
 *
 */
public interface Verteilung {

	/**
	 * Dokument jetzt letztendlich im Netzwerk verteilen
	 * 
	 */
	public void distributeDocument(Document doc) throws Exception;
	
}

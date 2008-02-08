package dd.middleware;

import dd.data.Document;

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

package project.middleware;

import project.data.*;

/**
 * @author ab
 *
 */
public interface Versionierung {

	/**
	 * Neues Dokument versionieren
	 * 
	 * @param doc	neues Dokoument
	 */
	public void newDocument(Document doc) throws Exception;
	
}

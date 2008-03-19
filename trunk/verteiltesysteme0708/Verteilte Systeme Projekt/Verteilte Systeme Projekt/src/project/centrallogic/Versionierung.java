package project.centrallogic;

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
	public void newDocument(Document doc, String comment) throws Exception;
	
	public void updateDocument(Document doc, String comment) throws Exception;
	
}

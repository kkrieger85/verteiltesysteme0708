package project.middleware;

import project.data.Document;
import project.data.Computer;

/**
 * @author ab
 *
 */
public interface Verteilung {

	/**
	 * Dokument jetzt letztendlich im Netzwerk verteilen
	 * 
	 */
	public Computer[] distributeDocument(Document doc, int anzahl) throws Exception;
	
	public long getMyAvailableSpace();
	
	public long getMyUsedSpace();
	
}

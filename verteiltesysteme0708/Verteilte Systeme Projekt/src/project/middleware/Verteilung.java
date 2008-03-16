package project.middleware;

import project.data.Document;
import project.data.Computer;
import java.util.Vector;

/**
 * @author ab
 *
 */
public interface Verteilung {

	/**
	 * Dokument jetzt letztendlich im Netzwerk verteilen
	 * 
	 */
	public Vector<Computer> distributeDocument(Document doc, int anzahl) throws Exception;
	
}

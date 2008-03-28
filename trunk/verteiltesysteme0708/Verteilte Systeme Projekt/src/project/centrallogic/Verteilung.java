package project.centrallogic;

import project.data.Document;
import java.util.*;
import project.network.ServerDataObject;

/**
 * @author ab
 *
 */
public interface Verteilung {

	/**
	 * Dokument jetzt letztendlich im Netzwerk verteilen
	 * 
	 */
	public LinkedList<ServerDataObject> distributeDocument(Document doc, int anzahl) throws Exception;
	
}

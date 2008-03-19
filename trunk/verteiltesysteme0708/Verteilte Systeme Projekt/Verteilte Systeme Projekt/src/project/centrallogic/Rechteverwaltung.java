package project.centrallogic;

import project.data.Document;

/**
 * @author ab
 *
 */
public interface Rechteverwaltung {

	/**
	 * Diese Methode soll anhand der beim Start der Rechteverwaltung
	 * vorliegenden Methoden (Login etc.) und dem übergebenen Dokument
	 * entscheiden, ob die Aktion "neues Dokument verteilen" zulässig ist
	 * 
	 * @param 	doc		Document, dass verteilt werden soll
	 *  
	 * @return	ob die Aktion durchgeführt werden darf  
	 */
	public boolean can_createDocument(Document doc);
	
}

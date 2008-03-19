package project.centrallogic;

import project.data.Document;

/**
 * @author ab
 *
 */
public interface Rechteverwaltung {

	/**
	 * Diese Methode soll anhand der beim Start der Rechteverwaltung
	 * vorliegenden Methoden (Login etc.) und dem �bergebenen Dokument
	 * entscheiden, ob die Aktion "neues Dokument verteilen" zul�ssig ist
	 * 
	 * @param 	doc		Document, dass verteilt werden soll
	 *  
	 * @return	ob die Aktion durchgef�hrt werden darf  
	 */
	public boolean can_createDocument(Document doc);
	
}

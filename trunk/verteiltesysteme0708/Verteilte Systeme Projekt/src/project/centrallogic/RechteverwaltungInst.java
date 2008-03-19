package project.centrallogic;

import project.data.*;

/**
 * @author ab
 *
 */
public class RechteverwaltungInst implements Rechteverwaltung {

	/**
	 * Diese Methode soll anhand der beim Start der Rechteverwaltung
	 * vorliegenden Methoden (Login etc.) und dem �bergebenen Dokument
	 * entscheiden, ob die Aktion "neues Dokument verteilen" zul�ssig ist
	 * 
	 * @param 	doc		Document, dass verteilt werden soll
	 *  
	 * @return	ob die Aktion durchgef�hrt werden darf  
	 */
	public boolean can_createDocument(Document doc) {
		
		System.out.println("Rechtemanagement: Anfrage, ob Dokument verteilt werden darf...");
		
		return true;
	}
	
}

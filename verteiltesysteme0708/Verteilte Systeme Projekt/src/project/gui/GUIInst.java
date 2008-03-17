package project.gui;

import project.*;
import project.data.*;


/**
 * @author ab
 *
 */
public class GUIInst implements GUI {

	/**
	 * Grafische Anwendung
	 * 
	 * erstmal sind hier nur Demo-Aktionen eingebaut
	 */
	public void start() {
		
		System.out.println("GUI: Starte GUI");
		
		System.out.println("GUI: Benutzer hat Datei test.doc ausgewählt zum Neu-Verteilen...");
		
		try {
		
			Document doc = new DocumentWrapper(new DocumentFile("test.doc"), new DocumentMetadata());
			
			Main.middleware.createDocument(doc, "testcomment");
			
		}
		
		catch (Exception e) {
			System.out.println("Fehler:");
			e.printStackTrace();
		}
		
		
	}

	
}

/**
 * 
 */
package project.centrallogic;

import java.io.File;

import project.helperclasses.XMLConfigHelper;
import project.helperclasses.*;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 * Klasse dient dazu Funktionen die mehrfach gebraucht werden zu beinhalten und an die 
 * Unterklassen weiterzugeben 
 */
public abstract class AbstractCentralLogic {

	
	/**
	 * 
	 */
	public AbstractCentralLogic() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * Prüft ob eine Datei schon auf der Festplatte existiert 
	 * @param filename
	 * @return
	 */
	public boolean checkFileExists(String filename){
		boolean returnvalue = false; 
		// Verzeichnis herausfinden wo die Datei liegen soll 
		XMLConfigHelper xmlhelper = new XMLConfigHelper(); 
		String pathtofiles = xmlhelper.getMainFolder(); 
		File searchfolder = new File(pathtofiles);
		File[] files = searchfolder.listFiles(new DDDirectoryFileFilter(""));
		
		for (int i = 0; i< files.length ; i++ ){
			if (files[i].toString().compareTo(filename) == 0)
			{
				returnvalue = true;  
			}
		}
		return returnvalue; 
	}

}

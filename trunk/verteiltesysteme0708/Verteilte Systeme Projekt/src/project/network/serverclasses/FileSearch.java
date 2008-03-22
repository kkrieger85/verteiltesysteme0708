/**
 * 
 */
package project.network.serverclasses;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import project.helperclasses.DDDirectoryFileFilter;
import project.helperclasses.NetworkHelper;
import project.helperclasses.XMLConfigHelper;
import project.network.SearchResult;

/**
 * Klasse dient zum Suchen von Dateien innerhalb eines Verzeichnisses
 * 
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert,
 *         reichert.sascha@googlemail.com</a>
 * 
 */
public class FileSearch {

	private LinkedList<SearchResult> result = new LinkedList<SearchResult>();

	/**
	 * Standardkonstruktor bewirkt eigendlich nichts ;)
	 */
	public FileSearch() {
	}

	/**
	 * @return the result
	 */
	public synchronized LinkedList<SearchResult> getResult() {
		return result;
	}

	/**
	 * Startet die Routine mit dem Hauptverzeichnis
	 * 
	 * @param searchString
	 */
	public void searchFiles(String searchString) {
		XMLConfigHelper xmlconfig = new XMLConfigHelper();

		String folder = xmlconfig.getMainFolder();
		searchFiles(searchString, folder);
	}

	/**
	 * Startet die Routine mit einem angegebenen Verzeichnis
	 * 
	 * @param searchString
	 * @param folder
	 */
	private void searchFiles(String searchString, String folder) {

		File searchfolder = new File(folder);

		// Nun nach Dateien suchen !!!
		File[] files = searchfolder.listFiles(new DDDirectoryFileFilter(searchString));
		// Nachschauen welche Dateien auch eine XML Datei dabeihaben 
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < files.length; i++) {
			String searchXML = files[i].toString() + ".xml"; 
			for (int j = 0; j < files.length; j++){
				if (searchXML.compareTo(files[j].toString()) == 0){
					list.add(files[i].toString()); 
				}
			}
		}

		// Eigene IP Adresse holen
		NetworkHelper nh = new NetworkHelper();
		if (files != null) {
			for (int i = 0; i < list.size(); i++) {
				long fileSize = 0;
				File f = new File(list.get(i));
				fileSize = f.length();
				this.result.add(new SearchResult(nh.getOwnIPAdress(), list.get(i), fileSize, nh.getOwnOpenPort()));
			}
		}
	}


	/**
	 * Einfache toString Methode die zur Ausgabe dient
	 */
	public String toString() {
		String returnValue = "";
		for (int i = 0; i < this.result.size(); i++) {
			returnValue += "Nr. " + i + "  " + this.result.get(i).toString()
					+ "\n";
		}

		return returnValue;
	}

}

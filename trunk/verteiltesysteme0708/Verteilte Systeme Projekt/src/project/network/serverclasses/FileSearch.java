/**
 * 
 */
package project.network.serverclasses;

import java.io.File;
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
		// Zuerst die unterverzeichnisse durchlaufen lassen

		// Searchstring modifizieren zuerst ^ und $ einfügen
		searchString = "^" + searchString + ".$";
		// * durch .* ersetzen
		searchString = searchString.replace("*", ".*");
		// ? durch . ersetzen
		searchString = searchString.replace("?", ".");

		// Nun nach Dateien suchen !!!
		File[] files = searchfolder.listFiles(new DDDirectoryFileFilter(searchString));

		// Eigene IP Adresse holen
		NetworkHelper nh = new NetworkHelper();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				long fileSize = 0;
				File f = new File(files[i].toString());
				fileSize = f.length();
				this.result.add(new SearchResult(nh.getOwnIPAdress(), files[i]
						.toString(), fileSize, nh.getOwnOpenPort()));
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

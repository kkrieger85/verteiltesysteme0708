/**
 * 
 */
package project.helperclasses;

import java.io.File;
import java.io.FileFilter;

/** 
 * Klasse dient zum Filtern der Dateien 
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class DDDirectoryFileFilter implements FileFilter {
	@SuppressWarnings("unused")
	private String searchString;

	public DDDirectoryFileFilter(String search) {
		this.searchString = search;
		
		// Zuerst die unterverzeichnisse durchlaufen lassen

		// Searchstring modifizieren zuerst ^ und $ einfügen
		this.searchString = "^" + this.searchString + ".$";
		// * durch .* ersetzen
		this.searchString = this.searchString.replace("*", ".*");
		// ? durch . ersetzen
		this.searchString = this.searchString.replace("?", ".");
	}

	public boolean accept(File f) {
		boolean returnvalue = false;
		if (f.isFile()) {
			if (f.getName().toString().matches(this.searchString)) {
				returnvalue = true;
			}
		//	if (!f.toString().endsWith(".xml")){
				
		//	}
			
		}
		return returnvalue;
	}
}
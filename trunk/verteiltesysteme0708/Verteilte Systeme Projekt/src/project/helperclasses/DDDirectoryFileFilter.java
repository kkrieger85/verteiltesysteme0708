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
	}

	public boolean accept(File f) {
		boolean returnvalue = false;
		if (f.isFile()) {
			if (!f.toString().endsWith(".xml")){
				returnvalue = true;
			}
			
		}
		return returnvalue;
	}
}
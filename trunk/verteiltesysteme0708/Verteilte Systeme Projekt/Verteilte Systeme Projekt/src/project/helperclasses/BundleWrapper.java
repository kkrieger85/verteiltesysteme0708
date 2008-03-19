/**
 * 
 */
package project.helperclasses;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class BundleWrapper {

	private ResourceBundle bundle;
	/**
	 * 
	 */
	public BundleWrapper() {
		XMLConfigHelper xmlconf = new XMLConfigHelper(); 
		String language = xmlconf.getProgrammLanguage(); 
		if (language.compareTo("") == 0)
			language = "de"; 
		if (language.compareTo("de") == 0) {
			this.bundle = ResourceBundle.getBundle("properties", Locale.GERMAN);
		} else if (language.compareTo("en") == 0) {
			this.bundle = ResourceBundle.getBundle("properties_en", Locale.ENGLISH);
		} else {
			this.bundle = ResourceBundle.getBundle("properties", Locale.GERMAN);
		} 
	}
	
	/**
	 * @return the bundle
	 */
	public synchronized ResourceBundle getBundle() {
		return bundle;
	}
	
	
	

}

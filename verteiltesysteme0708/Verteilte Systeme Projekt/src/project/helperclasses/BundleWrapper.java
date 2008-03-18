/**
 * 
 */
package project.helperclasses;

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
		if (language.compareTo("") != 0)
			language = "de"; 
		
		if (language.compareTo("de") == 0) {
			this.bundle = ResourceBundle.getBundle("properties");
		} else if (language.compareTo("en") == 0) {
			this.bundle = ResourceBundle.getBundle("properties_en");
		} else {
			this.bundle = ResourceBundle.getBundle("moepse");
		} 
	}
	
	/**
	 * @return the bundle
	 */
	public synchronized ResourceBundle getBundle() {
		return bundle;
	}

}

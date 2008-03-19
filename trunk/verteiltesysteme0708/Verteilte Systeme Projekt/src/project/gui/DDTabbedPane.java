/**
 * 
 */
package project.gui;

import java.util.ResourceBundle;

import javax.swing.JTabbedPane;

import project.helperclasses.BundleWrapper;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class DDTabbedPane extends JTabbedPane {

	private ResourceBundle bundle;

	/**
	 * 
	 */
	public DDTabbedPane() {
		// ResourceBundle festlegen (Standard ist Deutsch)
		BundleWrapper bw = new BundleWrapper(); 
		this.bundle = bw.getBundle();
		
		// Add Panes 
		
		// Information Page 
		this.add(this.bundle.getString("tabbedpages_informationpage_title"), new InformationTabbedPage()); 
		// Search Page 
		this.add(this.bundle.getString("tabbedpages_searchpage_title"), new SearchTabbedPane()); 	
		// Local Files Page 
		this.add(this.bundle.getString("tabbedpages_localpage_title"), new LocaleTabbedPane()); 
		
	}



}

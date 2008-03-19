/**
 * 
 */
package project.gui;

import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JTabbedPane;

import project.helperclasses.BundleWrapper;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class DDTabbedPane extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7990331438587462729L;
	private ResourceBundle bundle;
	private InformationTabbedPage infopane;
	private SearchTabbedPane searchpane;
	private LocaleTabbedPane localpane;

	/**
	 * 
	 */
	public DDTabbedPane() {
		// ResourceBundle festlegen (Standard ist Deutsch)
		BundleWrapper bw = new BundleWrapper(); 
		this.bundle = bw.getBundle();
		
		// Add Panes 
		
		// Information Page 
		this.infopane = new InformationTabbedPage(); 
		this.add(this.bundle.getString("tabbedpages_informationpage_title"), this.infopane); 
		// Search Page 
		this.searchpane = new SearchTabbedPane(); 
		this.add(this.bundle.getString("tabbedpages_searchpage_title"), this.searchpane); 	
		// Local Files Page 
		this.localpane = new LocaleTabbedPane();
		this.add(this.bundle.getString("tabbedpages_localpage_title"), this.localpane); 
		
	}
	
	/** 
	 * Gibt die Informationen nur weiter !!!
	 * @param tableData
	 */
	public void addResults(Vector<Vector<Object>> tableData) {
		this.searchpane.addResults(tableData); 
	}



}

/**
 * 
 */
package project.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.util.ResourceBundle;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import project.helperclasses.BundleWrapper;
import project.helperclasses.NetworkHelper;
import project.helperclasses.XMLConfigHelper;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class InformationTabbedPage extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8455730417119680112L;
	private ResourceBundle bundle;

	/**
	 * 
	 */
	public InformationTabbedPage() {
		// ResourceBundle festlegen (Standard ist Deutsch)
		BundleWrapper bw = new BundleWrapper();
		this.bundle = bw.getBundle();
		NetworkHelper nh = new NetworkHelper(); 
		XMLConfigHelper xmlconfig = new XMLConfigHelper(); 
		
		String dir = xmlconfig.getMainFolder(); 
		File f = new File(dir); 
		String[] files = f.list(); 
		int countFiles = files.length; 
		
		// Freienspeicher anzeigen lassen 
		long freeSpace = f.getFreeSpace(); 
		// Belegten Speicher anzeigen lassen 
		long Totalspace = f.getTotalSpace(); 

		// Layout festlegen
		GridBagLayout bagLayout = new GridBagLayout();
		bagLayout.minimumLayoutSize(this);
		this.setLayout(bagLayout);

		// Gridlayout festlegen
		GridBagConstraints gridbag = new GridBagConstraints();
		gridbag.insets = new Insets(5, 5, 5, 5);
		gridbag.weightx = 0;
		gridbag.weighty = 0;
		gridbag.fill = GridBagConstraints.HORIZONTAL;
		gridbag.anchor = GridBagConstraints.FIRST_LINE_START;

		// Label für IP Adresse 
		gridbag.gridx = 0;
		gridbag.gridy = 0;
		JLabel ipadresslabeltext = new JLabel(this.bundle
				.getString("informationpane_iplabel_text"));
		this.add(ipadresslabeltext, gridbag);	
		// eigene IP Adresse anzeigen 
		// Adresse herausfinden !!! 

		String ipadress = nh.getOwnIPAdress(); 
		// Label für IP Adresse 
		gridbag.gridx = 1;
		gridbag.gridy = 0;
		JLabel ipadresslabel = new JLabel(ipadress);
		this.add(ipadresslabel, gridbag);

		// Label für freien Port 
		gridbag.gridx = 0;
		gridbag.gridy = 1;
		JLabel portlabeltext = new JLabel(this.bundle
				.getString("informationpane_portlabel_text"));
		this.add(portlabeltext, gridbag);	
		// Port anzeigen 
		String port = nh.getOwnOpenPort(); 
		// Label für IP Adresse 
		gridbag.gridx = 1;
		gridbag.gridy = 1;
		JLabel portlabel = new JLabel(port);
		this.add(portlabel, gridbag);	
		
		
		// Label für Verzeichnis 
		gridbag.gridx = 0;
		gridbag.gridy = 2;
		JLabel dirlabeltext = new JLabel(this.bundle
				.getString("informationpane_dirlabel_text"));
		this.add(dirlabeltext, gridbag);			
		// Label für Verzeichnis 
		gridbag.gridx = 1;
		gridbag.gridy = 2;
		JLabel dirlabel = new JLabel(dir);
		this.add(dirlabel, gridbag);

		// Label für Anzahl der dateien 
		gridbag.gridx = 0;
		gridbag.gridy = 3;
		JLabel dircountfileslabel = new JLabel(this.bundle
				.getString("informationpane_dircountfileslabel_text"));
		this.add(dircountfileslabel, gridbag);	
		// eigene IP Adresse anzeigen 
		// Adresse herausfinden !!! 

		
		// Label für IP Adresse 
		gridbag.gridx = 1;
		gridbag.gridy = 3;
		JLabel countfiles = new JLabel(Integer.toString(countFiles));
		this.add(countfiles, gridbag);		
	
		// Label für freien Speicher 
		gridbag.gridx = 0;
		gridbag.gridy = 4;
		JLabel dirfreespaceavailablelabel = new JLabel(this.bundle
				.getString("informationpane_dirfreespacelabel_text"));
		this.add(dirfreespaceavailablelabel, gridbag);	
		// eigene IP Adresse anzeigen 
		// Adresse herausfinden !!! 

		
		// Label für IP Adresse 
		gridbag.gridx = 1;
		gridbag.gridy = 4;
		JLabel freespace = new JLabel(Long.toString(freeSpace));
		this.add(freespace, gridbag);	
		
		
		
		// anzahl dateien im Verzeichnis anzeigen 
		
		// Belegter speicherplatz 
		
		// Sonstige einstellungen 
		
		
	}



}

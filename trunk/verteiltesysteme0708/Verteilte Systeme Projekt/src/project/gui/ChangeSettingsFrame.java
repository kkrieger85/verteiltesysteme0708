/**
 * 
 */
package project.gui;



import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import project.helperclasses.BundleWrapper;
import project.helperclasses.XMLConfigHelper;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class ChangeSettingsFrame extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1229422196558528624L;
	private ResourceBundle bundle;
	private JButton settingsPathButton; 
	private JButton logfilePathButton; 
	private JTextField logfilePath; 

	/**
	 * 
	 */
	public ChangeSettingsFrame() {
		
		// ResourceBundle festlegen (Standard ist Deutsch)
		BundleWrapper bw = new BundleWrapper();
		this.bundle = bw.getBundle();
		
		// Standardwerte setzen 
		// ToDo: weitere Standardwerte feststellen und dann auch einstellen 
		this.setTitle(this.bundle.getString("pathsettings_title_text"));
		this.setMinimumSize(new Dimension(500, 300));
		this.setPreferredSize(new Dimension(500, 300)); 
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		// Layout festlegen
		GridBagLayout bagLayout = new GridBagLayout();
		bagLayout.minimumLayoutSize(this);
		getContentPane().setLayout(bagLayout);
		
		// Gridlayout festlegen
		GridBagConstraints  gridbag = new GridBagConstraints ();
		gridbag.insets = new Insets(10, 10, 10, 10);  
		gridbag.weightx = 1; 
		gridbag.weighty= 0; 
		gridbag.fill = GridBagConstraints.BOTH;	
		gridbag.anchor = GridBagConstraints.CENTER; 
		
		
		// Label für den Dateipfad  
		gridbag.gridx = 0;
		gridbag.gridy = 0;
		JLabel settingsPathLabel = new JLabel(this.bundle.getString("pathsettings_settings_text"));
		this.add(settingsPathLabel, gridbag); 
		
		// Textfeld für Einstellungspfad
		// Hier alten Pfad auslesen und eintragen 
		String settingPath; 
		try {
			XMLConfigHelper xmlconf = new XMLConfigHelper(); 
			settingPath = xmlconf.getMainFolder(); 
		} catch (Exception exc) {
			settingPath = "c:\\temp\\";
		}
		
		JTextField settingsPath = new JTextField();
		settingsPath.setText(settingPath); 
		gridbag.gridx = 0;
		gridbag.gridy = 1;
		settingsPath.setEditable(false);
		this.add(settingsPath,gridbag); 
		
		// Button für Einstellungspfad 
		settingsPathButton = new JButton(this.bundle.getString("pathsettings_settingsbutton_text")); 
		settingsPathButton.addActionListener(this);
		gridbag.gridx = 1;
		gridbag.gridy = 1;
		this.add(settingsPathButton, gridbag);
		
		
		// Logdatei angeben !!! 
		
		// Label für den Dateipfad  
		gridbag.gridx = 0;
		gridbag.gridy = 2;
		JLabel logPath = new JLabel(this.bundle.getString("pathsettings_logfile_text"));
		this.add(settingsPathLabel, gridbag); 
		
		// Textfeld für Einstellungspfad
		// Hier alten Pfad auslesen und eintragen 
		String oldLogfilePath; 
		try {
			XMLConfigHelper xmlconf = new XMLConfigHelper(); 
			oldLogfilePath = xmlconf.getLogfile(); 
		} catch (Exception exc) {
			oldLogfilePath = "default.log";
		}
		
		this.logfilePath = new JTextField();
		this.logfilePath.setText(oldLogfilePath); 
		gridbag.gridx = 0;
		gridbag.gridy = 3;
		this.logfilePath.setEditable(false);
		this.add(	this.logfilePath ,gridbag); 
		
		// Button für Einstellungspfad 
		logfilePathButton = new JButton(this.bundle.getString("pathsettings_settingsbutton_text")); 
		logfilePathButton.addActionListener(this);
		gridbag.gridx = 1;
		gridbag.gridy = 3;
		this.add(logfilePathButton, gridbag);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	// Dateipfad einstellen !!! 
	if (e.getSource().equals(this.settingsPathButton)){
		
		XMLConfigHelper xmlconf = new XMLConfigHelper(); 	
		String settingspath = xmlconf.getMainFolder(); 
		try {
			JFileChooser fc = new JFileChooser(settingspath);
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				File file = fc.getSelectedFile();
				String newSettingspath = file.getCanonicalPath();
				xmlconf.saveAttribut(XMLConfigHelper.MAINFOLDERATTR, newSettingspath);
				((JTextField)((JButton)e.getSource()).getParent().getComponent(1)).setText(newSettingspath);
			} 
		} catch (Exception ex ){
			// TODO Richtig abfangen !!! 
		}
	}
	// Logdatei einstellen 
	if (e.getSource().equals(this.logfilePathButton)){
		
		XMLConfigHelper xmlconf = new XMLConfigHelper(); 	
		String settingspath = xmlconf.getMainFolder(); 
		try {
			JFileChooser fc = new JFileChooser(settingspath);
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				File file = fc.getSelectedFile();
				String newSettingspath = file.getCanonicalPath();
				xmlconf.saveAttribut(XMLConfigHelper.LOGFILE, newSettingspath);
				this.logfilePath.setText(newSettingspath); 
			} 
		} catch (Exception ex ){
			// TODO Richtig abfangen !!! 
		}
	}

	}
}

/**
 * 
 */
package project.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;

import project.data.DocumentWrapper;
import project.exception.DocumentWrapperException;
import project.helperclasses.BundleWrapper;
import project.helperclasses.DDLogger;
import project.helperclasses.NetworkHelper;
import project.helperclasses.XMLConfigHelper;


/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class DDFileInfoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4145743193273367325L;
	private String file;
	private JLabel label;
	private ResourceBundle bundle;
	private JLabel version;
	private JLabel desc;
	private JLabel autor;
	private JLabel create;
	private JLabel comment; 
	
	/**
	 * Standardkonstruktor 
	 */
	public DDFileInfoPanel(String file){
		
		// ResourceBundle festlegen (Standard ist Deutsch)
		BundleWrapper bw = new BundleWrapper();
		this.bundle = bw.getBundle();
		
		this.file = file; 
		this.createForm(); 
	
		this.fillForm(); 
		

	}
	
	/**
	 * Füllt das Formular mit Daten !!!
	 */
	private void fillForm() {
		// DocumentWrapper öffnen 
		DocumentWrapper dwrapper = null;
		try {
			dwrapper = DocumentWrapper.loadFromXml(this.file);
		} catch (DocumentWrapperException e) {
			DDLogger.getLogger().createLog(e.getMessage(), DDLogger.ERROR); 
		} 
		if (dwrapper != null){
			this.desc.setText(dwrapper.getMetadata().getBeschreibung()); 			
			this.version.setText(Integer.toString(dwrapper.getVersion().getVersionNumber()));
			this.autor.setText(dwrapper.getVersion().getAuthor_username()); 
			this.create.setText(dwrapper.getVersion().getCreationTime().toString()); 
			this.comment.setText(dwrapper.getVersion().getComment()); 
		}
		this.repaint(); 
	}

	/** 
	 * Erstellt die Rohformen des Forumars
	 */
	public void createForm(){
		// Layout festlegen
		GridBagLayout bagLayout = new GridBagLayout();
		bagLayout.minimumLayoutSize(this);
		this.setLayout(bagLayout);

		// Gridlayout festlegen
		GridBagConstraints gridbag = new GridBagConstraints();
		gridbag.insets = new Insets(5, 5, 5, 5);
		gridbag.weightx = 1;
		gridbag.weighty = 1;

		gridbag.fill = GridBagConstraints.BOTH;
		gridbag.anchor = GridBagConstraints.FIRST_LINE_START;

		
		// Dateinamenslabel 
		gridbag.gridx = 0;
		gridbag.gridy = 0;
		this.add(new JLabel(this.bundle.getString("fileinfopanel_filenamelabel_text")),gridbag); 
		
		// Dateinamen anzeigen 
		gridbag.gridx = 1;
		gridbag.gridy = 0;
		this.label = new JLabel(this.file) ; 
		this.label.setVisible(true); 
		this.add(this.label, gridbag); 
		this.setVisible(true); 
		
		// Beschreibung 
		gridbag.gridx = 0;
		gridbag.gridy = 1;
		this.add(new JLabel(this.bundle.getString("fileinfopanel_descriptionlabel_text")),gridbag); 
		
		// Beschreibung 
		gridbag.gridx = 1;
		gridbag.gridy = 1;
		this.desc = new JLabel() ; 
		this.add(this.desc, gridbag); 
		
		
		// Versionlabel 
		gridbag.gridx = 0;
		gridbag.gridy = 2;
		this.add(new JLabel(this.bundle.getString("fileinfopanel_versionlabel_text")),gridbag); 
		
		// Version anzeigen 
		gridbag.gridx = 1;
		gridbag.gridy = 2;
		this.version = new JLabel() ; 
		this.add(this.version, gridbag); 
		

	
		// Autorlabel 
		gridbag.gridx = 0;
		gridbag.gridy = 3;
		this.add(new JLabel(this.bundle.getString("fileinfopanel_autorlabel_text")),gridbag); 
		
		// Version anzeigen 
		gridbag.gridx = 1;
		gridbag.gridy = 3;
		this.autor = new JLabel() ; 
		this.add(this.autor, gridbag); 		
		
		// Erstellt am Label  
		gridbag.gridx = 0;
		gridbag.gridy = 4;
		this.add(new JLabel(this.bundle.getString("fileinfopanel_creationlabel_text")),gridbag); 
		
		// Erstellt am 
		gridbag.gridx = 1;
		gridbag.gridy = 4;
		this.create = new JLabel() ; 
		this.add(this.create, gridbag); 	
		
		// Erstellt am Label  
		gridbag.gridx = 0;
		gridbag.gridy = 5;
		this.add(new JLabel(this.bundle.getString("fileinfopanel_comment_text")),gridbag); 
		
		// Erstellt am 
		gridbag.gridx = 1;
		gridbag.gridy = 5;
		this.comment = new JLabel() ; 
		this.add(this.comment, gridbag); 			
	}
}

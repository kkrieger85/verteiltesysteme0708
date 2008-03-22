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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileFilter;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import project.helperclasses.BundleWrapper;
import project.helperclasses.DDDirectoryFileFilter;
import project.helperclasses.XMLConfigHelper;


/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class LocaleTabbedPane extends JPanel implements ActionListener, ListSelectionListener, MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9173104823649931155L;
	private ResourceBundle bundle;
	private JLabel topiclabel;
	private JLabel filelistlabel;
	private JLabel fileinfolabel;
	private JList filelist;
	private DDFileInfoPanel informationPanel;
	private JButton addDocumentButton;
	private JScrollPane jspFileInfo;

	/**
	 * 
	 */
	public LocaleTabbedPane() {
		
		
		// ResourceBundle festlegen (Standard ist Deutsch)
		BundleWrapper bw = new BundleWrapper(); 
		this.bundle = bw.getBundle();
		

		// Layout festlegen
		GridBagLayout bagLayout = new GridBagLayout();
		bagLayout.minimumLayoutSize(this);
		this.setLayout(bagLayout);
		// Gridlayout festlegen
		GridBagConstraints gridbag = new GridBagConstraints();
		gridbag.insets = new Insets(5, 5, 5, 5);
		
		// Topiclabel setzen 
		this.topiclabel = new JLabel(this.bundle.getString("localpane_topiclabel_text")); 
		gridbag.gridx = 0;
		gridbag.gridy = 0;
		gridbag.gridwidth = 2; 
		gridbag.weightx = 0.4; 
		gridbag.weighty = 0.1;
		gridbag.anchor = GridBagConstraints.FIRST_LINE_START; 
		gridbag.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.topiclabel, gridbag);
		
		// Label DateiListe setzen 
		this.filelistlabel = new JLabel(this.bundle.getString("localpane_filelistlabel_text")); 
		gridbag.gridx = 0;
		gridbag.gridy = 1;
		gridbag.gridwidth = 1; 
		gridbag.weighty = 0.1;
		gridbag.weightx = 0.4; 
		this.add(this.filelistlabel, gridbag);
		
		// Label Dateiinfos setzen 
		this.fileinfolabel = new JLabel(this.bundle.getString("localpane_fileinfolabel_text")); 
		gridbag.gridx = 1;
		gridbag.gridy = 1;
		gridbag.weighty = 0.1;
		gridbag.weightx = 0.6; 
		this.add(this.fileinfolabel, gridbag);
		
		// Dateiliste setzen 
        this.filelist = this.fillFileList();
        JScrollPane jsp = new JScrollPane(this.filelist); 
		gridbag.gridx = 0;
		gridbag.gridy = 2;
		gridbag.weightx = 0.4; 
		gridbag.weighty = 0.9;
		gridbag.fill = GridBagConstraints.BOTH;
		gridbag.anchor = GridBagConstraints.CENTER; 
		this.filelist.addListSelectionListener(this); 
		this.filelist.addMouseListener(this); 
		this.add(jsp,gridbag); 
 
		// Dateiinfopanel einbauen !!! 
		this.informationPanel = new DDFileInfoPanel("Test"); 
        this.jspFileInfo = new JScrollPane(this.informationPanel); 
		gridbag.gridx = 1;
		gridbag.gridy = 2;
		gridbag.weightx = 0.6; 
		gridbag.weighty = 0.9;
		this.add(this.jspFileInfo,gridbag); 	
		
		// Neu anlegen Button reinbauen 
		this.addDocumentButton = new JButton(this.bundle.getString("localpane_adddocumentbutton_text")); 
		gridbag.gridx = 0;
		gridbag.gridy = 3;
		gridbag.weightx = 1; 
		gridbag.gridwidth = 2; 
		gridbag.weighty = 0.1;
		gridbag.fill = GridBagConstraints.NONE;
		this.addDocumentButton.addActionListener(this); 
		this.add(this.addDocumentButton,gridbag); 	
		
	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (((JButton)arg0.getSource()).equals(this.addDocumentButton)){
			AddNewFileFrame anff = new AddNewFileFrame();
			anff.setSize(new Dimension(640, 480));
			anff.setVisible(true);
		}
		
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {

	}
	
	/** 
	 * Dateiliste erstellen 
	 * @return
	 */
	private JList fillFileList(){
		XMLConfigHelper xmlconfig = new XMLConfigHelper(); 
		String dir = xmlconfig.getMainFolder(); 
		
		File searchfolder = new File(dir);
		File[] files = searchfolder.listFiles(new DDDirectoryFileFilter(""));
		String[] filenames = new String[files.length]; 
		for (int i = 0; i< files.length ; i++ ){
			if (!files[i].toString().endsWith(".xml"))
				filenames[i] = files[i].toString(); 			
		}
		this.filelist = new JList(filenames); 
		return this.filelist; 
	}
	
	public void repaint(){
		super.repaint(); 
		this.setVisible(true);
	}
	


	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		if (arg0.getSource().equals(this.filelist)){
			if (arg0.getClickCount() == 1){

				// DDLogger.getLogger().createLog(((JList)arg0.getSource()).getSelectedValue().toString(), DDLogger.DEBUG); 

				DDFileInfoPanel ddfip  = new DDFileInfoPanel(((JList)arg0.getSource()).getSelectedValue().toString());				
				this.jspFileInfo.getViewport().removeAll(); 
				this.jspFileInfo.getViewport().add(ddfip);
				this.jspFileInfo.repaint(); 
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

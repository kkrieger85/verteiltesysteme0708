/**
 * 
 */
package project.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import project.helperclasses.BundleWrapper;
import project.helperclasses.DDLogger;
import project.centrallogic.*;
import java.util.Vector;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class SearchTabbedPane extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5676877783101073084L;
	private ResourceBundle bundle;
	private JTextField searchValue;
	private JLabel topiclabel;
	private JButton startSearchButton;
	private JTable table;
	private JButton downloadButton;
	private JButton clearTable; 
	/**
	 * 
	 */
	public SearchTabbedPane() {
		
		
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
		
		// Tabpage einbauen
		this.topiclabel = new JLabel(this.bundle.getString("searchpane_topiclabel_text")); 
		gridbag.gridx = 0;
		gridbag.gridy = 1;
		gridbag.weighty = 0.1;
		gridbag.fill = GridBagConstraints.BOTH;
		this.add(topiclabel, gridbag);
		

		// Suchfeld einbauen !!! 
		this.searchValue = new JTextField(this.bundle.getString("searchpane_textfield_text")); 
		gridbag.gridx = 0;
		gridbag.gridy = 2;
		gridbag.weightx = 1;
		gridbag.weighty = 0.1;
		gridbag.fill = GridBagConstraints.HORIZONTAL;
		gridbag.anchor = GridBagConstraints.PAGE_START; 
		this.add(this.searchValue, gridbag);
		
		// Einbau Suchestarten button 
		this.startSearchButton = new JButton(this.bundle.getString("searchpane_startsearchbutton_text"));
		gridbag.gridx = 1;
		gridbag.gridy = 2;
		gridbag.weightx = 0.2;
		gridbag.weighty = 0.1;
		gridbag.fill = GridBagConstraints.HORIZONTAL;
		gridbag.anchor = GridBagConstraints.PAGE_START; 
		this.startSearchButton.addActionListener(this); 
		this.add(this.startSearchButton, gridbag);

		// Label einbauen 
		gridbag.gridx = 0;
		gridbag.gridy = 3;
		gridbag.weightx = 0.5;
		gridbag.weighty = 0.1;
		gridbag.fill = GridBagConstraints.HORIZONTAL;
		JLabel tableLabel = new JLabel(this.bundle.getString("searchpane_resulttablelabel_text")); 
		this.add(tableLabel, gridbag); 
		
		gridbag.gridx = 0;
		gridbag.gridy = 4;
		gridbag.gridheight = 2; 
		gridbag.weightx = 0.82;
		gridbag.fill = GridBagConstraints.BOTH;	
		gridbag.anchor = GridBagConstraints.FIRST_LINE_START;
		
		// TODO Spaltennamen richtig einbauen 
		String[] tableHeaders = { this.bundle.getString("searchpane_tabletopic_filename"),	 
								  this.bundle.getString("searchpane_tabletopic_ipaddress"),
								  this.bundle.getString("searchpane_tabletopic_port"),
								  this.bundle.getString("searchpane_tabletopic_filesize")}; 
		Object[][] tableData = {};
		DDTableModel tablemodel = new DDTableModel(tableData,tableHeaders);
		this.table = new JTable(tablemodel);
		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JScrollPane jsp = new JScrollPane(table); 
		jsp.setSize(this.getSize()); 
		jsp.setColumnHeaderView(this.table.getTableHeader());
		this.table.getTableHeader().setReorderingAllowed(false);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
	
		this.add(jsp,gridbag); 
		this.setVisible(true); 
		
		// Buttons einbauen 
		// Runterladen Button 
		gridbag.gridx = 1;
		gridbag.gridy = 4;
		gridbag.weightx = 0.2; 
		gridbag.weighty= 0; 
		gridbag.gridheight = 1; 
		gridbag.fill = GridBagConstraints.HORIZONTAL;	
		this.downloadButton = new JButton(this.bundle.getString("searchpane_downloadbutton_text"));
		this.downloadButton.addActionListener(this); 
		this.add(this.downloadButton,gridbag); 
		
		// Tabelle leeren Button 
		gridbag.gridx = 1;
		gridbag.gridy = 5;
		gridbag.weightx = 0.2; 

		gridbag.fill = GridBagConstraints.HORIZONTAL;	
		this.clearTable = new JButton(this.bundle.getString("searchpane_deletetable_text"));
		this.clearTable.addActionListener(this); 
		this.add(this.clearTable,gridbag); 
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(this.startSearchButton)){
			
			
			// Wenn suchen Button gedrück wurde mach etwas ;) 
			MainFrame.getInstance().refreshInformationLabel(this.bundle.getString("searchpane_searchstartet_text")+ " " + this.searchValue.getText());
			
			// Central Logic Klasse ansteuern 
			@SuppressWarnings("unused")
			NormalFilesearchTemplate nft = new NormalFilesearchTemplate(this.searchValue.getText()); 
			
			// Testfüllen 
			// this.addResults(this.fillTest()); 
		}
		
		if (arg0.getSource().equals(this.downloadButton)){
			// Wenn suchen Button gedrück wurde mach etwas ;) 
			MainFrame.getInstance().refreshInformationLabel(this.bundle.getString("searchpane_startfileload_text"));
			// Datenheraussuchen 
			
			
			int row = this.table.getSelectedRow(); 
			if (row > 0)				
			{
				// Dateiname 
				String filename = this.table.getValueAt(row, 0).toString(); 
				
				// IPAdresse 
				String ipaddress = this.table.getValueAt(row,1).toString(); 
				
				// Port 
				String port = this.table.getValueAt(row, 2).toString(); 
				
				// Central Logic Klasse ansteuern 
				@SuppressWarnings("unused")
				DownLoadFileTemplate dft = new DownLoadFileTemplate(filename,ipaddress, port); 
			} else {
				DDLogger.getLogger().createLog("Nichts angewählt!!",DDLogger.ERROR); 
				// TODO Lokalisierung und Fehlermeldung ausgeben !!! 
			}
			
		}
		
		if (arg0.getSource().equals(this.clearTable)){
			// Wenn suchen Button gedrück wurde mach etwas ;) 
			
		
			this.clearRows(); 
			this.table.repaint(); 
			// Central Logic Klasse ansteuern 
		
		}	
	}
	
	/**
	 * Mit dieser Methode kann man die Tabelle leeren
	 */
	private void clearRows() {
		for(int i = this.table.getRowCount() - 1; i >= 0; i--) {
			((DefaultTableModel) this.table.getModel()).removeRow(i);
			}
		} 

	/** 
	 * Methode dient zum Füllen der Tabelle 
	 * @param tableData
	 */
	public void addResults(Vector<Vector<Object>> tableData) {
		Vector<Object> innerVector; 
		for (int i = 0; i < tableData.size(); i++){
			innerVector = tableData.get(i); 
			((DefaultTableModel) this.table.getModel()).addRow(innerVector);
		}		
	}
	
	/**
	 * Testmethode zum Testen der Tabelle !!! 
	 * @return
	 */
	public Vector<Vector<Object>> fillTest(){
		Vector<Vector<Object>> outerVector = new Vector<Vector<Object>>(); 
		Vector<Object> innerVector = new Vector<Object>(); 
		innerVector.add("Test"); 
		innerVector.add("1.2.3.4"); 
		outerVector.add(innerVector); 
		innerVector = new Vector<Object>(); 
		innerVector.add("Test2"); 
		innerVector.add("5.6.7.8"); 
		outerVector.add(innerVector); 
		return outerVector; 
		
	}


}

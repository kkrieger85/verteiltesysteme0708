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
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import project.helperclasses.BundleWrapper;
import project.helperclasses.DDLogger;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class LogFileView extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3733159510240858138L;
	private ResourceBundle bundle; 
	public JTextArea ta; 
	private JButton delLogButton;
	private JButton cancelButton; 
	
	public LogFileView (){
		
		// ResourceBundle festlegen (Standard ist Deutsch)
		BundleWrapper bw = new BundleWrapper();
		this.bundle = bw.getBundle();
		
		this.setTitle(this.bundle.getString("logfileview_title_text")); 
		this.setMinimumSize(new Dimension(500, 500));
		this.setPreferredSize(new Dimension(500, 500)); 
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		
		// Layout festlegen
		GridBagLayout bagLayout = new GridBagLayout();
		bagLayout.minimumLayoutSize(this);
		getContentPane().setLayout(bagLayout);
		
		// Gridlayout festlegen
		GridBagConstraints  gridbag = new GridBagConstraints ();
		gridbag.insets = new Insets(10,10,10,10);  
		gridbag.weightx = 1; 
		gridbag.weighty= 1; 
		gridbag.gridx = 0; 
		gridbag.gridy= 0; 
		gridbag.gridwidth = 2; 
		gridbag.fill = GridBagConstraints.BOTH;	
		gridbag.anchor = GridBagConstraints.CENTER; 
		
		// Textarea bauen für den Inahlt der Logfile anzuzeigen
		this.ta = new JTextArea();
		this.ta.setMinimumSize(new Dimension(500, 100));
		this.ta.setVisible(true);
		
		
		String logfile = DDLogger.requestLog(); 
		
		this.ta.append(logfile); 
		this.ta.append("\n"); 

		
		JScrollPane jsp = new JScrollPane(this.ta); 
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		this.add(jsp,gridbag); 
		
		//
	
		// Button für Abbrechen
		gridbag.weightx = 0.25; 
		gridbag.weighty= 0; 		
		gridbag.gridx = 0; 
		gridbag.gridy= 1; 
		gridbag.gridwidth = 1; 
		delLogButton = new JButton(this.bundle.getString("logfileview_delbutton_text")); 
		delLogButton.addActionListener(this); 
		this.add(delLogButton, gridbag); 		
		
		// Button für Abbrechen
		
		gridbag.gridx = 1; 
		gridbag.gridy= 1; 
		cancelButton = new JButton(this.bundle.getString("logfileview_cancelbutton_text")); 
		cancelButton.addActionListener(this); 
		this.add(cancelButton, gridbag); 
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (arg0.getSource().equals(this.cancelButton)){
			this.setVisible(false);   					
			this.dispose();  
		}
		if (arg0.getSource().equals(this.delLogButton)){
			DDLogger.cleanLog(); 
			this.ta.setText("");  

		}
	}
	


}

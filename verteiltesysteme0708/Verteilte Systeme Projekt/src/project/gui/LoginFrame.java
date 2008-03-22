/**
 * 
 */
package project.gui;

import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import project.exception.LoginException;
import project.helperclasses.BundleWrapper;
import project.helperclasses.DDLogger;
import project.helperclasses.XMLConfigHelper;
import project.rightmanagement.RightQuery;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class LoginFrame extends JFrame implements ActionListener{

	private ResourceBundle bundle;
	private JTextField username; 
	private JPasswordField pwd; 
	private JCheckBox merke; 
	private JButton abbr; 
	private JButton loginbut; 

	/**
	 * @throws HeadlessException
	 */
	public LoginFrame() throws HeadlessException {
		
		// ResourceBundle festlegen (Standard ist Deutsch)
		BundleWrapper bw = new BundleWrapper();
		this.bundle = bw.getBundle();
		
		// Loginnamen auslesen 
		XMLConfigHelper xmlconfig = new XMLConfigHelper(); 
		
		
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
		
		// Loginname Label  
		gridbag.gridx = 0;
		gridbag.gridy = 0;
		this.add(new JLabel(this.bundle.getString("login_loginnamelabel_text")),gridbag); 
	
		// Passwort Label  
		gridbag.gridx = 0;
		gridbag.gridy = 1;
		this.add(new JLabel(this.bundle.getString("login_passwordlabel_text")),gridbag); 
		
		// Benutzername Eingabefeld 
		gridbag.gridx = 1;
		gridbag.gridy = 0;
		this.username = new JTextField(); 
		this.username.setText(xmlconfig.getLoginname()); 
		this.add(this.username, gridbag); 
		
		// Passworteingabefeld 
		gridbag.gridx = 1;
		gridbag.gridy = 1;
		this.pwd = new JPasswordField(); 
		this.add(this.pwd, gridbag); 
		
		// Merkenfeld einbauen 
		gridbag.gridx = 1;
		gridbag.gridy = 2;
		this.merke = new JCheckBox(this.bundle.getString("login_merkelabel_text")); 
		this.add(this.merke,gridbag); 
		
		// Abbrechen Button einbauen 
		gridbag.gridx = 0;
		gridbag.gridy = 3;
		this.abbr = new JButton(this.bundle.getString("login_cancelbutton_text")); 
		this.abbr.addActionListener(this);
		this.add(this.abbr, gridbag); 
		
		// Loginbutton einbauen 
		gridbag.gridx = 1;
		gridbag.gridy = 3;
		this.loginbut = new JButton(this.bundle.getString("login_loginbutton_text")); 
		this.loginbut.addActionListener(this); 
		this.add(this.loginbut, gridbag); 
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (((JButton)arg0.getSource()).equals(this.abbr)){
			// Abbrechen Button !!! 
			DDLogger.getLogger().createLog("Loginabgebrochen", DDLogger.DEBUG); 
		}
		if (((JButton)arg0.getSource()).equals(this.loginbut)){
			if (this.merke.isSelected()){
				XMLConfigHelper xmlconf = new XMLConfigHelper(); 
				xmlconf.saveAttribut(XMLConfigHelper.LOGINNAME, this.username.getText()); 				
			}
			
			RightQuery rq = new RightQuery(); 
			try {
				if (rq.login("localhost", this.username.getText(), this.pwd.getText())){
					DDLogger.getLogger().createLog("Login mit: " + this.username.getText() + " Erfolgreich" , DDLogger.DEBUG); 
					
				} else 
					DDLogger.getLogger().createLog("Login mit: " + this.username.getText() + " Nicht erfolgreich" , DDLogger.DEBUG); 
				
			} catch (LoginException e) {
				
				e.printStackTrace();
			} 
			

			// Einloggen Button ;) 
						
		}
		
	}



}

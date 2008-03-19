/**
 * 
 */
package project.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


import project.helperclasses.BundleWrapper;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class MainFrame extends JFrame implements WindowListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6676395207469927113L;
	private ResourceBundle bundle;
	private static MainFrame instance; 
	
	private JLabel informationLabel; 
	private JMenuItem file_exit;
	private JMenuItem file_log;
	private JMenuItem settings_language;
	private JMenuItem settings_settings;
	private JMenuItem help_help;
	private JMenuItem help_info;
	private DDTabbedPane tabPageContainer;

	/**
	 * @throws HeadlessException
	 */
	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}
	
	private MainFrame(){
		
		// Schließ-Event
		addWindowListener(this);

		// ResourceBundle festlegen (Standard ist Deutsch)
		BundleWrapper bw = new BundleWrapper(); 
		this.bundle = bw.getBundle();
		
		// Layout festlegen
		GridBagLayout bagLayout = new GridBagLayout();
		bagLayout.minimumLayoutSize(this);
		getContentPane().setLayout(bagLayout);
		// Gridlayout festlegen
		GridBagConstraints gridbag = new GridBagConstraints();
		gridbag.insets = new Insets(0, 0, 0, 0);

		// Standardwerte setzen
		// ToDo: weitere Standardwerte feststellen und dann auch einstellen
		this.setTitle(this.bundle.getString("programm_name"));
		this.setMinimumSize(new Dimension(1024, 768));
		
		// Menü einbauen 
		this.setJMenuBar(this.createMenuBar()); 
		

		// Tabpage einbauen
		this.tabPageContainer = new DDTabbedPane();
		gridbag.gridx = 0;
		gridbag.gridy = 2;
		gridbag.weightx = 1;
		gridbag.weighty = 1;
		gridbag.fill = GridBagConstraints.BOTH;
		this.add(tabPageContainer, gridbag);
		
		// Einbau Eingabezeile !!!
		gridbag.gridx = 0;
		gridbag.gridy = 3;
		gridbag.weightx = 0;
		gridbag.weighty = 0;
		gridbag.fill = GridBagConstraints.HORIZONTAL;
		this.informationLabel = new JLabel(this.bundle.getString("mainframe_informationlabel_text"));
		
		this.add(this.informationLabel, gridbag);
	}

	
	
	
	
	
	public JMenuBar createMenuBar() {
		// Einzelne Programmteile mit einbauen !!!
		// Menüleiste
		JMenuBar menubar = new JMenuBar();

		// Datei Menü
		JMenu file_menu = new JMenu(this.bundle.getString("file_text"));
		file_menu.updateUI();
		menubar.add(file_menu);

		JMenuItem file_new_server = new JMenuItem(this.bundle.getString("file_new_file_text"));
		
		file_menu.add(file_new_server);

		
		this.file_log = new JMenuItem(this.bundle.getString("file_log_text"));
		this.file_log.addActionListener(this);
		file_menu.add(this.file_log);
		

		this.file_exit = new JMenuItem(this.bundle.getString("file_exit_text"));
		this.file_exit.addActionListener(this);
		file_menu.add(this.file_exit);

		// Look and Feel
		JMenu settings_menu = new JMenu(this.bundle.getString("settings_text"));
		menubar.add(settings_menu);

		// Sprache einstellen !!!
		this.settings_language = new JMenuItem(this.bundle.getString("settings_language_text"));
		this.settings_language.addActionListener(this);
		settings_menu.add(this.settings_language);

		// Einstellungen einstellen ;) 
		this.settings_settings = new JMenuItem(this.bundle.getString("settings_settings_text"));
		this.settings_settings.addActionListener(this);
		settings_menu.add(this.settings_settings);

		// Hilfe
		JMenu help_menu = new JMenu(this.bundle.getString("help_text"));
		menubar.add(help_menu);

		// Hilfe selbst aufrufen 
		this.help_help = new JMenuItem(this.bundle.getString("help_help_text"));
		this.help_help.addActionListener(this);
		help_menu.add(this.help_help);

		// Info aufrufen 
		this.help_info = new JMenuItem(this.bundle.getString("help_info_text"));
		this.help_info.addActionListener(this);
		help_menu.add(this.help_info);

		menubar.setVisible(true);
		return menubar;
	}

	/**
	 * @return the bundle
	 */
	public synchronized ResourceBundle getBundle() {
		return bundle;
	}

	public void refreshInformationLabel(String info){
		this.informationLabel.setText(info); 
	}

	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowClosing(WindowEvent arg0) {}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}


	@Override
	public void actionPerformed(ActionEvent e) {
		// Abfangen vom Sprache ändern 
		if (e.getSource().equals(this.settings_language)){
			try {
				LanguageFrame newLanguage = new LanguageFrame();
				newLanguage.setSize(new Dimension(250, 100));
				newLanguage.setVisible(true);
			} catch (Exception ex) {
				// TODO Exception ordentlich abfangen !!! 
			}
		}
		// Abfangen vom Exit
		if (e.getSource().equals(this.file_exit)){
			System.exit(0);
		}
		
		// Abfangen vom Einstellungen ändern 
		if (e.getSource().equals(this.settings_settings)){
			ChangeSettingsFrame settings = new ChangeSettingsFrame();
			settings.setSize(new Dimension(250, 100));
			settings.setVisible(true);
		}
		
		// Abfangen vom Log ansehen 
		if (e.getSource().equals(this.file_log)){
			LogFileView lfv = new LogFileView();
			lfv.setSize(new Dimension(640, 480));
			lfv.setVisible(true);
		}
		
	}

	public void repaint(){
		super.repaint(); 
	}
	
	/**
	 * @return the tabPageContainer
	 */
	public synchronized DDTabbedPane getTabPageContainer() {
		return tabPageContainer;
	}
}

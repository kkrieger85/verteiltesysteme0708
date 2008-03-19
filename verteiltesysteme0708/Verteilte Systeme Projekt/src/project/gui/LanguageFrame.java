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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import project.helperclasses.BundleWrapper;
import project.helperclasses.XMLConfigHelper;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert,
 *         reichert.sascha@googlemail.com</a>
 * 
 */
public class LanguageFrame extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5385313872640907077L;
	private ResourceBundle bundle;
	private JComboBox combobox; 
	private JButton languageButton;

	public LanguageFrame() {

		// ResourceBundle festlegen (Standard ist Deutsch)
		BundleWrapper bw = new BundleWrapper();
		this.bundle = bw.getBundle();

		// Standardwerte setzen
		// ToDo: weitere Standardwerte feststellen und dann auch einstellen
		this.setTitle(this.bundle.getString("language_title"));
		this.setMinimumSize(new Dimension(300, 200));
		this.setPreferredSize(new Dimension(300, 200));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		// Layout festlegen
		GridBagLayout bagLayout = new GridBagLayout();
		bagLayout.minimumLayoutSize(this);
		getContentPane().setLayout(bagLayout);

		// Gridlayout festlegen
		GridBagConstraints gridbag = new GridBagConstraints();
		gridbag.insets = new Insets(10, 10, 10, 10);
		gridbag.weightx = 1;
		gridbag.weighty = 0;
		gridbag.fill = GridBagConstraints.BOTH;
		gridbag.anchor = GridBagConstraints.CENTER;

		// Label für Deutsch
		gridbag.gridx = 0;
		gridbag.gridy = 0;
		JLabel deutsch = new JLabel(this.bundle
				.getString("language_choose_language"));
		this.add(deutsch, gridbag);

		// Button für Deutsch
		combobox = new JComboBox();
		combobox.addItem("DE");
		combobox.addItem("EN");
		combobox.addItem("FR");
		combobox.addItem("ES");
		combobox.addItem("IT");
		combobox.addItem("RU");

		gridbag.gridx = 1;
		gridbag.gridy = 0;
		this.add(combobox, gridbag);

		// Button für Deutsch
		languageButton = new JButton(this.bundle
				.getString("language_choose_button"));
		languageButton.addActionListener(this);
		gridbag.gridx = 1;
		gridbag.gridy = 1;
		this.add(languageButton, gridbag);

	}

	/**
	 * @return the bundle
	 */
	public synchronized ResourceBundle getBundle() {
		return bundle;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		MainFrame mf = MainFrame.getInstance();
		try {
			Object obj = arg0.getSource();
			if (this.languageButton.equals(obj)) {
				// Wert herausfinden 
				String value = "";
				value = this.combobox.getSelectedItem().toString(); 
				// In Datei Speichern
				XMLConfigHelper xmlconf = new XMLConfigHelper(); 
				xmlconf.saveAttribut(XMLConfigHelper.PROGRAMMLANGUAGE, value); 
				
				mf.refreshInformationLabel(this.getBundle().getString(
						"language_change_information")+ "  " + value);
				mf.repaint();
			}
		} catch (Exception ex) {

		}
	}
}

/**
 * 
 */
package project.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;


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
	
	/**
	 * Standardkonstruktor 
	 */
	public DDFileInfoPanel(String file){
		this.file = file; 
		this.label = new JLabel(this.file) ; 
		this.label.setVisible(true); 
		this.add(this.label); 
		this.setVisible(true); 
	}
}

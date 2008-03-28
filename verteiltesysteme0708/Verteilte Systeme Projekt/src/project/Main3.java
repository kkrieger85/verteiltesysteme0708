/**
 * 
 */
package project;

import java.awt.Dimension;

import project.gui.LoginFrame;
import project.gui.MainFrame;
import project.helperclasses.DDLogger;
import project.network.RMIServerImpl;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class Main3 {

	public RMIServerImpl server; 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		DDLogger ddl = new DDLogger(DDLogger.ALL); 
		Main3 main = new Main3();
		main.server = new RMIServerImpl(); 
		
		LoginFrame login = new LoginFrame();
		login.setSize(new Dimension(400, 200));
		login.setVisible(true);
		
	}

}

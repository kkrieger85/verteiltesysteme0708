/**
 * 
 */
package project.classtests.helper;

import java.awt.Dimension;

import project.Main2;
import project.gui.ChangeSettingsFrame;
import project.gui.LoginFrame;
import project.gui.MainFrame;
import project.helperclasses.DDLogger;
import project.network.RMIServerImpl;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class TestLoginMask {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DDLogger ddl = new DDLogger(DDLogger.ALL); 

		LoginFrame login = new LoginFrame();
		login.setSize(new Dimension(400, 200));
		login.setVisible(true);
	}

}

/**
 * 
 */
package project;

import project.gui.MainFrame;
import project.helperclasses.DDLogger;
import project.network.RMIServerImpl;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class Main2 {
	
	public RMIServerImpl server; 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		DDLogger ddl = new DDLogger(DDLogger.ALL); 
		Main2 main = new Main2();
		main.server = new RMIServerImpl(); 
		
		MainFrame mf = MainFrame.getInstance(); 
		mf.setVisible(true);
	
	}

}

package project;

import project.gui.*;
import project.helperclasses.DDLogger;
import project.helperclasses.OptionSingleton;
import project.network.RMIServerImpl;


/**
 * Neue richtige Mainmethode wo keine unnötigen Sachen drin stehen ;) 
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class Main {
	
	// RMI Serveraufruf kommt später in der Mainmethode 
	public RMIServerImpl server; 
	
	public static String AUTHMODE = "auth"; 
	public static String DEBUGMODE = "debug"; 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {	
		// Startargumente durchgehen 
		for (int i = 0; i < args.length; i++){
			if (args[i].compareTo(Main.AUTHMODE) == 0){
				OptionSingleton.setAutservermode(true); 
			}
			
			if (args[i].compareTo(Main.DEBUGMODE) == 0){
				OptionSingleton.setDebugmode(true); 
			}
		}
		
		
		// Logger muss initialisiert werden 
		if (OptionSingleton.isDebugmode()){
			@SuppressWarnings("unused")
			DDLogger ddl = new DDLogger(DDLogger.ALL); 
		} else {
			@SuppressWarnings("unused")
			DDLogger ddl = new DDLogger(DDLogger.ERROR); 
		}
		
		Main2 main = new Main2();
		main.server = new RMIServerImpl(); 
		
		MainFrame mf = MainFrame.getInstance(); 
		mf.setVisible(true);
	
	}


}

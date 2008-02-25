/**
 * 
 */
package project.classtests;

import project.helperclasses.DDLogger;
import project.network.*;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class ObserverTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Observer holen !!! 
		ThreadObserver tobs = ThreadObserver.getInstance(); 
		
		// Logger init 
		@SuppressWarnings("unused")
		DDLogger ddl = new DDLogger(DDLogger.ALL); 
		
		// Variablen anlegen fürs spätere Testen !!! 
		String address = "Localhost"; 
		String port = "3333"; 
		int type = ThreadObject.THREADTEST; 
		
/*		// Test mit einem einzigen Thread 
		try {
			ThreadObject to = new ThreadObject(address,port,type); 

			to.addObserver(tobs); 
			tobs.addThread(to); 

		} catch (Exception ex){
			
		}*/
		for (int i = 0; i < 3; i++){
			try {
				
			ThreadObject to = new ThreadObject(address,port,type); 
			to.addObserver(tobs); 
			tobs.addThread(to); 
			} catch (Exception ex){
				
			}
		}
		
		
		
	}

}

/**
 * 
 */
package project.classtests;

import project.helperclasses.DDLogger;
import project.network.ThreadObject;
import project.network.ThreadObserver;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class NetworkTests01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DDLogger ddl = new DDLogger(DDLogger.ALL); 
		ThreadObserver tobs = ThreadObserver.getInstance(); 
		String address = "192.168.2.101"; 
		String port = "1099"; 
		int type = ThreadObject.GETIPLISTACTION; 
		try {
			
			for (int i = 0; i < 5; i++) {
			ThreadObject to = new ThreadObject(address,port,type); 
			to.addObserver(tobs); 
			tobs.addThread(to); 
			}
			
		} catch (Exception ex){
			
		}
		
		try {
			
			for (int i = 0; i < 5; i++) {
			ThreadObject to = new ThreadObject("192.168.2.105",port,type); 
			to.addObserver(tobs); 
			tobs.addThread(to); 
			}
			
		} catch (Exception ex){
			
		}
	}

}

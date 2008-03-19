/**
 * 
 */
package project.classtests.network;

import project.exception.NetworkFacadeException;
import project.helperclasses.DDLogger;
import project.network.NetworkFacade;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class NetworkFacadeTestcaseTwo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Logger init 
		@SuppressWarnings("unused")
		DDLogger ddl = new DDLogger(DDLogger.ALL); 
		
		NetworkFacade nf = new NetworkFacade(); 
	
		try {
			nf.startSearchRoutine("*", nf.getIPList());
		} catch (NetworkFacadeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}

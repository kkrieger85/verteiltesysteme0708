/**
 * 
 */
package project.centrallogic;

import project.exception.NetworkFacadeException;
import project.network.*;
import project.helperclasses.*;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class NormalFilesearchTemplate extends AbstractCentralLogic implements Runnable{

	private String searchFileName; 
	/**
	 * 
	 */
	public NormalFilesearchTemplate(String filename) {
		// TODO Fehleingaben abfangen !!! 
		this.searchFileName = filename; 
		Thread thread = new Thread(this); 
		thread.start(); 
	}

	@Override
	public void run() {
		// Zuerst die Fassade laden 
		NetworkFacade networkFacade = new NetworkFacade(); 
		
		// IP Addressen holen 
		try {
			networkFacade.startSearchRoutine(this.searchFileName, IPList.getInstance().getIPList());
		} catch (NetworkFacadeException e) {
			DDLogger.getLogger().createLog(e.getMessage(), DDLogger.ERROR); 
		} 	
	}

}

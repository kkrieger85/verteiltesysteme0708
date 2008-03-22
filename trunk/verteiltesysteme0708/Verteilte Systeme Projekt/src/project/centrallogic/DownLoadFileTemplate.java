/**
 * 
 */
package project.centrallogic;

import project.helperclasses.DDLogger;
import project.network.NetworkFacade;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class DownLoadFileTemplate extends AbstractCentralLogic implements
		Runnable {

	private String filename, ipaddress, port;
	
	public DownLoadFileTemplate(String filename, String ipaddress, String port){
		this.filename = filename; 
		this.ipaddress = ipaddress; 
		this.port = port; 
		Thread thrd = new Thread(this); 
		thrd.start(); 
	}
	
	@Override
	public void run() {
		// Fassade laden 
		NetworkFacade nf = new NetworkFacade(); 
		try {
			nf.downloadFileFromServer(this.filename, this.ipaddress, this.port);
		} catch (Exception ex) {
			DDLogger.getLogger().createLog(ex.getMessage(),DDLogger.ERROR); 
		}
		
		
	}

}

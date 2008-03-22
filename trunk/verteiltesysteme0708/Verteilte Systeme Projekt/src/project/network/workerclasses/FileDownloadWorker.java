/**
 * 
 */
package project.network.workerclasses;

import java.rmi.Naming;
import java.util.HashMap;

import project.helperclasses.DDLogger;
import project.network.RMINetworkInterface;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class FileDownloadWorker extends AbstractThreadWorker {

	/**
	 * @param address
	 * @param port
	 */
	public FileDownloadWorker(String address, String port) {
		super(address, port);
	}

	/**
	 * @param address
	 * @param port
	 * @param informationHash
	 */
	public FileDownloadWorker(String address, String port,
			HashMap<String, Object> informationHash) {
		super(address, port, informationHash);
	}
	
	public synchronized boolean start() {
		try {
		RMINetworkInterface intf = (RMINetworkInterface) Naming.lookup("rmi://"+this.serverAddress +"/server");		
		this.resultObject = intf.getFile(this.informationHash.get("filename").toString()); 
		if (this.resultObject != null)
			return true; 
		else return false; 
		} catch (Exception e) {
			DDLogger.getLogger().createLog(e.getMessage(), DDLogger.ERROR); 
			return false; 
		}
	}
}

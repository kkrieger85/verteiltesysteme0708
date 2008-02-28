/**
 * 
 */
package project.network.workerclasses;

import java.rmi.Naming;
import project.helperclasses.*;

import project.network.IPList;
import project.network.RMINetworkInterface;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class PushIPListWorker extends AbstractThreadWorker {

	/**
	 * @param address
	 * @param port
	 */
	public PushIPListWorker(String address, String port) {
		super(address, port);
	}

	/* (non-Javadoc)
	 * @see project.network.workerclasses.AbstractThreadWorker#start()
	 */
	@Override
	public synchronized boolean start() {
		try {
		RMINetworkInterface intf = (RMINetworkInterface) Naming.lookup("rmi://"+this.serverAddress +"/server");
		IPList iplist = IPList.getInstance(); 		
		this.resultObject = intf.pushIPList(iplist.getIPList()); 
		if (this.resultObject != null)
			return true; 
		else return false; 
		} catch (Exception e) {
			DDLogger.getLogger().createLog(e.getMessage(), DDLogger.WARN); 
			return false; 
		}
}
	
}

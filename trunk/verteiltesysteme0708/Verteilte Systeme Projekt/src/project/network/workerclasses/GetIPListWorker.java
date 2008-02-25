/**
 * 
 */
package project.network.workerclasses;

import java.rmi.Naming;

import project.network.RMINetworkInterface;

import konzept.spielwiese.RMITestInterface;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class GetIPListWorker extends AbstractThreadWorker {

	/**
	 * @param address
	 * @param port
	 */
	public GetIPListWorker(String address, String port) {
		super(address, port);
	}

	/* (non-Javadoc)
	 * @see project.network.workerclasses.AbstractThreadWorker#start()
	 */
	@Override
	public synchronized boolean start() {
	try {
		RMINetworkInterface intf = (RMINetworkInterface) Naming.lookup("rmi://"+this.serverAddress +"/server");
		this.resultObject = intf.getIPList(); 
		if (this.resultObject != null)
			return true; 
		else return false; 
		} catch (Exception e) {
			return false; 
		}
	}
}

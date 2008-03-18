/**
 * 
 */
package project.network.workerclasses;

import java.util.HashMap;


/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public abstract class AbstractThreadWorker {

	@SuppressWarnings("unused")
	protected String serverPort; 
	@SuppressWarnings("unused")
	protected String serverAddress; 
	protected Object resultObject; 
	protected HashMap<String,Object> informationHash; 
	/**
	 * Standardstart Methode, gibt true zurück wenn alles geklappt hat!!
	 * @return
	 */
	public synchronized boolean start(){
		return false; 
	}; 
	
	/**
	 * Dient als Standardkonstruktor bei allen abgeleitenden Klassen, diese Müssen 
	 * nämlich alle Server und Port implementieren 
	 * Zudem gibt es auch noch ein resultObject. 
	 * @param address	Serveradresse 
	 * @param port		Serverport 
	 */
	public AbstractThreadWorker(String address, String port){
		this.serverAddress = address; 
		this.serverPort = port; 
		this.resultObject = null; 
	}
	
	/**
	 * Konstruktor der zusätzlich eine Hashmap enthält !!
	 * @param address
	 * @param port
	 * @param informationHash
	 */
	public AbstractThreadWorker(String address, String port, HashMap<String,Object> informationHash){
		this(address, port); 
		this.informationHash = informationHash; 
	}

	/**
	 * @return the resultObject
	 */
	public synchronized Object getResultObject() {
		return resultObject;
	}
	
}

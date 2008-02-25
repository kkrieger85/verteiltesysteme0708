/**
 * 
 */
package project.network;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public abstract class AbstractThreadWorker {

	@SuppressWarnings("unused")
	private String serverPort; 
	@SuppressWarnings("unused")
	private String serverAddress; 
	private Object resultObject; 
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
	 * @return the resultObject
	 */
	public synchronized Object getResultObject() {
		return resultObject;
	}
	
}

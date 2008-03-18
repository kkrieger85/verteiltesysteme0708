/**
 * 
 */
package project.network;

import java.io.Serializable;
import java.util.Date;

import project.exception.ServerDataObjectException;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *	Implementiert einen ServerContainer, hierdrin stehen Name und Port 
 *	Evtl. wird es noch weitere Informationen geben 
 */
public class ServerDataObject implements Serializable {

	/**
	 * serialUID 
	 */
	private static final long serialVersionUID = 3678580298868835682L;

	private String address; 
	private String port; 
	private boolean active; 
	private Date latestActivity; 
	private long freespace; 
	
	
	/**
	 * Standardkonstruktor der Adresse und Port benötigt 
	 * @param address
	 * @param port
	 * @throws ServerDataObjectException 
	 */
	public ServerDataObject(String address, String port, long freespace) throws ServerDataObjectException {
		if (address == null)
			throw new ServerDataObjectException(ServerDataObjectException.NOADDRESS); 
		
		if (port == null)
			throw new ServerDataObjectException(ServerDataObjectException.NOPORT); 
		
		
		this.address = address; 
		this.port = port; 
		this.active = true; 
		this.latestActivity = new Date(); 
		this.freespace = freespace; 
	}


	/**
	 * @return the address
	 */
	public synchronized String getAddress() {
		return address;
	}


	/**
	 * @param address the address to set
	 */
	public synchronized void setAddress(String address) {
		this.address = address;
	}


	/**
	 * @return the port
	 */
	public synchronized String getPort() {
		return port;
	}


	/**
	 * @param port the port to set
	 */
	public synchronized void setPort(String port) {
		this.port = port;
	}


	/**
	 * @return the active
	 */
	public synchronized boolean isActive() {
		return active;
	}


	/**
	 * @param active the active to set
	 */
	public synchronized void setActive(boolean active) {
		this.active = active;
	}


	/**
	 * @return the latestActivity
	 */
	public synchronized Date getLatestActivity() {
		return latestActivity;
	}


	/**
	 * @param latestActivity the latestActivity to set
	 */
	public synchronized void setLatestActivity() {
		this.latestActivity = new Date();
	}


	/**
	 * @return the serialVersionUID
	 */
	public static synchronized long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	/** 
	 * Ganz normale toStringmethode zum Testen des Inhalts
	 * gibt halt einen String zurück. 
	 */
	public String toString(){
		return "Object: " + this.address + " | " + this.port + " | " + this.latestActivity.toString();  
	}


	/**
	 * @return the freespace
	 */
	public synchronized long getFreespace() {
		return freespace;
	}


	/**
	 * @param freespace the freespace to set
	 */
	public synchronized void setFreespace(long freespace) {
		this.freespace = freespace;
	}
	

}

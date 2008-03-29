/**
 * 
 */
package project.centrallogic;

import project.data.dataSendingWrapper;
import project.helperclasses.*;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class FileRequestTemplate extends AbstractCentralLogic implements
	Runnable {

	private String filename;
	private String username; 
	private dataSendingWrapper dsw; 
	
	/**
	 * Konstruktor der Klasse FileRequestTemplate 
	 * Baut einen Thread und startet diesen direkt, also sich selbst !!! 
	 */
	public FileRequestTemplate(String filename, String username) {
		this.filename = filename; 
		this.username = username; 
		Thread thrd = new Thread(this); 
		thrd.start(); 
	}

	@Override
	public void run() {
		
		if (OptionSingleton.isAutservermode()){
			// TODO an dieser Stelle Rechtemanagement einfügen !!! 
		}
		
		dsw = new dataSendingWrapper(filename);
		
	}

	/**
	 * @return the dsw
	 */
	public synchronized dataSendingWrapper getDsw() {
		return dsw;
	}

}

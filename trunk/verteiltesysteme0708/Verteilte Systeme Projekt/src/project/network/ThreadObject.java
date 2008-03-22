/**
 * 
 */
package project.network;

import java.util.Date;
import java.util.HashMap;
import java.util.Observable;

import project.exception.ThreadObjectException;
import project.helperclasses.DDLogger;
import project.network.workerclasses.*;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 *
 * Threadobject das später die Anfrage an den Server und so stellt !!! 
 * Beinhaltet 
 * 		IP Adresse des Servers  *
 *		Port des Servers     	*
 *		Anfragetyp         		* 
 *		Anzahl Versuche        	*
 *		Zeit 					*
 *		ID 				
 *	TODO Unique ID aufbauen !!! 		
 */
public class ThreadObject extends Observable implements Runnable, Comparable<Object> {

	
	public static final int THREADTEST = 0; 
	public static final int GETIPLISTACTION = 1; 
	public static final int PUSHIPLISTACTION = 2; 
	public static final int SEARCHFILE = 3;
	public static final int DOWNLOADFILE = 4; 
	// TODO weitere Typen festlegen !!! 
	
	private static int MAXTRIALS = 5; 
	
	private int type;  
	private String serverPort; 
	private String serverAddress; 
	private Date startingTime; 
	private Date endingTime; 
	private boolean failure; 
	private boolean maxtrials; 
	private int trials; 
	private String ident; 
	
	// Weitere Informationen für das Threadobjekt 
	private HashMap<String,Object> informationHash; 
	
	private AbstractThreadWorker threadWorkerInterf; 
	/**
	 * Standardkonstruktor ohne Parameter 
	 * Ist private damit man es nicht so anlegen kann 
	 */	
	private ThreadObject() {
		this.trials = 0; 
		this.failure = false; 
		this.maxtrials = false; 	
	}
	
	/**
	 * Legt ein Threadobjekt an 
	 * @param address	IPAdresse 
	 * @param port		Port des Servers 
	 * @param type		Typ der Anfrage 
	 * @throws ThreadObjectException
	 */
	public ThreadObject(String address, String port, int type) 
			throws ThreadObjectException {
		this(); 
		if (address == null)
			throw new ThreadObjectException(ThreadObjectException.NOADDRESS); 

		if (port == null)
			throw new ThreadObjectException(ThreadObjectException.NOPORT); 
	
		
		// TODO type noch abfragen 
		this.serverAddress = address; 
		this.serverPort = port; 
		this.type = type; 	
		
		this.refreshIdent(); 
	}
	
	/**
	 * Erweiterter Konstruktor mit dem eine zusätzliche HashMap übergeben wird 
	 * Dient dazu weitere Informationen zu übergeben, Struktur kennt dann der ThreadWorker 
	 * selber. 
	 * @param address
	 * @param port
	 * @param type
	 * @param informationHash
	 * @throws ThreadObjectException
	 */
	public ThreadObject(String address, String port, int type, HashMap<String,Object> informationHash) 
			throws ThreadObjectException {  
		this(address, port, type); 
		if (informationHash == null)
			throw new ThreadObjectException(ThreadObjectException.NOINFOHASH); 
		this.informationHash = informationHash; 
	}
	

	/** (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// Startzeit speichern 
		this.startingTime = new Date(); 
		// Logger einbinden 
		DDLogger ddl = DDLogger.getLogger(); 
		// ddl.createLog("Thread started", DDLogger.DEBUG); 
		
		// Thread beginnen 
		/* Runable kann nicht überladen werden mit eigenen Exceptions !!! 
		 * 
		 */
		try {
			if (this.trials > ThreadObject.MAXTRIALS)
				throw new ThreadObjectException(ThreadObjectException.MAXTRIALS);
			ddl.createLog(this.ident + " : Thread working !!!", DDLogger.DEBUG); 
			
			if (this.threadWorkerInterf == null){
				switch (this.type){
				case ThreadObject.THREADTEST: this.threadWorkerInterf = new ThreadTestObject(this.serverAddress, this.serverPort, this.informationHash); break; 
				case ThreadObject.GETIPLISTACTION: this.threadWorkerInterf = new GetIPListWorker(this.serverAddress, this.serverPort); break;
				case ThreadObject.PUSHIPLISTACTION: this.threadWorkerInterf = new PushIPListWorker(this.serverAddress, this.serverPort); break;
				case ThreadObject.SEARCHFILE: this.threadWorkerInterf = new SearchFileWorker(this.serverAddress, this.serverPort,this.informationHash);break; 
				case ThreadObject.DOWNLOADFILE: this.threadWorkerInterf = new FileDownloadWorker(this.serverAddress,this.serverPort, this.informationHash); break; // TODO Einbauen !!! 
				
				default: throw new ThreadObjectException(ThreadObjectException.NOTYPE); 
				}
				
			}
			this.failure = false; 
			if (!((AbstractThreadWorker)this.threadWorkerInterf).start()){
				throw new ThreadObjectException(ThreadObjectException.THREADFAILED); 
			}
			
			
		} catch (ThreadObjectException ex)
		{
			if (ex.getMessage().equals((ThreadObjectException.MAXTRIALS))){
				this.maxtrials = true; 				
			}
			this.failure = true; 
			ddl.createLog(this.ident + " : " + ex.getMessage(), DDLogger.ERROR);
			
		} finally {
			synchronized (this) {
				countTrials(); 
				this.endingTime = new Date(); 
				ddl.createLog("Thread ended", DDLogger.DEBUG); 
				this.setChanged(); 
				this.notifyObservers(); 
				refreshIdent(); 
			}
		}		
	}

	/**
	 * @return the failure
	 */
	public synchronized boolean isFailure() {
		return this.failure;
	}

	/**
	 * @return the trials
	 */
	public synchronized int getTrials() {
		return this.trials;
	}
	
	/**
	 * Gibt die Zeitdifferenz zurück !!!
	 */
	public synchronized int getRunningTime(){
		int runningtime; 
		runningtime = this.endingTime.compareTo(this.startingTime); 
		return runningtime; 
	}
	
	/**
	 * Zählt die Fehlversuche hoch!!
	 * Setzt zudem die Variable falls zu viele Versuche waren / sind !! 
	 */
	private  synchronized void countTrials(){
		this.trials++; 
		if (this.trials >= ThreadObject.MAXTRIALS){
			this.maxtrials = true; 
		}
	}

	/**
	 * @return the maxtrials
	 */
	public synchronized boolean isMaxtrials() {
		return maxtrials;
	}
	
	/**
	 * Aktuallisiert einfach nur die Ident !! 
	 * Das Ding ändert sich halt durch jeden neuen Versuch !!! 
	 */
	public void refreshIdent(){
		this.ident = this.serverAddress + "_" + this.serverPort + "_" + this.type + "_" + this.trials; 		
	}

	/**
	 * @return the ident
	 */
	public synchronized String getIdent() {
		return ident;
	}

	/**
	 * @return the type
	 */
	public synchronized int getType() {
		return type;
	}

	/**
	 * @return the serverPort
	 */
	public synchronized String getServerPort() {
		return serverPort;
	}

	/**
	 * @return the serverAddress
	 */
	public synchronized String getServerAddress() {
		return serverAddress;
	}
	
	/**
	 * Übergibt das resultObject
	 * @return
	 */
	public synchronized Object getResultObject(){
		return this.threadWorkerInterf.getResultObject(); 		
	}
	
	public String toString(){
		return this.ident; 
	}

	@Override
	public int compareTo(Object arg0) {
		int returnValue = 1; 
		if (this.type == ((ThreadObject)arg0).type)
			returnValue = 0; 
		else returnValue = 1; 
		
		if (this.serverAddress.compareTo(((ThreadObject)arg0).serverAddress) == 0)
			returnValue = 0; 
		else returnValue = 1; 
		
		if (this.serverPort.compareTo(((ThreadObject)arg0).serverPort) == 0)
			returnValue = 0; 
		else returnValue = 1; 
		
		return returnValue;
	}
}

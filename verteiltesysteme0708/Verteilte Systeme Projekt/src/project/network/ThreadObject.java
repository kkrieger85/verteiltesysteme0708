/**
 * 
 */
package project.network;

import java.util.Date;
import java.util.Observable;

import project.exception.ThreadObjectException;
import project.helperclasses.DDLogger;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 *
 * Threadobject das sp�ter die Anfrage an den Server und so stellt !!! 
 * Beinhaltet 
 * 		IP Adresse des Servers  *
 *		Port des Servers     	*
 *		Anfragetyp         		* 
 *		Anzahl Versuche        	*
 *		Zeit 					*
 *		ID 				
 *	TODO Unique ID aufbauen !!! 		
 */
public class ThreadObject extends Observable implements Runnable {

	
	public static final int THREADTEST = 0; 
	// TODO weitere Typen festlegen !!! 
	
	private static int MAXTRIALS = 10; 
	
	private int type;  
	private String serverPort; 
	private String serverAddress; 
	private Date startingTime; 
	private Date endingTime; 
	private boolean failure; 
	private boolean maxtrials; 
	private int trials; 
	private String ident; 
	
	
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
		/* Runable kann nicht �berladen werden mit eigenen Exceptions !!! 
		 * 
		 */
		try {
			if (this.trials > ThreadObject.MAXTRIALS)
				throw new ThreadObjectException(ThreadObjectException.MAXTRIALS);
			ddl.createLog(this.ident + " : Thread working !!!", DDLogger.DEBUG); 
			
			if (this.threadWorkerInterf == null){
				switch (this.type){
				case ThreadObject.THREADTEST: this.threadWorkerInterf = new ThreadTestObject(this.serverAddress, this.serverPort); break; 
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
				// ddl.createLog("Thread ended", DDLogger.DEBUG); 
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
	 * Gibt die Zeitdifferenz zur�ck !!!
	 */
	public synchronized int getRunningTime(){
		int runningtime; 
		runningtime = this.endingTime.compareTo(this.startingTime); 
		return runningtime; 
	}
	
	/**
	 * Z�hlt die Fehlversuche hoch!!
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
	 * Das Ding �ndert sich halt durch jeden neuen Versuch !!! 
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
}
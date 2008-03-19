/**
 * 
 */
package project.network;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import project.helperclasses.*;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *	
 *	Diese Observerklasse dient dem Überwachen der einzelnen laufenden Threads.
 *	Jedesmal wenn ein Thread fertig ist wird er sich beim Observer melden. Dieser kann
 *	dann das Ergebnis weiterleiten und entsprechend neue Threads starten / kontrollieren
 *	Der Observer besitzt die beiden Listen in denen die Threads drin stehen. 
 *	Der Observer selber ist eine Singleton Klasse, damit er nur ein einziges mal ausgeführt wird. 
 */
public class ThreadObserver implements Observer {
	
	// Anzahl Maximal gestarteter Threads 
	private static int MAXTHREADS = 10; 
	// Singleton Umsetzung 
	private static ThreadObserver observer = new ThreadObserver();
	
	/// Implementierung der beiden Listen:
	private ArrayList<ThreadObject> runningQueue = new ArrayList<ThreadObject>(); 
	private ArrayList<ThreadObject> unfinnishedQueue = new ArrayList<ThreadObject>(); 
	
	
	
	/**
	 * Konstruktor ist Private damit man die Klasse nicht mehrfach erstellt 
	 */
	private ThreadObserver() {		
		// Mache nichts ;) 
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 * Überladene Funktion mit deren Hilfe sich die einzelnen Threads beim 
	 * Observer melden können. 
	 */
	@Override
	public synchronized void  update(Observable arg0, Object arg1) {
		// Threadobjekt holen, hierbei weis man das es ein Threadobjekt ist 
		// TODO evtl. doch noch Abfrage rein obs wirklich ein ThreadObject ist 
		ThreadObject obj = (ThreadObject) arg0; 
		// Logger bereit machen 
		DDLogger ddl = DDLogger.getLogger(); 
		
		
		// Abfrage ob das Objekt das den Observer aufgerufen hat auch wirklich gelöscht
		// wurde 
		int removevalue = this.runningQueue.lastIndexOf(obj); 
		if (removevalue != -1) {
			this.runningQueue.remove(removevalue); 
			ddl.createLog("Obj aus Liste gelöscht!! " , DDLogger.DEBUG); 
			ddl.createLog("Liste:  " + runningQueue.toString(), DDLogger.DEBUG); 
			
			
		} else {
			ddl.createLog("Obj nicht aus Liste gelöscht!!", DDLogger.DEBUG); 
		}
		
		// Abfrage ob die runningQueue leer ist und noch Threads aufnehmen kann 
		// Falls ja wird die Funktion startNewThread aufgerufen. 
		if (this.runningQueue.size() < ThreadObserver.MAXTHREADS){
			this.startNewThread(); 
		}
		
		if (obj.isFailure()){
			if (!obj.isMaxtrials()){
				try {		
					// Threadobjekt falls es nochmal probiert werden muss in die 
					// unfinnished Queue stecken 
					this.addThread(obj); 
				} catch (Exception ex) {
					ddl.createLog(ex.getMessage(), DDLogger.ERROR); 
				}		
			} 
		} else {
			// Falls alles geklappt hat mache hier weiter 
			// ddl.createLog(obj.getIdent() + " : " +"Thread ist fertig!!!", DDLogger.INFO); 
			// TODO Behandlung von fertigen Threads einbauen 
			ThreadResultProcessing processing = new ThreadResultProcessing(obj.getServerAddress(),obj.getServerPort(),obj.getType(), obj.getResultObject());
			Thread thread = new Thread(processing); 
			thread.start(); 
		}
	}
	
	/** 
	 * Singletonimplementierung der Observerklasse 
	 * @return ThreadObserver 
	 */
	public static ThreadObserver getInstance(){
		return ThreadObserver.observer; 
	}
	
	/** 
	 * Hinzufügen eines Threads in die entsprechende Queue
	 * In die unfinnished falls die maximale Anzahl Threads läuft 
	 * falls nicht in die runningQueue 
	 * @param tobj 	Das zu bearbeitende Threadobjekt wird hier übergeben 
	 */
	public void addThread(ThreadObject tobj){
		// TODO ThreadObj auf Null überprüfen !!! 
		
		if (this.runningQueue.size() >= ThreadObserver.MAXTHREADS){
			this.unfinnishedQueue.add(tobj);
			 DDLogger ddl = DDLogger.getLogger(); 
			 ddl.createLog("Queue ist Voll!!!", DDLogger.INFO); 
		} else {
			startThread(tobj); 
			this.runningQueue.add(tobj); 
		}
	}
	
	/**
	 * Thread starten 
	 * @param tobj Der zu startende Thread 
	 */
	private synchronized void startThread(ThreadObject tobj){
		Thread thread = new Thread(tobj); 
		thread.start(); 
	}
	
	/** 
	 * Nimmt einen neuen Thread aus der Queue und startet diesen 
	 * Tritt ein wenn ein anderer Thread fertig wurde!! 
	 */
	private synchronized void startNewThread(){
		if (this.unfinnishedQueue.size() > 0){
			ThreadObject tobj = this.unfinnishedQueue.get(0); 
			this.startThread(tobj); 
			this.runningQueue.add(tobj); 
		}
	}
	

}

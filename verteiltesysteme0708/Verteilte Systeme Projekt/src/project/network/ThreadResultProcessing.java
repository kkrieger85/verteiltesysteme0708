/**
 * 
 */
package project.network;

import java.util.LinkedList;

import project.centrallogic.FileSearchResultTemplate;
import project.helperclasses.DDLogger;
import project.data.dataSendingWrapper;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *	Diese Klasse hier dient dazu die fertigen Threads zu bearbeiten 
 *	Hierbei wird das ResultObject benutzt! 
 */
public class ThreadResultProcessing implements Runnable{
	
	@SuppressWarnings("unused")
	private String serverAddress; 
	@SuppressWarnings("unused")
	private String serverPort;
	private int type;
	private Object resultObject; 
	
	
	/**
	 * Im Konstruktor wird schon entschieden was passieren soll !!! 
	 * @param serverAddress
	 * @param serverPort
	 * @param type
	 * @param resultObject
	 */
	public ThreadResultProcessing(String serverAddress, String serverPort, int type, Object resultObject){
		// TODO Fehlerbehandlung hier rein !!! 
				
		this.serverAddress = serverAddress; 
		this.serverPort = serverPort; 
		this.type = type; 
		this.resultObject = resultObject; 
	}
	
	/** 
	 * Verarbeitung wenn eine IP Liste angefordert wurde !!! 
	 */
	@SuppressWarnings("unchecked")
	public void getIPListActionHandler(){
		DDLogger ddl = DDLogger.getLogger(); 
		ddl.createLog("Sichere IP Liste!!", DDLogger.DEBUG);
		IPList iplist = IPList.getInstance(); 
		try {
			iplist.setIPList((LinkedList<ServerDataObject>)this.resultObject); 
		} catch (Exception ex) {
			ddl.createLog("IPListe konnte nicht abgerufen werden!", DDLogger.ERROR);
		}
	}

	@Override
	/** 
	 * Thread wird gestartet und leitet die verarbeitung in die Wege !!! 
	 */
	public void run() {
		DDLogger.getLogger().createLog("Verarbeitung gestartet!!", DDLogger.DEBUG);
		switch (type){
		case ThreadObject.GETIPLISTACTION: this.getIPListActionHandler(); break; 
		case ThreadObject.SEARCHFILE: this.searchFileActionHandler(); break; 
		case ThreadObject.DOWNLOADFILE: this.downloadFileActionHandler(); break; 
		case ThreadObject.PUSHIPLISTACTION: break; 
		default: break; 
		}	
	}
	
	@SuppressWarnings("unchecked")
	public void searchFileActionHandler(){
		DDLogger ddl = DDLogger.getLogger(); 
		ddl.createLog("Logge gefundene Dateien!!", DDLogger.DEBUG);
		ddl.createLog(((LinkedList<SearchResult>)this.resultObject).toString(),DDLogger.DEBUG);
		@SuppressWarnings("unused")
		FileSearchResultTemplate fsrt = new FileSearchResultTemplate((LinkedList<SearchResult>)this.resultObject); 
	}
	
	/**
	 * Tritt ein wenn type nicht definiert wurde !!! 
	 */
	public void defaultCase(){
		DDLogger ddl = DDLogger.getLogger(); 
		ddl.createLog("Type wurde nicht gefunden!!", DDLogger.DEBUG);
	}
	
	/**
	 * Bearbeitet das Ergebnis also speichert die Datei auf dem Rechner ab!!
	 */
	public void downloadFileActionHandler(){
		DDLogger ddl = DDLogger.getLogger(); 
		ddl.createLog("Sichere Datei", DDLogger.DEBUG);
		// Object casten !!! 
		try {
			((dataSendingWrapper)this.resultObject).saveToFile(); 
		} catch (Exception ex){
			ddl.createLog("Cannot cast Object!!", DDLogger.ERROR); 
		}
	}
}

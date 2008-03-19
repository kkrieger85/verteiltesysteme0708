/**
 * 
 */
package project.helperclasses;

import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;

import org.apache.log4j.*; 

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class DDLogger {

	private static Logger log = Logger.getRootLogger();
	private static DDLogger ddlogger; 
	public static Level ALL = Level.ALL; 
	public static Level DEBUG = Level.DEBUG; 
	public static Level INFO = Level.INFO; 
	public static Level WARN = Level.WARN; 
	public static Level ERROR = Level.ERROR; 
	public static Level FATAL = Level.FATAL; 
	public static Level OFF = Level.OFF; 
		
	/**
	 * C'tor 
	 * Vereinfachter Konstruktor mit Standardeinstellungen!! 
	 */
	public DDLogger(){
		this(null, null); 	
	}
	
	/**
	 * C'tor 
	 * Konstruktor zum explizieten Anlegen des Loglevels 
	 * @param lvl Level der geloggt werden soll 
	 */
	public DDLogger(Level lvl){
		this(lvl, null);
	}
	
	/**
	 * C'tor 
	 * Klasse mit einer bestimmten Logdatei anlegen 
	 * @param logfile String 
	 */
	public DDLogger(String logfile){
		this(null,logfile); 
	}
	
	/**
	 * C'tor 
	 * Dient zum Einstellen des Loggers, es gibt ne Standardeinstellung !!! 
	 * @param lvl Der Level des Loggers 
	 * @param logfile Die Datei in die geschrieben werden soll 
	 */
	public DDLogger(Level lvl, String logfile){

		if (ddlogger == null)
		{
			/// Logger Bekommt als Standardeinstellung ALL falls nichts übergeben wurde 
			if (lvl == null){
				lvl = ALL; 
			}
			// Logfile kann angegeben werden Muss aber nicht !!! 	
			if (logfile == null){

				try {
					XMLConfigHelper xmlconf = new XMLConfigHelper(); 
					logfile = xmlconf.getLogfile(); 
				} catch (Exception exc) {
					XMLConfigHelper xmlconf = new XMLConfigHelper(); 
					logfile = "default.log"; 
					xmlconf.saveAttribut(XMLConfigHelper.LOGFILE, logfile); 
				}
				
			}
		    try {
		    	// Alle Appender vorher Löschen!!! Es könnten sonst noch welche drin sein die dort nicht sein sollen. 
		    	log.removeAllAppenders(); 
		    	PatternLayout layout = new PatternLayout( "%d{ISO8601} %-5p [%t] %c: %m%n" );     
			    // Es wird immer der ConsolenAppender und der Dateiappender benutzt !!! Bei Bedarf erweiterbar !!
			    ConsoleAppender consoleAppender = new ConsoleAppender( layout );
			    log.addAppender( consoleAppender );
			    FileAppender fileAppender = new FileAppender( layout, logfile, true );
			    log.addAppender( fileAppender );
			    // ALL | DEBUG | INFO | WARN | ERROR | FATAL | OFF:
			    log.setLevel( lvl );
			  } catch( Exception ex ) {
			    System.out.println( ex );
			  }	    
			  ddlogger = this;  
		}
	}
	
	/**
	 * Dient zum Erstellen eines Logeintrages 
	 * Der Level kann mit DDLogger.* angegeben werden!! 
	 * @param msg Die Nachricht als String 
	 * @param lvl 	Den level 
	 * @return Boolean obs geklappt hat 
	 */
	public boolean createLog(String msg, Level lvl){		
		if (msg == null){
			msg = "No Message!!!"; 
		}		
		if (lvl.toInt() == DDLogger.ALL.toInt()){
			DDLogger.log.debug(msg); 
		}
		if (lvl.toInt() == DDLogger.DEBUG.toInt()){
			DDLogger.log.debug(msg); 
		}		
		if (lvl.toInt() == DDLogger.INFO.toInt()){
			DDLogger.log.info(msg); 
		}	
		if (lvl.toInt() == DDLogger.WARN.toInt()){
			DDLogger.log.warn(msg); 
		}		
		if (lvl.toInt() == DDLogger.ERROR.toInt()){
			DDLogger.log.error(msg); 
		}		
		if (lvl.toInt() == DDLogger.FATAL.toInt()){
			DDLogger.log.fatal(msg); 
		}			
		return true; 
	}
	
	/**
	 * Halbe Singleton Implementation 
	 * @return  den DDLogger 
	 */
	public synchronized static DDLogger getLogger(){
		return DDLogger.ddlogger;
	}
	
	/**
	 * Die Methode requestLog sendet den Verlauf an die GUI um diesen
	 * anzuzeigen.
	 * 
	 * @return String der das Log enthält.
	 */
	public static String requestLog() {
		String returnString = ""; 
		try {
			
			XMLConfigHelper xmlconf = new XMLConfigHelper(); 
			String logfile = xmlconf.getLogfile(); 
						


		    RandomAccessFile f = new RandomAccessFile(logfile, "r" ); 
		 
		    for ( String line; (line=f.readLine()) != null; ) 
		       returnString += line; 
		
		    f.close(); 
			
		} catch (Exception ex) {
		}
		return returnString;
	}
	
	
	/**
	 * Methode zum zurücksetzen des Logs.
	 */	
	public static void cleanLog(){
		try {
			
			XMLConfigHelper xmlconf = new XMLConfigHelper(); 
			String logfile = xmlconf.getLogfile(); 
			
		    RandomAccessFile f = new RandomAccessFile(logfile, "rw" ); 
		    f.setLength(0);  
		    f.close(); 
			
		} catch (Exception ex) {
			DDLogger.getLogger().createLog(ex.getMessage(), DDLogger.ERROR); 

		}
	}
	
}

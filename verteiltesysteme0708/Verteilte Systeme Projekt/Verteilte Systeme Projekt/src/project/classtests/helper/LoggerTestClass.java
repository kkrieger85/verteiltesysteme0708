/**
 * 
 */
package project.classtests.helper;
import project.helperclasses.DDLogger;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class LoggerTestClass {

	public DDLogger ddl; 
	/**
	 * C'tor 
	 */
	public LoggerTestClass() {
	}
	
	/** 
	 * Tests für den Logger 
	 */
	public void automatisierterTest(){
		// Test mit Standardkonstruktor 
		System.out.println("Test mit Standard"); 
		ddl = new DDLogger(); 
		this.createLogs(); 
		// Debug mode !! 
		System.out.println("----------DEBUG----------"); 
		ddl = new DDLogger(DDLogger.DEBUG); 
		this.createLogs(); 
		System.out.println("----------INFO----------"); 		
		ddl = new DDLogger(DDLogger.INFO); 
		this.createLogs(); 
		System.out.println("----------WARN----------"); 		
		ddl = new DDLogger(DDLogger.WARN); 
		this.createLogs(); 
		System.out.println("----------ERROR----------"); 		
		ddl = new DDLogger(DDLogger.ERROR); 
		this.createLogs(); 
		System.out.println("----------FATAL----------"); 		
		ddl = new DDLogger(DDLogger.FATAL); 
		this.createLogs(); 
	}
	
	/*
	 * Einfache Logausgaben generieren !!! 
	 */
	public void createLogs(){
		ddl.createLog("Alles", DDLogger.ALL); 
		ddl.createLog("Info", DDLogger.INFO); 
		ddl.createLog("Debug", DDLogger.DEBUG); 
		ddl.createLog("Warn", DDLogger.WARN); 
		ddl.createLog("Error", DDLogger.ERROR); 
		ddl.createLog("Fatal", DDLogger.FATAL); 
	}

}

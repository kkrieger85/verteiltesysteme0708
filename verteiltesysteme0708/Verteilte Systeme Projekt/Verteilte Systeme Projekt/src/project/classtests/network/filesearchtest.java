/**
 * 
 */
package project.classtests.network;

import project.helperclasses.DDLogger;
import project.network.serverclasses.FileSearch;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class filesearchtest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		DDLogger ddl = new DDLogger(DDLogger.ALL); 
		FileSearch fs = new FileSearch(); 
		fs.searchFiles("*"); 
		DDLogger.getLogger().createLog(fs.toString(), DDLogger.DEBUG); 
		
		
		fs = new FileSearch(); 
		fs.searchFiles("*a*"); 
		DDLogger.getLogger().createLog(fs.toString(), DDLogger.DEBUG); 
		
		fs = new FileSearch(); 
		fs.searchFiles("*eu*d?k*"); 
		DDLogger.getLogger().createLog(fs.toString(), DDLogger.DEBUG); 

	}

}

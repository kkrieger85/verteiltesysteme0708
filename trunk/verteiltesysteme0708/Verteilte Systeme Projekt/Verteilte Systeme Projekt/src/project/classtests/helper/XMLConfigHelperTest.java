/**
 * 
 */
package project.classtests.helper;

import project.helperclasses.DDLogger;
import project.helperclasses.XMLConfigHelper;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class XMLConfigHelperTest {

	/**
	 * Paar Testfälle und so halt
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		DDLogger ddl = new DDLogger(DDLogger.ALL); 
		
		XMLConfigHelper xmlch = new XMLConfigHelper(); 
		
		try {
			// Test 1 
			String searchString = "authserver"; 
			ddl.createLog("Test 1: Attribut vorhanden !!! Suchstring = " + searchString , DDLogger.DEBUG); 
			String result = ""; 
			
			result = xmlch.searchAttribut(searchString); 
			ddl.createLog("Test 1: Ergebnis: " + result, DDLogger.DEBUG); 
			result = ""; 
			
			// Test 2 
			searchString = "nichtda"; 
			ddl.createLog("Test 2: Attribut nicht vorhanden !!! Suchstring = " + searchString , DDLogger.DEBUG); 
			result = ""; 
			
			result = xmlch.searchAttribut(searchString); 
			ddl.createLog("Test 2: Ergebnis: " + result, DDLogger.DEBUG); 
			result = ""; 
			

			
			
		} catch (Exception ex) {
			ddl.createLog("Exception " + ex.getMessage(), DDLogger.DEBUG); 
		}
		try {
			// Schreibtest 
			
			// Test 3 
			String searchString = "Blubb123"; 
			String value = "Testwert !!!"; 
			ddl.createLog("Test 3: Attribut nicht vorhanden !!! Suchstring = " + searchString , DDLogger.DEBUG); 
			
			
			Boolean result2 = xmlch.saveAttribut(searchString, value); 
			ddl.createLog("Test 3: Ergebnis: " + result2, DDLogger.DEBUG); 
			
			
		} catch (Exception ex) {
			ddl.createLog("Exception " + ex.getMessage(), DDLogger.DEBUG); 
		}
		
		
		try {
			// Schreibtest 
			
			// Test 4 
			String searchString = "Blubb123"; 
			String value = "kein Testwert mehr !!!"; 
			ddl.createLog("Test 4: Attribut nicht vorhanden !!! Suchstring = " + searchString , DDLogger.DEBUG); 
			
			
			Boolean result2 = xmlch.saveAttribut(searchString, value); 
			ddl.createLog("Test 4: Ergebnis: " + result2, DDLogger.DEBUG); 
			
			
		} catch (Exception ex) {
			ddl.createLog("Exception " + ex.getMessage(), DDLogger.DEBUG); 
		}
		
		try {
			// Schreibtest 
			
			// Test 5 
			String searchString = "Nichtda"; 
			String value = "Testwert2342"; 
			ddl.createLog("Test 5: Attribut nicht vorhanden !!! Suchstring = " + searchString , DDLogger.DEBUG); 
			
			
			Boolean result2 = xmlch.saveAttribut(searchString, value); 
			ddl.createLog("Test 5: Ergebnis: " + result2, DDLogger.DEBUG); 
			
			
		} catch (Exception ex) {
			ddl.createLog("Exception " + ex.getMessage(), DDLogger.DEBUG); 
		}
	}

}

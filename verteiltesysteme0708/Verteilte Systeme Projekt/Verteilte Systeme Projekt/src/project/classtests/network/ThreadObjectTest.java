/**
 * 
 */
package project.classtests.network;

import project.helperclasses.DDLogger;
import project.network.ThreadObject;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class ThreadObjectTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Logger init 
		@SuppressWarnings("unused")
		DDLogger ddl = new DDLogger(DDLogger.ALL); 
		
		// Variablen anlegen fürs spätere Testen !!! 
		String address = "Localhost"; 
		String port = "3333"; 
		int type = ThreadObject.THREADTEST; 
		// Nullvariablen anlegen 
		String nulladdress = null; 
		String nullport = null;
		int nulltype = Integer.MAX_VALUE;		
		
		// Konstruktoren falsch anlegen und so 
		// Adresse falsch !!! 
		try {
			ThreadObject to = new ThreadObject(nulladdress,nullport,nulltype); 
			Thread thread = new Thread(to); 
			thread.start(); 
		} catch (Exception ex){
			
		}
		
		// Port falsch !!! 
		try {
			ThreadObject to = new ThreadObject(address,nullport,nulltype); 
			Thread thread = new Thread(to); 
			thread.start(); 
		} catch (Exception ex){
			
		}
		System.out.println("------------------------------------------"); 
		System.out.println("   ein Thread    "); 		
		// Richtig anlegen und starten 
		try {
			ThreadObject to = new ThreadObject(address,port,type); 
			Thread thread = new Thread(to); 
			thread.start(); 
			thread.join(); 
		} catch (Exception ex){
			
		}
		
		// 2 Threads parallel 
		// Port falsch !!! 
		System.out.println("------------------------------------------"); 
		System.out.println("   2 Threads parallel    "); 
		try {
			ThreadObject to = new ThreadObject(address,port,type); 
			ThreadObject to2 = new ThreadObject(address,port,type); 
			Thread thread = new Thread(to); 
			thread.start(); 
			thread = new Thread(to2);
			thread.start(); 
			thread.join(); 
		} catch (Exception ex){
			
		}
		

		
		// Laufzeit ermitteln  
		System.out.println("------------------------------------------"); 
		System.out.println("   Laufzeit ermitteln !!!    "); 
		try {
			ThreadObject to = new ThreadObject(address,port,type); 
			Thread thread = new Thread(to); 
			thread.start(); 
			
			thread.join(); 
			
			System.out.println("Laufzeit Thread: " + to.getRunningTime()); 
			to = null; 

		} catch (Exception ex){
			System.out.println("Laufzeit konnte nicht ermittelt werden!!!" ); 
		}	

		// Maximale Anzahl Versuche testen !!! 
		System.out.println("------------------------------------------"); 
		System.out.println("   Ein Thread mehrfach laufen lassen !!!    "); 		
		// Richtig anlegen und starten 
		try {
			ThreadObject to = new ThreadObject(address,port,type); 
			Thread thread; 
			for (int i = 0; i <= 15; i++){	
					thread = new Thread(to); 
					thread.start(); 
					System.out.println("Run number: " + i);
			}
		} catch (Exception ex){
			
		}	
	}

}

/**
 * 
 */
package project.network;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *	Klasse wird wiederum auch als Singleton erstellt da sie genau mitzählen muss 
 *  Wieviele Threads auf der Serverseite derzeit gestartet sind. 
 *  
 */
public class IncomingThreadCounter {

	public static final int MAXTHREADCOUNT = 2; 
	private static IncomingThreadCounter itc = new IncomingThreadCounter(); 
	private static int threadCount; 
	
	/**
	 * private da singleton 
	 */
	private IncomingThreadCounter() {
		IncomingThreadCounter.threadCount = 0; 
	}
	
	/** 
	 * Hierdurch bekommt man die Instanz auf die Klasse 
	 * @return
	 */
	public synchronized static IncomingThreadCounter getInstance(){
		return IncomingThreadCounter.itc; 
	}
	
	/** 
	 * Man bekommt den derzeit aktuellen Zähler 
	 * @return
	 */
	public synchronized int getCount(){
		return IncomingThreadCounter.threadCount; 
	}
	
	/**
	 * Zählt den Zähler eins hoch 
	 */
	public synchronized void incCount(){
		IncomingThreadCounter.threadCount++; 
	}
	
	/** 
	 * Zieht vom Zähler eins ab 
	 */
	public synchronized void decCount(){
		if (IncomingThreadCounter.threadCount >= 1)
			IncomingThreadCounter.threadCount--; 
	}

}

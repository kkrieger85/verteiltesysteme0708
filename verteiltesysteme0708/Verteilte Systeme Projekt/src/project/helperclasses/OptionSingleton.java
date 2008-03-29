/**
 * 
 */
package project.helperclasses;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class OptionSingleton {

	private static OptionSingleton instance = new OptionSingleton(); 
	private static boolean debugmode = false; 
	private static boolean autservermode = false; 
	
	
	/**
	 * @return the debugmode
	 */
	public static synchronized boolean isDebugmode() {
		return debugmode;
	}

	/**
	 * @param debugmode the debugmode to set
	 */
	public static synchronized void setDebugmode(boolean debugmode) {
		OptionSingleton.debugmode = debugmode;
	}

	/**
	 * @return the autservermode
	 */
	public static synchronized boolean isAutservermode() {
		return autservermode;
	}

	/**
	 * @param autservermode the autservermode to set
	 */
	public static synchronized void setAutservermode(boolean autservermode) {
		OptionSingleton.autservermode = autservermode;
	}

	/**
	 * Private da Singletonpattern 
	 */
	private OptionSingleton(){
		
	}
	
	/**
	 * Operation um die Instanz zu bekommen!!! 
	 * @return
	 */
	public static OptionSingleton getInstance(){
		return instance; 
	}
	
	
}

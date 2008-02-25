/**
 * 
 */
package project.exception;


/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class ThreadObjectException extends Exception {

	// TODO Sprachwrapper einbauen !!! 
	public static String MAXTRIALS 		= "Maximale Anzahl Versuche erreicht !!!"; 
	public static String NOADDRESS 		= "Keine IP Adresse mitgegeben!!!"; 
	public static String NOPORT 		= "Keinen PORT mitgegeben!!!"; 
	public static String NOTYPE 		= "Keinen Anfragetyp mitgegeben!!!"; 
	public static String THREADFAILED 	= "Ausführung Thread fehlgeschlagen!!"; 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8456552687244404467L;

	/**
	 * 
	 */
	public ThreadObjectException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public ThreadObjectException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public ThreadObjectException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ThreadObjectException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}

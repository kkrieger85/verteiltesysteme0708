/**
 * 
 */
package project.exception;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class XMLConfigException extends Exception {
	
	// TODO Sprachwrapper einbauen !!! 
	public static String ATTRIBUTENOTFOUND 		= "Attribut wurde nicht gefunden!!"; 

	/**
	 * 
	 */
	public XMLConfigException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public XMLConfigException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public XMLConfigException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public XMLConfigException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}

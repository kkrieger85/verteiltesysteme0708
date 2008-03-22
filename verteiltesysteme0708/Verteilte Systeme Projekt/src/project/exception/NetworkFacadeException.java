/**
 * 
 */
package project.exception;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class NetworkFacadeException extends Exception {

	public static final String SERVICENOTIMPLEMENTED = "Dienst noch nicht eingebaut!!!"; 
	public static final String VALUEISNULL = "Ein Wert ist NULL"; 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8567942269086517967L;

	/**
	 * 
	 */
	public NetworkFacadeException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public NetworkFacadeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public NetworkFacadeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NetworkFacadeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}

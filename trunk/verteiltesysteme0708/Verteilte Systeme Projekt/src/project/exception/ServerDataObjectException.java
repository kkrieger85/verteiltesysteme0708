/**
 * 
 */
package project.exception;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class ServerDataObjectException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2659484650519756582L;
	
	public static String NOADDRESS 		= "Keine IP Adresse mitgegeben!!!"; 
	public static String NOPORT 		= "Keinen PORT mitgegeben!!!"; 
	/**
	 * 
	 */
	public ServerDataObjectException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public ServerDataObjectException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public ServerDataObjectException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ServerDataObjectException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}

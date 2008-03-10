package project.exception;
/**
 * Fehler die das Login betreffen
 * @author mafolz
 *
 */
public class LoginException extends Exception {
	public final static String NOTLOGGEDIN = "nicht eignelogt";

	public LoginException(String arg0) {
		super(arg0);
	}

}

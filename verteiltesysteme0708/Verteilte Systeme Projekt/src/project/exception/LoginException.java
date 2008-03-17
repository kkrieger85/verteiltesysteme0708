package project.exception;
/**
 * Fehler die das Login betreffen 
 * @author mafolz
 *
 */
public class LoginException extends Exception {
	public final static String SERVERFAIL	= "Server nicht erreichbar";
	public final static String NOTLOGGEDIN = "nicht eingelogt";
	public final static String CANNOTLOGIN = "Benutzername oder Password falsch";

	public LoginException(String arg0) {
		super(arg0);
	}

}

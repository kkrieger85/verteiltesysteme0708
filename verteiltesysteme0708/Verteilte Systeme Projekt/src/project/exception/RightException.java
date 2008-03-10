package project.exception;
/**
 * Fehler die die Rechte betreffen und dessen Ver-/Entschlüßelung
 * @author mafolz
 *
 */
public class RightException extends Exception {
	public final static String DECRYPT = "konnte nicht entschlüßeln";
	public final static String ENCRYPT = "konnte nicht verschlüßeln";

	public RightException(String arg0) {
		super(arg0);
	}
}

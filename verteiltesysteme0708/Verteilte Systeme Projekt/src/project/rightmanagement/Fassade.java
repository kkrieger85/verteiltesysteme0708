package project.rightmanagement;

import project.exception.*;
import project.data.*;
import java.rmi.RemoteException;
/**
 * Diese Fassade Kapselt die Implementierung der Authentifikation
 * von der aufruferklasse RightQuery und implemetiert f&uuml;r alle
 * authentifikationsarten wichtige Funktionen bzw
 * Konstruktoren
 * @author mafolz
 */
public abstract class Fassade {
	protected String url;
	protected String user;
	protected String[] roles;
	
	/**
	 * Login wird gestartet und die Fassade Initialisiert
	 * Wenn Benutzername oder Password nicht stimmen, wird die
	 * LoginException.CANNOTLOGIN geworfen
	 * @param url
	 * @param user
	 * @param password
	 * @throws LoginException
	 */
	public Fassade(String url, String user, String password)throws  LoginException{
		this.url=url;
		this.user=user;
		if(!login(user,password))
			throw new LoginException(LoginException.CANNOTLOGIN);
	}
	/**
	 * Gibt den Benutzernamen zur&uuml;ck, welcher lokal 
	 * eingelogt ist.
	 * @return
	 */
	public String getUsername(){
		return this.user;
		
	}
	
	/** Abstrakte Methode:
	 * Gib zur&uuml;ck ob Dokument ge&auml;ndert werden darf, 
	 * Implementation muss erforderliche Daten aus dem DokumentWrapper ziehen 
	 * @param doc
	 * @return
	 */
	public abstract boolean changeDocument(DocumentWrapper doc);
	/** Abstrakte Methode:
	 * Gib zur&uuml;ck ob Dokument gelesen werden darf, 
	 * Implementation muss erforderliche Daten aus dem DokumentWrapper ziehen 
	 * @param doc
	 * @return
	 */
	public abstract boolean openDocument(DocumentWrapper doc);
	/** Abstrakte Methode:
	 * Gib zur&uuml;ck ob ein Dokument in der angegebenen
	 * Rolle erstellt werden darf.
	 * Implementation muss dies pr&uuml;fen.
	 * @param doc
	 * @return
	 */
	public abstract boolean createDocument(String role);
	/** Abtrakte Login-Funktion:
	 * Diese wird vom Abstrakten Konstruktor aufgerufen um zu erzwingen, 
	 * dass eine Eingelogte Instanz der Authentifizierung vorhanden ist.
	 * Diese Funktion muss
	 * @param user
	 * @param password
	 * @return
	 * @throws LoginException
	 */
	protected abstract boolean login (String user, String password)throws LoginException;
	public abstract boolean setVertrauensstelle(String user, String role);
	public abstract boolean addRoleToDocument(DocumentWrapper doc, String role);
	public abstract boolean removeRoleFromDocument(DocumentWrapper doc, String role);
	public abstract boolean addUserToRole(String user, String role);
	public abstract boolean removeUserFromRole(String user, String role);
	public abstract String[] listRoles();
	public DocumentWrapper encrypt( DocumentWrapper doc) throws RightException{
		return doc;
	}
	public DocumentWrapper decrypt( DocumentWrapper doc) throws RightException{
		return doc;
	}
}

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
	
	/** <b>Abstrakte Methode:</b>
	 * Gib zur&uuml;ck ob Dokument ge&auml;ndert werden darf, 
	 * Implementation muss erforderliche Daten aus dem DokumentWrapper ziehen 
	 * @param doc
	 * @return
	 */
	public abstract boolean changeDocument(DocumentWrapper doc);
	/** <b>Abstrakte Methode:</b>
	 * Gib zur&uuml;ck ob Dokument gelesen werden darf, 
	 * Implementation muss erforderliche Daten aus dem DokumentWrapper ziehen 
	 * @param doc
	 * @return
	 */
	public abstract boolean openDocument(DocumentWrapper doc);
	/** <b>Abstrakte Methode:</b>
	 * Gib zur&uuml;ck ob ein Dokument in der angegebenen
	 * Rolle erstellt werden darf.
	 * Implementation muss dies pr&uuml;fen.
	 * @param doc
	 * @return
	 */
	public abstract boolean createDocument(String role);
	/** <b>Abstrakte Login-Funktion:</b>
	 * Diese wird vom Abstrakten Konstruktor aufgerufen um zu erzwingen, 
	 * dass eine Eingelogte Instanz der Authentifizierung vorhanden ist.
	 * Diese Funktion muss
	 * @param user
	 * @param password
	 * @return
	 * @throws LoginException
	 */
	protected abstract boolean login (String user, String password)throws LoginException;	
	/** <b>Abstrakte Methode:</b>
	 * Einen Benutzer als Vertrauensstelle bzw. Rollenadministrator festlegen.
	 * Hierzu muss der ausf&uuml;rende Benutzer selbst eine Vertrauensstelle dieser 
	 * Rolle sein.
	 * @param user
	 * @param role
	 * @return
	 */
	public abstract boolean setVertrauensstelle(String user, String role);
	/** <b>Abstrakte Methode:</b>
	 * F&uuml,gt eine Rolle zu dem Dokument hinzu, hierzu muss das Dokument
	 * in einer Rolle änderbar sein, in der der Benutzer Rollenadministrator ist.
	 * @param doc
	 * @param role
	 * @return
	 */
	public abstract boolean addRoleToDocument(DocumentWrapper doc, String role);
	/** <b>Abstrakte Methode:</b>
	 * Entfernt eine Rolle von dem Dokument, der ausf&uuml,hrende Benutzer
	 * muss hierzu Vertrauensstelle der zu entfernenden Rolle sein.
	 * @param doc
	 * @param role
	 * @return
	 */
	public abstract boolean removeRoleFromDocument(DocumentWrapper doc, String role);
	/** <b>Abstrakte Methode:</b>
	 * F&uuml,gt einen Benutzer zu einer Rolle hinzu.
	 * Der ausf&uuml;hrende Benutzer muss Vertrauensstelel dieser Rolle sein.
	 * @param user
	 * @param role
	 * @return
	 */
	public abstract boolean addUserToRole(String user, String role);
	/** <b>Abstrakte Methode:</b>
	 * Entfernt einen Benutzer von einer Rolle.
	 * Der ausf&uuml;hrende Benutzer muss Vertrauensstelel dieser Rolle sein.
	 * Der benutzer kann auch entfernt werden, wenn er selbst Vertrauensstelle dieser Rolle ist.
	 * @param user
	 * @param role
	 * @return
	 */
	public abstract boolean removeUserFromRole(String user, String role);
	/** <b>Abstrakte Methode:</b>
	 * Listet alle Rollen auf, in die der ausf&uuml;hrende Benutzer
	 * eingeordnet ist.
	 * @return
	 */
	public abstract String[] listRoles();
	/** <b>Dummy Methode:</b>
	 * Diese Methode bietet dem Rechtemanagement die M&ouml;glichkeit,
	 * Dateien vor dem Verschicken zu verschl&uuml;&szlig;eln.
	 * Wenn sie nicht &uuml;berladen wird, wird das Dokument nicht ver&auml,ndert
	 * @param doc
	 * @return
	 * @throws RightException
	 */
	public DocumentWrapper encrypt( DocumentWrapper doc) throws RightException{
		return doc;
	}
	/** <b>Dummy Methode:</b>
	 * Diese Methode bietet dem Rechtemanagement die M&ouml;glichkeit,
	 * Dateien nach dem erhalt zu entschl&uuml;&szlig;eln.
	 * Wenn sie nicht &uuml;berladen wird, wird das Dokument nicht ver&auml,ndert
	 * @param doc
	 * @return
	 * @throws RightException
	 */
	public DocumentWrapper decrypt( DocumentWrapper doc) throws RightException{
		return doc;
	}
}

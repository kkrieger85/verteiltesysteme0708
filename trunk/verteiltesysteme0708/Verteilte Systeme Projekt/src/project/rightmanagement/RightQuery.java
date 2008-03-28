package project.rightmanagement;
import project.exception.*;
import project.data.*;
/**
 * Eigendliche Instanz zum ausf&uuml;hren des Rechtemanagement,
 * Durch das Fassadeninterface muss nur die Instanz-Variabel
 * mit der entsprechenden Rechtemanagement-Klasse
 * initialisiert werden. Weitere Modifikationen sind nicht n&ouml;tig.
 * @author mafolz
 *
 */
public class RightQuery {
	private Fassade instanz;
	
	/**
	 * Gibt zur&uuml;ck ob der DocumentWrapper ge&auml;ndert werden darf.
	 * Wirft LoginException wenn zuvor nicht eingeloggt wurde.
	 * @return
	 * @throws LoginException
	 */
	public boolean changeDocument(DocumentWrapper id) throws LoginException{
		if(instanz == null)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.changeDocument(id);
	}
	/**
	 * Gibt zur&uuml;ck ob der DocumentWrapper gelesen werden darf.
	 * Wirft LoginException wenn zuvor nicht eingeloggt wurde.
	 * @return
	 * @throws LoginException
	 */
	public boolean openDocument(DocumentWrapper id) throws LoginException{
		if(instanz == null)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.openDocument(id);
		
	}
	/**
	 * Gibt zur&uuml;ck ob in der entsprechenden Rolle Dateien erzeugt werden d&uuml;fen
	 * Wirft LoginException wenn zuvor nicht eingeloggt wurde.
	 * @return
	 * @throws LoginException
	 */
	public boolean createDocument(String role) throws LoginException{
		if(instanz == null)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.createDocument(role);
		
	}
	/**loggt den Benutzer ein
	 * Hier kann der Anwendungsentwickler die <i>Implementation der Fassade</i> angeben,
	 * diese Funktion <b>muss als erstes Aufgerufen</b> werden damit der Benutzer eingelogt 
	 * ist und eine Instanz der Authentifizierung erstellt werden kann. 
	 * @param user
	 * @param password
	 * @return
	 * @throws LoginException
	 */
	public boolean login(String url, String user, String password) throws LoginException{
		return (instanz= new Simple(url,user, password))!=null;
	}
	/**
	 * Setzt eine neue Vertrauensstelle bzw. RollenAdministrator f&uuml;r die
	 * angegebene Rolle.
	 * @param user
	 * @param role
	 * @return
	 * @throws LoginException
	 */
	public boolean setVertrauensstelle(String user, String role) throws LoginException{
		if( instanz == null)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.setVertrauensstelle(user, role);
		
	}
	/**
	 * Dokument eine Rolle hinzuf&uuml;gen
	 * @return
	 * @throws LoginException
	 */
	public boolean addRoleToDocument(DocumentWrapper doc, String role) throws LoginException{
		if(instanz == null)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.addRoleToDocument(doc ,role);
		
	}
	/**
	 * Rolle einem Dokument entziehen
	 * @return
	 * @throws LoginException
	 */
	public boolean removeRoleFromDocument(DocumentWrapper doc, String role) throws LoginException{
		if(instanz==null)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.removeRoleFromDocument(doc , role);
		
	}
	
	/**
	 * Benutzer zu einer Rolle hinzuf&uuml;gen
	 * @param user
	 * @param role
	 * @return
	 * @throws LoginException
	 */
	public boolean addUserToRole(String user, String role) throws LoginException{
		if(instanz==null)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.addUserToRole(user, role);
		
	}
	/**
	 * Benutzer eine Rolle entziehen
	 * @param user
	 * @param role
	 * @return
	 * @throws LoginException
	 */
	public boolean removeUserFromRole(String user, String role) throws LoginException{
		if(instanz==null)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.removeUserFromRole(user, role);
		
	}
	/**
	 * Alle Rollen des aufrufenden Benutzers auflisten
	 * @return
	 * @throws LoginException
	 */
	public String[] listRoles() throws LoginException{
		if(instanz==null)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.listRoles();
		
	}
	/**
	 * Dokumente verschl&uuml;&szlig;eln
	 * @param doc
	 * @return
	 * @throws LoginException
	 * @throws RightException
	 */
	public DocumentWrapper encrypt(DocumentWrapper doc) throws LoginException, RightException{
		if(instanz==null)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.encrypt(doc);
		
	}
	/**
	 * Dokumente entschl&uuml;&szlig;eln
	 * @param doc
	 * @return
	 * @throws LoginException
	 * @throws RightException 
	 */
	public DocumentWrapper decrypt(DocumentWrapper doc) throws LoginException, RightException{
		if(instanz==null)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.decrypt(doc);
		
	}
	
	/**
	 * Debug-Testing  Main-methode.
	 * @param args
	 */
	public static void main(String[] args){
		RightQuery test = new RightQuery();
		try{
			System.out.println("Starte Test\nLogin als heinz testosteron");
			System.out.println(test.login("localhost","heinz","testosteron"));
			System.out.println("Starte Test\nLogin als peter lustig");
			System.out.println(test.login("localhost","peter","lustig"));
			System.out.println(test.listRoles());
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
	}

}

package project.rightmanagement;
import project.exception.*;
/**
 * Eigendliche Instanz des Rechtemanagement,
 * Durch das Fassadeninterface muss nur die Instanz-Variabel
 * mit der entsprechenden Rechtemanagement-Klasse
 * initialisiert werden. Weitere Modifikationen sind nicht nötig.
 * @author mafolz
 *
 */
public class RightQuery {
	private Fassade instanz = new Simple();
	
	/**
	 * Gibt zurück ob die Datei mit der entsprechenden ID geändert werden darf
	 * @return
	 * @throws LoginException
	 */
	public boolean changeDocument(String id) throws LoginException{
		if(!instanz.loggedin)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.changeDocument(id);
	}
	/**
	 * Gibt zurück ob die Datei mit der entsprechenden ID gelesen werden darf
	 * @return
	 * @throws LoginException
	 */
	public boolean openDocument(String id) throws LoginException{
		if(!instanz.loggedin)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.openDocument(id);
		
	}
	/**
	 * Gibt zurück ob in der entsprechenden ROlle Dateien erzeugt werden dürfen
	 * @return
	 * @throws LoginException
	 */
	public boolean createDocument(String role) throws LoginException{
		if(!instanz.loggedin)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.createDocument(role);
		
	}
	/**loggt den Benutzer ein
	 * 
	 * @param user
	 * @param password
	 * @return
	 * @throws LoginException
	 */
	public boolean login(String user, String password) throws LoginException{
		return instanz.login(user, password);
	}
	public boolean setVertrauensstelle() throws LoginException{
		if(!instanz.loggedin)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.setVertrauensstelle();
		
	}
	public boolean addRoleToDocument() throws LoginException{
		if(!instanz.loggedin)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.addRoleToDocument();
		
	}
	public boolean removeRoleFromDocument() throws LoginException{
		if(!instanz.loggedin)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.removeRoleFromDocument();
		
	}
	public boolean addUserToRole(String user, String role) throws LoginException{
		if(!instanz.loggedin)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.addUserToRole(user, role);
		
	}
	public boolean removeUserFromRole(String user, String role) throws LoginException{
		if(!instanz.loggedin)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.removeUserFromRole(user, role);
		
	}
	public String[] listRoles() throws LoginException{
		if(!instanz.loggedin)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.listRoles();
		
	}

}

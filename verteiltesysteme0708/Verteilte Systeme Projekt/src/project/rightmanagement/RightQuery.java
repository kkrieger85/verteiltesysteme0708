package project.rightmanagement;
import project.exception.*;
import project.data.*;
/**
 * Eigendliche Instanz des Rechtemanagement,
 * Durch das Fassadeninterface muss nur die Instanz-Variabel
 * mit der entsprechenden Rechtemanagement-Klasse
 * initialisiert werden. Weitere Modifikationen sind nicht nötig.
 * @author mafolz
 *
 */
public class RightQuery {
	private Fassade instanz;
	
	/**
	 * Gibt zurück ob der DocumentWrapper geändert werden darf
	 * @return
	 * @throws LoginException
	 */
	public boolean changeDocument(DocumentWrapper id) throws LoginException{
		if(instanz == null)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.changeDocument(id);
	}
	/**
	 * Gibt zurück ob der DocumentWrapper gelesen werden darf
	 * @return
	 * @throws LoginException
	 */
	public boolean openDocument(DocumentWrapper id) throws LoginException{
		if(instanz == null)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.openDocument(id);
		
	}
	/**
	 * Gibt zurück ob in der entsprechenden ROlle Dateien erzeugt werden dürfen
	 * @return
	 * @throws LoginException
	 */
	public boolean createDocument(String role) throws LoginException{
		if(instanz == null)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.createDocument(role);
		
	}
	/**loggt den Benutzer ein
	 * Hier kann der Anwendungsentwickler die Implementation der Fassade angeben
	 * @param user
	 * @param password
	 * @return
	 * @throws LoginException
	 */
	public boolean login(String url, String user, String password) throws LoginException{
		return instanz== new Simple(url,user, password);
	}
	
	public boolean setVertrauensstelle(String user, String role) throws LoginException{
		if( instanz == null)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.setVertrauensstelle(user, role);
		
	}
	public boolean addRoleToDocument() throws LoginException{
		if(instanz == null)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.addRoleToDocument();
		
	}
	public boolean removeRoleFromDocument() throws LoginException{
		if(instanz==null)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.removeRoleFromDocument();
		
	}
	
	
	public boolean addUserToRole(String user, String role) throws LoginException{
		if(instanz==null)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.addUserToRole(user, role);
		
	}
	public boolean removeUserFromRole(String user, String role) throws LoginException{
		if(instanz==null)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.removeUserFromRole(user, role);
		
	}
	public String[] listRoles() throws LoginException{
		if(instanz==null)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.listRoles();
		
	}
	public DocumentWrapper encrypt(DocumentWrapper doc) throws LoginException, RightException{
		if(instanz==null)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.encrypt(doc);
		
	}
	public DocumentWrapper decrypt(DocumentWrapper doc) throws LoginException, RightException{
		if(instanz==null)
			throw new LoginException(LoginException.NOTLOGGEDIN);
		return instanz.decrypt(doc);
		
	}
	
	public static void main(String[] args){
		RightQuery test = new RightQuery();
		try{
			test.login("localhost","peter","lustig");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}

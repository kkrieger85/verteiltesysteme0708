package project.rightmanagement;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RMI Interface f&uuml;r die Simple Authentifizierung.
 * Diese Methoden werden von dem Authentifikationsserver
 * Implementiert. m&uuml;&szlig;en aber dem Client bekannt sein f&uuml;r 
 * einen RMI-Aufruf.
 * @author mafolz
 */
public interface TasInterface extends Remote {
	/**
	 * Pr&uuml;ft ob der Benutzer in der Rolle schreibrecht hat
	 * @param user
	 * @param role
	 * @return
	 * @throws RemoteException
	 */
	public boolean canWriteInRole(String user, String role)throws RemoteException;
	/**
	 * Pr&uuml;ft ob der Benutzer in der Rolle leserecht hat
	 * @param user
	 * @param role
	 * @return
	 * @throws RemoteException
	 */
	public boolean canReadInRole( String user, String role)throws RemoteException;
	/**
	 * Pr&uuml;ft ob der Benutzer in der Rolle Dokumente erstellen darf
	 * @param user
	 * @param role
	 * @return
	 * @throws RemoteException
	 */
	public boolean canCreateInRole(String user, String role)throws RemoteException;
	/**
	 * Login Funktion bei dem Authorisation-Server.
	 * War das Login erfolgreich, wird True zur&uuml,ck gegeben.
	 * @param user
	 * @param password
	 * @return
	 * @throws RemoteException
	 */
	public boolean login(String user, String password)throws RemoteException;
	/**
	 * Diese Methode pr&uuml;ft, ob der angegebene Benutzer in der angegebenen Rolle
	 * Rollenadministrator ist.
	 * @param user
	 * @param role
	 * @return
	 * @throws RemoteException
	 */
	public boolean isRoleAdmin(String user, String role)throws RemoteException;
	/**
	 * Erstellt einen Rollenadministrator, &uuml;ber einen Auszuf%uuml;renden Benutzer,
	 * welcher bei der Angegebenen Rolle Rollenadministrator sein muss.
	 * @param owner
	 * @param role
	 * @param user
	 * @return
	 * @throws RemoteException
	 */
	public boolean createRoleAmin(String owner, String role , String user)throws RemoteException;
	/**
	 * F&uuml;gt einen Benutzer, einer Rolle hinz&uuml;.
	 * Hierzu muss der aufrufende Benutzer Rollenadministrator der entsprechenden Rolle sein
	 * @param owner
	 * @param role
	 * @param user
	 * @return
	 * @throws RemoteException
	 */
	public boolean addUserToRole(String owner, String role , String user)throws RemoteException;
	/**
	 * Entfernt einen Benutzer von einer Rolle.
	 * Hierzu muss der aufrufende Benutzer Rollenadministrator der entsprechenden Rolle sein
	 * @param owner
	 * @param role
	 * @param user
	 * @return
	 * @throws RemoteException
	 */
	public boolean removeUserFromRole(String owner, String role , String user)throws RemoteException;
	/**
	 * Gibt eine Liste der ROllen zur&uuml;ck, in die der angegebene Benutzer registriert ist
	 * @param user
	 * @return
	 * @throws RemoteException
	 */
	public String[] listRoles(String user)throws RemoteException;
	

}

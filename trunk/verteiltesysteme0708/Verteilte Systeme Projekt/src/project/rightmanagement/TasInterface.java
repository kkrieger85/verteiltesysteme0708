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
	public boolean canWriteInRole(String user, String role)throws RemoteException;
	public boolean canReadInRole( String user, String role)throws RemoteException;
	public boolean canCreateInRole(String user, String role)throws RemoteException;
	public boolean login(String user, String password)throws RemoteException;
	public boolean isRoleAdmin(String user, String role)throws RemoteException;
	public boolean createRoleAmin(String owner, String role , String user)throws RemoteException;
	public boolean addUserToRole(String owner, String role , String user)throws RemoteException;
	public boolean removeUserFromRole(String owner, String role , String user)throws RemoteException;
	public String[] listRoles(String user)throws RemoteException;
	

}

package project.rightmanagement;
import java.rmi.Remote;
import java.rmi.RemoteException;

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

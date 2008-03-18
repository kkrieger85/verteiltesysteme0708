package project.rightmanagement;

import java.rmi.Remote;

public interface TasInterface extends Remote {
	public boolean canWriteInRole(String user, String role);
	public boolean canReadInRole( String user, String role);
	public boolean canCreateInRole(String user, String role);
	public boolean login(String user, String password);
	public boolean isRoleAdmin(String user, String role);
	public boolean createRoleAmin(String user, String role);
	public boolean addUserToRole(String user, String role);
	public boolean remoteUserFromRole(String user, String role);
	public String[] listRoles(String user);
	

}

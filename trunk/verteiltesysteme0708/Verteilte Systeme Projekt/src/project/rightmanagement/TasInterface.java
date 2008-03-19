package project.rightmanagement;

import java.rmi.Remote;

public interface TasInterface extends Remote {
	public boolean canWriteInRole(String user, String role);
	public boolean canReadInRole( String user, String role);
	public boolean canCreateInRole(String user, String role);
	public boolean login(String user, String password);
	public boolean isRoleAdmin(String user, String role);
	public boolean createRoleAmin(String owner, String role , String user);
	public boolean addUserToRole(String owner, String role , String user);
	public boolean removeUserFromRole(String owner, String role , String user);
	public String[] listRoles(String user);
	

}

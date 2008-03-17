package project.rightmanagement;

import project.data.*;
import project.exception.LoginException;
import project.exception.RightException;
import java.rmi.Naming;

public class Simple extends Fassade{
	private TasInterface authServer;
	public Simple(String url, String user, String passwd){
		super(url, user, passwd);
		authServer = (TasInterface) Naming.lookup(url);
		roles = listRoles(user);
	}
	
	public boolean changeDocument(DocumentWrapper doc){
		return authServer.canWriteInRole(user, DocumentWrapper.getMetadata().getRole());
	}
	public boolean openDocument(DocumentWrapper doc ){
		return authServer.canReadInRole(user, DocumentWrapper.getMetadata().getRole());		
	}
	public boolean createDocument(String role){
		return authServer.canCreateInRole(user, role);	
	}
	protected boolean login(String user, String password)throws RemoteException{
		return authServer.login(user, password);
	}
	public boolean setVertrauensstelle(String user, String role){
		return authServer.isRoleAdmin(this.user, role) ? authServer.createRoleAmin(String user, String role): false;		
	}
	public boolean addRoleToDocument(){
		return true;
	}
	public boolean removeRoleFromDocument(){
		return true;
	}
	public boolean addUserToRole(String user, String role){
		return authServer.isRoleAdmin(this.user, role) ? authServer.addUserToRole(String user, String role): false;
	}
	public boolean removeUserFromRole(String user, String role){
		return authServer.isRoleAdmin(this.user, role) ? authServer.remoteUserFromRole(String user, String role): false;
	}
	public String[] listRoles(){
		return roles;
	}

}

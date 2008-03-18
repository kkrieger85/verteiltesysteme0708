package project.rightmanagement;

import project.data.*;
import project.exception.LoginException;
import project.exception.RightException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.io.IOException;

public class Simple extends Fassade{
	private TasInterface authServer;
	public Simple(String url, String user, String passwd)throws LoginException  {
		super(url, user, passwd);
	}
	
	public boolean changeDocument(DocumentWrapper doc){
		return authServer.canWriteInRole(user, doc.getMetadata().getRolle());
	}
	public boolean openDocument(DocumentWrapper doc ){
		return authServer.canReadInRole(user, doc.getMetadata().getRolle());		
	}
	public boolean createDocument(String role){
		return authServer.canCreateInRole(user, role);	
	}
	protected boolean login(String user, String password)throws LoginException{
		try{
			authServer = (TasInterface) Naming.lookup(url);
			roles = authServer.listRoles(user);
		}catch(RemoteException e){
			throw new LoginException(LoginException.SERVERNOTRESPONSE);
		}catch(NotBoundException e){
			throw new LoginException(LoginException.SERVERNOTRESPONSE);
		}catch(IOException e){
			throw new LoginException(LoginException.SERVERFAIL);
		}
		return authServer.login(user, password);
	}
	public boolean setVertrauensstelle(String user, String role){
		return authServer.isRoleAdmin(this.user, role) ? authServer.createRoleAmin(user, role): false;		
	}
	public boolean addRoleToDocument(){
		return true;
	}
	public boolean removeRoleFromDocument(){
		return true;
	}
	public boolean addUserToRole(String user, String role){
		return authServer.isRoleAdmin(this.user, role) ? authServer.addUserToRole(user, role): false;
	}
	public boolean removeUserFromRole(String user, String role){
		return authServer.isRoleAdmin(this.user, role) ? authServer.remoteUserFromRole(user, role): false;
	}
	public String[] listRoles(){
		return roles;
	}

}

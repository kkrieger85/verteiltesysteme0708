package project.rightmanagement;

import project.exception.*;
import project.data.*;
import java.rmi.RemoteException;
/**
 * Fassadeninterface für das Rechtemanagement
 * @author mafolz
 *
 */
public abstract class Fassade {
	protected String url;
	protected String user;
	protected String[] roles;
	
	public Fassade(String url, String user, String password)throws  LoginException{
		this.url=url;
		this.user=user;
		if(!login(user,password))
			throw new LoginException(LoginException.CANNOTLOGIN);
	}
	public abstract boolean changeDocument(DocumentWrapper doc);
	public abstract boolean openDocument(DocumentWrapper doc);
	public abstract boolean createDocument(String role);
	protected abstract boolean login (String user, String password)throws LoginException;
	public abstract boolean setVertrauensstelle(String user, String role);
	public abstract boolean addRoleToDocument();
	public abstract boolean removeRoleFromDocument();
	public abstract boolean addUserToRole(String user, String role);
	public abstract boolean removeUserFromRole(String user, String role);
	public abstract String[] listRoles();
	public DocumentWrapper encrypt( DocumentWrapper doc) throws RightException{
		return doc;
	}
	public DocumentWrapper decrypt( DocumentWrapper doc) throws RightException{
		return doc;
	}
}

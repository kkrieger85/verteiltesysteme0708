package project.rightmanagement;

import project.data.*;
import project.exception.LoginException;
import project.exception.RightException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.io.IOException;

public class Simple extends Fassade {
	private TasInterface authServer;

	public Simple(String url, String user, String passwd) throws LoginException {
		super(url, user, passwd);
	}

	public boolean changeDocument(DocumentWrapper doc) {
		try {
			return authServer
					.canWriteInRole(user, doc.getMetadata().getRolle());
		} catch (Exception e) {
		}
		return false;
	}

	public boolean openDocument(DocumentWrapper doc) {
		try {
			return authServer.canReadInRole(user, doc.getMetadata().getRolle());
		} catch (Exception e) {
		}
		return false;
	}

	public boolean createDocument(String role) {
		try {
			return authServer.canCreateInRole(user, role);
		} catch (Exception e) {
		}
		return false;
	}

	protected boolean login(String user, String password) throws LoginException {
		try {
			authServer = (TasInterface) Naming.lookup(url);
			roles = authServer.listRoles(user);
			return authServer.login(user, password);
		} catch (RemoteException e) {
			throw new LoginException(LoginException.SERVERNOTRESPONSE);
		} catch (NotBoundException e) {
			throw new LoginException(LoginException.SERVERNOTRESPONSE);
		} catch (IOException e) {
			throw new LoginException(LoginException.SERVERFAIL);
		}
	}

	public boolean setVertrauensstelle(String user, String role) {
		try {
			return authServer.isRoleAdmin(this.user, role) ? authServer
					.createRoleAmin(this.user, role, user) : false;
		} catch (Exception e) {
		}
		return false;
	}

	public boolean addRoleToDocument() {
		return true;
	}

	public boolean removeRoleFromDocument() {
		return true;
	}

	public boolean addUserToRole(String user, String role) {
		try {
			return authServer.isRoleAdmin(this.user, role) ? authServer
					.addUserToRole(this.user, role, user) : false;
		} catch (Exception e) {
		}
		return false;
	}

	public boolean removeUserFromRole(String user, String role) {
		try {
			return authServer.isRoleAdmin(this.user, role) ? authServer
					.removeUserFromRole(this.user, role, user) : false;
		} catch (Exception e) {
		}
		return false;
	}

	public String[] listRoles() {
		return roles;
	}

}

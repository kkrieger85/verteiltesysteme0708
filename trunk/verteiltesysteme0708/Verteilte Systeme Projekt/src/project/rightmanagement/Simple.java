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

	/**
	 * Initialisierung mit Authorisationsserver, Benutzername und Password
	 * 
	 * @param url
	 * @param user
	 * @param passwd
	 * @throws LoginException
	 */
	public Simple(String url, String user, String passwd) throws LoginException {
		super(url, user, passwd);
	}

	/**
	 * Kann der Benutzer das Dokument verändern?
	 */
	public boolean changeDocument(DocumentWrapper doc) {
		try {
			for (String rolle : doc.getMetadata().getRolle())
				if (authServer.canWriteInRole(user, rolle))
					return true;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * Kann der Benutzer das Dokument öffnen
	 */
	public boolean openDocument(DocumentWrapper doc) {
		try {
			for (String rolle : doc.getMetadata().getRolle())
				if (authServer.canReadInRole(user, rolle))
					return true;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * Kann der Benuter in der Rolle Dokumente erstellen
	 */
	public boolean createDocument(String role) {
		try {
			return authServer.canCreateInRole(user, role);
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * Wird intern von der Fassade während der Initialisierung aufgerufen
	 */
	protected boolean login(String user, String password) throws LoginException {
		try {
			authServer = (TasInterface) Naming.lookup("rmi://" + url + "/server");
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

	/**
	 * Rollenadministrator setzen
	 */
	public boolean setVertrauensstelle(String user, String role) {
		try {
			return authServer.isRoleAdmin(this.user, role) ? authServer
					.createRoleAmin(this.user, role, user) : false;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * Rolle einem Dokument hinzufügen, hierzu muss der ausführende User ein
	 * RollenAdministrator mind. einer rolle des Dokumentes sein
	 */
	public boolean addRoleToDocument(DocumentWrapper doc, String role) {
		try {
			// gehe alle Rollen durch
			for (String rolle : doc.getMetadata().getRolle())
				// wenn der Benutzer Rollenadministrator ist, füge hinzu
				if (authServer.isRoleAdmin(this.user, rolle)) {
					doc.getMetadata().getRolle().add(role);
					return true;
				}
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * Rolle einem Dokument entziehen, hierzu muss der ausführende user
	 * RollenAdministrator der zu entfernenden Rolle sein
	 */
	public boolean removeRoleFromDocument(DocumentWrapper doc, String role) {
		try {
			if (authServer.isRoleAdmin(this.user, role))
				return doc.getMetadata().getRolle().remove(role);
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * Benutzer einer Rolle hinzufügen
	 */
	public boolean addUserToRole(String user, String role) {
		try {
			return authServer.isRoleAdmin(this.user, role) ? authServer
					.addUserToRole(this.user, role, user) : false;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * Benutzer eine Rolle entziehen
	 */
	public boolean removeUserFromRole(String user, String role) {
		try {
			return authServer.isRoleAdmin(this.user, role) ? authServer
					.removeUserFromRole(this.user, role, user) : false;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * Gibt die Liste aller ROllen des Users zurück
	 */
	public String[] listRoles() {
		return roles;
	}

}

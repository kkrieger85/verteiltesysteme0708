package project.rightmanagement;

import project.exception.*;
import project.data.*;

/**
 * Fassadeninterface für das Rechtemanagement
 * @author mafolz
 *
 */
public interface Fassade {	
	public boolean loggedin = false;
	public boolean changeDocument(String id);
	public boolean openDocument(String id);
	public boolean createDocument(String role);
	public boolean login (String user, String password)throws LoginException;
	public boolean setVertrauensstelle();
	public boolean addRoleToDocument();
	public boolean removeRoleFromDocument();
	public boolean addUserToRole(String user, String role);
	public boolean removeUserFromRole(String user, String role);
	public String[] listRoles();
	public void encrypt(Document doc)throws RightException;
	public void decrypt(Document doc)throws RightException;
}

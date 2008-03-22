package project.rightmanagement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class TasImpl  extends UnicastRemoteObject implements TasInterface {

	private static final long serialVersionUID = 7771914302242479133L;
	private Vector<String> rollen;
	private Vector<TasObject> benutzer;
	
	
	public TasImpl() throws RemoteException {
		super();	
		benutzer= new Vector<TasObject>(40);
		rollen = new Vector<String>(); 
		
		rollen.add("benutzer");
		rollen.add("gast");
		// erinnerung an Bitmaske. lesen,schreiben,erstellen,admin
		benutzer.add(new TasObject("heinz", "testosteron"));
		benutzer.get(0).addRole(
				rollen.get(0), true,false,false,false); // Benutzerrolle
		benutzer.get(0).addRole(
				rollen.get(1),true,true,true,true); // Gastrolle
	}
	
	public boolean canWriteInRole(String user, String role)throws RemoteException{
		if(benutzer.contains(user))
			return benutzer.get(benutzer.indexOf(user)).canWrite(role);
		return false;
	}
	public boolean canReadInRole( String user, String role)throws RemoteException{
		if(benutzer.contains(user))
			return benutzer.get(benutzer.indexOf(user)).canRead(role);
		return false;
	}
	public boolean canCreateInRole(String user, String role)throws RemoteException{
		if(benutzer.contains(user))
			return benutzer.get(benutzer.indexOf(user)).canCreate(role);
		return false;
	}
	
	public boolean login(String user, String password)throws RemoteException{
		if(benutzer.contains(user))
			return benutzer.get(benutzer.indexOf(user)).login(password);
		return false;
	}
	public boolean isRoleAdmin(String user, String role)throws RemoteException{
		if(benutzer.contains(user))
			return benutzer.get(benutzer.indexOf(user)).isAdmin(role);
		return false;
		
	}
	/**
	 * eventuell hier noch überprüfen ob der username des
	 */
	public boolean createRoleAmin(String owner, String role , String user)throws RemoteException{
		if(isRoleAdmin(owner,role) &&
				benutzer.get(benutzer.indexOf(owner)).login &&
				benutzer.get(benutzer.indexOf(user)).isRole(role) ){
			benutzer.get(benutzer.indexOf(user)).setRole(role, true,true,true,true);
			return true;
		}
		return false;
			
	}	
	/**
	 * benutzer einer rolle hinzufügen mit standardlesereht
	 */
	public boolean addUserToRole(String owner, String role , String user)throws RemoteException{
		if(isRoleAdmin(owner,role) &&
				benutzer.get(benutzer.indexOf(owner)).login &&
				benutzer.get(benutzer.indexOf(user)).isRole(role) ){
			benutzer.get(benutzer.indexOf(user)).addRole(role, true,false,false,false);
			return true;
		}
		return false;		
	}
	public boolean removeUserFromRole(String owner, String role , String user)throws RemoteException{
		if(isRoleAdmin(owner,role) &&
				benutzer.get(benutzer.indexOf(owner)).login &&
				benutzer.get(benutzer.indexOf(user)).isRole(role) ){
			benutzer.get(benutzer.indexOf(user)).removeRole(role);
			return true;
		}
		return false;				
	}
	
	public String[] listRoles(String user)throws RemoteException{
		String[] returnValue = new String[rollen.size()]; 
		for (int i = 0; i < rollen.size(); i++){
			returnValue[i] = rollen.get(i); 
		}
		return returnValue; 
	}
}

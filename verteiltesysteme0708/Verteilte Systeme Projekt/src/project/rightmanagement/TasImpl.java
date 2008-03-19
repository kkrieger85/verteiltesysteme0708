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
		
		benutzer.add(new TasObject("heinz", "testosteron"));
		benutzer.get(benutzer.indexOf("heinz")).rollen.put(rollen.get(0), false); // Benutzerrolle
		benutzer.get(benutzer.indexOf("heinz")).rollen.put(rollen.get(1), true); // Gastrolle
	}
	
	public boolean canWriteInRole(String user, String role){
		
	}
	public boolean canReadInRole( String user, String role){
		
	}
	public boolean canCreateInRole(String user, String role){
		
	}
	public boolean login(String user, String password){
		
	}
	public boolean isRoleAdmin(String user, String role){
		
	}
	public boolean createRoleAmin(String user, String role);
	public boolean addUserToRole(String user, String role);
	public boolean remoteUserFromRole(String user, String role);
	public String[] listRoles(String user);
}

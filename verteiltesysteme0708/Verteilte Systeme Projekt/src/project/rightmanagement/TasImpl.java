package project.rightmanagement;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 * Dies ist die Implementierung des RMI Interfaces der Simple-Authorisation
 * @author mafolz
 */
public class TasImpl  extends UnicastRemoteObject implements TasInterface {

	private static final long serialVersionUID = 7771914302242479133L;
	//Alle Rollen die vergeben werden können
	private Vector<String> rollen;
	//Alle dem Server bekannten Benutzer
	private Vector<TasObject> benutzer;
	
	
	public TasImpl() throws RemoteException {	
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
		
		// erinnerung an Bitmaske. lesen,schreiben,erstellen,admin
		benutzer.add(new TasObject("peter", "lustig"));
		benutzer.get(0).addRole(
				rollen.get(1),true,false,false,false); // Gastrolle
	}
	
	public boolean canWriteInRole(String user, String role)throws RemoteException{
		// Temporäres vergleichsobjekt
		TasObject temp = new TasObject(user,"nichtleer");
		
		if(benutzer.contains(temp)){
			// Debuggausgabe
			System.out.println(user+" kann in "+role +" schreiben: ");
			System.out.println("\t"+benutzer.get(benutzer.indexOf(temp)).canWrite(role));
			return benutzer.get(benutzer.indexOf(temp)).canWrite(role);
		}
		return false;
	}
	public boolean canReadInRole( String user, String role)throws RemoteException{
		// Temporäres vergleichsobjekt
		TasObject temp = new TasObject(user,"nichtleer");
		
		if(benutzer.contains(temp)){
			// Debuggausgabe
			System.out.println(user+" kann in "+role +" lesen: ");
			System.out.println("\t"+benutzer.get(benutzer.indexOf(temp)).canRead(role));
			return benutzer.get(benutzer.indexOf(temp)).canRead(role);
		}
		return false;
	}
	public boolean canCreateInRole(String user, String role)throws RemoteException{
		// Temporäres vergleichsobjekt
		TasObject temp = new TasObject(user,"nichtleer");
		
		if(benutzer.contains(temp)){
			// Debuggausgabe
			System.out.println(user+" kann in "+role +" Dokumente erstellen: ");
			System.out.println("\t"+benutzer.get(benutzer.indexOf(temp)).canCreate(role));
			return benutzer.get(benutzer.indexOf(temp)).canCreate(role);
		}
		return false;
	}
	/**
	 * Liefert true wenn benutzer und PW eines TasOjbectes passen
	 */
	public boolean login(String user, String password)throws RemoteException{
		// Temporäres vergleichsobjekt
		TasObject temp = new TasObject(user,password);
		// Debuggausgabe
		System.out.println("Benutzer "+user+" logt sich gerade ein");
		System.out.println("\tPasswort: "+ password);
		
		if(benutzer.contains(temp)){
			System.out.println("\tist vorhanden !");
			return benutzer.get(benutzer.indexOf(temp)).login(password);
		}
		return false;
	}
	/**
	 * Gbit zurück ob der entsprechende beutzer in der angegebenen Role Administrator ist
	 */
	public boolean isRoleAdmin(String user, String role)throws RemoteException{
		// Temporäres vergleichsobjekt
		TasObject temp = new TasObject(user,"nichtleer");
		
		if(benutzer.contains(temp)){
			// Debuggausgabe
			System.out.println(user+" kann in "+role +" lesen:");
			System.out.println("\t"+benutzer.get(benutzer.indexOf(temp)).isAdmin(role));
			return benutzer.get(benutzer.indexOf(temp)).isAdmin(role);
		}
		return false;
		
	}
	/**
	 * erstellt Rollenadministrator, Aufrufender benutzer mus in der entsprechenden Rolle
	 * Administrator sein und der betrefende Benutzer muss ind er Rolle sein 
	 */
	public boolean createRoleAmin(String owner, String role , String user)throws RemoteException{
		// Temporäre vergleichsobjekte
		TasObject aufrufer	= new TasObject(owner,"nichtleer");
		TasObject temp		= new TasObject(user,"nichtleer");
		
		if(isRoleAdmin(owner,role) &&
				benutzer.get(benutzer.indexOf(aufrufer)).login &&
				benutzer.get(benutzer.indexOf(temp)).isRole(role) ){
			// Debuggausgabe
			System.out.println(owner+" macht "+user+" zu RoleAdmin in "+role);
			benutzer.get(benutzer.indexOf(temp)).setRole(role, true,true,true,true);
			return true;
		}
		return false;
			
	}	
	/**
	 * benutzer einer rolle hinzufügen mit standardlesereht
	 */
	public boolean addUserToRole(String owner, String role , String user)throws RemoteException{
		// Temporäre vergleichsobjekte
		TasObject aufrufer	= new TasObject(owner,"nichtleer");
		TasObject temp		= new TasObject(user,"nichtleer");
		
		if(isRoleAdmin(owner,role) &&
				benutzer.get(benutzer.indexOf(aufrufer)).login &&
				benutzer.get(benutzer.indexOf(temp)).isRole(role) ){
			// Debuggausgabe
			System.out.println(owner+" fügt "+user+" zu "+role+ " hinzu");
			benutzer.get(benutzer.indexOf(temp)).addRole(role, true,false,false,false);
			return true;
		}
		return false;		
	}
	/**
	 * entfernt benutzer von eienr Rolle, er Aufrufende benutzer muss in dieser
	 * Rolle Administrator sein
	 */
	public boolean removeUserFromRole(String owner, String role , String user)throws RemoteException{
		// Temporäre vergleichsobjekte
		TasObject aufrufer	= new TasObject(owner,"nichtleer");
		TasObject temp		= new TasObject(user,"nichtleer");
		
		if(isRoleAdmin(owner,role) &&
				benutzer.get(benutzer.indexOf(aufrufer)).login &&
				benutzer.get(benutzer.indexOf(temp)).isRole(role) ){
			// Debuggausgabe
			System.out.println(owner+" entfernt "+user+" von "+role);
			benutzer.get(benutzer.indexOf(temp)).removeRole(role);
			return true;
		}
		return false;				
	}
	
	public String[] listRoles(String user)throws RemoteException{
		// Temporäres vergleichsobjekt
		TasObject temp	= new TasObject(user,"nichtleer");
		
		if(benutzer.contains(temp)){
			// Debuggausgabe
			System.out.println(user+" erfragt seine Rollen");
			return benutzer.get(benutzer.indexOf(temp)).getRoles();
		}
		return new String[1];
	}
}

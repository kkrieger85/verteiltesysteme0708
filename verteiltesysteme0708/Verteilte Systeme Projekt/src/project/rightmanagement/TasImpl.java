package project.rightmanagement;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 * Dies ist die Implementierung des RMI Interfaces der Simple-Authorisation
 * auf der Serverseite. Hier k&ouml;nnen Benutzerdaten festgelegt werden.
 * @author mafolz
 */
public class TasImpl  extends UnicastRemoteObject implements TasInterface {

	private static final long serialVersionUID = 7771914302242479133L;
	//Alle Rollen die vergeben werden können
	private Vector<String> rollen;
	//Alle dem Server bekannten Benutzer
	private Vector<TasObject> benutzer;	
	
	/**
	 * Erstellt ein Objekt der TasImpl.
	 * Die Benutzerdaten werden hier festgelegt.
	 * @throws RemoteException
	 */
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
	/**
	 * Implementierung der abstrakten Methode.
	 * Pr&uuml;ft ob der Benutzer &uuml;berhaupt existiert und gibt
	 * dessen Schreibrecht in der Rolle zur&uuml;ck
	 */
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
	/**
	 * Implementierung der abstrakten Methode.
	 * Pr&uuml;ft ob der Benutzer &uuml;berhaupt existiert und gibt
	 * dessen Leserecht in der Rolle zur&uuml;ck
	 */
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
	/**
	 * Implementierung der abstrakten Methode.
	 * Pr&uuml;ft ob der Benutzer &uuml;berhaupt existiert und gibt
	 * dessen Recht in der Rolle Dokumente zu erstellen zur&uuml;ck
	 */
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
	 * Liefert wenn Benutzer und PW eines TasOjbectes passen
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
	 * Gbit zur&uuml;ck ob der entsprechende Benutzer in der angegebenen Rolle Administrator ist
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
	 * Erstellt Rollenadministrator, Aufrufender Benutzer muss in der entsprechenden Rolle
	 * Administrator sein und der betrefende Benutzer muss in der Rolle sein 
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
	 * Benutzer eine Rolle hinzuf&uuml;gen mit Standardleserecht
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
	 * Entfernt Benutzer von einer Rolle, der Aufrufende Benutzer muss in dieser
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
	/**
	 * Gibt die Rollen des angegebenen Benutzers wieder
	 */
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

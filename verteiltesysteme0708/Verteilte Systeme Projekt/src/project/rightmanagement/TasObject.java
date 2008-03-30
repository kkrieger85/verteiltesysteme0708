package project.rightmanagement;

import java.util.*;
/**
 * ein Benutzerobjekt, die &Uuml;berklasse muss darauf achten das die angegebene Rolle existiert
 * @author mafolz
 *
 */
public class TasObject implements Comparable{
	public String user;
	public boolean login = false;
	// Tabelle aus rollenstring und rechtebitmaske
	private Hashtable<String, TasRole> rollen;
	private String passwort;
	
	public TasObject(String user, String passwort){
		this.user=user;
		this.passwort=passwort;
		this.rollen=new Hashtable<String, TasRole>(40);
	}
	/**
	 * Login-Funktion welche da Passwort mit dem &uuml;bergebenen
	 * vergleicht.
	 * @param passwort
	 * @return
	 */
	public boolean login(String passwort){
		// Debugausgabe
		System.out.println("\tlogin:"+this.passwort.equals(passwort));
		
		if( this.passwort.equals(passwort)){
			login= true;
			return true;
		}
		return false;		
	}
	/**
	 * Benutzer eine Rolle zuweisen
	 * @param rolle
	 * @param lesen
	 * @param schreiben
	 * @param erstellen
	 * @param admin
	 */
	public void addRole(String rolle, boolean lesen, boolean schreiben, boolean erstellen, boolean admin){
		rollen.put( rolle, new TasRole(lesen,schreiben,erstellen,admin));
	}
	/**
	 * Benutzerrechte in einer Rolle setzen.
	 * @param rolle
	 * @param lesen
	 * @param schreiben
	 * @param erstellen
	 * @param admin
	 */
	public void setRole(String rolle, boolean lesen, boolean schreiben, boolean erstellen, boolean admin){
		rollen.get( rolle).lesen = lesen;
		rollen.get( rolle).schreiben = schreiben;
		rollen.get( rolle).erstellen = erstellen;
		rollen.get( rolle).admin = admin;
	}		
	/**
	 * Rolle einem benutzer vollst&auml;ndig entziehen.
	 * @param rolle
	 */
	public void removeRole(String rolle){
		rollen.remove(rolle);
	}
	public String[] getRoles(){
		String[] temp = new String[40];
		return (String[]) rollen.keySet().toArray(temp);
	}
	
	/**
	 * Abfrage ob Benutzer in der Rolle ist.
	 * @param rolle
	 * @return
	 */
	public boolean isRole(String rolle){
		return rollen.containsKey(rolle);
	}
	/**
	 * Abfrage ob benutzer in der angegebenen Rolle
	 * Rollenadministrator ist.
	 * @param rolle
	 * @return
	 */
	public boolean isAdmin(String rolle){
		if( isRole(rolle))
			return rollen.get(rolle).admin;
		return false;
	}
	/**
	 * Abfrage ob der Benutzer in der angegebenen Rolle
	 * lesen kann
	 * @param rolle
	 * @return
	 */
	public boolean canRead(String rolle){
		if( isRole(rolle))
			return rollen.get(rolle).lesen;
		return false;
	}
	/**
	 * Abfrage ob der Benutzer in der angegebenen Rolle
	 * schreiben kann
	 * @param rolle
	 * @return
	 */
	public boolean canWrite(String rolle){
		if( isRole(rolle))
			return rollen.get(rolle).schreiben;
		return false;
	}
	/**
	 * Abfrage ob der Benutzer in der angegebenen Rolle
	 * Dokumente erstellen kann
	 * @param rolle
	 * @return
	 */
	public boolean canCreate(String rolle){
		if( isRole(rolle))
			return rollen.get(rolle).erstellen;
		return false;
	}
	
	/**
	 * Vergleichsmethode &uuml;ber Benutzernamen, da Benutzernamen einmalig sein m&uuml;&szlig;en,
	 * kann dies so geschehen um schnell benutzer in einem Benutzervektor zu 
	 * finden
	 */ 
	public int compareTo(Object o){
		if(o instanceof TasObject){
			TasObject t = (TasObject)o;
			// Debug ausgabe
//			System.out.println("vergleiche "+ user+" mit "+ t.user);
//			System.out.println("\t"+user.compareTo(t.user));
			return user.compareTo(t.user);
		}else if(o instanceof String){
			String t = (String)o;
			return user.compareTo(t);
		}
		return -1;
	}
	public boolean equals(Object o){
		if(compareTo(o) == 0) {
//			System.out.println("ist gleich.");
			return true;
		}
		return false;
	}
}

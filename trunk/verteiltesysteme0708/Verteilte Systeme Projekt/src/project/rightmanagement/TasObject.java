package project.rightmanagement;

import java.util.*;
/**
 * ein Benutzerobjekt, die überklasse mus darauf achten das die angegebene Rolle existiert
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
	 * Login funktion
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
	 * eine Rolle hizufügen
	 * @param rolle
	 * @param lesen
	 * @param schreiben
	 * @param erstellen
	 * @param admin
	 */
	public void addRole(String rolle, boolean lesen, boolean schreiben, boolean erstellen, boolean admin){
		rollen.put( rolle, new TasRole(lesen,schreiben,erstellen,admin));
	}
	public void setRole(String rolle, boolean lesen, boolean schreiben, boolean erstellen, boolean admin){
		rollen.get( rolle).lesen = lesen;
		rollen.get( rolle).schreiben = schreiben;
		rollen.get( rolle).erstellen = erstellen;
		rollen.get( rolle).admin = admin;
	}		
	public void removeRole(String rolle){
		rollen.remove(rolle);
	}
	public String[] getRoles(){
		String[] temp = new String[40];
		return (String[]) rollen.keySet().toArray(temp);
	}
	
	/**
	 * Abfrage der berechtigungen
	 * @param rolle
	 * @return
	 */
	public boolean isRole(String rolle){
		return rollen.containsKey(rolle);
	}
	public boolean isAdmin(String rolle){
		if( isRole(rolle))
			return rollen.get(rolle).admin;
		return false;
	}
	public boolean canRead(String rolle){
		if( isRole(rolle))
			return rollen.get(rolle).lesen;
		return false;
	}
	public boolean canWrite(String rolle){
		if( isRole(rolle))
			return rollen.get(rolle).schreiben;
		return false;
	}
	public boolean canCreate(String rolle){
		if( isRole(rolle))
			return rollen.get(rolle).erstellen;
		return false;
	}
	
	/**
	 * vergleich über usernamen, da usernamen einmalig sein müßen,
	 * kann dies so geschehen um schnell benutzer in einem benutzervektor zu 
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

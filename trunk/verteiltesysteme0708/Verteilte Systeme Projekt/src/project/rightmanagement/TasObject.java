package project.rightmanagement;

import java.util.*;

public class TasObject implements Comparable{
	public String user;
	public String passwort;
	public Hashtable<String, Boolean> rollen;
	
	public TasObject(String user, String passwort){
		this.user=user;
		this.passwort=passwort;
		this.rollen=new Hashtable<String, Boolean>(40);
	}
	public boolean isRole(String rolle){
		return rollen.containsKey(rolle);
	}
	public boolean isAdmin(String rolle){
		if( isRole(rolle))
			return rollen.get(rolle);
		return false;
	}
	public int compareTo(Object o){
		if(o instanceof TasObject){
			TasObject t = (TasObject)o;
			return user.compareTo(t.user);
		}
		return -1;
	}
	public boolean equals(Object o){
		if(compareTo(o) == 0)
			return true;
		return false;
	}
}

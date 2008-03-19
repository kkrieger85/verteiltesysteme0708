package project.rightmanagement;

/**
 * Bitmaske der rechtevergabe einer Rolle pro benutzer
 * @author mafolz
 *
 */
public class TasRole {
	public boolean lesen,schreiben,erstellen,admin;
	public TasRole( boolean lesen, boolean schreiben, boolean erstellen, boolean admin){
		this.lesen=lesen;
		this.schreiben=schreiben;
		this.erstellen=erstellen;
		this.admin=admin;
	}
}

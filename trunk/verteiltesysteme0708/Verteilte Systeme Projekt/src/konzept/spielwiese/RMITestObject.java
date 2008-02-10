/**
 * 
 */
package konzept.spielwiese;

import java.io.Serializable;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class RMITestObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2995734813658047445L;

	private String nachname; 
	private String vorname; 
	private String matrnr;

	/**
	 * @param nachname
	 * @param vorname
	 * @param matrnr
	 */
	public RMITestObject(String nachname, String vorname, String matrnr) {
		this.nachname = nachname;
		this.vorname = vorname;
		this.matrnr = matrnr;
	}

	/**
	 * @return the matrnr
	 */
	public String getMatrnr() {
		return matrnr;
	}

	/**
	 * @param matrnr the matrnr to set
	 */
	public void setMatrnr(String matrnr) {
		this.matrnr = matrnr;
	}

	/**
	 * @return the nachname
	 */
	public String getNachname() {
		return nachname;
	}

	/**
	 * @param nachname the nachname to set
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	/**
	 * @return the vorname
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * @param vorname the vorname to set
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	/**
	 * toString Methode für das Objekt RMITestObject
	 * @return String 
	 */
	public String toString(){
		String returnValue = ""; 
		returnValue += "Name: " + this.getVorname() + " " + this.getNachname(); 
		return returnValue; 
	}

}

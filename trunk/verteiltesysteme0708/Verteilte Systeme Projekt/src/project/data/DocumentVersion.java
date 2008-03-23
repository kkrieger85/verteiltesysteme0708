
package project.data;

import java.util.Date;

/**
 *  Klasse die versionierungsspezifische Daten enth�lt.
 * @author Christian Schwerdtfeger
 *
 */
public class DocumentVersion {

	private int versionNumber;
	private DocumentVersion parent;
	private String author_username;
	private Date creationTime;
	private String comment;
	private String sperrender = "";
	private String sperrhost = "";
	private Date sperrzeit = null; 
	
	/**
	 * Erstellt ein leeres DocumentVersion Objekt
	 */
	
	public DocumentVersion() {
	}
	
	/**
	 * Gibt die Versionsnummer zur�ck
	 * @return Versionsnummer
	 */
	
	public int getVersionNumber() {
		return versionNumber;
	}
	
	/**
	 * Setzt die Versionsnummer auf einen neuen Wert
	 * @param versionNumber neuer Wert der Versionsnummer
	 */

	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
	}
	
	/**
	 * Gibt die Parent-Version zur�ck
	 * @return Parent-Version
	 */

	public DocumentVersion getParent() {
		return parent;
	}
	
	/**
	 * Setzt die Parent-Version
	 * @param parent neue Parent-Version
	 */

	public void setParent(DocumentVersion parent) {
		this.parent = parent;
	}
	
	/**
	 * Gibt den Autor zur�ck
	 * @return Autor
	 */

	public String getAuthor_username() {
		return author_username;
	}
	
	/**
	 * Setzt den Autor auf einen neuen Namen
	 * @param author_username neuer Name des Autors
	 */

	public void setAuthor_username(String author_username) {
		this.author_username = author_username;
	}
	
	/**
	 * gibt den Kommentar zur�ck
	 * @return Kommentar
	 */

	public String getComment() {
		return comment;
	}
	
	/**
	 * Setzt den Kommentar neu
	 * @param comment neuer Wert f�r den Kommentar
	 */

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	/**
	 * gibt die Erstellungszeit zur�ck
	 * @return Erstellungszeitpunkt
	 */

	public Date getCreationTime() {
		return creationTime;
	}
	
	/**
	 * Setzt ein neues Date Objekt f�r die Erstellungszeit
	 * @param creationTime neues Erstellungszeit
	 */

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	
	/**
	 * Gibt einen String zur�ck der das DocumentVersion Objekt repr�sentiert
	 */
	
	public String toString(){
		String tmp = "";
		tmp += author_username;
		tmp += ", " + comment;
		tmp += ", " + versionNumber;
		tmp += ", " + parent;
		tmp += ", " + getCreationTime().toString();
		return String.valueOf(versionNumber);
	}

	public String getSperrender() {
		return sperrender;
	}

	public void setSperrender(String sperrender) {
		this.sperrender = sperrender;
	}

	public String getSperrhost() {
		return sperrhost;
	}

	public void setSperrhost(String sperrhost) {
		this.sperrhost = sperrhost;
	}

	public Date getSperrzeit() {
		return sperrzeit;
	}

	public void setSperrzeit(Date sperrzeit) {
		this.sperrzeit = sperrzeit;
	}
	
}

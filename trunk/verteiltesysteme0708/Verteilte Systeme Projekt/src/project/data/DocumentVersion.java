
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
	private String authorUsername;
	private Computer authorHost;
	private Date creationTime;
	private String comment;
	
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

	public String getAuthorUsername() {
		return authorUsername;
	}
	
	/**
	 * Setzt den Autor auf einen neuen Namen
	 * @param authorUsername neuer Name des Autors
	 */

	public void setAuthorUsername(String authorUsername) {
		this.authorUsername = authorUsername;
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
		tmp += authorUsername;
		tmp += ", " + comment;
		tmp += ", " + versionNumber;
		tmp += ", " + parent;
		tmp += ", " + getCreationTime().toString();
		return String.valueOf(versionNumber);
	}

	public Computer getAuthorHost() {
		return authorHost;
	}

	public void setAuthorHost(Computer authorHost) {
		this.authorHost = authorHost;
	}
}


package project.data;

import java.util.Date;
import project.network.ServerDataObject;

/**
 *  Klasse die versionierungsspezifische Daten enth�lt.
 * @author Christian Schwerdtfeger
 *
 */
public class DocumentVersion {

	private int versionNumber;
	private DocumentVersion parent;
	private String authorUsername;
	private ServerDataObject authorHost;
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
		if (authorUsername != null)
			tmp += authorUsername;
		if (comment != null)
			tmp += ", " + comment;
		tmp += ", " + versionNumber;
		if (parent != null)
			tmp += ", " + parent;
		if (creationTime != null)
			tmp += ", " + getCreationTime().toString();
		return tmp;
	}
	
	/**
	 * Methode die den Host des Dokumentenautors zur�ckgibt.
	 * @return ServerDataObject des Autorenhosts.
	 */

	public ServerDataObject getAuthorHost() {
		return authorHost;
	}
	
	/**
	 * Setzt den Host des Dokumentenautors auf "authorHost".
	 * @param authorHost der neue Dokumentenautoren-Host.
	 */

	public void setAuthorHost(ServerDataObject authorHost) {
		this.authorHost = authorHost;
	}
}

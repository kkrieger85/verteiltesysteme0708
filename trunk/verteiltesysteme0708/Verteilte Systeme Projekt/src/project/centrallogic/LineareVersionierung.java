package project.centrallogic;

import project.data.*;
import project.helperclasses.NetworkHelper;
import project.helperclasses.XMLConfigHelper;

import java.io.File;
import java.util.Date;

/**
 * @author ab
 *
 */
public class LineareVersionierung implements Versionierung {
	// Die Anzahl Sekunden f�r die eine Sperre g�ltig bleibt.
	public static final int LOCK_TIME_IN_SECONDS = 24 * 60 * 60;
	
	public static class LockException extends Versionierung.Exception
	{
		public LockException(String msg) {
			super(msg);
		}

		private static final long serialVersionUID = 1L;		
	}
	
	/**
	 * Hilfsmethode. Pr�ft, ob das Dokument durch den angegeben Computer
	 * gesperrt wurde.
	 * 
	 * Hinweis: Diese Methode pr�ft nicht, ob die Sperre abgelaufen ist.
	 * Das ist so gewollt.
	 * 
	 * @param doc
	 * @param byHost
	 * @return
	 */
	public static boolean isDocumentLockedBy(Document doc, Computer byHost) {
		DocumentMetadata mdata = doc.getMetadata();

		return mdata.getSperrhost().getIP().equals(byHost.getIP());
	}
	
	/**
	 * Hilfsmethode.
	 * 
	 * Liefert die Zeit zur�ck, zu der eine Sperre fr�hstens angelegt werden
	 * musste, damit sie jetzt noch g�ltig ist.
	 * @return Date
	 */
	public static Date getEarliestValidLockTime()
	{
		Date now = new Date();
		
		Date result = new Date();
		result.setTime(now.getTime() - LOCK_TIME_IN_SECONDS);
		
		return result;
	}
	
	/**
	 * Hilfsmethode. Gibt zur�ck, ob ein Dokument aktuell g�ltig gesperrt ist.
	 * 
	 * @param doc Zu pr�fendes Dokument
	 * @return true falls das Dokument gelockt ist.
	 */
	public static boolean isDocumentLocked(Document doc)
	{
		DocumentMetadata mdata = doc.getMetadata();

		// �berhaupt keine Sperre vorhanden?
		//
		// Eigentlich m�ssten wir nur pr�fen, ob sperrender == null ist.
		// Ich habe aber kein besonders gro�es Vertrauen in die Konsistenz
		// der Metadaten. -- flgr
		if (mdata.getSperrender() == null ||
				mdata.getSperrhost() == null ||
				mdata.getSperrzeit() == null)
			return false;
		
		// Es ist eine Sperre vorhanden.
		//
		// Ist diese noch aktuell?
		Date lockTime = mdata.getSperrzeit();
		return lockTime.after(getEarliestValidLockTime());
	}
	
	/**
	 * Hilfsmethode.
	 * 
	 * Stellt sicher, dass das Dokument noch nicht gesperrt wurde.
	 * @param doc
	 * @throws LockException
	 */
	public static void ensureDocumentUnlocked(Document doc)
		throws LockException
	{
		if (isDocumentLocked(doc))
		{
			DocumentMetadata mdata = doc.getMetadata();
			
			throw new LockException(String.format(
					"Document %s has been locked by user %s (host %s) at %s.",
					doc, mdata.getSperrender(), mdata.getSperrhost(),
					mdata.getSperrzeit()));
		}
	}
	
	/**
	 * Hilfsmethode.
	 * 
	 * Stellt sicher, dass das Dokument durch den angegeben Computer gesperrt
	 * ist.
	 * 
	 * Hinweis: Diese Methode pr�ft nicht, ob die Sperre abgelaufen ist.
	 * Das ist so gewollt.
	 * 
	 * @param doc
	 * @param byHost
	 * @throws LockException 
	 */
	public static void ensureDocumentLockedBy(Document doc, Computer byHost)
		throws LockException
	{
		if (!isDocumentLockedBy(doc, byHost))
		{
			DocumentMetadata mdata = doc.getMetadata();
			
			throw new LockException(String.format(
					"Document %s wasn't locked by host %s before." +
					"It was locked by %s (host: %s) at %s.",
					doc, byHost,
					mdata.getSperrender(), mdata.getSperrhost(),
					mdata.getSperrzeit()));
		}
	}

	/**
	 * Ein Dokument f�r die Bearbeitung sperren.
	 * 
	 * Ein Dokument kann nur gesperrt werden, wenn es bisher noch nicht
	 * gesperrt ist.
	 * 
	 * Diese Methode wird auf dem Rechner aufgerufen, der der Autoren-Host
	 * des Dokuments ist. �bergabeparameter sind das Dokument, der Name
	 * des sperrenden Benutzers und der sperrende Rechner.
	 */
	public void lockDocument(Document doc, String byUser, Computer byHost)
		throws LockException
	{
		ensureDocumentUnlocked(doc);

		DocumentMetadata mdata = doc.getMetadata();
		
		mdata.setSperrender(byUser);
		mdata.setSperrhost(byHost);
		mdata.setSperrzeit(new Date());
		
		// Anschlie�end k�nnen bei byHost Metadaten f�r die Bearbeitung
		// der neuen Version angelegt werden.
	}
	
	/**
	 * Neues Dokument versionieren
	 * 
	 * @param doc	neues Dokument
	 */
	public void newDocument(Document doc, String comment) throws Exception {
		// Anmerkung: Zu diesem Zeitpunkt muss das Dokument bereits
		// ins Arbeitsverzeichnis kopiert worden sein!

		XMLConfigHelper xmlconfig = new XMLConfigHelper(); 
		NetworkHelper network = new NetworkHelper();
		
		DocumentVersion version = new DocumentVersion();
		
		version.setVersionNumber(1);
		version.setParent(null);
		version.setAuthorUsername(xmlconfig.getLoginname());
		version.setAuthorHost(new ComputerWrapper(network.getOwnIPAdress()));
		version.setCreationTime(new Date());
		version.setComment(comment);

		doc.setVersion(version);
		
		// Anmerkung: Anschliessend muss das Dokument an die vorgegeben Backup-
		// Rechner repliziert werden. Es kann dann im Netzwerk gefunden werden.
	}
	
	/**
	 * Verl�ngert die Sperre eines Dokuments.
	 * 
	 * Eine Sperre kann nur durch den bisherigen Inhaber der Sperre
	 * verl�ngert werden.
	 * 
	 * Diese Methode wird auf dem Rechner aufgerufen, der der Autoren-Host
	 * des Dokuments ist. �bergabeparameter sind das Dokument, der Name
	 * des sperrenden Benutzers und der sperrende Rechner.
	 * 
	 * @param doc
	 * @param byUser
	 * @param byHost
	 * @throws LockException
	 */
	public void renewDocumentLock(Document doc, String byUser, Computer byHost)
		throws LockException
	{
		ensureDocumentLockedBy(doc, byHost);
		
		doc.getMetadata().setSperrzeit(new Date());
	}
	
	/**
	 * Entfernt die Sperre eines Dokuments.
	 * 
	 * Eine Sperre kann nur durch den bisherigen Inhaber der Sperre
	 * entfernt werden.
	 * 
	 * Diese Methode wird auf dem Rechner aufgerufen, der der Autoren-Host
	 * des Dokuments ist. �bergabeparameter sind das Dokument, der Name
	 * des sperrenden Benutzers und der sperrende Rechner.
	 * 
	 * @param doc
	 * @param byUser
	 * @param byHost
	 * @throws LockException
	 */
	public void unlockDocument(Document doc, String byUser, Computer byHost)
		throws LockException
	{
		if (isDocumentLockedBy(doc, byHost))
		{
			DocumentMetadata mdata = doc.getMetadata();

			mdata.setSperrender(null);
			mdata.setSperrhost(null);
			mdata.setSperrzeit(null);
		}
		
		// Falls das Dokument bisher nicht durch den angegeben Computer
		// gesperrt war, kann die Anfrage stillschweigend ignoriert werden.
	}
	
	/**
	 * Methode zum Erfragen der neuen Versionsmetadaten bei der
	 * Ver�ffentlichung einer neuen Version eines Dokuments.
	 * 
	 * Eine neue Version kann nur durch den bisherigen Inhaber der Sperre
	 * ver�ffentlicht werden. Dies wird beim bisherigen Autoren-Host gepr�ft.
	 * 
	 * Diese Methode wird auf dem Rechner des �ndernden Autors aufgerufen.
	 * 
	 * Sie gibt die neue Version des Dokuments zur�ck.
	 */
	public DocumentVersion newVersion(Document doc, String comment)
	{
		XMLConfigHelper xmlconfig = new XMLConfigHelper(); 

		File file = new File(doc.getFile().getFilepath());
		Date lastModified = new Date(file.lastModified());
		
		DocumentVersion oldVersion = doc.getVersion();
		
		DocumentVersion newVersion = new DocumentVersion();
		newVersion.setVersionNumber(oldVersion.getVersionNumber() + 1);
		newVersion.setParent(oldVersion);
		newVersion.setAuthorUsername(xmlconfig.getLoginname());
		newVersion.setCreationTime(lastModified);
		newVersion.setComment(comment);
		
		return newVersion;
				
		// Anschlie�end erfolgt ein Release. Dazu werden alle Rechner im System
		// (mindestens jedoch die dedizierten Backup-Rechner und der ehemalige
		// Autoren-Host), die das Dokument vorliegen haben, �ber die neue Version
		// des Dokuments per Aufruf der Methode documentWasUpdated() informiert.
	}
	
	/**
	 * Diese Methode muss zur Ver�ffentlichung einer neuen Version bei allen
	 * bisherigen Backup-Rechnern und dem ehemaligen Autoren-Host aufgerufen
	 * werden.
	 * 
	 * Es wird gepr�ft, ob der ausl�sende Rechner eine g�ltige Sperre am
	 * Dokument besitzt.
	 * 
	 * Im Erfolgsfall werden die Metadaten so aktualisiert, dass die neue
	 * Version als offiziell gilt.
	 * 
	 * Andernfalls wird ein Fehler ausgel�st, den der ausl�sende Rechner
	 * behandeln muss.
	 */
	public void documentWasUpdated(Document doc, DocumentVersion newVersion,
			String byUser, Computer byHost)
		throws LockException
	{
		ensureDocumentLockedBy(doc, byHost);

		doc.setVersion(newVersion);
		
		this.unlockDocument(doc, byUser, byHost);
	}
}

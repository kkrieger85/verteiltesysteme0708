package project.data;

import java.util.LinkedList;
import project.network.*;


/**
 * Klasse die verteilungsspezifische Informationen enth�lt.
 * 
 * @author Christian Schwerdtfeger
 *
 */

public class DocumentDistribution {

	// Backup-Rechner
	// @TODO: korrekt implementieren
	private LinkedList<ServerDataObject> backup;
	
	/**
	 * Erstellt aus einem Vektor von Computer ein DocumentDistribution Objekt
	 * @param backups
	 */
	
	public DocumentDistribution(LinkedList<ServerDataObject> backups) 
	{
		this.backup = backups; 
	}
	
	/**
	 * Gibt einen Vektor mit den Backup-Rechnern zur�ck
	 * @return Vector<Computer> welcher die Backuprechner enth�lt.
	 */
	
	public LinkedList<ServerDataObject> getBackups(){
		return backup;
	}
	
	/**
	 * F�gt einen neuen Backuprechner hinzu.
	 * @param newBackup neuer Backuprechner
	 */
	
	public void addComputer(ServerDataObject newBackup)
	{
		backup.add(newBackup);
	}
	
	/**
	 * Entfernt einen Backuprechner
	 * @param oldBackup zu entfernender Backuprechner
	 */
	
	public void removeComputer(ServerDataObject oldBackup)
	{
		backup.remove(oldBackup);
	}

	/**
	 * gibt eine String Representation des DocumentDistribution Objektes zur�ck
	 */
	public String toString() {
		return backup.toString();
	}
	
}

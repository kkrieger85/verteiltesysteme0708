package project.data;

import java.util.Vector;

/**
 * Klasse die verteilungsspezifische Informationen enthält.
 * 
 * @author Christian Schwerdtfeger
 *
 */

public class DocumentDistribution {

	// Backup-Rechner
	// @TODO: korrekt implementieren
	private Vector<Computer> backup;
	
	/**
	 * Erstellt aus einem Vektor von Computer ein DocumentDistribution Objekt
	 * @param backups
	 */
	
	public DocumentDistribution(Vector<Computer> backups) 
	{
		this.backup = backups; 
	}
	
	/**
	 * Gibt einen Vektor mit den Backup-Rechnern zurück
	 * @return Vector<Computer> welcher die Backuprechner enthält.
	 */
	
	public Vector<Computer> getBackups(){
		return backup;
	}
	
	/**
	 * Fügt einen neuen Backuprechner hinzu.
	 * @param newBackup neuer Backuprechner
	 */
	
	public void addComputer(Computer newBackup)
	{
		backup.add(newBackup);
	}
	
	/**
	 * Entfernt einen Backuprechner
	 * @param oldBackup zu entfernender Backuprechner
	 */
	
	public void removeComputer(Computer oldBackup)
	{
		backup.remove(oldBackup);
	}

	/**
	 * gibt eine String Representation des DocumentDistribution Objektes zurück
	 */
	public String toString() {
		return backup.toString();
	}
	
}

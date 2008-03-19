package project.data;

import java.util.Vector;

public class DocumentDistribution {

	// Backup-Rechner
	// @TODO: korrekt implementieren
	private Vector<Computer> backup;
	
	public DocumentDistribution(Vector<Computer> backups) 
	{
		this.backup = backups; 
	}
	
	public void addComputer(Computer newBackup)
	{
		backup.add(newBackup);
	}
	
	public void removeComputer(Computer oldBackup)
	{
		backup.remove(oldBackup);
	}

	/*
	 * toString()
	 */
	public String toString() {
		String ausgabe = "{";
		for (int i = 0; i < backup.size(); i++)
		{
			ausgabe += backup.elementAt(i) + ", ";
		}
		ausgabe += "}";
		return ausgabe;
	}
	
}

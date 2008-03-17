package project.helperclasses;

import java.io.File;

/**
 * 
 * @author Christian Schwerdtfeger
 * 
 * Diese Klasse dient zum feststellen des durch Dokumente des Systems benutzten und des
 * noch verfügbaren Speicherplatzes.
 *
 */

public class DiskSpaceHelper {
	
	/**
	 * Methode die den noch verfügbaren Speicherplatz auf der Partition auf der das Programm
	 * installiert wurde zurückgibt.
	 * 
	 * @return verfügbarer Speicherplatz in bytes.
	 * 
	 * @throws SecurityException wenn der angemeldete Benutzer keine Schreibrechte für das
	 * Verzeichnis hat.
	 */
	
	public long getMyAvailableSpace()throws SecurityException{
		File mainDir = new File ("../");
		return mainDir.getFreeSpace();
	}
	
	/**
	 * Methode die den bereits vom System verwendeten Speicherplatz zurückgibt.
	 * @return verwendeter Speicherplatz in bytes.
	 */
	
	public long getMyUsedSpace(){
		return getDirUsedSpace("../");
	}
	
	/**
	 * Methode die den verbrauchten Speicherplatz für ein spezifiziertes Directory zurückgibt.
	 * Wird von getMyUsedSpace verwendet.
	 * 
	 * @param dirpath das Directory das untersucht werden soll.
	 * 
	 * @return vom Directory dirpath belegter Speicherplatz in bytes.
	 * 
	 */
	
	public long getDirUsedSpace(String dirpath)
	{
		long dirsize = 0;
		File dir = new File (dirpath);
		if (dir.isDirectory())
		{
			File[] fileList = dir.listFiles();
			for (int i = 0; i < fileList.length; i++)
			{
				if (fileList[i].isDirectory())
				{
					dirsize += getDirUsedSpace(fileList[i].getPath());
				}
				else
				{
					dirsize += fileList[i].length();
				}
			}
		}
		return dirsize;
	}

}

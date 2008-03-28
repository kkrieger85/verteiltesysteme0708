package project.centrallogic;

import project.data.*;
import java.io.*;
import java.util.*;
import project.network.*;

/**
 * @author Christian Schwerdtfeger
 * 
 * Klasse zum Suchen geeigneter Backup-Rechner im Netzwerk.
 *
 */
public class VerteilungInst implements Verteilung {

	/**
	 * Methode die eine Liste von Computern zurückgibt.
	 * @param doc Das Dokument für das Backup-Rechner gesucht werden
	 * @param anzahl Die Anzahl der Backup-Rechner die gesucht werden sollen.
	 */
	public LinkedList<ServerDataObject> distributeDocument(Document doc, int anzahl){

		//zuerst einmal holen wir uns die größe unseres dokumentes.
		
		File docfile = new File (doc.getFile().toString());
		long groesse = docfile.length();
		
		// wir schnappen uns einfach mal den ersten Rechner aus unserer IP-Liste ...
		NetworkFacade network = new NetworkFacade();
		LinkedList<ServerDataObject> computers = network.getIPList();
		
		if (computers.size() < anzahl)
		{
			anzahl = computers.size();
		}
		
		// wir durchsuchen die IP-Liste und suchen uns 3 Rechner aus
		
		for (int i = 0; i < computers.size() - 1; i++)
		{
			for (int j = i; j < computers.size() - 1; j++)
			{
				long firstspace; // prozentuale Anzahl des benutzten Speicherplatzes für computer(j)
				long secondspace; // prozentuale Anzahl des benutzten Speicherplatzes für computer(j+1)
				
				firstspace = ((ServerDataObject)computers.get(j)).getFreespace();
				secondspace = ((ServerDataObject)computers.get(j)).getFreespace();
				if (firstspace > secondspace)
				{
					ServerDataObject temp = (ServerDataObject)computers.get(j);
					computers.set(j, computers.get(j+1));
					computers.set(j+1, temp);
				}
			}
		}
		
		LinkedList<ServerDataObject> complist = new LinkedList<ServerDataObject>();
		
		for (int i = 0; i < anzahl; i++)
		{
			//Wenn der Speicherplatz größer als die Größe des Dokumentes ist kommt der Computer in die Liste.
			if (((ServerDataObject)computers.get(i)).getFreespace() > groesse)
			{
				complist.add((ServerDataObject)computers.get(i));
			}
		}
		
	
		return complist;
	}

}

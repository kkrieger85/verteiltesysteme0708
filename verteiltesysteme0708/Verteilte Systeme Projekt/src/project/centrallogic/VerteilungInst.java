package project.centrallogic;

import project.data.*;
import java.io.*;
import java.util.*;

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
	public Vector<Computer> distributeDocument(Document doc, int anzahl){

		//zuerst einmal holen wir uns die größe unseres dokumentes.
		
		File docfile = new File (doc.getFile().toString());
		long groesse = docfile.length();
		
		// wir schnappen uns einfach mal den ersten Rechner aus unserer IP-Liste ...
		Vector<Computer> computers = project.Main.network.getIPListe();
		
		if (computers.size() < anzahl)
		{
			anzahl = computers.size();
		}
		
		// wir durchsuchen die IP-Liste und suchen uns 3 Rechner aus
		
		for (int i = 0; i < computers.size() - 1; i++)
		{
			for (int j = i; j < computers.size() - 1; j++)
			{
				long firstpercents; // prozentuale Anzahl des benutzten Speicherplatzes für computer(j)
				long secondpercents; // prozentuale Anzahl des benutzten Speicherplatzes für computer(j+1)
				
				firstpercents = ((ComputerWrapper)computers.get(j)).getUsedSpace() / (((ComputerWrapper)computers.get(j)).getAvailableSpace() / 100);
				secondpercents = ((ComputerWrapper)computers.get(j)).getUsedSpace() / (((ComputerWrapper)computers.get(j)).getAvailableSpace() / 100);
				if (firstpercents > secondpercents)
				{
					ComputerWrapper temp = (ComputerWrapper)computers.get(j);
					computers.setElementAt(computers.get(j+1), j);
					computers.setElementAt(temp, j+1);
				}
			}
		}
		
		Vector<Computer> complist = new Vector<Computer>();
		
		for (int i = 0; i < anzahl; i++)
		{
			//Wenn der Speicherplatz größer als die Größe des Dokumentes ist kommt der Computer in die Liste.
			if (((ComputerWrapper)computers.get(i)).getAvailableSpace() > groesse)
			{
				complist.add((ComputerWrapper)computers.get(i));
			}
		}
		
	
		return complist;
	}

}

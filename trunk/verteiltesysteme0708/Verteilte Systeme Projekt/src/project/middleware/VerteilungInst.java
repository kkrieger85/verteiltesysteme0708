package project.middleware;

import project.data.*;
import java.io.*;
import java.util.*;

/**
 * @author ab
 *
 */
public class VerteilungInst implements Verteilung {

	/**
	 * Dokument jetzt letztendlich im Netzwerk verteilen
	 * 
	 */
	public Computer[] distributeDocument(Document doc, int anzahl) throws Exception {

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
		
		ComputerWrapper[] complist = new ComputerWrapper[anzahl];
		
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
		
		for (int i = 0; i < anzahl; i++)
		{
			if (((ComputerWrapper)computers.get(i)).getAvailableSpace() - ((ComputerWrapper)computers.get(i)).getUsedSpace() > groesse)
			{
				complist[i] = (ComputerWrapper)computers.get(i);
			}
		}
		
		
		
		// ... und verschicken das Dokument an ihn ...
	
		return complist;
	}
	
}

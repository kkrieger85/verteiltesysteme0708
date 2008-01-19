package ueb01;

import java.net.*;
import java.io.*;

import ueb01.*;


public class Client {

	BufferedReader empfangen;
	BufferedWriter senden;
	Socket clientSocket;
	String puffer;		// Wird zum auslesen der Rueckgaben verwendet

	public Client(String ip) {
		try {
			System.out.println("!> Verbinde zu "+ip);
			clientSocket = new Socket(InetAddress.getByName(ip), 4242);
			
			// Klasse Socket besitzt Methoden
			// getInputStream bzw. getOutputStream
			empfangen = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));
			senden = new BufferedWriter(
					 new OutputStreamWriter(clientSocket.getOutputStream()));

			// Haendeschuetteln mit dem Server starten
			System.out.println("?> Server bereit?"); 
			senden.write("Ja ich bin da.\n");
			senden.flush();
			
			/* auf Bestaetigung warten,			
			 * Es wird getrimt, weil der C-Server
			 * einen String der gesammten
			 * Bufferlaenge zurückgiebt
			 */
			puffer = empfangen.readLine().trim();
			
			if(puffer.equals("Ich hoehre...")){				
				System.out.println("!> Server ist bereit!"); 
				// Solange anmelden lassen bis Authorisiert
				while(!einLoggen());
				
			}else{
				// Server antwortet nicht, voll? anderer Server als gewollt?
				System.out.println("!> Verbinden zu "+ip+" nicht moeglich!");
				System.out.println("\t> er antwortete:"+ puffer);
			}
			
			// Socket schliessen, Programm beenden
			clientSocket.close();			
			System.exit(0);
			
		} catch (Exception e) {
			System.out.println("!> Fehler:" +e.getMessage());
		}
	}

	/**
	 * Diese Methode dient zum interaktiven einloggen auf dem Server
	 * @return True wenn vom Server Authorisiert
	 */
	public boolean einLoggen() throws IOException{
		boolean autorisieren= false;
		
		while(!autorisieren){			//Laeuft solange bis Alles stimmt
			while(!benutzer()){};		//Frage solange Benutzer stimmt nach Benutzername
			autorisieren=passwort();	// Frage nach Passwort
		}		
		return true;
	}	
	
	/**
	 * Vereinfacht das 3 Fache Abfragen des Benutzernamens falls dieser Falsch ist
	 * @return
	 * @throws IOException
	 */
	private boolean benutzer() throws IOException{
		// Benutzernamen eingeben	
		System.out.print("$> Benutzername:\t");	
		puffer = Stdin.readString();
		
		// Benutzernamen senden
		senden.write(puffer.trim());
		senden.newLine();
		senden.flush();
				
		System.out.println("test");
		// Vom Server bestaetigen lassen
		puffer = empfangen.readLine().trim();			
		System.out.println("test2"); // Hier kommt er nie an, obwohl server fertig sendet
		
		if(puffer.equals("Stimmt!"))
			return true;
		else if(puffer.equals("Falsch"))
			return false;
		else
			throw new IOException(puffer);		
	}
	
	/**
	 * Vereinfacht das 3 Fache Abfragen des Passwortes falls dieser Falsch ist
	 * @return
	 * @throws IOException
	 */
	private boolean passwort() throws IOException{
		// Passwort eingeben
		System.out.print("$> Passwort(KLARTEXT!):\t");	
		puffer = Stdin.readlnString();
		
		// Passwort senden
		senden.write(puffer.trim());
		senden.newLine();
		senden.flush();
				
		// Vom Server bestaetigen lassen
		puffer = empfangen.readLine().trim();	
											
		if(puffer.equals("Stimmt!"))
			return true;
		else if(puffer.equals("Falsch"))
			return false;
		else
			throw new IOException(puffer);		
	}	
	/**
	 * Mainmethode welche den Start des Clients anregt, und die IP des servers Festlegt
	 * @param args
	 */
	public static void main(String args[]) {
		// f�r schnelleres Testen feste ip.
		Client test = new Client("192.168.0.3");
	}

}

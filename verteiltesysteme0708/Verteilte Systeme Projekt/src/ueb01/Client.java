package ueb01;

import java.net.*;
import java.io.*;


public class Client {

	BufferedReader empfangen;
	PrintStream senden;
	Socket clientSocket;

	public Client(String ip) {
		try {
			System.out.println("!> Verbinde zu "+ip);
			clientSocket = new Socket(InetAddress.getByName(ip), 4242);
			
			// Klasse Socket besitzt Methoden
			// getInputStream bzw. getOutputStream, hier
			// Konversion zu DataInputStream / PrintStream:
			empfangen = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));
			senden = new PrintStream(clientSocket.getOutputStream());

			// Händeschütteln mit dem Server starten
			System.out.println("?> Server bereit?"); 
			senden.print("Ja ich bin da.");
			
			// auf Bestätigung warten
			if(empfangen.readLine() == "Ich hoehre..."){				
				System.out.println("!> Server ist bereit!"); 
				// Solange anmelden lassen bis Authorisiert
				while(!einLoggen());
			}else{
				// Server antwortet nicht, voll? anderer Server als gewollt?
				System.out.println("!> Verbinden zu "+ip+" nicht möglich!");
			}
			
			// Socket schließen, Programm beenden
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
		// Benutzernamen eingeben
		System.out.print("$> Benutzername:\t");
		senden.println(Stdin.readString());
		// Vom Server bestätigen lassen
		if(empfangen.readLine() != "Stimmt!")
			return false;
		
		// Passwort eingeben
		System.out.print("$> Passwort(KLARTEXT!):\t");
		senden.println(Stdin.readString());	
		// Vom Server bestätigen lassen
		if(empfangen.readLine() != "Stimmt!")
			return false;
		
		return true;
	}	
	
	/**
	 * Mainmethode welche den Start des Clients anregt, und die IP des servers Festlegt
	 * @param args
	 */
	public static void main(String[] args) {
		// für schnelleres Testen feste ip.
		Client test = new Client("192.168.0.3");
	}

}

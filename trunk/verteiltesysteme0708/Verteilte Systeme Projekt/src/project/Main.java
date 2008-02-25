package project;

import konzept.diverse.abnetwork.Network;
import konzept.diverse.abnetwork.NetworkInst;
import konzept.diverse.abnetwork.Server;
import konzept.diverse.abnetwork.ServerInst;
import project.gui.*;
import project.middleware.*;
import project.network.*;

/**
 * Main-Klasse, die den Startvorgang der Software durchführt
 * und alle global nötigen Module als Singleton enthält
 * @author ab
 * 
 */
public class Main {

	// Programmbereich Netzwerk: Netzwerkdienste (Client) und Netzwerkserver
	public static Server server;
	public static Network network;

	// Programmbereich Middleware
	public static Versionierung vers;
	public static Verteilung vert;
	public static Rechteverwaltung rechte;
	
	public static Middleware middleware;
	
	// Programmbereich GUI
	public static GUI gui;
	
	public static void main(String[] args) {

		// zuerst starten wir den Server, arbeitet unabhängig von allen anderen Modulen
		server = new ServerInst();

		// dann starten wir die clientseitigen Netzwerkfunktionen ...
		network = new NetworkInst();
		
		// dann die ganzen Module der Middleware starten
		vers  = new LineareVersionierung();
		vert  = new VerteilungInst();
		rechte= new RechteverwaltungInst();
		
		middleware = new MiddlewareInst();

		// und zuletzt die GUI
		gui = new GUIInst();
		
		// und wir starten das grafische Benutzerinterface
		gui.start();
		
	}

}

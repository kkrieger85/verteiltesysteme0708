package konzept;

// Konfiguration, globale Konstanten, ...

public class Configuration {

	// wieviele Backups werden pro Datei vorgehalten?
	public static final int numOfBackups = 3;
	
	// Retry Time 30 Minuten
	public static final int retryTime = 1000 * 60 * 30;
	
	// unsere ActionQ
	public static ActionQ actionQ = new ActionQ();
	
	// unser Netzwerk
	public static Network network = new Network();
}

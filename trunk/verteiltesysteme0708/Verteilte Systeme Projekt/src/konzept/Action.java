package konzept;

// root-Action, hiervon werden alle anderen Actions abgeleitet

public class Action implements Runnable {

	// Zeitpunkt, an dem die Action wieder angestossen werden soll
	long triggerTime;
	
	public void run() {}
}

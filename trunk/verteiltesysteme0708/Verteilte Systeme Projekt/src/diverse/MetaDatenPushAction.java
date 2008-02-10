package diverse;

public class MetaDatenPushAction extends Action {

	// welche Metadaten auf welchen Computer pushen?
	Computer computer;
	Document doc;

	public MetaDatenPushAction(Computer computer, Document doc) {
		this.computer = computer;
		this.doc = doc;
	}
	
	public void run() {
		// einfach solange versuchen bis erfolgreich
		if (!Configuration.network.transferFile(computer, doc, true)) {
			triggerTime = System.currentTimeMillis() + Configuration.retryTime;
			Configuration.actionQ.add(this);
		}
	}
	
}

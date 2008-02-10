package project.data;

public class DocumentDistribution {

	// Backup-Rechner
	// @TODO: korrekt implementieren
	private Computer backup1 = null, backup2 = null, backup3 = null;
	
	public DocumentDistribution(Computer backup1) {
		this.backup1 = backup1; 
	}

	/*
	 * toString()
	 */
	public String toString() {
		return "(" + backup1 + "," + backup2 + "," + backup3 + ")";
	}
	
}

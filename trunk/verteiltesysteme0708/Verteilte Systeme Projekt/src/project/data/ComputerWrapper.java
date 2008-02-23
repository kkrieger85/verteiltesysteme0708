/**
 * 
 */
package project.data;

/**
 * @author ab
 *
 */
public class ComputerWrapper implements Computer {

	// IP des Computers
	private String IP;

	private long speicherPlatz_available;
	private long speicherPlatz_used;
	// ...
	
	/**
	 * Konstruktor
	 * 
	 * @param IP
	 */
	public ComputerWrapper(String IP) {
		this.IP = IP;
	}
	
	public long getAvailableSpace(){
		return speicherPlatz_available;
	}
	
	public long getUsedSpace(){
		return speicherPlatz_used;
	}

	/*
	 * toString()
	 */
	public String toString() {
		return IP;
	}
	
}

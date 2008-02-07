/**
 * 
 */
package dd.data;

/**
 * @author ab
 *
 */
public class ComputerWrapper implements Computer {

	// IP des Computers
	private String IP;

	// private long speicherPlatz_avail ?
	// private long speicherPlatz_free  ?
	// ...
	
	/**
	 * Konstruktor
	 * 
	 * @param IP
	 */
	public ComputerWrapper(String IP) {
		this.IP = IP;
	}

	/*
	 * toString()
	 */
	public String toString() {
		return IP;
	}
	
}

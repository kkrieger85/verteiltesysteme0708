/**
 * 
 */
package project.data;

/**
 * @author ab
 *
 */
public interface Computer {
	public long getAvailableSpace();
	
	public long getUsedSpace();
	
	public String getIP();
	public void setIP(String ip);
}


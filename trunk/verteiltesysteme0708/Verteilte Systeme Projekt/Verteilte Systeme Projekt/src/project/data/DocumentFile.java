package project.data;

/**
 * @author ab
 *
 */
public class DocumentFile {

	private String filepath;
	
	public DocumentFile(String filepath)
	{
		this.filepath = filepath;
	}
	
	public void setFilepath(String filepath)
	{
		this.filepath = filepath;
	}
	/*
	 * toString()
	 */
	public String toString() {
		return filepath;
	}
	
}

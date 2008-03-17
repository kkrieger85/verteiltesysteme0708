/**
 * 
 */
package project.data;

import java.util.Date;

/**
 * @author ab
 *
 */
public class DocumentVersion {

	private int versionNumber;
	private DocumentVersion parent;
	private String author_username;
	private Date creationTime;
	private String comment;
	
	public DocumentVersion() {
	}
	
	public int getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
	}

	public DocumentVersion getParent() {
		return parent;
	}

	public void setParent(DocumentVersion parent) {
		this.parent = parent;
	}

	public String getAuthor_username() {
		return author_username;
	}

	public void setAuthor_username(String author_username) {
		this.author_username = author_username;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public int getVersion() {
		return versionNumber;
	}
	
}

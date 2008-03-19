package project.centrallogic;

import project.data.*;
import java.util.Date;

/**
 * @author ab
 *
 */
public class LineareVersionierung implements Versionierung {

	/**
	 * Neues Dokument versionieren
	 * 
	 * @param doc	neues Dokoument
	 */
	public void newDocument(Document doc, String comment) throws Exception {
		
		DocumentVersion version = new DocumentVersion();
		version.setVersionNumber(1);
		version.setParent(null);
		version.setAuthor_username("testauthor");
		version.setCreationTime(new Date());
		version.setComment(comment);
		doc.setVersion(version);
		
	}

	public void updateDocument(Document doc, String comment) throws Exception {
		DocumentVersion version = new DocumentVersion();
		version.setVersionNumber(0);
		version.setParent(doc.getVersion());
		version.setAuthor_username("testauthor");
		version.setCreationTime(new Date());
		version.setComment(comment);
		doc.setVersion(version);
	}
	
	
	
}

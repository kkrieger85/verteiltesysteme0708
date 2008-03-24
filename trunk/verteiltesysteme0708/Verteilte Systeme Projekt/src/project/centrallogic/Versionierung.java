package project.centrallogic;

import project.data.*;

/**
 * @author ab
 *
 */
public interface Versionierung {
	public static class Exception extends java.lang.Exception
	{
		public Exception(String msg) {
			super(msg);
		}

		private static final long serialVersionUID = 1L;
	}
	
	/**
	 * Neues Dokument versionieren
	 * 
	 * @param doc	neues Dokoument
	 * @param comment
	 */
	public void newDocument(Document doc, String comment)
		throws Exception;
	
	/**
	 * Neue Version eines Dokuments anlegen.
	 * @param doc
	 * @param comment
	 * @return die neue Version
	 * @throws Exception
	 */
	public DocumentVersion newVersion(Document doc, String comment)
		throws Exception;

	/**
	 * Wird bei entfernten Rechnern aufgerufen um sie über eine neue Version
	 * eines Dokuments zu informieren. Diese aktualisieren ihre Metadaten
	 * entsprechend.
	 * 
	 * @param doc
	 * @param newVersion
	 * @param byUser
	 * @param byHost
	 * @throws Exception
	 */
	public void documentWasUpdated(Document doc, DocumentVersion newVersion,
			String byUser, Computer byHost)
		throws Exception;
}

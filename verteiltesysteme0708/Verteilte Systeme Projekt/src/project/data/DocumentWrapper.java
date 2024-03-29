/**
 * 
 */
package project.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import project.exception.DocumentWrapperException;
import project.helperclasses.DDLogger;
import project.network.ServerDataObject;

/**
 * Kapselt alle m�glichen Metadaten zu einem Dokument
 * 
 * @author Christian Schwerdtfeger
 *
 */
public class DocumentWrapper implements project.data.Document {


	/**
	 * Informationen �ber physikalischen Speicherort
	 */ 
	private DocumentFile dfile 				= null;

	/**
	 * Metadaten des Dokuments
	 */ 
	private DocumentMetadata dmeta 			= null;
	
	/**
	 * Versionsinformationen zur aktuellen Version des Dokuments
	 */
	private DocumentVersion lastVersion 		= null;
	
	/**
	 * Verteilungs- (Backup-) Informationen �ber das Dokument 
	 */
	private DocumentDistribution dbackup 	= null;
	
	

	/**
	 * Konstruktor, um ein neues lokales Dokument anzulegen.
	 * 
	 * Es wird �berpr�ft, ob das Dokument lokal vorliegt
	 * (lokal vorliegen == f ist nicht null)
	 * 
	 * @param f 	DocumentFile		physikalischer Ort der Metadaten, muss in diesem Fall lokal sein	
	 * @param m		DocumentMetadata	Metadaten des Dokuments
	 */
	
	public DocumentWrapper(DocumentFile f, DocumentMetadata m) throws Exception {
		if (f == null)
			throw new Exception("Dokument muss lokal vorliegen");
		if (m == null)
			throw new Exception("Dokument muss mindestens minimale Metainformationen haben");
		this.dfile = f;
		this.dmeta = m;
	}
	
	/**
	 * Standardkonstruktor. Wird von static Methode "loadFromXml(String)" verwendet und sollte
	 * ansonsten nicht benutzt werden.
	 */
	
	private DocumentWrapper()
	{
	}
	

	/**
	 * isLocal
	 * 
	 * @return	ist die Datei lokal und g�ltig, also sind mindestens Informationen
	 * 			�ber Speicherort sowie Metadaten vorhanden ?
	 */
	public boolean isValid() {
		return (dfile != null && dmeta != null);
	}

	/**
	 * Methode die die Versionierungsinformationen zur�ckgibt.
	 */

	public DocumentVersion getVersion() {
		return lastVersion;
	}
	
	/**
	 * Setzt die Versionierungsinformationen auf "dversion".
	 */

	public void setVersion(DocumentVersion dversion) {
		this.lastVersion = dversion;
	}
	
	/**
	 * Methode die die Verteilungsinformationen zur�ckgibt.
	 */

	public DocumentDistribution getDistribution() {
		return dbackup;
	}
	
	/**
	 * Setzt die Verteilungsinformationen auf "dbackup"
	 * @param dbackup die neuen Backupinformationen 
	 */

	public void setDistribution(DocumentDistribution dbackup) {
		this.dbackup = dbackup;
	}
	
	/**
	 * Setzt die Fileinformationen auf "dfile"
	 * @param dfile die neuen Fileinformationen
	 */
	
	public void setFile(DocumentFile dfile){
		this.dfile = dfile;
	}
	
	/**
	 * Setzt die Metadaten auf "dmeta"
	 * @param dmeta die neuen Metainformationen
	 */
	
	public void setMetadata(DocumentMetadata dmeta){
		this.dmeta = dmeta;
	}
	
	/**
	 * Methode die die Fileinformationen zur�ckgibt.
	 */
	
	public DocumentFile getFile() {
		return dfile;
	}
	
	/**
	 * Methode die die Metadaten zur�ckgibt.
	 */

	public DocumentMetadata getMetadata() {
		return dmeta;
	}
	
	/**
	 * Methode die das DocumentWrapper-Objekt in eine XML-Datei abspeichert.
	 * Sollte nach jeder �nderung an den Metadaten aufgerufen werden um diese zu sichern.
	 */
	
	public void saveToXml(){
		File xmlFile = new File(dfile.toString() + ".xml");
		Document doc = new Document();
		Element root;
		try{
			if (xmlFile.exists())
			{
				xmlFile.delete();
			}
			xmlFile.createNewFile();
			xmlFile.setWritable(true);
			if (doc.hasRootElement()) {
				root = doc.getRootElement();
			} else {
				root = new Element("root");
				doc.setRootElement(root);
			}
			//beschreibung eintragen
			Element dataObjElem = new Element("beschreibung");
			dataObjElem.setText(dmeta.getBeschreibung());
			root.addContent(dataObjElem);
			
			//Rollen eintragen
			Vector<String> rollen = new Vector<String>();
			rollen = dmeta.getRolle();
			for (int i = 0; i < rollen.size(); i++)
			{
				dataObjElem = new Element("rolle" + i);
				dataObjElem.setText(rollen.get(i));
				root.addContent(dataObjElem);
			}
			
			
			//Versionsnummer eintragen
			dataObjElem = new Element("versionNumber");
			dataObjElem.setText("" + lastVersion.getVersionNumber());
			root.addContent(dataObjElem);
			
			//Parent eintragen
			dataObjElem = new Element("parent");
			dataObjElem.setText("" + lastVersion.getParent().getVersionNumber());
			root.addContent(dataObjElem);
			
			//authorUsername eintragen
			dataObjElem = new Element("authorUsername");
			dataObjElem.setText(lastVersion.getAuthorUsername());
			root.addContent(dataObjElem);

			//authorHost eintragen
			dataObjElem = new Element("authorHost");
			dataObjElem.setText(lastVersion.getAuthorHost() != null ? lastVersion.getAuthorHost().getAddress() : "");
			root.addContent(dataObjElem);
			
			//CretionTime eintragen
			dataObjElem = new Element("creationTime");
			DateFormat datef = DateFormat.getDateTimeInstance();
			dataObjElem.setText(datef.format(lastVersion.getCreationTime()));
			root.addContent(dataObjElem);
			
			//Comment eintragen
			dataObjElem = new Element("comment");
			dataObjElem.setText(lastVersion.getComment());
			root.addContent(dataObjElem);
			
			//Filepath eintragen
			dataObjElem = new Element("filepath");
			dataObjElem.setText(dfile.toString());
			root.addContent(dataObjElem);
			
			//sperrenden eintragen
			dataObjElem = new Element("sperrender");
			dataObjElem.setText(dmeta.getSperrender());
			root.addContent(dataObjElem);
			
			//Sperrhost eintragen
			dataObjElem = new Element("sperrhost");
			dataObjElem.setText(dmeta.getSperrhost() != null ? dmeta.getSperrhost().getAddress() : "");
			root.addContent(dataObjElem);
			
			//Sperrzeit eintragen
			dataObjElem = new Element("sperrZeit");
			if (dmeta.getSperrzeit() != null)
				dataObjElem.setText(datef.format(dmeta.getSperrzeit()));
			else
				dataObjElem.setText("");
			root.addContent(dataObjElem);
			
			LinkedList<ServerDataObject> backups = dbackup.getBackups();
			
			//Backups eintragen
			for (int i = 0; i < backups.size(); i++)
			{
				dataObjElem = new Element("backup" + i);
				dataObjElem.setText(backups.get(i).toString());
				root.addContent(dataObjElem);
			}
			
			XMLOutputter xmlout = new XMLOutputter();
			FileWriter filewriter = new FileWriter(
					xmlFile, false);
			xmlout.output(root, filewriter);
			filewriter.flush();
		} catch (Exception ex) {
			DDLogger.getLogger().createLog(ex.getMessage(), DDLogger.ERROR);
		}
	}
	
	
	/**
	 * Methode welche aus einer XML Datei ein DocumentWrapper Objekt erstellt und dieses zur�ckgibt
	 * @param filename Name der XML-Datei
	 * @return DocumentWrapper Objekt mit dem Inhalt der XML Datei
	 * @throws DocumentWrapperException Wenn beim parsen der XML Datei ein Fehler passiert
	 */	
	
	public static DocumentWrapper loadFromXml(String filename)throws DocumentWrapperException{
		try
		{
			filename += ".xml"; 
			DocumentWrapper wrapper = new DocumentWrapper();
			DocumentMetadata mdata = new DocumentMetadata();
			DocumentFile dfile = new DocumentFile(filename);
			DocumentVersion dversion = new DocumentVersion();
			project.helperclasses.NetworkHelper nwhelper = new project.helperclasses.NetworkHelper();
			
			mdata.setBeschreibung(searchAttribut("beschreibung", filename));
			
			Vector<String> rollen = new Vector<String>();
			int i = 0;
			while(true)
			{
				String tmps = "";
				try
				{
					tmps = searchAttribut("rolle" + i, filename);
				} catch(DocumentWrapperException dwe)
				{
				}
				if (tmps != "")
				{
					rollen.add(tmps);
					tmps = "";
					i++;
				}
				else
				{
					break;
				}
			}
			mdata.setRolle(rollen);
			
			dversion.setAuthorUsername(searchAttribut("authorUsername", filename));

			String authorHost = searchAttribut("authorHost", filename);
			dversion.setAuthorHost(authorHost != "" ? new ServerDataObject(authorHost, nwhelper.getOwnOpenPort(), 0) : null);
			
			dversion.setComment(searchAttribut("comment", filename));
			
			DateFormat datef = DateFormat.getDateTimeInstance();
			dversion.setCreationTime(datef.parse(searchAttribut("creationTime", filename)));
			
			mdata.setSperrender(searchAttribut("sperrender", filename));
			
			String sperrhost = searchAttribut("sperrhost", filename);
			ServerDataObject sperrhostobj;
			if (sperrhost != null)
			{
				sperrhostobj = new ServerDataObject(sperrhost, "1099", 0);
			}
			else
			{
				sperrhostobj = null;
			}
			mdata.setSperrhost(sperrhostobj);

			try
			{
				mdata.setSperrzeit(datef.parse(searchAttribut("sperrzeit", filename)));
			} catch (Exception ex)
			{
				mdata.setSperrzeit(null);
			}
			DocumentVersion tmp = new DocumentVersion();
			try{
				tmp.setVersionNumber(Integer.parseInt(searchAttribut("parent", filename)));
			}catch(Exception dwe)
			{
				tmp.setVersionNumber(0);
			}
			dversion.setParent(tmp);
			dversion.setVersionNumber(Integer.parseInt(searchAttribut("versionNumber", filename)));
			
			
			
			LinkedList<ServerDataObject> backups = new LinkedList<ServerDataObject>();
			i = 0;
			while(true)
			{
				String tmps = "";
				try
				{
					tmps = searchAttribut("backup" + i, filename);
				} catch(DocumentWrapperException dwe)
				{
				}
				if (tmps != "")
				{
					backups.add(new ServerDataObject(tmps, nwhelper.getOwnOpenPort(), 0));
					tmps = "";
					i++;
				}
				else
				{
					break;
				}
			}
			DocumentDistribution ddist = new DocumentDistribution(backups);
			wrapper.setDistribution(ddist);
			wrapper.setVersion(dversion);
			wrapper.setFile(dfile);
			wrapper.setMetadata(mdata);
			return wrapper;
		}catch (Exception ex)
		{
			throw new DocumentWrapperException(ex.getMessage());
		}
	}
	
	
	/**
	 * Methode die in einer XML-Datei nach einem Attribut sucht.
	 * @param searchString gesuchtes Attribut
	 * @param filename Dateiname
	 * @return Inhalt des Attributes
	 * @throws DocumentWrapperException Wenn beim auslesen der XML-Datei ein Fehler auftritt
	 */
	
	@SuppressWarnings("unchecked")
	public static String searchAttribut(String searchString, String filename) throws DocumentWrapperException {
		String resultString = "";
		Element root;
		List<Element> list;
		Iterator<Element> ite;
		SAXBuilder saxb = new SAXBuilder();
		Document doc;

		try {
			doc = saxb.build(new File(filename));
			root = doc.getRootElement();
			// Abfragen ob Root �berhaupt Knoten besitzt
			if (root.getContentSize() > 0) {
				list = root.getChildren();
				ite = list.iterator();
				while (ite.hasNext()) {
					Element tmp;
					tmp = ite.next();
					if (tmp.getName().equals(searchString)) {
						resultString = tmp.getText();
					}
				}
			}
		} catch (JDOMException e) {
			DDLogger.getLogger().createLog(e.getMessage(), DDLogger.ERROR);
			throw new DocumentWrapperException(DocumentWrapperException.ATTRIBUTENOTFOUND);
		} catch (IOException e) {
			DDLogger.getLogger().createLog(e.getMessage(), DDLogger.ERROR);
			throw new DocumentWrapperException(DocumentWrapperException.ATTRIBUTENOTFOUND);
		}


		return resultString;
	}

	/**
	 * Methode die eine Stringrepresentation des Objektes zur�ckgibt.
	 */
	
	public String toString() {
		String s = dfile + ",\n " + dmeta + ",\n " + lastVersion + ",\n " + dbackup;
		return s;
	}
}

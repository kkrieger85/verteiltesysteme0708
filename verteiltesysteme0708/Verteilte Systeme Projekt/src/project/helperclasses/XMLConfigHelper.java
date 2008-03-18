/**
 * 
 */
package project.helperclasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import project.exception.XMLConfigException;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert,
 *         reichert.sascha@googlemail.com</a>
 * 
 */
public class XMLConfigHelper {

	public static final String MAINFOLDERATTR = "mainfolder"; 
	public static final String PROGRAMMLANGUAGE = "language"; 
	
	private SAXBuilder saxb = new SAXBuilder();
	private Document doc;

	public static String XMLCONFIGFILE = "config.xml";

	/**
	 * Standardkonstruktor Aufgabau der XML-Datei:
	 * 
	 * <root> <attrib1>Blubb</attrib1> <attrib2>Blubb</attrib2> <attrib3>Blubb</attrib3>
	 * </root>
	 */
	public XMLConfigHelper() {
	}

	/**
	 * Lädt ein Attribut aus der XML Datei heraus, wirft ne Exception falls
	 * dieses nicht Existiert !!!
	 * 
	 * @param attrib
	 * @return
	 * @throws XMLConfigException
	 */
	@SuppressWarnings("unchecked")
	public String searchAttribut(String searchString) throws XMLConfigException {
		String resultString = "";
		Element root;
		List<Element> list;
		Iterator<Element> ite;

		try {
			doc = saxb.build(new File(XMLConfigHelper.XMLCONFIGFILE));
			root = doc.getRootElement();
			// Abfragen ob Root überhaupt Knoten besitzt
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
			throw new XMLConfigException(XMLConfigException.ATTRIBUTENOTFOUND);
		} catch (IOException e) {
			DDLogger.getLogger().createLog(e.getMessage(), DDLogger.ERROR);
			throw new XMLConfigException(XMLConfigException.ATTRIBUTENOTFOUND);
		}

		if (resultString == null || resultString.compareTo("") == 0) {
			throw new XMLConfigException(XMLConfigException.ATTRIBUTENOTFOUND);
		}

		return resultString;
	}

	/**
	 * Speichert ein angegebenes Attribut ab!! 
	 * @param attribut
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean saveAttribut(String attribut, String value) {
		Element root;
		boolean found = false;
		try {
			File file = new File(XMLConfigHelper.XMLCONFIGFILE);
			if (!file.exists()) {
				file.createNewFile();
				file.setWritable(true);
			} else {
				doc = saxb.build(new File(XMLConfigHelper.XMLCONFIGFILE));
			}
			if (doc.hasRootElement()) {
				root = doc.getRootElement();
			} else {
				root = new Element("root");
				doc.setRootElement(root);
			}
			List<Element> list;
			Iterator<Element> ite;

			// Suche nach vorhandenen Sachen !!!
			if (root.getContentSize() > 0) {
				list = root.getChildren();
				ite = list.iterator();
				while (ite.hasNext()) {
					Element tmp;
					tmp = ite.next();
					if (tmp.getName().equals(attribut)) {
						tmp.setText(value);
						found = true;
					}
				}
			}
			if (!found) {
				Element dataObjElem = new Element(attribut);
				dataObjElem.setText(value);
				root.addContent(dataObjElem);
			}

			// In Datei schreiben !!!
			XMLOutputter xmlout = new XMLOutputter();
			FileWriter filewriter = new FileWriter(
					XMLConfigHelper.XMLCONFIGFILE, false);
			xmlout.output(root, filewriter);
			filewriter.flush();

		} catch (Exception ex) {
			DDLogger.getLogger().createLog(ex.getMessage(), DDLogger.ERROR);
		}

		return false;
	}
	
	/**
	 * Liefert das Hauptverzeichnis zurück, in dem die Dateien gespeichert werden sollen !!!
	 * @return
	 */
	public String getMainFolder(){
		String returnString = ""; 
		try {
			returnString = this.searchAttribut(XMLConfigHelper.MAINFOLDERATTR); 
		} catch (Exception ex) {
			DDLogger.getLogger().createLog(ex.getMessage(), DDLogger.ERROR);
		}
		return returnString; 
	}
	
	/**
	 * Gibt die Sprache zurück
	 * @return
	 */
	public String getProgrammLanguage(){	
		String returnString = ""; 
		try {
			returnString = this.searchAttribut(XMLConfigHelper.PROGRAMMLANGUAGE); 
		} catch (Exception ex) {
			DDLogger.getLogger().createLog(ex.getMessage(), DDLogger.ERROR);
		}
		return returnString; 
		
		
	}

}

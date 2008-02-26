/**
 * 
 */
package project.network;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import project.exception.ServerDataObjectException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import project.helperclasses.*;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert,
 *         reichert.sascha@googlemail.com</a>
 * 
 */
public class IPList implements Serializable {

	private static String file = "iplist.xml";
	private static IPList iplist = new IPList();
	private static LinkedList<ServerDataObject> list = new LinkedList<ServerDataObject>();
	/**
	 * 
	 */
	private static final long serialVersionUID = -5049195550478153165L;

	/**
	 * Private da Singleton
	 */
	private IPList() {
	}

	/**
	 * XML Datei laden
	 */
	public void loadXML() {
		SAXBuilder saxb = new SAXBuilder();
		String serverAddress = "address";
		String serverPort = "port";
		Document doc;
		Element root;
		List<Element> list;
		Iterator<Element> ite;
		try {
			doc = saxb.build(new File(IPList.file));
			root = doc.getRootElement();
			// Abfragen ob Root �berhaupt Knoten besitzt
			if (root.getContentSize() > 0) {
				list = root.getChildren();
				ite = list.iterator();
				while (ite.hasNext()) {
					// Inneres XML Auslesen !!!
					List<Element> innerlist = ((Element) ite.next()).getChildren();
					Iterator<Element> innerIte = innerlist.iterator();
					Element tmp;
					String tmpAddress = null;
					String tmpPort = null;
					if (innerlist.size() != 0){
						while (innerIte.hasNext()) {
							tmp = (Element) innerIte.next();
							if (tmp.getName().equals(serverAddress)) {
								tmpAddress = tmp.getText();
							} else if (tmp.getName().equals(serverPort)) {
								tmpPort = tmp.getText();
							}
						}
						try {
							ServerDataObject sdo = new ServerDataObject(tmpAddress,
									tmpPort);
							IPList.iplist.addObject(sdo); 
						} catch (ServerDataObjectException e) {
							// TODO Catch besser bearbeiten
						}
					}
				}

			}

		} catch (JDOMException e) {
			// TODO Exceptions ordentlich abfangen 1!!
		} catch (IOException e) {

		}

	}

	/**
	 * XML Datei speichern Grundstruktur soll so aussehen: <iplist> <dataobject>
	 * <address></address> <port></port> usw. </dataobject> </iplist>
	 */
	public void saveXML() {
		Element iplistElem;
		Document doc = new Document();
		try {
			// Falls die Datei nicht erstellt werden konnte wirf eine Exception
			// Teste ob die Datei existiert
			File file = new File(IPList.file);
			if (!file.exists()) {
				file.createNewFile();
				file.setWritable(true);
			}
			// Zuerst Grundvoraussetzungen schaffen f�r die Liste !!!
			if (doc.hasRootElement()) {
				iplistElem = doc.getRootElement();
			} else {
				iplistElem = new Element("iplist");
				doc.setRootElement(iplistElem);
			}

			// Durchlaufe die IP Liste !!!
			if (!IPList.list.isEmpty()) {
				for (int i = 0; i < IPList.list.size(); i++) {
					// Objekt aus IP Liste holen
					ServerDataObject obj = IPList.list.get(i);
					// Dataobj anlegen !!
					Element dataObjElem = new Element("dataobject");
					// Kinderknoten anlegen
					Element addressElem = new Element("address");
					Element portElem = new Element("port");
					// f�llen !!
					addressElem.addContent(obj.getAddress());
					portElem.addContent(obj.getPort());
					// Kinderknoten dem Elternknoten zuweisen
					dataObjElem.addContent(addressElem);
					dataObjElem.addContent(portElem);
					// Das Datenobjekt dem Rootknoten zuweisen
					iplistElem.addContent(dataObjElem);
				}
				// In Datei schreiben !!!
				XMLOutputter xmlout = new XMLOutputter();
				FileWriter filewriter = new FileWriter(IPList.file, false);
				xmlout.output(iplistElem, filewriter);
				filewriter.flush();
			}
		} catch (Exception ex) {
			DDLogger logger = DDLogger.getLogger();
			logger.createLog(ex.getMessage(), DDLogger.DEBUG);
		}
	}

	/**
	 * Testfunktion TODO Testfunktion sp�ter rausschmei�en !!!
	 * 
	 * @return
	 */
	public LinkedList<ServerDataObject> testIPList() {
		LinkedList<ServerDataObject> ll = new LinkedList<ServerDataObject>();
		try {
			ServerDataObject sdo = new ServerDataObject("192.168.2.101", "1099");
			ll.add(sdo);
			sdo = new ServerDataObject("192.168.2.100", "1099");
			ll.add(sdo);
			sdo = new ServerDataObject("192.168.2.102", "1099");
			ll.add(sdo);
			sdo = new ServerDataObject("192.168.2.103", "1099");
			ll.add(sdo);
		} catch (ServerDataObjectException e) {
			e.printStackTrace();
		}
		IPList.list = ll;
		return ll;
	}

	public static IPList getInstance() {
		return IPList.iplist;
	}

	public String toString() {
		return "Count Objects " + IPList.list.size()+ "  " + IPList.list.toString();
	}

	public void addObject(ServerDataObject obj){
		IPList.list.add(obj); 
		this.saveXML(); 
	}
	
	public LinkedList<ServerDataObject> getIPList(){
		IPList.iplist.loadXML(); 
		if (IPList.list == null || IPList.list.size() == 0){	
			return null; 
		} else 
			return IPList.list; 
	}
	
	/** 
	 * Setzt eine neue Liste ein und sichert diese
	 * @param newList Die neue Liste 
	 * @return false wenn die �bergebene Liste leer war
	 * 			true wenns geklappt hat 
	 */
	public boolean setIPList(LinkedList<ServerDataObject> newList){
		if (newList != null){
			IPList.list = newList; 
			IPList iplist = IPList.getInstance(); 
			iplist.saveXML(); 
			return true; 
		} else 
			return false; 
	}
}

/**
 * 
 */
package project.centrallogic;

import java.util.LinkedList;

import project.gui.MainFrame;
import project.network.SearchResult;
import java.util.Vector; 

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 * Diese Klasse gibt gefundene Dinge wieder an die GUI zurück 
 */
public class FileSearchResultTemplate extends AbstractCentralLogic implements
		Runnable {
	
	private LinkedList<SearchResult> result; 
	
	/**
	 * Konstruktor bekommt das Ergebnis direkt mit
	 */
	public FileSearchResultTemplate(LinkedList<SearchResult> result) {
		this.result = result; 
		Thread thread = new Thread(this); 
		thread.start(); 
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// Umbau des Results 
		Vector<Vector<Object>> outerVector = new Vector<Vector<Object>>(); 
		for (int i = 0; i < result.size(); i++){
			Vector<Object> innerVector = new Vector<Object>(); 
			innerVector.add(result.get(i).getFileName()); 
			innerVector.add(result.get(i).getServerIP()); 
			innerVector.add(result.get(i).getFileSize()); 
			outerVector.add(innerVector); 
		}
		// MainFrame holen 
		MainFrame mf = MainFrame.getInstance();
		mf.getTabPageContainer().addResults(outerVector); 
		
		// An GUI Weiterleiten 

	}

}

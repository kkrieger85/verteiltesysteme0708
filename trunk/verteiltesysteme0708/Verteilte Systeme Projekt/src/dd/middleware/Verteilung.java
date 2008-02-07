package dd.middleware;

import dd.data.Document;

/**
 * @author ab
 *
 */
public interface Verteilung {

	public void distributeDocument(Document doc) throws Exception;
	
}

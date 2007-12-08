/**
 * 
 */
package spielwiese;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class RMITestImpl extends UnicastRemoteObject implements RMITestInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7771915602214179133L;

	private Vector objectList; 
	
	/**
	 * Konstruktor der Klasse RMITestImpl
	 * @throws RemoteException
	 */
	public RMITestImpl() throws RemoteException {
		super();	
		objectList = new Vector(); 
		RMITestObject personOne = new RMITestObject("Test", "Tester", "12345"); 
		this.objectList.add(personOne); 
		RMITestObject personTwo = new RMITestObject("Blah", "Blubb", "4711"); 
		this.objectList.add(personTwo); 
		RMITestObject personThree = new RMITestObject("ABC", "DEF", "7891011"); 
		this.objectList.add(personThree); 
	}

	/**
	 * @param int Nummer der auszugebenden Person 
	 * @return RMITestObject Personenobjekt 
	 */
	public RMITestObject getPerson(int number) {	
		RMITestObject returnObject = null; 
		
		// Es wirdn Thread (Fred im weiteren Text) angelegt und gestartet 
		RMITestThread thread = new RMITestThread(this.objectList, number); 
		thread.start(); 
		try {
			// Warten auf den Fred 
			thread.join(); 
		} catch (Exception ex){
			// Falls Fred nen Fehler macht 
			ex.printStackTrace(); 
		}
		// Man bekommt ein Object vom Fred zurück ;) 
		returnObject = thread.getReturnObject(); 
		
		// returnObject = (RMITestObject)this.objectList.firstElement(); 
		return returnObject;
	}
}

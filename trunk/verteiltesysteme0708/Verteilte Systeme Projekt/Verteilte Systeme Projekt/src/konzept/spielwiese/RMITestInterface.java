/**
 * 
 */
package konzept.spielwiese;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public interface RMITestInterface extends Remote {
	
	/**
	 * Methode liefert ein Objekt vom Typ RMITestObject zurück !!!
	 * @param number
	 * @return RMITestObject
	 */
	public RMITestObject getPerson(int number) throws RemoteException;; 
}

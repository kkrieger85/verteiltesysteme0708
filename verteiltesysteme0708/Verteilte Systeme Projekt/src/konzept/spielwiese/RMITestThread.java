/**
 * 
 */
package konzept.spielwiese;

import java.util.Date;
import java.util.Vector;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert,
 *         reichert.sascha@googlemail.com</a>
 * 
 */
public class RMITestThread implements Runnable {

	private Vector objectList;
	private int personNumber; 
	private RMITestObject returnObject;

	/**
	 * Konstruktor 
	 * @param objectList
	 * @param personNumber
	 */
	public RMITestThread(Vector objectList, int personNumber){
		this.objectList = objectList; 
		this.personNumber = personNumber; 
	}
	
	/**
	 * @return the returnObject
	 */
	public RMITestObject getReturnObject() {
		return returnObject;
	}

	/**
	 * @param objectList
	 *            the objectList to set
	 */
	public void setObjectList(Vector objectList) {
		this.objectList = objectList;
	}

	/**
	 * Runmethode des Threads 
	 */
	public void run() {
		Date dt = new Date();
		System.out.println(dt.toString() + ": Thread started");
		
	/*	try {
			sleep(5000);
		} catch (InterruptedException e) {
		} */ 
		for (int i = 0; i < this.objectList.size(); i++)
		{
			if (i == this.personNumber)
			
				this.returnObject = (RMITestObject)this.objectList.get(i);
		}
		dt = new Date();
		System.out.println(dt.toString() + ": Thread ended");
	}

}

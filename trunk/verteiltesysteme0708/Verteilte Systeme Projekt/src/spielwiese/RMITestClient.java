/**
 * 
 */
package spielwiese;

import java.rmi.Naming;
import java.util.Date;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class RMITestClient {

	/**
	 * Standard Mainmethode zum Starten des Clients 
	 * @param args
	 */
	public static void main(String[] args) {
		String host = "localhost"; 
		if (args.length != 0){
			if (args[0]!= null)
			{
				host = args[0]; 
			}
		} 
		try {
			// Das Interface registrieren 
			RMITestInterface testinterface = (RMITestInterface) Naming.lookup("rmi://"+host+"/server");
			// Testausgaben und so nen Käse halt
			for (int i = 0; i<5; i++)
			{
				Date dt = new Date();
				RMITestObject abc = testinterface.getPerson(i); 
				if (abc != null){
					System.out.println(dt + ": "+abc); 
				}
				else 
				{
					System.out.println(dt + ": "+"no object found!!");
				}
			}

		} catch (Exception e) {
			// Zugriff auf Remote-Object fehlgeschlagen
			System.out.println(e.getMessage()); 
			e.printStackTrace(); 
		}
	}

}

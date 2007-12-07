/**
 * 
 */
package ueb03;
import java.rmi.*;

/**
 * Clientimplementierung zum Testen der RMI Geschichte 
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 * @version 1.00
 * <h3>Wichtig: Berechtigungen</h3>
 * <b> Das Ausführen einer RMI-Anwendung benötigt einige zusätzliche Berechtigungen - auf dem Client wie auf dem Server.<br/>Die java-Sicherheitseinstellungen befinden sich in der Datei $JDK_HOME/jre/lib/security/java.policy, welche um folgende Zeile ergänzt werden muss:<br/>
 * <i>permission java.security.AllPermission; </i></b>
 * 
 */
public class UebClient {

	/**
	 * <h1>Mainmethode des Clients </h1>

	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			ServerInterface myServerInt = (ServerInterface) Naming.lookup("rmi://localhost/server");
			boolean login = false; 
			boolean logout = false; 
			String loginstring = "Nicht eingelogt"; 
			login = myServerInt.login("Admin", "Admin");
			if (login)
				loginstring = myServerInt.getInfo(); 			
			else 
				loginstring = "Wrong Login!!"; 
			
			System.out.println("Login = " + loginstring + "\n"); 
			
			logout = myServerInt.logout(); 
			if (logout)
				System.out.println("Ausgeloggt"); 
			
			
			login = false; 
			logout = false; 
			loginstring = "Nicht eingelogt"; 
			login = myServerInt.login("Admin1", "Admin1");
			if (login)
				loginstring = myServerInt.getInfo(); 			
			else 
				loginstring = "Wrong Login!!"; 
			
			System.out.println("Login = " + loginstring + "\n"); 
			
			logout = myServerInt.logout(); 
			if (logout)
				System.out.println("Ausgeloggt"); 


		} catch (Exception e) {
			// Zugriff auf Remote-Object fehlgeschlagen
			System.out.println(e.getMessage()); 
		}
	}

}

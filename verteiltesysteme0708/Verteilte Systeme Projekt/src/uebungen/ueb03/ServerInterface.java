package uebungen.ueb03;

import java.rmi.*;

/**
 * Interface zum Testen der RMI Geschichte 
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 * @version 1.00
 */
public interface ServerInterface extends Remote {

	/**
	 * 
	 * @param name Der Loginname
	 * @param pass Das Loginpasswort
	 * @return 	gibt true zurück wenn das einloggen funktioniert hat
	 * 			gibt false zurück wenn das einloggen nicht funktioniert hat
	 * @throws RemoteException
	 */
	public boolean login(String name, String pass) throws RemoteException;

	/**
	 * Methode zum herausfinden von Informationen
	 * @return	Gibt die Logininformationen zurück 
	 * @throws RemoteException
	 */
	public String getInfo() throws RemoteException;

	/**
	 * Logout Funktion
	 * @return Gibt True zurück wenn es keine Probleme gab. 
	 * @throws RemoteException
	 */
	public boolean logout() throws RemoteException;
}

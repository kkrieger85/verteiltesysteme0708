import java.net.*;
import java.io.*;

public class Client {

	BufferedReader in;
	PrintStream out;
	Socket server;

	public Client() {
		try {
			server = new Socket(InetAddress.getByName("localhost"), 4242);
			// Klasse Socket besitzt Methoden
			// getInputStream bzw. getOutputStream, hier
			// Konversion zu DataInputStream / PrintStream:
			in = new BufferedReader(new InputStreamReader(server
					.getInputStream()));
			out = new PrintStream(server.getOutputStream());

			// Etwas an den Echo-Server senden:
			// out.println("huhu");

			// Vom Echo-Server empfangen; vielleicht
			// am besten in einem anderen Thread:
			String line;
			while ((line = in.readLine()) != null)
				System.out.println(line);

			server.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client test = new Client();
	}

}

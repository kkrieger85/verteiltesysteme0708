package ueb01.proof_concept;

import java.net.*;
import java.io.*;


public class Client {

	BufferedReader empfangen;
	PrintStream senden;
	Socket clientSocket;

	public Client(String ip) {
   
	    // Create a socket with a timeout
	    try {
	        InetAddress addr = InetAddress.getLocalHost();
	        int port = 7000;
	        SocketAddress sockaddr = new InetSocketAddress(addr, port);
	    
	        // Create an unbound socket
	        Socket sock = new Socket();
	    
	        // This method will block no more than timeoutMs.
	        // If the timeout occurs, SocketTimeoutException is thrown.
	        int timeoutMs = 2000;   // 2 seconds
	        sock.connect(sockaddr, timeoutMs);
	        
	        try {
	            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
	            wr.write("Blub" + "\n");
	            wr.flush();
	        } catch (IOException e) {
	        }
	    
	        try {
	            BufferedReader rd = new BufferedReader(new InputStreamReader(sock.getInputStream()));
	        
	            String str;
	            while ((str = rd.readLine()) != null) {
	            	System.out.println("Empfange:");	            	
	            	System.out.println(str);
	            }
	            rd.close();
	        } catch (IOException e) {
	        }	        
	      
	    } catch (UnknownHostException e) {
	    } catch (SocketTimeoutException e) {
	    } catch (IOException e) {
	    }
	    
	}
	
	

	/**
	 * Mainmethode welche den Start des Clients anregt, und die IP des servers Festlegt
	 * @param args
	 */
	public static void main(String[] args) {
		// für schnelleres Testen feste ip.
		Client test = new Client("localhost");
	}

}
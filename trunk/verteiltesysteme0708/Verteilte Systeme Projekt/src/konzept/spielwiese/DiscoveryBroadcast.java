/**
 * 
 */
package konzept.spielwiese;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.rmi.*;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class DiscoveryBroadcast implements Runnable{


	
	public DiscoveryBroadcast(){


	}
	
	@Override
	public void run() {
		try {
			boolean active = true; 
			int port=6789; 
			String multicastAddress="228.5.6.7";
			MulticastSocket socket=new MulticastSocket(port);
			InetAddress address=InetAddress.getByName(multicastAddress);    
			socket.joinGroup(address);
			//parse packet etc
					
			while(active){
			    byte[] buf=new byte[512];
			    DatagramPacket packet=new DatagramPacket(buf,buf.length);
			    socket.receive(packet);
			    
			    System.out.println(packet.getAddress().toString() + " - Daten:  " + packet.getData().toString()); 
			}
			socket.leaveGroup(address);
		} catch (Exception ex){
			
		}
		
		
	}
}

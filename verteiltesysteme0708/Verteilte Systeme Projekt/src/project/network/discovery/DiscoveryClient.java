package project.network.discovery;

import java.rmi.*;

/**
 * RMIClient
 * 
 * @author Andreas Kuntz
 * 
 * @version 0.1 (17.03.2008)
 * 
 */
public class DiscoveryClient {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			if (args.length != 1) {
				System.out.println("syntax: TestClient <service>");
				System.exit(1);
			}

			System.setSecurityManager(new RMISecurityManager());

			DiscoveryProp.setProperties("discovery.properties");

			String service = args[0];

			// Use RMIDiscovery to locate the service

			System.out.println("Attempting RMI discovery....");

			Remote remote = RMIDiscovery.lookup((Class<DiscoveryServerInterface>) DiscoveryServerInterface.class, service);

			System.out.println("Discovered a matching RMI service!!!");

			// cast to correct interface type
			DiscoveryServerInterface server = (DiscoveryServerInterface) remote;

			System.out.println("Attemping to test...");

			String word = server.test();

			System.out.println(word);
			System.out.println("Done!");

			/*
			 * //example of using the unicast lookup if multicast fails
			 * //RMILookup will also try to register with the rmi-registry //if
			 * it is running
			 * 
			 * System.out.println("### Attempting unicast");
			 * 
			 * String address []=new String[]{ "cardassia:1099",
			 * "unimatrix-zero:1099"
			 *  };
			 * 
			 * Remote [] remArray=RMIDiscovery.lookupAll(language,address);
			 * for(int i=0;i<remArray.length;i++){ if(remArray[i]!=null){
			 * System.out.println("Got ref "+remArray[i].getClass());
			 * ((Translator)remArray[i]).translateWord("hello");
			 *  } }
			 */

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

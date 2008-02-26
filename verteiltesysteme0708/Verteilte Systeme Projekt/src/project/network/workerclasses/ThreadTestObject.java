/**
 * 
 */
package project.network.workerclasses;

import java.util.HashMap;
import java.util.Random;

import project.helperclasses.DDLogger;
/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class ThreadTestObject extends AbstractThreadWorker {

	public ThreadTestObject(String address, String port) {
		super(address, port);
	}
	
	public ThreadTestObject(String address, String port, HashMap<String,String> informationHash) {
		super(address, port, informationHash);		
	}
	
	public int i = 0; 
	/* (non-Javadoc)
	 * @see project.network.ThreadWorkerInterface#start()
	 */
	@Override
	public synchronized boolean start() {
		// Sleeptime Randomize 
		Random r = new Random();
		DDLogger ddl = DDLogger.getLogger(); 
		int runtime = (r.nextInt(3000)); 
		
		try {
			Thread.sleep(runtime); 
			}
		catch (Exception ex){
			
			ddl.createLog("Thread Sleep Error", DDLogger.ERROR); 
		}
		
		ddl.createLog(this.informationHash.get("blubb"), DDLogger.INFO); 
		if (i < 2){
			countI(); 
			return false;
		}
		else {
			return true; 
		}
		
	}
	
	public synchronized void countI(){
		this.i++; 
	}

}

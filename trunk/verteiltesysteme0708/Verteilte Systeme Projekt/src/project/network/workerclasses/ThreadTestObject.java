/**
 * 
 */
package project.network.workerclasses;

import java.util.Random;

import project.helperclasses.DDLogger;
/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class ThreadTestObject extends AbstractThreadWorker {

	public ThreadTestObject(String address, String port) {
		super(address, port);
		// TODO Auto-generated constructor stub
	}
	
	public int i = 0; 
	/* (non-Javadoc)
	 * @see project.network.ThreadWorkerInterface#start()
	 */
	@Override
	public synchronized boolean start() {
		// Sleeptime Randomize 
		Random r = new Random();
		int runtime = (r.nextInt(3000)); 
		
		try {
			Thread.sleep(runtime); 
			}
		catch (Exception ex){
			DDLogger ddl = DDLogger.getLogger(); 
			ddl.createLog("Thread Sleep Error", DDLogger.ERROR); 
		}
		
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

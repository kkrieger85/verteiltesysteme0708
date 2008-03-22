/**
 * 
 */
package project.network;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

import project.data.dataSendingWrapper;
import project.helperclasses.DDLogger;
import project.network.serverclasses.FileSearch;

/**
 * @author <a href="mailto:reichert.sascha@googlemail.com">Sascha Reichert, reichert.sascha@googlemail.com</a>
 *
 */
public class RMINetworkImpl extends UnicastRemoteObject implements
		RMINetworkInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5498171438169548681L;

	/**
	 * Auto-generated Code 
	 * @throws RemoteException
	 */
	public RMINetworkImpl() throws RemoteException {

	}

	/**
	 * Auto-generated Code 
	 * @param arg0
	 * @throws RemoteException
	 */
	public RMINetworkImpl(int arg0) throws RemoteException {
		super(arg0);
	}

	/**
	 * Auto-generated Code 
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws RemoteException
	 */
	public RMINetworkImpl(int arg0, RMIClientSocketFactory arg1,
			RMIServerSocketFactory arg2) throws RemoteException {
		super(arg0, arg1, arg2);			
	}

	/** 
	 * Destruktor von Java ;) 
	 */
	protected void finalize() throws Throwable
	{
		//do finalization here
		super.finalize(); //not necessary if extending Object.
	} 
	
	/* (non-Javadoc)
	 * @see project.network.RMINetworkInterface#getIPList()
	 */
	@Override
	public LinkedList<ServerDataObject> getIPList() throws RemoteException {
		/* Auskommentiert wegen fehlender Testmöglichkeit 
		if (this.checkMaxThreads()==false)
			throw new RemoteException("Zuviele Threads!!!"); 
			*/
		DDLogger ddl = DDLogger.getLogger(); 
		ddl.createLog("Liste wird abgerufen!", DDLogger.INFO); 
		IPList list = IPList.getInstance(); 
		// IncomingThreadCounter.getInstance().decCount(); 
		return list.getIPList(); 
	}
	
	/* (non-Javadoc)
	 * @see project.network.RMINetworkInterface#pushIPList()
	 */
	@Override
	public boolean pushIPList(LinkedList<ServerDataObject> list)
			throws RemoteException {		
		/* Auskommentiert wegen fehlender Testmöglichkeit 
		if (this.checkMaxThreads()==false)
			throw new RemoteException("Zuviele Threads!!!"); 
			*/
		boolean returnvalue = false; 
		DDLogger ddl = DDLogger.getLogger(); 
		ddl.createLog("Liste wird geschickt!", DDLogger.INFO); 
		
		IPList iplist = IPList.getInstance(); 
		returnvalue = iplist.setIPList(list); 
		// IncomingThreadCounter.getInstance().decCount(); 
		return returnvalue;
	}
	
	
	/** 
	 * Checkt ob zuviele threads am laufen sind 
	 * Übergibt True wenn ein weiterer benutzt werden kann 
	 * @return
	 */
	@SuppressWarnings("unused")
	private synchronized boolean checkMaxThreads(){
		DDLogger.getLogger().createLog("Count=  " + IncomingThreadCounter.getInstance().getCount(), DDLogger.DEBUG); 
		if (IncomingThreadCounter.getInstance().getCount() >= IncomingThreadCounter.MAXTHREADCOUNT){		
			return false; 
		} else {
			IncomingThreadCounter.getInstance().incCount(); 
			return true;  
			
		}
	}

	/* (non-Javadoc)
	 * @see project.network.RMINetworkInterface#startSearchRoutine(java.lang.String)
	 */
	@Override
	public LinkedList<SearchResult> startSearchRoutine(String searchString)
			throws RemoteException {
		DDLogger ddl = DDLogger.getLogger(); 
		ddl.createLog("Es wird nach einer Datei gesucht: " + searchString, DDLogger.INFO); 
		FileSearch fs = new FileSearch(); 
		fs.searchFiles(searchString); 		
		return fs.getResult();
	}

	@Override
	public dataSendingWrapper getFile(String filename) throws RemoteException {
		
		dataSendingWrapper dsw = new dataSendingWrapper(filename);
		return dsw;
	}
	

}

package project.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Vector;

/**
 * Klasse zum übermitteln eines Dokumentes samt zugehöriger Metadaten XML-Datei
 * per RMI.
 * 
 * @author Christian Schwerdtfeger
 *
 */

public class dataSendingWrapper implements java.io.Serializable{

	private Vector<byte[]> file;
	private int fileoffset = -1;
	private Vector<byte[]> xmlfile;
	private int xmloffset = -1;
	private String filename;
	
	private FileInputStream in;
	
	/**
	 * Konstruktor durch den ein Objekt angelegt wird, welches die Datei als auch die zugehörige
	 * XML-Datei in Form eines Vector<byte[]> enthält.
	 * 
	 * @param filename Die Datei die in das Objekt geladen werden soll.
	 */
	
	public dataSendingWrapper(String filename)
	{
		this.filename = filename;
		try{
			int filelength = 0;
			in = new FileInputStream(filename);
			file = new Vector<byte[]>();
			byte[] b = new byte[4096];
			int nbyte= -1;

			while(-1!=(nbyte=(in.read(b)))){
			filelength=nbyte;
			file.addElement(b.clone()); //tiefe kopie
			}
			fileoffset = filelength;
			
			nbyte= -1;
			filelength = 0;
			in = new FileInputStream(filename + ".xml");

			while(-1!=(nbyte=(in.read(b)))){
			filelength=nbyte;
			xmlfile.addElement(b.clone()); //tiefe kopie
			}
			xmloffset = filelength;

			in.close();
			}catch(Throwable e){e.printStackTrace();
			}
		
	}
	
	/**
	 * Methode die aus dem dataSendingWrapper-Objekt die zugehörige Datei sowie die XML-Datei
	 * erzeugt.
	 * 
	 */
	
	public void saveToFile()
	{
		try{
			java.io.File outputfile = new java.io.File(filename);
			outputfile.createNewFile();
			FileOutputStream out = new FileOutputStream(outputfile);

			for(int i = 0; i < file.size(); i++){
				if(i==(file.size()-1))
					out.write((byte[])file.get(i),0,fileoffset);
				else 
					out.write((byte[])file.get(i)); 
			}
			outputfile = new java.io.File(filename + ".xml");
			outputfile.createNewFile();
			out = new FileOutputStream(outputfile);

			for(int i = 0; i < xmlfile.size(); i++){
				if(i==(xmlfile.size()-1))
					out.write((byte[])xmlfile.get(i),0,xmloffset);
				else 
					out.write((byte[])xmlfile.get(i)); 
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	
	}
	
}

package project.classtests.helper;

import java.util.Date;
import java.util.Vector;

import project.data.*;

public class DocumentWrapperTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DocumentFile dfile = new DocumentFile("test.txt");
		DocumentMetadata dmeta= new DocumentMetadata();
		dmeta.setBeschreibung("testfile");
		dmeta.setRolle("testrolle");
		try
		{
			DocumentWrapper wrapper = new DocumentWrapper(dfile, dmeta);
		
			DocumentVersion dversion = new DocumentVersion();
			dversion.setAuthorUsername("testautor");
			dversion.setComment("testcomment");
			dversion.setCreationTime(new Date());
			DocumentVersion tmp = new DocumentVersion();
			tmp.setVersionNumber(0);
			dversion.setParent(tmp);
			dversion.setVersionNumber(1);
			
			Vector<Computer> backups = new Vector<Computer>();
			for (int i = 1; i <= 3; i++)
			{
				backups.add(new ComputerWrapper("192.168.1." + i));
			}
			
			DocumentDistribution ddist = new DocumentDistribution(backups);
			wrapper.setDistribution(ddist);
			wrapper.setVersion(dversion);
			
			wrapper.saveToXml();
			
			DocumentWrapper newwrapper = DocumentWrapper.loadFromXml("test.txt.xml");
			System.out.println(newwrapper);
			
		}catch (Exception ex)
		{
			System.out.println(ex);
		}
	}

}

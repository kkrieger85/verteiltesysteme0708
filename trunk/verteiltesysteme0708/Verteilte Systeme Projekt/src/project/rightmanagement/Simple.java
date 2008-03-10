package project.rightmanagement;

import project.data.Document;
import project.exception.LoginException;
import project.exception.RightException;

public class Simple implements Fassade{
	
	public boolean changeDocument(String id){
		return true;
	}
	public boolean openDocument(String id){
		return true;
		
	}
	public boolean createDocument(String role){
		return true;		
	}
	public boolean login (String user, String password)throws LoginException{
		return true;
	}
	public boolean setVertrauensstelle(){
		return true;		
	}
	public boolean addRoleToDocument(){
		return true;
	}
	public boolean removeRoleFromDocument(){
		return true;
	}
	public boolean addUserToRole(String user, String role){
		return true;
	}
	public boolean removeUserFromRole(String user, String role){
		return true;
	}
	public String[] listRoles(){
		String[] temp = new String[1];
		temp[0]= "Test";
		return temp;
	}
	public void encrypt(Document doc)throws RightException{
		
	}
	public void decrypt(Document doc)throws RightException{
		
	}

}

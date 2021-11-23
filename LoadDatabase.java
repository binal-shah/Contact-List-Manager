/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contact_app_manager;
/**
 *
 * @author Binal Shah
 */
import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class LoadDatabase {
	
	

	String fname,mname,lname,haddress,homeState,homeCity,homeZipcode,waddress,workCity,workState,workZipcode,cell_area_code,cellNumber,home_area_code,homeNumber,work_area_code,workNumber,format,bday;
	
	
	
	public static void main(String[] args) throws Exception
	{
		ContactConnectDatabase c = new ContactConnectDatabase();
		PreparedStatement myStatement=null;
		Statement my=null;
		
		String contact_file="C:/Users/Binal Shah/OneDrive/Desktop/Contacts.csv";
		BufferedReader br=null;
		String line="";
		
		
		try
		{
			String fname,mname,lname,haddress,homeState,homeCity,homeZipcode,waddress,workCity,workState,workZipcode,cell_area_code,cellNumber,home_area_code,homeNumber,work_area_code,workNumber,format,bday;
			
                            br=new BufferedReader(new FileReader(contact_file));
                        
			int k=0;
			while((line=br.readLine())!=null)
			{
				k++;
				if(k==1)
				{
					continue;
				}
				
				
				fname=mname=lname=haddress=homeState=homeCity=homeZipcode=waddress=workCity=workState=workZipcode=cell_area_code=cellNumber=home_area_code=homeNumber=work_area_code=workNumber=bday="";
				format = "yyyy-mm-dd";
				String[] contact = line.split(",",-1);
				
				
				fname=contact[1];
				mname=contact[2];
				lname=contact[3];
				
				
				
				if(!contact[4].equals(""))
				{
				home_area_code=contact[4].substring(0,3);
					homeNumber= contact[4].substring (4,12);
				}
				
				
				if(!contact[5].equals(""))
				{
				
					 cell_area_code=contact[5].substring(0,3);
					 cellNumber= contact[5].substring (4,12);
				}
				
				
				if(!contact[10].equals(""))
				{
					work_area_code=contact[10].substring(0,3);
					workNumber= contact[10].substring (4,12);
				}
				
				
				
				 haddress = contact[6];
				 homeCity = contact[7];
				 homeState = contact[8];
				 homeZipcode = contact[9];
				
				
				
				 waddress = contact[11];
				 workCity = contact[12];
				 workState = contact[13];
				 workZipcode = contact[14];
			
				bday = contact[15];
				
				ContactListForm temp = new ContactListForm(fname,mname,lname,haddress,homeState,homeCity,homeZipcode,waddress,workCity,workState,workZipcode,
						cell_area_code,cellNumber,home_area_code,homeNumber,work_area_code,workNumber,format,bday);
				c.addContact(temp);
				
			
		
			}
		}
		catch(IndexOutOfBoundsException e)
		{
			e.printStackTrace();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

}

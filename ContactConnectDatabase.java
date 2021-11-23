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
import java.util.ArrayList;
import java.util.List;

public class ContactConnectDatabase {
	
	private int crecords;
	private int check;
	private String f,m,l,homeStreet,homeCity,homeState,homeZipcode,workStreet,workCity,workState,workZipcode,cell_area_code,cellNumber,home_area_code,homeNumber,work_area_code,workNumber,dformat,date;

	private Connection myConnection;

	public ContactConnectDatabase() throws Exception{
		String mySQL_username="root";
		String mySQL_password="Honeybug@123";
		String mySQL_database_URL="jdbc:mysql://localhost:3306/contact_app_manager?zeroDateTimeBehavior=CONVERT_TO_NULL";
		 myConnection = DriverManager.getConnection(mySQL_database_URL,mySQL_username,mySQL_password);
	System.out.println("Database connected successfully to JAVA via JDBC");
	}



	public List<ContactListForm> getAllContacts() throws Exception{
		List<ContactListForm> manager = new ArrayList<>();
		Statement myStatement=null;
		ResultSet myResult=null;

		try{
			myStatement=myConnection.createStatement();
			myResult=myStatement.executeQuery("select C.contact_id,C.Fname,C.Mname,C.Lname,\n" + 
					"IFNULL(group_concat(Distinct concat(A.address_type,': ',A.address_street,',',A.city, ',',A.state, ',',A.zipcode,' \\n')),\"\") as Address,\n" + 
					"IFNULL(group_concat(Distinct concat(P.phone_type,': ',P.area_code,',',P.pnumber,'\\n')) ,\"\") as Phone,\n" + 
					"IFNULL(group_concat(Distinct concat(D.date_type,\", \",D.date_birth)),\"\") as  Birthday\n" + 
					"from (((contact C  left join address A on C.contact_id=A.contac_id)\n" + 
					"left join phone P on C.contact_id=P.c_id)\n" + 
					"left join Date D on C.contact_id=D.con_id)\n" + 
					"	group by C.contact_id");
			while(myResult.next())
			{
				ContactListForm temp = convertRowtoContact(myResult);
				manager.add(temp);
			}
			return manager;
		}
		finally{
			close(myStatement,myResult);
		}

	}

	private ContactListForm convertRowtoContact(ResultSet myResult) throws SQLException{
		int id= myResult.getInt("contact_id");
		String fname= myResult.getString("Fname");
		String mname= myResult.getString("Mname");
		String lname = myResult.getString("Lname");
		String addresses = myResult.getString("Address");
		String phones = myResult.getString("Phone");
		String birthday = myResult.getString("Birthday");


		ContactListForm temp = new ContactListForm(id,fname,mname,lname,addresses,phones,birthday);
		return temp;

	}



	public List<ContactListForm> searchForContact(String l) throws Exception{
		List<ContactListForm> list = new ArrayList<>();
		PreparedStatement myStatement=null;
		ResultSet myResult=null;

		try{

			myStatement=myConnection.prepareStatement("select C.contact_id,C.Fname,C.Mname,C.Lname,\n" + 
					"IFNULL(group_concat(Distinct concat(A.address_type,': ',A.address_street,',',A.city, ',',A.state, ',',A.zipcode,' \\n')),\"\") as Address,\n" + 
					"IFNULL(group_concat(Distinct concat(P.phone_type,': ',P.area_code,',',P.pnumber,'\\n')) ,\"\") as Phone,\n" + 
					"IFNULL(group_concat(Distinct concat(D.date_type,\", \",D.date_birth)),\"\") as  Birthday\n" + 
					"from (((contact C  left join address A on C.contact_id=A.contac_id)\n" + 
					"left join phone P on C.contact_id=P.c_id)\n" + 
					"left join Date D on C.contact_id=D.con_id)\n" + 
					"\n" +
					"where C.Fname like ? or C.Mname like ? or C.Lname like ?\n"
					+"or A.address_street like ? or A.city like ? or A.state like ? or A.zipcode like ? \n"
					+"or P.area_code like ? or P.pnumber like ?\n"
					+" or D.date_birth like ?\n"
					+"group by contact_id\n" +
					"");
			myStatement.setString(1, "%" + l +"%");
			myStatement.setString(2, "%" + l +"%");
			myStatement.setString(3, "%" + l +"%");
			myStatement.setString(4, "%" + l +"%");
			myStatement.setString(5, "%" + l +"%");
			myStatement.setString(6, "%" + l +"%");
			myStatement.setString(7, "%" + l +"%");
			myStatement.setString(8, "%" + l +"%");
			myStatement.setString(9, "%" + l +"%");
			myStatement.setString(10, "%" + l +"%");
			myResult=myStatement.executeQuery();

			while(myResult.next()){
				ContactListForm temp=convertRowtoContact(myResult);
				list.add(temp);
			}
			return list;
		}
		finally{
			close(myStatement,myResult);
		}
	}


	private static void close(Connection myConnection, Statement myStatement, ResultSet myResult)
			throws SQLException {

		if (myResult != null) 
                {
			myResult.close();
		}

		if (myStatement != null) 
                {
			myStatement.close();
		}

		if (myConnection != null)
                {
			myConnection.close();
		}
	}

	private void close(Statement myStatement, ResultSet myResult) throws SQLException 
        {
		close(null, myStatement, myResult);
	}

	
	
	
	
	public void addContact(ContactListForm theContact) throws Exception{
		PreparedStatement myStatement=null;
		Statement my=null;
		ResultSet res=null;
		try{
		
		if(theContact.getFirstName().equals("") && theContact.getMiddleName().equals("") && theContact.getLastName().equals("")){
			return;
		}
                
		myStatement=myConnection.prepareStatement("insert into contact (Fname,Mname,Lname) values (?,?,?) ");
		myStatement.setString(1, theContact.getFirstName());
		myStatement.setString(2, theContact.getMiddleName());
		myStatement.setString(3, theContact.getLastName());
		myStatement.executeUpdate();
		
		my = myConnection.createStatement();
		res = my.executeQuery("select max(contact_id) as total from contact");
		while(res.next()){
			crecords=res.getInt("total");
		}
	
		
		if(!theContact.getHomeStreet().equals("") || !theContact.getHomeCity().equals("") || !theContact.getHomeState().equals("") || !theContact.getHomeZIP().equals("")){
		myStatement = myConnection.prepareStatement("insert into address(contac_id,address_type,address_street,city,state,zipcode) values (?,'home',?,?,?,?)");
		myStatement.setInt(1, crecords);
		myStatement.setString(2, theContact.getHomeStreet());
		myStatement.setString(3, theContact.getHomeCity());
		myStatement.setString(4, theContact.getHomeState());
		myStatement.setString(5, theContact.getHomeZIP());
		myStatement.executeUpdate();
		}
		
		if(!theContact.getWorkStreet().equals("") || !theContact.getWorkCity().equals("") || !theContact.getWorkState().equals("") || !theContact.getWorkZIP().equals("")){
		myStatement = myConnection.prepareStatement("insert into address(contac_id,address_type,address_street,city,state,zipcode) values (?,'work',?,?,?,?)");
		myStatement.setInt(1, crecords);
		myStatement.setString(2, theContact.getWorkStreet());
		myStatement.setString(3, theContact.getWorkCity());
		myStatement.setString(4, theContact.getWorkState());
		myStatement.setString(5, theContact.getWorkZIP());
		myStatement.executeUpdate();
		}
		
		
		if(!theContact.getCellAreaCode().equals("") || !theContact.getCellPhone().equals("")){
		myStatement = myConnection.prepareStatement("insert into phone(c_id,phone_type,area_code,pnumber) values (?,'cell',?,?)");	
		myStatement.setInt(1, crecords);
		myStatement.setString(2, theContact.getCellAreaCode());
		myStatement.setString(3, theContact.getCellPhone());
		myStatement.executeUpdate();
		close(null,myStatement,null);
			}
		
		
		if(!theContact.getHomeAreaCode().equals("") || !theContact.getHomePhone().equals("")) {
		myStatement = myConnection.prepareStatement("insert into phone(c_id,phone_type,area_code,pnumber) values (?,'home',?,?)");
		myStatement.setInt(1, crecords);
		myStatement.setString(2, theContact.getHomeAreaCode());
		myStatement.setString(3, theContact.getHomePhone());
		myStatement.executeUpdate();
		}
		
		
		
		if(!theContact.getWorkAreaCode().equals("") || !theContact.getWorkPhone().equals("")) {
		myStatement = myConnection.prepareStatement("insert into phone(c_id,phone_type,area_code,pnumber) values (?,'work',?,?)");
		myStatement.setInt(1, crecords);
		myStatement.setString(2, theContact.getWorkAreaCode());
		myStatement.setString(3, theContact.getWorkPhone());
		myStatement.executeUpdate();
		}

		
		if(!theContact.getBD().equals("")){
		myStatement = myConnection.prepareStatement("insert into Date(con_id,date_type,date_birth) values (?,?,?)");
		myStatement.setInt(1, crecords);
		myStatement.setString(2, theContact.getFormat());
		myStatement.setString(3, theContact.getBD());
		myStatement.executeUpdate();
		close(myStatement,null);
		}
                }
		finally{
			close(myStatement,null);
		}
	}
	
	
	
	public void modifyContact(ContactListForm theContact) throws Exception{
		PreparedStatement myStatement=null;
		ResultSet res=null;
		
		myStatement = myConnection.prepareStatement("update contact set Fname=?,Mname=?,Lname=? where contact_id=?");
		myStatement.setString(1, theContact.getFirstName());
		myStatement.setString(2, theContact.getMiddleName());
		myStatement.setString(3, theContact.getLastName());
		myStatement.setInt(4, theContact.getID());
		myStatement.executeUpdate();
		
		
			myStatement=myConnection.prepareStatement("select count(*) from phone where c_id=? and phone_type='home'");
			myStatement.setInt(1,theContact.getID());
			res=myStatement.executeQuery();
			while(res.next())
			{
				check=res.getInt("count(*)");
			}
			if(check==1)
			{
				if(theContact.getHomePhone().equals("") && theContact.getHomeAreaCode().equals("")  )
				{
					
					myStatement = myConnection.prepareStatement("delete from phone where c_id=? and phone_type='home'");
					myStatement.setInt(1, theContact.getID());
					myStatement.executeUpdate();
					}
				
				else {
				myStatement = myConnection.prepareStatement("update phone set area_code=?,pnumber=? where c_id=? and phone_type='home'");
				myStatement.setString(1, theContact.getHomeAreaCode());
				myStatement.setString(2, theContact.getHomePhone());
				myStatement.setInt(3, theContact.getID());
				myStatement.executeUpdate();
				}
			}
			else
			{
				if(!theContact.getHomeAreaCode().equals("") || !theContact.getHomePhone().equals("")) 
				{
				myStatement = myConnection.prepareStatement("insert into phone(c_id,phone_type,area_code,pnumber) values(?,'home',?,?)");
				myStatement.setInt(1, theContact.getID());
				myStatement.setString(2, theContact.getHomeAreaCode());
				myStatement.setString(3, theContact.getHomePhone());
				
				myStatement.executeUpdate();
				}
			}
		
		
		
			myStatement=myConnection.prepareStatement("select count(*) from phone where c_id=? and phone_type='cell'");
			myStatement.setInt(1,theContact.getID());
			res=myStatement.executeQuery();
			while(res.next())
			{
				check=res.getInt("count(*)");
			}
			if(check==1)
			{
				if(theContact.getCellPhone().equals("") && theContact.getCellAreaCode().equals(""))
				{
					
					myStatement = myConnection.prepareStatement("delete from phone where c_id=? and phone_type='cell'");
					myStatement.setInt(1, theContact.getID());
					myStatement.executeUpdate();
					}
				else
				{
				myStatement = myConnection.prepareStatement("update phone set area_code=?,pnumber=? where c_id=? and phone_type='cell'");
				myStatement.setString(1, theContact.getCellAreaCode());
				myStatement.setString(2, theContact.getCellPhone());
				myStatement.setInt(3, theContact.getID());
				myStatement.executeUpdate();
				}
			}
			else
			{
				if(!theContact.getCellAreaCode().equals("") || !theContact.getCellPhone().equals("")) 
				{
				myStatement = myConnection.prepareStatement("insert into phone(c_id,phone_type,area_code,pnumber) values(?,'cell',?,?)");
				myStatement.setInt(1, theContact.getID());
				myStatement.setString(2, theContact.getCellAreaCode());
				myStatement.setString(3, theContact.getCellPhone());
				myStatement.executeUpdate();
				}
			}
			
			
			
			myStatement=myConnection.prepareStatement("select count(*) from phone where c_id=? and phone_type='work'");
			myStatement.setInt(1,theContact.getID());
			res=myStatement.executeQuery();
			while(res.next())
			{
				check=res.getInt("count(*)");
			}
			if(check==1)
			{	
				if(theContact.getWorkStreet().equals("") && theContact.getWorkAreaCode().equals("")  )
				{
					
					myStatement = myConnection.prepareStatement("delete from phone where c_id=? and phone_type='work'");
					myStatement.setInt(1, theContact.getID());
					myStatement.executeUpdate();
					}
				else
				{
				myStatement = myConnection.prepareStatement("update phone set area_code=?,pnumber=? where c_id=? and phone_type='work'");
				myStatement.setString(1, theContact.getWorkAreaCode());
				myStatement.setString(2, theContact.getWorkPhone());
				myStatement.setInt(3, theContact.getID());
				myStatement.executeUpdate();
				}
				
			}
			else
			{
				if(!theContact.getWorkAreaCode().equals("") || !theContact.getWorkPhone().equals("")) 
				{
				myStatement = myConnection.prepareStatement("insert into phone(c_id,phone_type,area_code,pnumber) values(?,'work',?,?)");
				myStatement.setInt(1, theContact.getID());
				myStatement.setString(2, theContact.getWorkAreaCode());
				myStatement.setString(3, theContact.getWorkPhone());
				myStatement.executeUpdate();
				
			}
			}
		
		
			myStatement=myConnection.prepareStatement("select count(*) from address where contac_id=? and address_type='work'");
			myStatement.setInt(1,theContact.getID());
			res=myStatement.executeQuery();
			while(res.next())
			{
				check=res.getInt("count(*)");
			}
			if(check==1)
			{
				if(theContact.getWorkStreet().equals("") && theContact.getWorkCity().equals("") &&
						theContact.getWorkState().equals("")&& theContact.getWorkZIP().equals(""))
				{
					
					myStatement = myConnection.prepareStatement("delete from address where contac_id=? and address_type='work'");
					myStatement.setInt(1, theContact.getID());
					myStatement.executeUpdate();
					}
				else
				{
				myStatement = myConnection.prepareStatement("update address set address_street=?,city=?,state=?,zipcode=? where contac_id=? and address_type='work'");
				myStatement.setString(1, theContact.getWorkStreet());
				myStatement.setString(2, theContact.getWorkCity());
				myStatement.setString(3, theContact.getWorkState());
				myStatement.setString(4, theContact.getWorkZIP());
				myStatement.setInt(5, theContact.getID());
				myStatement.executeUpdate();
				}
			}
			else
			{

				if(!theContact.getWorkStreet().equals("") || !theContact.getWorkCity().equals("") || !theContact.getWorkState().equals("") || !theContact.getWorkZIP().equals(""))
				{
				myStatement = myConnection.prepareStatement("insert into address(contac_id,address_type,address_street,city,state,zipcode) values(?,'work',?,?,?,?)");
				myStatement.setInt(1, theContact.getID());
				myStatement.setString(2, theContact.getWorkStreet());
				myStatement.setString(3, theContact.getWorkCity());
				myStatement.setString(4, theContact.getWorkState());
				myStatement.setString(5, theContact.getWorkZIP());
				myStatement.executeUpdate();
				}
			}
		
		
			myStatement=myConnection.prepareStatement("select count(*) from address where contac_id=? and address_type='home'");
			myStatement.setInt(1,theContact.getID());
			res=myStatement.executeQuery();
			while(res.next())
			{
				check=res.getInt("count(*)");
			}
			if(check==1)
			{
				if(theContact.getHomeStreet().equals("") && theContact.getHomeCity().equals("") &&
						theContact.getHomeState().equals("") && theContact.getHomeZIP().equals(""))
				{
					
					myStatement = myConnection.prepareStatement("delete from address where  contac_id=? and address_type='home'");
					myStatement.setInt(1, theContact.getID());
					myStatement.executeUpdate();
					}
				else
				{
				myStatement = myConnection.prepareStatement("update address set address_street=?,city=?,state=?,zipcode=? where contac_id=? and address_type='home'");
				myStatement.setString(1, theContact.getHomeStreet());
				myStatement.setString(2, theContact.getHomeCity());
				myStatement.setString(3, theContact.getHomeState());
				myStatement.setString(4, theContact.getHomeZIP());
				myStatement.setInt(5, theContact.getID());
				myStatement.executeUpdate();
				}
			}
			else
			{ 
				if(!theContact.getHomeStreet().equals("") || !theContact.getHomeCity().equals("") || !theContact.getHomeState().equals("") || !theContact.getHomeZIP().equals(""))
				{
					myStatement = myConnection.prepareStatement("insert into address(contac_id,address_type,address_street,city,state,zipcode) values(?,'home',?,?,?,?)");
				myStatement.setInt(1, theContact.getID());
				myStatement.setString(2, theContact.getHomeStreet());
				myStatement.setString(3, theContact.getHomeCity());
				myStatement.setString(4, theContact.getHomeState());
				myStatement.setString(5, theContact.getHomeZIP());
				myStatement.executeUpdate();
				}
				
			}
			
			
			
			myStatement=myConnection.prepareStatement("select count(*) from Date where con_id=? ");
			myStatement.setInt(1,theContact.getID());
			res=myStatement.executeQuery();
			while(res.next())
			{
				check=res.getInt("count(*)");
			}
			if(check==1)
			{
				if(theContact.getBD().equals("")){
                                    myStatement = myConnection.prepareStatement("delete from Date where con_id=?");
                                    myStatement.setInt(1, theContact.getID());
                                    myStatement.executeUpdate();
				}
				else {
                                    myStatement = myConnection.prepareStatement("update Date set date_type=?,date_birth=? where con_id=?");
                                    myStatement.setString(1, theContact.getFormat());
                                    myStatement.setString(2, theContact.getBD());
                                    myStatement.setInt(3, theContact.getID());
                                    myStatement.executeUpdate();
				}
			}
			else
			{
				if(!theContact.getBD().equals(""))
				{
				myStatement = myConnection.prepareStatement("insert into Date(con_id,date_type,date_birth) values(?,?,?)");
				myStatement.setInt(1, theContact.getID());
				myStatement.setString(2, theContact.getFormat());
				myStatement.setString(3, theContact.getBD());
				
				myStatement.executeUpdate();
			}
			}
	}
	
	
	
	
	public void deleteContact(ContactListForm theContact) throws Exception
	
	{
		PreparedStatement myStatement;
		myStatement = myConnection.prepareStatement("delete from contact where contact_id=?");
		myStatement.setInt(1, theContact.getID());
		myStatement.executeUpdate();
		
		myStatement = myConnection.prepareStatement("delete from address where contac_id=?");
		myStatement.setInt(1, theContact.getID());
		myStatement.executeUpdate();
		
		myStatement = myConnection.prepareStatement("delete from phone where c_id=?");
		myStatement.setInt(1, theContact.getID());
		myStatement.executeUpdate();
		
		myStatement = myConnection.prepareStatement("delete from Date where con_id=?");
		myStatement.setInt(1, theContact.getID());
		myStatement.executeUpdate();
		
	}
	
	public ContactListForm getSpecificContact(int id) throws Exception
	{
		PreparedStatement myStatement=null;
		ResultSet res=null;
		f=m=l=homeStreet=homeCity=homeState=homeZipcode=workStreet=workCity=workState=workZipcode=cell_area_code=cellNumber=home_area_code=homeNumber=work_area_code=workNumber=dformat=date="";
		
		try
		{
			myStatement = myConnection.prepareStatement("select * from contact where contact_id=?");
			myStatement.setInt(1, id);
			res=myStatement.executeQuery();
			while(res.next())
			{
				f=res.getString("Fname");
				m=res.getString("Mname");
				l=res.getString("Lname");
				}
			
		
			myStatement = myConnection.prepareStatement("select * from address where contac_id=? and address_type='home'");
			myStatement.setInt(1, id);
			res=myStatement.executeQuery();
			
			while(res.next()) {
				homeStreet=res.getString("address_street");
			homeCity=res.getString("city");
			homeState=res.getString("state");
			 homeZipcode=res.getString("zipcode");
			}
			
			
			
			myStatement = myConnection.prepareStatement("select * from address where contac_id=? and address_type='work'");
			myStatement.setInt(1, id);
			res=myStatement.executeQuery();
			while(res.next())
			{
				
				 workStreet=res.getString("address_street");
				 workCity=res.getString("city");
				 workState=res.getString("state");
				 workZipcode=res.getString("zipcode");
			}
			
			
			
			myStatement = myConnection.prepareStatement("select * from phone where c_id=? and phone_type='cell'");
			myStatement.setInt(1, id);
			res=myStatement.executeQuery();
			while(res.next()) 
			{
				cell_area_code=res.getString("area_code");
			
			 cellNumber=res.getString("pnumber");
			}
			
			
			myStatement = myConnection.prepareStatement("select * from phone where c_id=? and phone_type='home'");
			myStatement.setInt(1, id);
			res=myStatement.executeQuery();
			while(res.next()) 
			{
			 home_area_code=res.getString("area_code");
			 homeNumber=res.getString("pnumber");
			}
			
			
			
			myStatement = myConnection.prepareStatement("select * from phone where c_id=? and phone_type='work'");
			myStatement.setInt(1, id);
			res=myStatement.executeQuery();
			while(res.next()) {
			work_area_code=res.getString("area_code");
			 workNumber=res.getString("pnumber");
			}
			
			
			myStatement = myConnection.prepareStatement("select * from Date where con_id=?");
			myStatement.setInt(1, id);
			res=myStatement.executeQuery();
			while(res.next()) {
			dformat=res.getString("date_type");
			date=res.getString("date_birth");
			}
			
			ContactListForm temp=new ContactListForm(f,m,l,homeStreet,homeCity,homeState,homeZipcode,workStreet,workCity,workState,workZipcode,cell_area_code,cellNumber,home_area_code,homeNumber,work_area_code,workNumber,dformat,date);
			System.out.println(f+","+m+","+l);
			return temp;
			
		}
		finally
		{
			close(myStatement,null);
		}
	
	}

	public static void main(String[] args) throws Exception {
		ContactConnectDatabase emp = new ContactConnectDatabase();
		
		


	}
}
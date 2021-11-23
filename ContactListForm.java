/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contact_app_manager;
/**
 *
 * @author Binal Shah
 */
public class ContactListForm {
	
	int id;
	private String lastName;
	private String firstName;
	private String middleName;
	private String homeStreet;
	private String homeCity;
	private String homeState;
	private String homeZipcode;
	private String workStreet;
	private String workCity;
	private String workState;
	private String workZipcode;
	private String home_area_code;
	private String homeNumber;
	private String work_area_code;
	private String workNumber;
	private String cell_area_code;
	private String cellNumber;
	private String dayformat;
	private String bdate;
	
	private String addresses;
	private String phones;


	public ContactListForm(int iden,String fname, String mname, String lname,String addresses,String phone,String bday) {
		super();
		this.id=iden;
		this.lastName = lname;
		this.firstName = fname;
		this.middleName = mname;
		this.addresses=addresses;
		this.phones = phone;
		this.bdate=bday;

	}


	public ContactListForm(String fname, String mname, String lname,String homeStreet,String homeCity,String homeState,String homeZipcode,
			String workStreet,String workCity,String workState,String workZipcode,String cell_area_code,String cellNumber,String home_area_code,String homeNumber,String work_area_code,String workNumber,
			String format,String bday) {
		super();
		this.lastName = lname;
		this.firstName = fname;
		this.middleName = mname;
		this.homeStreet=homeStreet;
		this.homeCity=homeCity;
		this.homeState=homeState;
		this.homeZipcode=homeZipcode;
		this.workStreet=workStreet;
		this.workCity=workCity;
		this.workState=workState;
		this.workZipcode=workZipcode;
		this.cell_area_code=cell_area_code;
		this.cellNumber=cellNumber;
		this.home_area_code=home_area_code;
		this.homeNumber=homeNumber;
		this.work_area_code=work_area_code;
		this.workNumber=workNumber;
		this.dayformat=format;
		this.bdate=bday;

	}


	public int getID() {
		return id;
	}
	
	public void setID(int iden) {
		iden=id;
	}
	

	public String getFirstName() {
		return firstName;
	}
	
	
	public void setFirstName(String s) {
		firstName=s;
	}
	
	

	public String getLastName() {
		return lastName;
	}
	
	
	public void setLastName(String s) {
		lastName=s;
	}

	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String s) {
		middleName=s;
	}


	public String getHomeStreet() {
		return homeStreet;
	}

	public void setHomeStreet(String s) {
		 homeStreet=s;
	}
	
	public String getHomeCity() {
		return homeCity;
	}

	public void setHomeCity(String s) {
		 homeCity=s;
	}
	
	public String getHomeState() {
		return homeState;
	}

	public void setHomeState(String s) {
		 homeState=s;
	}
	
	public String getHomeZIP() {
		return homeZipcode;
	}

	public void setHomeZIP(String s) {
		 homeZipcode=s;
	}

	
	public String getWorkStreet() {
		return workStreet;
	}

	public void setWorkStreet(String s) {
		 workStreet=s;
	}
	
	public String getWorkCity() {
		return workCity;
	}

	public void setWorkCity(String s) {
		 workCity=s;
	}
	
	public String getWorkState() {
		return workState;
	}

	public void setWorkState(String s) {
		 workState=s;
	}
	
	public String getWorkZIP() {
		return workZipcode;
	}

	public void setWorkZIP(String s) {
		 workZipcode=s;
	}



	public String getCellAreaCode() {
		return cell_area_code;
	}
	
	public void setCellAreaCode(String s) {
		cell_area_code=s;
	}
	
	public String getCellPhone() {
		return cellNumber;
	}
	
	public void setCellPhone(String s) {
		cellNumber=s;
	}

	
	public String getHomeAreaCode() {
		return home_area_code;
	}
	public void setHomeAreaCode(String s) {
		home_area_code=s;
	}
	
	
	public String getHomePhone() {
		return homeNumber;
	}
	
	public void setHomePhone(String s) {
		homeNumber=s;
	}
	
	
	
	public String getWorkAreaCode() {
		return work_area_code;
	}
	public void setWorkAreaCode(String s) {
		work_area_code=s;
	}
	
	public String getWorkPhone() {
		return workNumber;
	}
	
	public void setWorkPhone(String s) {
		workNumber=s;
	}
	
	
	public String getFormat() {
		return dayformat;
	}
	public void setFormat(String s) {
		dayformat=s;
	}
	
	
	public String getBD() {
		return bdate;
	}
	
	public void setBD(String s) {
		bdate=s;
	}


	public String getAddresses() {
		return addresses;
	}
	public String getPhones() {
		return phones;
	}


	@Override
	public String toString() {
		return String
				.format("Employee [firstName=%s, middleName=%s,lastName=%s]",id,
						 firstName, middleName, lastName);
	}

}


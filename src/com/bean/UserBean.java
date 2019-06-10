package com.bean;

import java.sql.ResultSet;

import com.dao.UserRegistrationDao;



public class UserBean {

	private String id,name,email,password,confirmpassword,dob,contact,gender,token,stresslevel;
	
	


	public String getStresslevel() {
		return stresslevel;
	}




	public void setStresslevel(String stresslevel) {
		this.stresslevel = stresslevel;
	}




	public String getToken() {
		return token;
	}

	
	
	
	public void setToken(String token) {
		this.token = token;
	}


	private int id1;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getId1() {
		return id1;
	}

	public void setId1(int id1) {
		this.id1 = id1;
	}

	

	public int insertData() 
	{
		String query="insert into userregistration values(?,?,?,?,?,?,?,?)";
		UserRegistrationDao dao=new UserRegistrationDao();
		dao.makeConnection();
		int i=dao.insertData(query,this);
		
		return i;
	}
	
	
	
	public int insertToken() 
	{
		String query="insert into fbtoken values(?,?,?,?)";
		UserRegistrationDao dao=new UserRegistrationDao();
		dao.makeConnection();
		int i=dao.insertTokenData(query,this);
		
		return i;
	}
	
	
	public String userlogin()
	{
		String query="select * from userregistration where email=? and password=?";
		
		UserRegistrationDao dao=new UserRegistrationDao();
		dao.makeConnection();
		String rs=dao.userlogin(query,this);
		
		return rs;
		
		
	}
	
	
	public boolean checkForgotPassword() 
	{
		String query="select * from userregistration where Email='"+getEmail()+"'";
		UserRegistrationDao dao=new UserRegistrationDao();
	    dao.makeConnection();
		ResultSet rs= dao.searchForgotPassword(query);
		
		try
		{
			rs.next();
			if(getEmail().equals(rs.getString(3)))
			{
				return true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	
	public int changePassword()throws Exception 
	{
		String query="UPDATE userregistration SET password=? , confirmpassword=? WHERE email='"+getEmail()+"' ";
		UserRegistrationDao dao=new UserRegistrationDao();
		dao.makeConnection();
		int i=dao.changeForgotPassword(query,this);
		/*try
		{
			rs.next();
			if()
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
		return i;
	}
	
	
	public int deleteRecord()
	{
		String query="delete from userregistration where id="+getId1()+"";
		
		UserRegistrationDao dao=new UserRegistrationDao();
		dao.makeConnection();
		int i=dao.deleteRecord(query,this);
		return i;
		
	}
	
	
	
	public boolean validuser() 
	{
		String query="select * from userregistration where email='"+getEmail()+"'";
		UserRegistrationDao dao=new UserRegistrationDao();
	    dao.makeConnection();
		ResultSet rs= dao.uservalid(query);
		
		try
		{
			rs.next();
			if(getEmail().equals(rs.getString(3)))
			{
				return true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	
	
	public int insertStresslevel() 
	{
		String query="insert into stresslevel values(?,?,?,?)";
		UserRegistrationDao dao=new UserRegistrationDao();
		dao.makeConnection();
		int i=dao.insertStress(query,this);
		
		return i;
	}
	
	
	public ResultSet userverification() {
		
	    System.out.println("i'm in LoginData method");
			
			//String query="select * from driverlist where phone='"+getPhone()+"'";
			String query="select * from userregistration where email='"+getEmail()+"'";
			UserRegistrationDao dao=new UserRegistrationDao();
			dao.makeConnection();
			ResultSet rs=dao.userauthentication(query);
			
			return rs;
		}
	
	
	
	
	
}

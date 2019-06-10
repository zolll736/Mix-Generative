package com.bean;

import java.sql.ResultSet;

import com.dao.AdministratorDao;





public class AdministratorBean {

	private String email,password,confirmpassword;

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
	
	
	public boolean administratorlogin()
	{
		String query="select * from  administratorlogin where administrator_email='"+getEmail()+"' AND administrator_password='"+getPassword()+"'";
		
		AdministratorDao dao=new AdministratorDao();
		dao.makeConnection();
		ResultSet rs=dao.administratorlogin(query);
		 try
		 {
		   rs.next();
			
			 System.out.println("in administrator bean try");
			   if(getEmail().equals(rs.getString(1)) && getPassword().equals(rs.getString(2)))
			   {
				 
				 
				   return true;
				   
			   }
		   
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		return false;
		
		
	}
	
	
	public boolean validuser1() 
	{
		String query="select * from administratorlogin where administrator_email='"+getEmail()+"'";
		AdministratorDao dao=new AdministratorDao();
	    dao.makeConnection();
		ResultSet rs= dao.uservalid1(query);
		
		try
		{
			rs.next();
			if(getEmail().equals(rs.getString(1)))
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
	
	
	
	
}

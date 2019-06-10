package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdministratorDao {
	
	Connection con=null;
	public   Connection makeConnection(){
 		try{
 		      Class.forName("com.mysql.jdbc.Driver");
 		      System.out.println("driver loaded..");
 		
 		    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mixedgenerative", "root", "root");
 		    System.out.println("connection done..");
 		}
 		
 		catch(Exception e)
 		{
 			e.printStackTrace();
 		}
 		return con;
 	}

	
	
       public ResultSet administratorlogin(String query) {
		// TODO Auto-generated method stub
		//boolean login=false;
		ResultSet rs=null;
		try{
			System.out.println("in Administrator login Dao..");
			
		
			Statement st=con.createStatement();
			System.out.println(st);
			System.out.println("hi i am in  Administrator  login");
			rs=st.executeQuery(query);
			System.out.println("query="+rs);
		   System.out.println("  1  Query done in login..");
		//  return rs;
		   
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return rs;
		
	}
       
       
       
       public ResultSet uservalid1(String query) 
   	{
   		Statement st=null;
   		ResultSet rs=null;
   	try {
   			st= con.createStatement();
   			System.out.println("In vaalid user DAO");
   			rs=st.executeQuery(query);
   		} 
   	
   	catch (SQLException e) {
   			e.printStackTrace();
   		}
   		return rs;
   	}

       
       public ResultSet senduserinfo() {
   		ResultSet rs=null;
   		String sql = "Select name,email,stresslevel from stresslevel";
   		try {
   			Statement stmt = con.createStatement();
   		     rs = stmt.executeQuery(sql);
   		
   			}catch (Exception e) {
   				e.printStackTrace();
   			}
   		return rs;
   	}

}

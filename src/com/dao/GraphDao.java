package com.dao;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import com.connection.DBConnection;

public class GraphDao {
	
	boolean flag=false;
	String sql;
	PreparedStatement pm;
	ResultSet rs;
	Connection con;
	String girl=null;
	
	public int Girl()
	{
		int count=0;
		String sql="select count(title) AS girlcount from addimages where title='girl'";
		
		con=DBConnection.getConnection();
		
		try {
			pm=con.prepareStatement(sql);
			
			rs=pm.executeQuery();
			
			while(rs.next())
			{
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return count;
	}
	
	public int bike()
	{
		int count=0;
		String sql="select count(title) AS girlcount from addimages where title='bike'";
		
		con=DBConnection.getConnection();
		
		try {
			pm=con.prepareStatement(sql);
			
			rs=pm.executeQuery();
			
			while(rs.next())
			{
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return count;
	}
	
	public int car()
	{
		int count=0;
		String sql="select count(title) AS girlcount from addimages where title='car'";
		
		con=DBConnection.getConnection();
		
		try {
			pm=con.prepareStatement(sql);
			
			rs=pm.executeQuery();
			
			while(rs.next())
			{
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return count;
	}
	
	public int boy()
	{
		int count=0;
		String sql="select count(title) AS girlcount from addimages where title='boy'";
		
		con=DBConnection.getConnection();
		
		try {
			pm=con.prepareStatement(sql);
			
			rs=pm.executeQuery();
			
			while(rs.next())
			{
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return count;
	}
	
	public int couple()
	{
		int count=0;
		String sql="select count(title) AS girlcount from addimages where title='couple'";
		
		con=DBConnection.getConnection();
		
		try {
			pm=con.prepareStatement(sql);
			
			rs=pm.executeQuery();
			
			while(rs.next())
			{
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return count;
	}
	public int animal()
	{
		int count=0;
		String sql="select count(title) AS girlcount from addimages where title='animal'";
		
		con=DBConnection.getConnection();
		
		try {
			pm=con.prepareStatement(sql);
			
			rs=pm.executeQuery();
			
			while(rs.next())
			{
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return count;
	}
	
	public int bird()
	{
		int count=0;
		String sql="select count(title) AS girlcount from addimages where title='bird'";
		
		con=DBConnection.getConnection();
		
		try {
			pm=con.prepareStatement(sql);
			
			rs=pm.executeQuery();
			
			while(rs.next())
			{
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return count;
	}
	
	public int flower()
	{
		int count=0;
		String sql="select count(title) AS girlcount from addimages where title='car'";
		
		con=DBConnection.getConnection();
		
		try {
			pm=con.prepareStatement(sql);
			
			rs=pm.executeQuery();
			
			while(rs.next())
			{
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return count;
	}
	
	public int nature()
	{
		int count=0;
		String sql="select count(title) AS girlcount from addimages where title='nature'";
		
		con=DBConnection.getConnection();
		
		try {
			pm=con.prepareStatement(sql);
			
			rs=pm.executeQuery();
			
			while(rs.next())
			{
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return count;
	}
	
	public String key()
	{
		
		char[] characterSet = "124".toCharArray();
		int length=3;
		String filekey=null;
						
			 Random random1 = new SecureRandom();
			    char[] result = new char[length];
			    for (int i = 0; i < result.length; i++) {
			       
			        int randomCharIndex = random1.nextInt(characterSet.length);
			        result[i] = characterSet[randomCharIndex];
			    }
			    filekey=new String(result);
				return filekey;

	}
	
	public String key1()
	{
		
		char[] characterSet = "345".toCharArray();
		int length=2;
		String filekey=null;
						
			 Random random1 = new SecureRandom();
			    char[] result = new char[length];
			    for (int i = 0; i < result.length; i++) {
			       
			        int randomCharIndex = random1.nextInt(characterSet.length);
			        result[i] = characterSet[randomCharIndex];
			    }
			    filekey=new String(result);
				return filekey;

	}
	public static ResultSet getfile(String filename) 
	{
		ResultSet rs = null;
	try
	{
		Connection conn=DBConnection.getConnection();
			Statement stmt=conn.createStatement();
			rs=stmt.executeQuery("select * from query_images where image_name='"+filename+"'");	
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	


	
}

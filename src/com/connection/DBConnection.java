package com.connection;

import java.sql.*;

public class DBConnection {

	public static String DBName="mixedgenerative";
	public static String DBUSER="root";
	public static String DBPASSWORD="root";
	public static Connection connection;
	public static Connection getConnection() {
		
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Registered....");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DBName,DBUSER,DBPASSWORD);
			System.out.println("Connection established....");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return connection;
	}
	
	
	
}

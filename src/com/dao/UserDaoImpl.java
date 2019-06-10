package com.dao;
import java.sql.*;
import java.util.ArrayList;

import com.bean.TitleBean;
import com.bean.User;
import com.bean.UserHistoryBean;
import com.connection.DBConnection;

public class UserDaoImpl implements UserDao{
	
	boolean flag=false;
	String sql;
	PreparedStatement pm;
	ResultSet rs;
	
	@Override
	public boolean InsertUser(User user) {
		
		
		try {
			sql="insert into register(name,email,password,mobileno,address,gender,dob,status) values(?,?,?,?,?,?,?,?)";
			Connection con=DBConnection.getConnection();
			
			 pm=con.prepareStatement(sql);
			
			pm.setString(1, user.getName());
			pm.setString(2, user.getEmail());
			pm.setString(3, user.getPassword());
			pm.setString(4, user.getMobileno());
			pm.setString(5, user.getAddress());
			pm.setString(6, user.getGender());
			pm.setString(7, user.getDob());
			pm.setString(8, user.getStatus());
			
			int index=pm.executeUpdate();
			
			if(index>0)
			{
				System.out.println("enter into the database");
				flag=true;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public boolean availableUser(User user) {
		
		sql="select * from register where email='"+user.getEmail()+"'";
		try {
			Statement st=DBConnection.getConnection().createStatement();
			rs=st.executeQuery(sql);
			
			while(rs.next())
			{
				flag=true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		return flag;
	}

	@Override
	public User CheckUser(String email, String password) {
		
		User user = new User();
		sql="select * from register where email='"+email+"' and password='"+password+"'";
		
		//Connection con=DBConnection.getConnection();
		
		try {
			Statement stmt = DBConnection.getConnection().createStatement();
			
			rs=stmt.executeQuery(sql);
			
			if(rs.next())
			{
				   user.setId(rs.getInt(1));
				   user.setName(rs.getString(2));
	               user.setEmail(rs.getString(3));
	               user.setPassword(rs.getString(4));
	               user.setMobileno(rs.getString(5));
	               user.setAddress(rs.getString(6));
	               user.setGender(rs.getString(7));
	               user.setDob(rs.getString(8));
	               user.setStatus(rs.getString(9));
			}
			else
			{
				flag=false;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean UserDelete(User user) {
		
	 String sql="delete from register where name='"+user.getName()+"'";
	 Connection con=DBConnection.getConnection();
	 try {
		pm=con.prepareStatement(sql);
		int index =pm.executeUpdate();
		if(index>0)
		{
			flag=true;
		}
		else
		{
			flag=false;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	 
		return flag;
	}

	@Override
	public User SelectUser(String email) {
		
		User user = new User();
		String sql = "Select * from register where email ='"+email+"'";
		try {
			Statement stmt = DBConnection.getConnection().createStatement();
			 rs = stmt.executeQuery(sql);
			if(rs.next()){
				
				   user.setName(rs.getString(2));
	               user.setEmail(rs.getString(3));
	               user.setPassword(rs.getString(4));
	               user.setMobileno(rs.getString(5));
	               user.setAddress(rs.getString(6));
	               user.setGender(rs.getString(7));
	               user.setDob(rs.getString(8));
					
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return user;
		
	}

	@Override
	public ArrayList<TitleBean> getCategory() throws SQLException{
		
		String sql="select * from title";
		Connection con=DBConnection.getConnection();
		
		ArrayList<TitleBean> titleList=new ArrayList<TitleBean>();
		
		Statement st=con.createStatement();
		
		rs=st.executeQuery(sql);
		
		while(rs.next())
		{
			TitleBean bean=new TitleBean();
			
			bean.setId(rs.getInt(1));
			bean.setTitle(rs.getString(2));
			
			titleList.add(bean);
		}
		
		
		return titleList;
	}

	@Override
	public boolean UpdateUserStatus(int userID, String status) {
		
		if(status.equalsIgnoreCase("Inactive"))
			status="Active";
		else
			status="Inactive";
		
		String sql="update register set status=? where id=?";
		
		try {
			pm=DBConnection.getConnection().prepareStatement(sql);
			pm.setString(1, status);
			pm.setInt(2, userID);
			
			int index=pm.executeUpdate();
			if(index>0)
			{
				flag=true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public User SelectUserID(int userID) {
		
		String sql="select * from register where id=?";
		
		User user=new User();
		
		try {
			pm=DBConnection.getConnection().prepareStatement(sql);
			pm.setInt(1, userID);
			
			rs=pm.executeQuery();
			if(rs.next())
			{
				   user.setId(rs.getInt(1));
				   user.setName(rs.getString(2));
	               user.setEmail(rs.getString(3));
	               user.setPassword(rs.getString(4));
	               user.setMobileno(rs.getString(5));
	               user.setAddress(rs.getString(6));
	               user.setGender(rs.getString(7));
	               user.setDob(rs.getString(8));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return user;
	}

	
	@Override
	public ResultSet SelectUser() {
		ResultSet rs= null;
		String sql ="Select * from register";
		try {
			PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public boolean InsertUserHistory(UserHistoryBean historybean) {
		try {
			sql="insert into search_history(user_email,image_search,tag_search) values(?,?,?)";
			Connection con=DBConnection.getConnection();
			
			 pm=con.prepareStatement(sql);
			
			pm.setString(1, historybean.getUser_email());
			pm.setString(2, historybean.getImage_search());
			pm.setString(3, historybean.getTag_search());
			
			int index=pm.executeUpdate();
			
			if(index>0)
			{
				System.out.println("enter into the database");
				flag=true;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return flag;
	}
	
	
}

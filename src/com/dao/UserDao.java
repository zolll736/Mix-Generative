package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.TitleBean;
import com.bean.User;
import com.bean.UserHistoryBean;

public interface UserDao {

	public boolean InsertUser(User user);
	
	public boolean availableUser(User user);
	
	public User CheckUser(String email,String password);
	
	public boolean UserDelete(User user);
	
	public User SelectUser(String email);
	
	public ArrayList<TitleBean> getCategory() throws SQLException;
	
	public boolean UpdateUserStatus(int userID,String status);
	
	public User SelectUserID(int userID);
	
	public ResultSet SelectUser();
	
	public boolean InsertUserHistory(UserHistoryBean historybean);
	
	
}

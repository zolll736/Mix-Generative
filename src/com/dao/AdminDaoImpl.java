package com.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.Part;

import com.bean.AddImageBean;
import com.connection.DBConnection;

public class AdminDaoImpl implements AdminDao {
	
	PreparedStatement ps;
	ResultSet rs;
	boolean flag;
	String sql;
	Connection con;
	InputStream inputstream;

	@Override
	public boolean AddImages(AddImageBean addimage) {

		sql="insert into addimages(title_id,title,name,color,image) values(?,?,?,?,?)";
		con=DBConnection.getConnection();
		
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, addimage.getTitle_id());
			ps.setString(2, addimage.getTitle());
			ps.setString(3, addimage.getName());
			ps.setString(4, addimage.getColor());
			
			
			int index=ps.executeUpdate();
			
			
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
	public boolean CheckAdmin(String email, String password) {
		
		sql="select * from admin where email=? and password=?";
		
		con=DBConnection.getConnection();
		
		try {
			ps=con.prepareStatement(sql);
			
			ps.setString(1, email);
			ps.setString(2, password);
			
			rs= ps.executeQuery(); 
			if(rs.next())
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
	public String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
        System.out.println("contentDisp:"+contentDisp);
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
	}

	@Override
	public String ExtractTitle(String path) {
		String title=null;
       sql="select title from addimages where path='"+path+"'";
		
		con=DBConnection.getConnection();
		
		try {
			ps=con.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				title=rs.getString(1);
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return title;
		
		
}
}

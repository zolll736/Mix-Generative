package com.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/ImageController")
public class ImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String dbURL = "jdbc:mysql://localhost:3306/mixedgenerative";
    private String dbUser = "root";
    private String dbPass = "root";
    public ImageController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		InputStream inputstream=null;
		
		Part filepart=request.getPart("image");
		
		if(filepart != null)
		{
			System.out.println(filepart.getName());
			System.out.println(filepart.getSize());
			System.out.println(filepart.getContentType());
			
			inputstream=filepart.getInputStream();
		}
		
		Connection con=null;
		String message=null;
		
		 try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(dbURL, dbUser, dbPass);
			
			String sql="insert into addimage(image) values(?)";
			
			PreparedStatement ps=con.prepareStatement(sql);
			
			if(inputstream != null)
			{
				ps.setBlob(1, inputstream);
				response.sendRedirect("AdminView.jsp");
			}
			
			int col=ps.executeUpdate();
			if(col>0)
			{
				message="save in database";
			}
		} catch (SQLException e) {
			
			message="ERROR:"+ e.getMessage();
			e.printStackTrace();
		} finally
		{
			if(con !=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			
			request.setAttribute("Message", message);
		}
         
	}

}

package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.algorithms.HashCodeGenerator;

/**
 * Servlet implementation class Search
 */
@WebServlet("/HashValueSearch")
public class HashValueSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HashValueSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(true);
		
     System.out.println("in search Servlet");
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "mixedgenerative";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";
 
        Statement st;
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("Connected!");
            
            
            
            String pid1 = request.getParameter("pid");
            
            String pid=pid1.toLowerCase();
            
            System.out.println("lowercase is"+pid);
            
            HashCodeGenerator hs=new HashCodeGenerator();
            String hashcode= hs.generate(pid);
           
            System.out.println("Hash value is:"+pid);
            ArrayList al = null;
            ArrayList pid_list = new ArrayList();
            String query = "select id,title,name,color,description,image,count,hashvalue from addimages where hashvalue='" + hashcode + "'"  ;
 
            System.out.println("query " + query);
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
           
             String iddd=null;
            while (rs.next()) {
                al = new ArrayList();
 
                  iddd=rs.getString(1);

                al.add(iddd);
                al.add(rs.getString(2));
                al.add(rs.getString(3));
                al.add(rs.getString(4));
                al.add(rs.getString(5));
                al.add(rs.getString(6));
                al.add(rs.getString(7));
                al.add(rs.getString(8));
                System.out.println("al :: "+al);
                pid_list.add(al);
            }
            session.setAttribute("file",iddd);
            
            request.setAttribute("piList", pid_list);
            
            RequestDispatcher view = request.getRequestDispatcher("/ViewSimilarHashCodeImages.jsp");
            view.forward(request, response);
            conn.close();
            System.out.println("Disconnected!");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}

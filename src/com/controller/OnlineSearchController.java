package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.algorithms.OnlineURLSearch;

@WebServlet("/OnlineSearchController")
public class OnlineSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public OnlineSearchController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
      String tag=request.getParameter("tag");
      System.out.println("in online search Controller Servet "+tag);
		
		ArrayList<String> list = null;
		try {
			list = OnlineURLSearch.getData(tag);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		HttpSession session=request.getSession();
		session.setAttribute("urllist", list);
		session.setAttribute("tag", tag);
		response.sendRedirect("OnlineImages.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

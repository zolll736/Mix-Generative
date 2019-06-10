package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.UserHistoryBean;
import com.dao.UserDao;
import com.dao.UserDaoImpl;

@WebServlet("/MixedGDSearchController")
public class MixedGDSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public MixedGDSearchController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		
	    String name=(String)session.getAttribute("email");
	    
	    String imagesearch=request.getParameter("imagesearch");
	    String tagsearch=request.getParameter("tagsearch");
	    
	    UserHistoryBean bean=new UserHistoryBean();
	    bean.setUser_email(name);
	    bean.setImage_search(imagesearch);
	    bean.setTag_search(tagsearch);
	    
	    UserDao dao=new UserDaoImpl();
	    
	    if(dao.InsertUserHistory(bean))
	    {
	    	out.println("<script type=\"text/javascript\">");
			out.println("alert('User history inserted successfully');");
			out.println("location='Search.jsp';");
			out.println("</script>");
	    }
	    else
	    {
	    	out.println("<script type=\"text/javascript\">");
			out.println("alert('User history inserted successfully');");
			out.println("location='Search.jsp';");
			out.println("</script>");
	    }
	    
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

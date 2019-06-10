package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.User;

import com.dao.UserDao;
import com.dao.UserDaoImpl;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public LoginController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		UserDao dao=new UserDaoImpl();
		User user = new User();
		user=dao.CheckUser(email, password);
		
		

		
		
		if((user.getEmail()!=null && user.getEmail()!="") && (user.getPassword()!=null && user.getPassword()!=""))
		{
			if(user.getStatus().equals("Active"))
			{
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Login Successful')");
			out.println("location='UserView.jsp';");
			out.println("</script>");
			out.print("welcome"+email);
			HttpSession session=request.getSession();  
	        session.setAttribute("email",email);
			}
			else {
				request.setAttribute("ErrMsg", "Wait for activation of your account!...");
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Wait For Activation')");
				out.println("location='UserLogin.jsp';");
				out.println("</script>");
			}
		}
		else
		{
	     out.println("<script type=\"text/javascript\">");
		 out.println("alert('Login Unsuccessful')");
		 out.println("location='UserLogin.jsp';");
		out.println("</script>");
			
		}
		
	}

}

package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.User;
import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.mail.ForgotPassword;

@WebServlet("/ForgotPasswordController")
public class ForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   RequestDispatcher rd;
    public ForgotPasswordController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter  out=response.getWriter();
		String email= request.getParameter("email");
		UserDao dao=new UserDaoImpl();
		User user = dao.SelectUser(email);
		if(user.getPassword()!=null && user.getPassword()!=""){
			ForgotPassword forgotPass=new ForgotPassword();
			forgotPass.forgotPassEmailSend(email, user.getPassword());
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Password Send Successfully');");
			out.println("location='UserLogin.jsp';");
			out.println("</script>");
			
		}else{
			
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Password not send');");
			out.println("location='ForgotPassword.jsp';");
			out.println("</script>");
	}
	}

}

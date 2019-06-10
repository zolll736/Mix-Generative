package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.User;
import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.mail.SendEmail;

@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UserRegister() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	String name=null;
	String email=null;
	String password=null;
	String mobileno=null;
	String address=null;
	String gender=null;
	String dob=null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String mobileno=request.getParameter("mobileno");
		String address=request.getParameter("address");
		String gender=request.getParameter("gender");
		String dob=request.getParameter("dob");
		String status = "Inactive";
		User user= new User();
		
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setMobileno(mobileno);
		user.setAddress(address);
		user.setGender(gender);
		user.setDob(dob);
		user.setStatus(status);
		
		UserDao dao=new UserDaoImpl();
		
		if(dao.availableUser(user))
		{
			out.println("<script type=\"text/javascript\">");
			out.println("alert('User is already available');");
			out.println("location='Register.jsp';");
			out.println("</script>");
			System.out.println("User is already available");
		}
		else
		{
			if(dao.InsertUser(user))
			{
				//EmailSend es = new EmailSend();
				//es.sendRegistrationEmail(email);
				
				out.println("<script type=\"text/javascript\">");
				
				out.println("alert('Registration Successfully..');");
				SendEmail mail=new SendEmail();
				mail.RegistrationMail(email);
				out.println("location='UserLogin.jsp';");
				out.println("</script>");
				
			}
			else
			{
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Registration UnSuccessfull..');");
				out.println("location='Register.jsp';");
				out.println("</script>");
			}
		//}
		
		
	}

}
}

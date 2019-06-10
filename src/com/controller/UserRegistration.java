package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;




import com.bean.UserBean;

import java.sql.*;

/**
 * Servlet implementation class UserRegistration
 */
@WebServlet("/UserRegistration")
public class UserRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
   
    public UserRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int id=Integer.parseInt(request.getParameter("id"));

		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

		try
		{
			System.out.println("id"+id);
			UserBean bean=new UserBean();
			bean.setId1(id);
			int a = bean.deleteRecord();
			
			System.out.println("result"+a);
		
			if(a>0)
			{
				out.println("<script type=\"text/javascript\">");
			    out.println("alert('Record deleted successfully');");
			    out.println("location='viewuserinfo.jsp';");
			    out.println("</script>");
				
			/*	 JOptionPane.showMessageDialog(null,"Record deleted successfully");
				response.sendRedirect("viewuserinfo.jsp");*/
				
			}
			
			
			
			
		}
		catch(Exception e)
		{
			out.println("<script type=\"text/javascript\">");
		    out.println("alert('Record Not Deleted..Error Occured');");
		    out.println("location='viewuserinfo.jsp';");
		    out.println("</script>");
			

			//JOptionPane.showMessageDialog(null,"error");
		}

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String btn=request.getParameter("submit");

		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession(true);
		
		if(btn.equals("SignUp"))
		{
try{
	System.out.println("in servlet...");
	
	String id=request.getParameter("id");
	String name=request.getParameter("name");
	String email=request.getParameter("email");
	String password=request.getParameter("password");
	String confirmpassword=request.getParameter("confirmpassword");
	String gender=request.getParameter("gender");
	String dob=request.getParameter("dob");
	String contact=request.getParameter("contact");
	
	session.setAttribute("email", email);
	
	
	 UserBean bean=new UserBean();
	
	
	bean.setEmail(email);
	
		
		ResultSet rs=bean.userverification();
		
	
		String email2 = null;
		
		if (!rs.isBeforeFirst() )
		{
			
			HttpSession ht=request.getSession(true);
			//HttpSession sanket=request.getSession(true);
			
			ht.setMaxInactiveInterval(100);
			

			ht.setAttribute("id",id);
			ht.setAttribute("name",name);
			ht.setAttribute("email",email);
			ht.setAttribute("password",password);
			ht.setAttribute("confirmpassword",confirmpassword);
			ht.setAttribute("dob",dob);
			ht.setAttribute("contact",contact);
			ht.setAttribute("gender",gender);

			
			// this is used for generate otp
			Random r=new Random();
			int j=r.nextInt(1000);
			System.out.println("Registration OTP is :"+j);
			
			String key=""+j;
			ht.setAttribute("key", key);
			
			// this is used for send otp to mail
			KeyOnMail kom=new KeyOnMail();
			
			kom.emailUtility(email, ""+j);
			System.out.println("sent");
			
			

		out.println("<script type=\"text/javascript\">");
		out.println("alert('OTP sent on mail.Please Check.');");
		out.println("location='registrationotp.jsp';");
		out.println("</script>");
			
		}
		
		else{
			
		
		while(rs.next())
		{
			
			email2=rs.getString(3);
		   
		}
		
		if(email2.equals(email))
		{
	
			out.println("<script type=\"text/javascript\">");  
    		out.println("alert('User are already register!!!');");  
    		out.println("</script>");
    		 getServletContext().getRequestDispatcher("/userregistration.jsp").include(request, response);
			
			
		}
		else
		{
	
	
	System.out.println(""+name);
	System.out.println(""+id);
	
//	System.out.println("value are="+name+""+""+email+""+password+""+passwd+""+address+""+gender+""+education+""+dob+""+contact+""+s+"");
	
	// here we first set the attribute 
	//request.setAttribute("id", id);
	HttpSession ht=request.getSession(true);
	//HttpSession sanket=request.getSession(true);
	
	ht.setMaxInactiveInterval(100);
	

	ht.setAttribute("id",id);
	ht.setAttribute("name",name);
	ht.setAttribute("email",email);
	ht.setAttribute("password",password);
	ht.setAttribute("confirmpassword",confirmpassword);
	ht.setAttribute("dob",dob);
	ht.setAttribute("contact",contact);
	ht.setAttribute("gender",gender);

	
	// this is used for generate otp
	Random r=new Random();
	int j=r.nextInt(1000);
	System.out.println("Registration OTP is :"+j);
	
	String key=""+j;
	ht.setAttribute("key", key);
	
	// this is used for send otp to mail
	KeyOnMail kom=new KeyOnMail();
	
	kom.emailUtility(email, ""+j);
	System.out.println("sent");
	
	

out.println("<script type=\"text/javascript\">");
out.println("alert('OTP sent on mail.Please Check.');");
out.println("location='registrationotp.jsp';");
out.println("</script>");
	
	
	/* JOptionPane.showMessageDialog(null,"OTP sent on mail.Please Check.");
	 response.sendRedirect("registrationotp.jsp");*/
	
	
		}
	
}
}
		catch(Exception e){
	
}

}
		
		
		
		
if(btn.equals("OTPSubmit"))
{
	HttpSession ht=request.getSession(true);
	String key=(String)request.getSession(false).getAttribute("key");
	
	String otp=request.getParameter("otp");
	
	//String id=(String)ht.getAttribute("id");
	String id=(String)ht.getAttribute("id");
	String name=(String)ht.getAttribute("name");
	String email=(String)ht.getAttribute("email");
	String password=(String)ht.getAttribute("password");
	String confirmpassword=(String)ht.getAttribute("confirmpassword");
	String dob=(String)ht.getAttribute("dob");
	String contact=(String)ht.getAttribute("contact");
	String gender=(String)ht.getAttribute("gender");
	
	 
	System.out.println("name"+name);
	System.out.println("id"+id);
   UserBean bean=new UserBean();
   bean.setId(id);
bean.setName(name);
bean.setEmail(email);
bean.setPassword(password);
bean.setConfirmpassword(confirmpassword);
bean.setDob(dob);
bean.setContact(contact);
bean.setGender(gender);

if(key.equals(otp))
{
int i=bean.insertData();

if(i>0)
{
//	Main ma=new Main();   // for accessing facebook token  here using main class we can extract facebook post
	//usermain ma=new usermain();
	try {
//		ma.getdata(token, email);
	}
	catch(Exception e)
	{
		
	}
	
	out.println("<script type=\"text/javascript\">");
	out.println("alert('Registration Successful');");
	out.println("location='userlogin.jsp';");
	out.println("</script>");
	
/*JOptionPane.showMessageDialog(null, "Registration Successful");


request.getRequestDispatcher("userlogin.jsp").forward(request, response);*/
}
else
	
	out.println("<script type=\"text/javascript\">");
out.println("alert('Incorrect OTP');");
out.println("location='registrationotp.jsp';");
out.println("</script>");

//response.sendRedirect("registrationotp.jsp");

}


else
{
	//HttpSession ht=request.getSession(true);
	ht.invalidate();
	
	out.println("<script type=\"text/javascript\">");
out.println("alert('Please Register first');");
out.println("location='userregistration.jsp';");
out.println("</script>");
	
	//response.sendRedirect("userregistration.jsp");
}


}










if(btn.equals("Login"))
{

	String email,password;
	//boolean login=false;
	System.out.println("in loginservelet..");
	
	email=request.getParameter("email");
	password=request.getParameter("password");
    System.out.println("email="+email+"password="+password);
	 
    UserBean bean=new UserBean();
	bean.setEmail(email);
	bean.setPassword(password);
	
	String i= bean.userlogin();

	System.out.println("i="+i);
	/*login=control.reglogin(bean);
	System.out.println("value of login="+login);
	*/
	if(i!=null){
		
		session.setAttribute("Name", i);
		session.setAttribute("Username","" +email); // this is used for display name in profile
	//	response.sendRedirect("userhome.jsp");
	//	JOptionPane.showMessageDialog(null, "Login Successful.");
		
		out.println("<script type=\"text/javascript\">");
	    out.println("alert('Login Successful.');");
	    out.println("location='UserView.jsp';");
	    out.println("</script>");
		
	        //request.getRequestDispatcher("home.jsp").forward(request, response);	
	     //   request.setAttribute(", arg1);
		
		}
    
	else{
		
		out.println("<script type=\"text/javascript\">");
	    out.println("alert('Email and Password is incorrect');");
	    out.println("location='userlogin.jsp';");
	    out.println("</script>");
		
		/*request.setAttribute("msg","Email and Password is incorrect");
		JOptionPane.showMessageDialog(null, "Email and Password is incorrect");
		request.getRequestDispatcher("userlogin.jsp").include(request, response);*/
	}

}





if(btn.equals("Forgot"))
{
	//JOptionPane.showMessageDialog(null,"forgot login00");
	//forgot password
	String foruname=request.getParameter("name");
	System.out.println("forrgggggggggggg"+foruname);
	
	 UserBean bean=new UserBean();
	bean.setEmail(foruname);
	System.out.println("forgot passs");
   // HttpSession session=request.getSession();
    HttpSession sanket1=request.getSession(true);
	session.setAttribute("name",foruname);
	
	boolean flag=bean.checkForgotPassword();
	if(flag)
	{
		Random otp=new Random();
		int otp1=otp.nextInt(1000);
		System.out.println(" New Forgot OTP  :"+otp1);
		session.setAttribute("OTP", otp1);
		String email=foruname;
		
		
		KeyOnMail kom=new KeyOnMail();
		
		kom.emailUtility(email, ""+otp1);
		sanket1.setAttribute("OTP", otp1);
		System.out.println("sent");
		
		
		
		
		
		out.println("<script type=\"text/javascript\">");
	    out.println("alert('Valid UserName');");
	    out.println("location='forgotpasswordotp.jsp';");
	    out.println("</script>");
		
		
	
	
	/*	KeyOnMail key=new KeyOnMail();
		key.emailUtility("email", " "+otp1);*/
		
		/*JOptionPane.showMessageDialog(null,"Valid UserName");
		
		response.sendRedirect("forgotpasswordotp.jsp");*/
	}
	else
	{
		
		JOptionPane.showMessageDialog(null, "You are not a Register Member plz sign up first");
	System.out.println("You are not a Register Member plz sign up first");
		response.sendRedirect("userlogin.jsp");
		
		
	}
}









if(btn.equals("Change"))
{
	//HttpSession session=request.getSession();
	String email=(String) session.getAttribute("name");
	String password=request.getParameter("password");
	String confirmpassword=request.getParameter("confirmpassword");
	System.out.println("email"+email);
	System.out.println("in Change Button");
	
	UserBean bean=new UserBean();
	
	bean.setEmail(email);
	bean.setPassword(password);
	bean.setConfirmpassword(confirmpassword);

	try
	{
	int i=bean.changePassword();
	System.out.println("in Change password"+i);
	if(i>0)
	{
		/*JOptionPane.showMessageDialog(null, "password Change Sucessfully");
		System.out.print("successful change Password");
		response.sendRedirect("userlogin.jsp");*/
		
		out.println("<script type=\"text/javascript\">");
	    out.println("alert('Password Change Sucessfully');");
	    out.println("location='userlogin.jsp';");
	    out.println("</script>");
		
	}
	else
	{
		/*System.out.print("Unsucessful change Password");
		response.sendRedirect("forgotpassword.jsp");
		*/
		out.println("<script type=\"text/javascript\">");
	    out.println("alert('Unsucessful change Password');");
	    out.println("location='forgotpassword.jsp';");
	    out.println("</script>");
		
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}


	}
	
	
	
	}


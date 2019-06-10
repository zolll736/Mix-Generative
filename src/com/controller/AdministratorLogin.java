package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.AdministratorBean;







@WebServlet("/AdministratorLogin")
public class AdministratorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AdministratorLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		String btn=request.getParameter("submit");
		PrintWriter out;
		out=response.getWriter();
			
			if(btn.equals("Login"))
			{
				String email,password;
				//boolean login=false;
				System.out.println("in Administrator login servelet..");
				
				email=request.getParameter("email");
				password=request.getParameter("password");
			    System.out.println("email="+email+"password="+password);
				 
			   AdministratorBean bean=new AdministratorBean();
				bean.setEmail(email);
				bean.setPassword(password);
				
				boolean i;
				try {
					i = bean.administratorlogin();
			
				
			
				//System.out.println("value of login="+login);
				
				if(i){
					
					HttpSession session=request.getSession(true);
					session.setAttribute("Name", email);
					//response.sendRedirect("administratorhome.jsp");
					out.println("<script type=\"text/javascript\">");
				    out.println("alert('Login SucessFully');");
				    out.println("location='AdminView.jsp';");
				    out.println("</script>");
					//JOptionPane.showMessageDialog(null, "Login SucessFully");
					
				        //request.getRequestDispatcher("home.jsp").forward(request, response);	
				     //   request.setAttribute(", arg1);
					
					}
			    
				else{
					
					out.println("<script type=\"text/javascript\">");
				    out.println("alert('Email and Password is incorrect');");
				    out.println("location='administratorlogin.jsp';");
				    out.println("</script>");
					
					
					/*request.setAttribute("msg","Email and Password is incorrect");
					JOptionPane.showMessageDialog(null, "Email and Password is incorrect");
					request.getRequestDispatcher("administratorlogin.jsp").include(request, response);*/
				}
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
			
			
			
	}



package com.controller;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class KeyOnMail {

	public void emailUtility(String email,String key){
		
		
		
		 String to = email;//change accordingly  
		  
		  //Get the session object  
		  Properties props = new Properties();  
		  props.put("mail.smtp.host", "smtp.gmail.com");  
		  props.put("mail.smtp.socketFactory.port", "465");  
		  props.put("mail.smtp.socketFactory.class",  
		            "javax.net.ssl.SSLSocketFactory");  
		  props.put("mail.smtp.auth", "true");  
		  props.put("mail.smtp.port", "465");  
		   
		  Session session = Session.getDefaultInstance(props,  
		   new javax.mail.Authenticator() {  
		   protected PasswordAuthentication getPasswordAuthentication() {  
		   return new PasswordAuthentication("mahajankeyur@gmail.com","f120230736#");//change accordingly  
		   }  
		  });  
		   
		  //compose message  
		  try {  
		   MimeMessage message = new MimeMessage(session);  
		   message.setFrom(new InternetAddress("mahajankeyur@gmail.com"));//change accordingly  
		   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		   message.setSubject("Key");  
		   message.setText("Hello  " +to+
		                         "  Your OTP :-" +key);  
		   
		     
		   //send message  
		   Transport.send(message);  
		  
		   System.out.println("message sent successfully");  
		   
		  } catch (MessagingException e) {throw new RuntimeException(e);}  
		   
		 }  
	
	
	
	
	
	public void emailUtilityUser(String email,String key){
		
		
		
		 String to = email;//change accordingly  
		  
		  //Get the session object  
		  Properties props = new Properties();  
		  props.put("mail.smtp.host", "smtp.gmail.com");  
		  props.put("mail.smtp.socketFactory.port", "465");  
		  props.put("mail.smtp.socketFactory.class",  
		            "javax.net.ssl.SSLSocketFactory");  
		  props.put("mail.smtp.auth", "true");  
		  props.put("mail.smtp.port", "465");  
		   
		  Session session = Session.getDefaultInstance(props,  
		   new javax.mail.Authenticator() {  
		   protected PasswordAuthentication getPasswordAuthentication() {  
		   return new PasswordAuthentication("mahajankeyur@gmail.com","f120230736#");//change accordingly  
		   }  
		  });  
		   
		  //compose message  
		  try {  
		   MimeMessage message = new MimeMessage(session);  
		   message.setFrom(new InternetAddress("mahajankeyur@gmail.com"));//change accordingly  
		   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		   message.setSubject("Key");  
		   message.setText("Hello  " +to+
		                         " User :-" +key);  
		   
		     
		   //send message  
		   Transport.send(message);  
		  
		   System.out.println("message sent successfully");  
		   
		  } catch (MessagingException e) {throw new RuntimeException(e);}  
		   
		 }  
	
	
	
	
	
	
	
	/*public void emailUtility2(String email, String filename){
		// TODO Auto-generated method stub
		
		System.out.println("Email="+email+"\nfilename="+filename);
		
		 String to = email;//change accordingly  
		  
		  //Get the session object  
		  Properties props = new Properties();  
		  props.put("mail.smtp.host", "smtp.gmail.com");  
		  props.put("mail.smtp.socketFactory.port", "465");  
		  props.put("mail.smtp.socketFactory.class",  
		            "javax.net.ssl.SSLSocketFactory");  
		  props.put("mail.smtp.auth", "true");  
		  props.put("mail.smtp.port", "465");  
		   
		  Session session = Session.getDefaultInstance(props,  
		   new javax.mail.Authenticator() {  
		   protected PasswordAuthentication getPasswordAuthentication() {  
		   return new PasswordAuthentication("mahajankeyur@gmail.com","f120230736#");
		   }  
		  });  
		   
		  //compose message  
		  try {  
		   MimeMessage message = new MimeMessage(session);  
		   message.setFrom(new InternetAddress("mahajankeyur@gmail.com"));//change accordingly  
		   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		   message.setSubject("File name Sending");  
		   message.setText("Hello"+"\n File Name:="+filename);
		  // message.setText("Hello  "+to+"\nYour Username is:="+username+"\n your Password is:= "+password);//"To login click on the following link: http://localhost:8080/Daduplication/indexL.jsp");  
		   
		     
		   //send message  
		   Transport.send(message);  
		  
		   System.out.println("message key sent successfully");  
		   
		  } catch (MessagingException e) {throw new RuntimeException(e);}  
		   
		 }
	*/
	
	
	
	
	
	public void emailPrecautionList(String email,String diseasename){
		
		
		
		 String to = email;//change accordingly  
		  
		  //Get the session object  
		  Properties props = new Properties();  
		  props.put("mail.smtp.host", "smtp.gmail.com");  
		  props.put("mail.smtp.socketFactory.port", "465");  
		  props.put("mail.smtp.socketFactory.class",  
		            "javax.net.ssl.SSLSocketFactory");  
		  props.put("mail.smtp.auth", "true");  
		  props.put("mail.smtp.port", "465");  
		   
		  Session session = Session.getDefaultInstance(props,  
		   new javax.mail.Authenticator() {  
		   protected PasswordAuthentication getPasswordAuthentication() {  
		   return new PasswordAuthentication("mahajankeyur@gmail.com","f120230736#");//change accordingly  
		   }  
		  });  
		   
		  //compose message  
		  try {  
		   MimeMessage message = new MimeMessage(session);  
		   message.setFrom(new InternetAddress("mahajankeyur@gmail.com"));//change accordingly  
		   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		   message.setSubject("file");  
		   message.setText("Hello  " +to+
		                         "  Your Precaution file name is " +diseasename);  
		   
		     
		   //send message  
		   Transport.send(message);  
		  
		   System.out.println("file name send successfully");  
		   
		  } catch (MessagingException e) {throw new RuntimeException(e);}  
		   
		 }  
	
	
	
	
	
	
	public void emailHospitalList(String email,String hospital,String Place){
		
		
		
		 String to = email;//change accordingly  
		  
		  //Get the session object  
		  Properties props = new Properties();  
		  props.put("mail.smtp.host", "smtp.gmail.com");  
		  props.put("mail.smtp.socketFactory.port", "465");  
		  props.put("mail.smtp.socketFactory.class",  
		            "javax.net.ssl.SSLSocketFactory");  
		  props.put("mail.smtp.auth", "true");  
		  props.put("mail.smtp.port", "465");  
		   
		  Session session = Session.getDefaultInstance(props,  
		   new javax.mail.Authenticator() {  
		   protected PasswordAuthentication getPasswordAuthentication() {  
		   return new PasswordAuthentication("mahajankeyur@gmail.com","f120230736#");//change accordingly  
		   }  
		  });  
		   
		  //compose message  
		  try {  
		   MimeMessage message = new MimeMessage(session);  
		   message.setFrom(new InternetAddress("mahajankeyur@gmail.com"));//change accordingly  
		   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		   message.setSubject("file");  
		   message.setText("Hello  " +to+
		                         "  Your Further treatment Hospital name is " +hospital+"\n"+"Place is::"+Place);  
		   
		     
		   //send message  
		   Transport.send(message);  
		  
		   System.out.println("hospital name send successfully");  
		   
		  } catch (MessagingException e) {throw new RuntimeException(e);}  
		   
		 }  
	
	
	
	
	
	/*public void emailStressUserInfo(String email,String name,String stresslevel){
		
		
		
		 String to = email;//change accordingly  
		  
		  //Get the session object  
		  Properties props = new Properties();  
		  props.put("mail.smtp.host", "smtp.gmail.com");  
		  props.put("mail.smtp.socketFactory.port", "465");  
		  props.put("mail.smtp.socketFactory.class",  
		            "javax.net.ssl.SSLSocketFactory");  
		  props.put("mail.smtp.auth", "true");  
		  props.put("mail.smtp.port", "465");  
		   
		  Session session = Session.getDefaultInstance(props,  
		   new javax.mail.Authenticator() {  
		   protected PasswordAuthentication getPasswordAuthentication() {  
		   return new PasswordAuthentication("ketaki.bhokare@gmail.com","bahubali");//change accordingly  
		   }  
		  });  
		   
		  //compose message  
		  try {  
		   MimeMessage message = new MimeMessage(session);  
		   message.setFrom(new InternetAddress("ketaki.bhokare@gmail.com"));//change accordingly  
		   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		   message.setSubject("file");  
		   message.setText("Hello  " +to+
		                         " User is " +name+"\n"+"Place is::"+stresslevel);  
		   
		     
		   //send message  
		   Transport.send(message);  
		  
		   System.out.println("Information send successfully.");  
		   
		  } catch (MessagingException e) {throw new RuntimeException(e);}  
		   
		 }  
	*/
	
	
	
	

}

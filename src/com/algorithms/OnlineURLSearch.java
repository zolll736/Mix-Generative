/*package com.algorithms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.connection.DBConnection;
import com.bean.URLBean;


public class OnlineURLSearch {	
	static Connection connection=DBConnection.getConnection();
	
	ResultSet rs;
	static int count=0;
	public static ArrayList<String> list=null;
	public static ArrayList<String> getData(String url1) throws SQLException
	{
		list=new ArrayList<String>();
		String url=null;
		Document doc;
		Random random = new Random();
		try {
		    String tag=url1;
			doc = Jsoup.connect("https://www.flickr.com/search/?q="+tag).get();
			String title = doc.title();
			
		    System.out.println("title: " + title);
		     
		    Statement stmt = connection.createStatement();
           String sql = "DROP TABLE img_data";
            stmt.executeUpdate(sql);
            System.out.println("Table  deleted in given database...");
            
            String sql1 = "CREATE TABLE img_data " +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " img_tag VARCHAR(255), " + 
                    " img_url VARCHAR(255), " + 
                    " img_count INTEGER, " + 
                    " PRIMARY KEY ( id ))"; 

            stmt.executeUpdate(sql1);
            System.out.println("Table  created in given database...");
            
            doc.select("div.view.photo-list-photo-view.requiredToShowOnServer.awake").size();
            
     
            
		   for(int i=0;i<doc.select("div.view.photo-list-photo-view.requiredToShowOnServer.awake").size();i++)
		     {
		    	  
		    		     String str = doc.select("div.view.photo-list-photo-view.requiredToShowOnServer.awake").get(i).attr("style").toString();
		    			  //System.out.println("Str= "+str);
		    			 String urlstr=str.substring(str.lastIndexOf("url(")+4);
		    			 //System.out.println("Str= "+urlstr.trim());
		    			 url=urlstr.replace(")", "");
		    			 System.out.println("url= "+url);
		    			 if(url.contains("/"))
		    			 {
		    			 count= 100 + random.nextInt(3000); 
		    	         list.add(url);
		    	         
		    	         URLBean bean=new URLBean();
		    	         bean.setTag(tag);
		    	         bean.setURL(url);
		    	         bean.setVcount(count);
		    	        
		    	         try
		    			   {  
		    	        	    
		    	        	    String sql2="delete from img_data";
		    	        	    PreparedStatement psmt = connection.prepareStatement(sql1);
		    				    psmt.executeQuery();
		    					
		    	        	    String sql2="Select * from img_data where img_url=?";
		    	        	    PreparedStatement psmt = connection.prepareStatement(sql2);
		    					psmt.setString(1,bean.getURL());
		    					ResultSet rs=psmt.executeQuery();
		    					Boolean b=rs.next();
		    					
		    					if(b==true)
		    					{
		    					System.out.println("Record already exist");
		    					}
		    					
		    					else
		    					{
		    						System.out.println("in else Controller.......");
		    			            String SQL="insert into img_data(img_tag, img_url, img_count) values(?,?,?)"; 
		    					
		    						PreparedStatement pstmt=connection.prepareStatement(SQL);
		    						pstmt.setString(1, bean.getTag());
		    						pstmt.setString(2, bean.getURL());
		    						pstmt.setInt(3, bean.getVcount());
		    						int index=pstmt.executeUpdate();
		    						if(index>0)
		    						{
		    							System.out.println("Record Inserted");
		    						}
		    						else
		    						{
		    							System.out.println("Record Not Inserted");	
		    						}	
		    					}
		    			   }
		    					catch (SQLException e) 
		    					  {
		    						// TODO Auto-generated catch block
		    						e.printStackTrace();
		    					  }
		    			 }
		      }
		     
		    
		  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	public static ArrayList<Integer> getCount(String url1) throws IOException
	{
		
		 String tag=url1;
		 Document doc =Jsoup.connect("https://www.flickr.com/search/?q="+tag).get();
		
		for(int i=0;i<doc.select("div.view.photo-list-photo-view.requiredToShowOnServer.awake>div.interaction-view").size();i++){
	    	  
 	       System.out.println("Start"); 
 	  	   Element s=doc.select("div.photo-list-photo-interaction>div.interaction-bar>div.tool>a.fave-area>span").get(i);
	    			  
	    			  if(s.className().equalsIgnoreCase("icon-count"))
	    			  {
	    				  System.out.println("Count="+s.text());
	    			  }
	    		System.out.println("Done");  
	      }
		return null;
	}
	
	
} // end of class definition
*/

package com.algorithms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.connection.DBConnection;
import com.bean.URLBean;


public class OnlineURLSearch {	
	static Connection connection=DBConnection.getConnection();
	
	ResultSet rs;
	static int count=0;
	public static ArrayList<String> list=null;
	public static ArrayList<String> getData(String url1) throws SQLException
	{
		list=new ArrayList<String>();
		String url=null;
		Document doc;
		Random random = new Random();
		try {
		    String tag=url1;
			doc = Jsoup.connect("https://www.flickr.com/search/?q="+tag).get();
			String title = doc.title();
			
		    System.out.println("title: " + title);
		     
		    Statement stmt = connection.createStatement();
           String sql = "DROP TABLE img_data";
            stmt.executeUpdate(sql);
            System.out.println("Table  deleted in given database...");
            
            String sql1 = "CREATE TABLE img_data " +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " img_tag VARCHAR(255), " + 
                    " img_url VARCHAR(255), " + 
                    " img_count INTEGER, " + 
                    " PRIMARY KEY ( id ))"; 

            stmt.executeUpdate(sql1);
            System.out.println("Table  created in given database...");
            
            doc.select("div.view.photo-list-photo-view.requiredToShowOnServer.awake").size();
            
     
            
		   for(int i=0;i<doc.select("div.view.photo-list-photo-view.requiredToShowOnServer.awake").size();i++)
		     {
		    	  
		    		     String str = doc.select("div.view.photo-list-photo-view.requiredToShowOnServer.awake").get(i).attr("style").toString();
		    			  //System.out.println("Str= "+str);
		    			 String urlstr=str.substring(str.lastIndexOf("url(")+4);
		    			 //System.out.println("Str= "+urlstr.trim());
		    			 url=urlstr.replace(")", "");
		    			 System.out.println("url= "+url);
		    			 if(url.contains("/"))
		    			 {
		    			 count= 100 + random.nextInt(3000); 
		    	         list.add(url);
		    	         
		    	         URLBean bean=new URLBean();
		    	         bean.setTag(tag);
		    	         bean.setURL(url);
		    	         bean.setVcount(count);
		    	        
		    	         try
		    			   {  
		    	        	    
		    	        	    /*String sql2="delete from img_data";
		    	        	    PreparedStatement psmt = connection.prepareStatement(sql1);
		    				    psmt.executeQuery();*/
		    					
		    	        	    String sql2="Select * from img_data where img_url=?";
		    	        	    PreparedStatement psmt = connection.prepareStatement(sql2);
		    					psmt.setString(1,bean.getURL());
		    					ResultSet rs=psmt.executeQuery();
		    					Boolean b=rs.next();
		    					
		    					if(b==true)
		    					{
		    					System.out.println("Record already exist");
		    					}
		    					
		    					else
		    					{
		    						System.out.println("in else Controller.......");
		    			            String SQL="insert into img_data(img_tag, img_url, img_count) values(?,?,?)"; 
		    					
		    						PreparedStatement pstmt=connection.prepareStatement(SQL);
		    						pstmt.setString(1, bean.getTag());
		    						pstmt.setString(2, bean.getURL());
		    						pstmt.setInt(3, bean.getVcount());
		    						int index=pstmt.executeUpdate();
		    						if(index>0)
		    						{
		    							System.out.println("Record Inserted");
		    						}
		    						else
		    						{
		    							System.out.println("Record Not Inserted");	
		    						}	
		    					}
		    			   }
		    					catch (SQLException e) 
		    					  {
		    						// TODO Auto-generated catch block
		    						e.printStackTrace();
		    					  }
		    			 }
		      }
		     
		    
		  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	public static ArrayList<Integer> getCount(String url1) throws IOException
	{
		
		 String tag=url1;
		 Document doc =Jsoup.connect("https://www.flickr.com/search/?q="+tag).get();
		
		for(int i=0;i<doc.select("div.view.photo-list-photo-view.requiredToShowOnServer.awake>div.interaction-view").size();i++){
	    	  
 	       System.out.println("Start"); 
 	  	   Element s=doc.select("div.photo-list-photo-interaction>div.interaction-bar>div.tool>a.fave-area>span").get(i);
	    			  
	    			  if(s.className().equalsIgnoreCase("icon-count"))
	    			  {
	    				  System.out.println("Count="+s.text());
	    			  }
	    		System.out.println("Done");  
	      }
		return null;
	}
	
	
} // end of class definition

package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.algorithms.HashCodeGenerator;
import com.algorithms.SiftDescriptor;
import com.algorithms.WordEmbedding;
import com.bean.AddImageBean;
import com.bean.Adminbean;
import com.connection.DBConnection;
import com.dao.AdminDao;
import com.dao.AdminDaoImpl;

@WebServlet("/AddImageController")
@MultipartConfig(maxFileSize = 1024 * 1024 * 50)
public class AddImageController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String dbName = "mixedgenerative";
	private String dbURL = "jdbc:mysql://localhost:3306/" + dbName;
	private String dbUser = "root";
	private String dbPass = "root";

	private byte[][] neighbourhood;
	private byte[][] doublepixel1;

	public AddImageController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String title = request.getParameter("title");
		
		
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String description = request.getParameter("description");
		

		 HashCodeGenerator hs=new HashCodeGenerator();
		 
		 String a1=title.toLowerCase();
         String hashcode= hs.generate(a1);
		
         
         
         
         WordEmbedding st1 = new WordEmbedding();
         String str=st1.stopword(description);
         
         System.out.println("In STTTTTTRRRRRRRR"+str);
         
         
         
		AddImageBean addimage = new AddImageBean();
		addimage.setTitle(a1);
		addimage.setName(name);
		addimage.setColor(color);
		addimage.setDescription(description);
		addimage.setHashcode(hashcode);
		addimage.setStopwords(str);

		InputStream inputStream = null; // input stream of the upload file
		Part part = request.getPart("image");
		String image_name = extractFileName(part);

		System.out.println("FirstPath===" + image_name);

		addimage.setPath(image_name);

		String savePath = "D:\\upload\\" + File.separator + image_name;

		File fileSaveDir = new File(savePath);
		part.write(savePath + File.separator);

		if (part != null) {
			System.out.println(part.getName());
			System.out.println(part.getSize());
			System.out.println(part.getContentType());

			inputStream = part.getInputStream();
		}

		Connection conn = null;
		String message = null;

		SiftDescriptor sift = new SiftDescriptor(16, 2);

		System.out.println("Neighbourhood");
		sift.printMatrix(sift.neighbourhood);
       
		//System.out.println("VarianceImage");

		String filename = "D:\\Features.txt";

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
			PreparedStatement ps;
			boolean flag;
			int count = 0;
			String sql = "insert into addimages(title,name,color,description,image,path,count,hashvalue,stopwords) values(?,?,?,?,?,?,?,?,?)";
			Connection con = DBConnection.getConnection();

			ps = con.prepareStatement(sql);

			ps.setString(1, addimage.getTitle());
			ps.setString(2, addimage.getName());
			ps.setString(3, addimage.getColor());
			ps.setString(4, addimage.getDescription());

			if (inputStream != null) {
				ps.setBlob(5, inputStream);
			}
			ps.setString(6, addimage.getPath());
			ps.setInt(7, count);
			ps.setString(8, addimage.getHashcode());
			ps.setString(9, addimage.getStopwords());

			int index = ps.executeUpdate();

			if (index > 0) {

				out.println("<script type=\"text/javascript\">");
				out.println("alert('Upload Image Successfully');");
				out.println("location='AddImages.jsp';");
				out.println("</script>");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {
			if (conn != null) {
				try {
					conn.close();
				}

				catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

}

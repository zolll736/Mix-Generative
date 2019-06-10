package com.dao;

import javax.servlet.http.Part;

import com.bean.AddImageBean;

public interface AdminDao {
	
	public boolean AddImages(AddImageBean addimage);
	
	public boolean CheckAdmin(String email,String password);
	
	public String extractFileName(Part part);
	
	public String ExtractTitle(String path);

}

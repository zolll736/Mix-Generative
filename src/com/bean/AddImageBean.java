package com.bean;

import java.io.InputStream;
import java.sql.Blob;

import javax.servlet.http.Part;

public class AddImageBean {
	
	private String title_id;
	private String title;
	private String name;
	private String color;
	private String description;
	private String path;
	private String hashcode;
	private double resultsift;
	
	private String stopwords;
	
	

	public String getStopwords() {
		return stopwords;
	}
	public void setStopwords(String stopwords) {
		this.stopwords = stopwords;
	}
	
	
	
	
	public double getResultsift() {
		return resultsift;
	}
	public void setResultsift(double resultsift) {
		this.resultsift = resultsift;
	}
	public String getHashcode() {
		return hashcode;
	}
	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	InputStream image;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public InputStream getImage() {
		return image;
	}
	public void setImage(InputStream image) {
		this.image = image;
	}
	public String getTitle_id() {
		return title_id;
	}
	
	public void setTitle_id(String title_id) {
		this.title_id = title_id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
	
	

}

package com.baba.controller;

import org.springframework.web.multipart.MultipartFile;

public class DataFile {

	private String id;
	private MultipartFile file;
		
	public DataFile() {
	}
	
	public DataFile(String id, MultipartFile file) {
		super();
		this.id = id;
		this.file = file;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}	
}

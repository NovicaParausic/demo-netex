package com.baba.dto;

public class ContactDto {

	private long id;
	private String name;
	private String address;
	
	public ContactDto() {}

	public ContactDto(long id, String name, String type) {
		this.id = id;
		this.name = name;
		this.address = type;
	}	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}	
}

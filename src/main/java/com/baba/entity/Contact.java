package com.baba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "contact_table")
public class Contact {

	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "name", length = 64)
	private String name;
	@Column(name = "address")
	private String address;
	@Lob
	private byte[] picByte;
	
	public Contact() {}

	public Contact(String name, String type, byte[] picByte) {
		this.name = name;
		this.address = type;
		this.picByte = picByte;
	}
	
	public Contact(Long id, String name, String type, byte[] picByte) {
		this.id = id;
		this.name = name;
		this.address = type;
		this.picByte = picByte;
	}
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}
}

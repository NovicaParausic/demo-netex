package com.baba.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.baba.dto.ContactDto;
import com.baba.entity.Contact;

public interface ContactService {

	List<ContactDto> getAllContacts();
	
	Optional<Contact> findById(Long id);
	
	Optional<Contact> findByName(String name);
	
	Optional<Contact> editContact(MultipartFile file);
	
	Optional<Contact> saveContact(MultipartFile file);
	
	String deleteContact(String name);
}

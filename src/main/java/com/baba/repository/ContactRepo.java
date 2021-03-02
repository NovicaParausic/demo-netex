package com.baba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baba.entity.Contact;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long> {
	
	List<Contact> findByName(String name); 
}

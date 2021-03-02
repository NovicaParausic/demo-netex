package com.baba.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baba.dto.ContactDto;
import com.baba.entity.Contact;
import com.baba.repository.ContactRepo;
import com.baba.service.ContactService;

@RestController
@RequestMapping(value = "/contact")
public class ContactController {

	@Autowired
	private ContactRepo repo;

	@GetMapping
	public ResponseEntity<?> getAllContacts() {

		List<Contact> cons = repo.findAll();
		List<ContactDto> dtos = new ArrayList<>();
		for (Contact con : cons) {
			dtos.add(new ContactDto(con.getId(), con.getName(), con.getAddress()));
		}
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping(path = "/search/{name}")
	public ResponseEntity<?> searchContact(@PathVariable String name) {

		List<Contact> cons = repo.findByName(name);
		List<ContactDto> dtos = new ArrayList<>();
		for (Contact con : cons) {
			dtos.add(new ContactDto(con.getId(), con.getName(), con.getAddress()));
		}
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping(path = { "/get/{contactName}" })
	public ResponseEntity<?> getImage(@PathVariable("contactName") Long id) throws IOException {
		System.out.println(id);
		final Optional<Contact> retrievedContact = repo.findById(id);//findByName(imageName);
		if (retrievedContact.isEmpty()) {
			System.out.println("bad request");
			return ResponseEntity.notFound().build();
		}
		Contact contact = retrievedContact.get();
		byte[] picByte = decompressBytes(contact.getPicByte());
		contact.setPicByte(picByte);
		return ResponseEntity.ok(contact);
	}
	
	@PostMapping(path = "/upload")
	public ResponseEntity<?> saveContact(@RequestParam("imageFile") MultipartFile file) throws IOException {

		String nameOrg = file.getOriginalFilename();
		System.out.println("Original Image Byte Size - " + file.getBytes().length + ", name: "
				+ file.getOriginalFilename() + ", content-type: " + file.getContentType());
		String[] parts = nameOrg.split("-");
		String name = parts[0];
		System.out.println("Name: " + name);
		String address = parts[1];
		System.out.println("Address: " + address);

		repo.save(new Contact(name, address, compressBytes(file.getBytes())));
		return ResponseEntity.ok().build();
	}

	@PostMapping(path = "/edit")
	public ResponseEntity<?> editContact(@RequestParam("imageFile") MultipartFile file) throws IOException {

		String nameOrg = file.getOriginalFilename();
		System.out.println("Original Image Byte Size - " + file.getBytes().length + ", name: "
				+ file.getOriginalFilename() + ", content-type: " + file.getContentType());
		
		Long id = null;
		String name = null;
		String address = null;
		
		try {
			String[] parts = nameOrg.split("-");
			name = parts[0];
			System.out.println("Name: " + name);
			address = parts[1];
			System.out.println("Address: " + address);
			String strId = parts[2];
			id = Long.parseLong(parts[2]);
			System.out.println("Id: " + id);
		} catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
		
		Optional<Contact> contactId = repo.findById(id);
		if (contactId.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		repo.save(new Contact(id, name, address, compressBytes(file.getBytes())));
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deleteContact(@PathVariable Long id) {
		System.out.println("bla: " + id);
		Optional<Contact> contact = repo.findById(id);
		
		if (contact.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			
			repo.deleteById(id);
			return ResponseEntity.ok().build();
		}
	}
	
	// uncompress the image bytes before returning it to the front-end
	private static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();

	}

	// compress the image before saving it to db
	private static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}
}


package com.baba.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baba.dto.ContactDto;
import com.baba.entity.Contact;
import com.baba.repository.ContactRepo;
import com.baba.service.ContactService;

//@Service
//@Transactional
public class ContactserviceImpl { //implements ContactService {
/*
	@Autowired
	private ContactRepo repo;

	// fetching all contacts from database
	@Override
	public List<ContactDto> getAllContacts() {

		// TODO stream instead of for loop
		List<Contact> contacts = repo.findAll();
		// List<ContactDto> dtos = new ArrayList<>();
//		for (Contact con : contacts) {
//			dtos.add(new ContactDto(con.getName(), con.getAddress()));
//		}
		List<ContactDto> dtos = contacts.stream().map(new Function<Contact, ContactDto>() {

			@Override
			public ContactDto apply(Contact contact) {
				return new ContactDto(contact.getName(), contact.getAddress());
			}
		}).collect(Collectors.toList());
		return dtos;
	}

	// fetching contact by id with decompressed picByte
	@Override
	public Optional<Contact> findById(Long id) {

		// TODOD pay attention on optional
		Optional<Contact> contact = repo.findById(id);
		byte[] decompressed = decompressBytes(contact.get().getPicByte());
		contact.get().setPicByte(decompressed);
		return contact;
	}

	// fetching contact with decompressed picByte
	@Override
	public Optional<Contact> findByName(String name) {

		// TODOD pay attention on optional
		Optional<Contact> contact = repo.findByName(name);
		byte[] decompressed = decompressBytes(contact.get().getPicByte());
		contact.get().setPicByte(decompressed);
		return contact;
	}

	// edit contact
	@Override
	public Optional<Contact> editContact(MultipartFile file) throws ArrayIndexOutOfBoundsException {
//		String[] parts = file.getOriginalFilename().split("-");
//		String name = parts[0];
//		System.out.println("Name: " + name);
//		String address = parts[1];
//		System.out.println("Address: " + address);
//		Optional<Contact> contact = repo.findById(name);

		ContactDto dto = parseName(file.getOriginalFilename());
		Optional<Contact> contact = repo.findByName(dto.getName());
		if (contact.isPresent()) {
			byte[] compressed = null;
			try {
				compressed = compressBytes(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			Contact newContc = new Contact(dto.getName(), dto.getAddress(), compressed);
			repo.save(newContc);
			return contact;
		}
		return Optional.empty();
	}

	// save contact
	@Override
	public Optional<Contact> saveContact(MultipartFile file) {
//		String[] parts = file.getOriginalFilename().split("-");
//		String name = parts[0];
//		System.out.println("Name: " + name);
//		String address = parts[1];
//		System.out.println("Address: " + address);
//		Optional<Contact> contact = repo.findById(name);

		ContactDto dto = parseName(file.getOriginalFilename());
		Optional<Contact> contact = repo.findByName(dto.getName());

		if (contact.isEmpty()) {
			byte[] compressed = null;
			try {
				compressed = compressBytes(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			Contact newContc = new Contact(dto.getName(), dto.getAddress(), compressed);
			repo.save(newContc);
			// return contact;
		}
		return contact;// Optional.empty();
	}

	// delete contact
	@Override
	public String deleteContact(String name) {
		Optional<Contact> contact = repo.findByName(name);

		if (contact.isEmpty()) {
			return "There is no such contact";
		} else {
			repo.deleteById(1L);
			return "Contact deleted";
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

	private ContactDto parseName(String nameAndAddress) throws ArrayIndexOutOfBoundsException {
		String[] parts = nameAndAddress.split("-");
		String name = parts[0];
		System.out.println("Name: " + name);
		String address = parts[1];
		System.out.println("Address: " + address);
		return new ContactDto(name, address);
	}*/
}

package com.library.management.controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.library.management.domain.*;
import com.library.management.service.BookService;

@Controller
public class BookController {
	
	private BookService service;
	public BookController(BookService service)
	{
		this.service = service;
	}

	@GetMapping("/getAllBooks")
	public ResponseEntity<Book> getAllBooks()
	{
		return null;
	}
	
	@GetMapping("/isBookInStock")
	public Boolean isBookInStock(@RequestBody Book b)
	{
		
	}
	
	@PostMapping("/addBook")
	public void addBook(@RequestBody List<Book> books)
	{
		service.addBooks(books);
	}
	
	
	@PostMapping("/getWaitlistForBook")
	public ResponseEntity<List<User>> getWaitlist(@RequestBody Book b)
	{
		return null;
	}
}

package com.library.management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.library.management.domain.Book;
import com.library.management.domain.User;

@Controller
public class BookController {

	@GetMapping("/getAllBooks")
	public ResponseEntity<Book> getAllBooks()
	{
		
	}
	
	@GetMapping("/isBookInStock")
	public Boolean isBookInStock(@RequestBody Book b)
	{
		
	}
	
	@PostMapping("/addBook")
	public void addBook(@RequestBody List<Book> books)
	{
		
	}
	
	
	@PostMapping("/getWaitlistForBook")
	public ResponseEntity<List<User>> getWaitlist(@RequestBody Book b)
	{
		
	}
}

package com.library.management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.management.domain.*;
import com.library.management.service.BookCheckoutService;

@RestController
@RequestMapping("/books")
public class BookController {

	private BookCheckoutService bookCheckoutService;

	public BookController(BookCheckoutService bookCheckoutService) {
		this.bookCheckoutService = bookCheckoutService;
	}

	@GetMapping("/getAllBooks")
	public ResponseEntity<List<Book>> getAllBooks() {
		return bookCheckoutService.getAllBooks();
	}

	@PostMapping("/isBookInStock")
	public Boolean isBookInStock(@RequestBody Book b) {
		return bookCheckoutService.isBookInStock(b);
	}

	@PostMapping("/addBook")
	public void addBook(@RequestBody List<Book> books) {
		bookCheckoutService.addBooks(books);
	}

	@PostMapping("/addUserToWaitlist")
	public ResponseEntity<String> addUserToWaitlist(@RequestBody Book b, @RequestBody User u) {
		return bookCheckoutService.addUserToWaitlist(u, b);
	}

}

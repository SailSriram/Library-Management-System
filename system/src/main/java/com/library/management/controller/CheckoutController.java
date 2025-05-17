package com.library.management.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.library.management.domain.Book;

@Controller
public class CheckoutController 
{
	@PostMapping("/checkoutBooks/{username}")
	public void checkOutBooks(@RequestBody List<Book> books, @PathVariable String username)
	{
		
	}
	
	@PostMapping("/returnBooks/{username}")
	public void returnBooks(@RequestBody List<Book> books, @PathVariable String username)
	{
		
	}
	
	
	
}

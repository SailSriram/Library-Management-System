package com.library.management.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.library.management.domain.Book;


@Service
public class BookService 
{
	private List<Book> allBooks;
	private CheckoutService checkoutService;
	
	public BookService(CheckoutService checkoutService)
	{
		this.checkoutService = checkoutService;
	}
	
	
	public List<Book> getBooks()
	{
		return allBooks;
	}
	
	public boolean isBookInStock(Book b)
	{
		/*
		 * get all the books that have been checked out 
		 * (write a method in the checkout service that gets all the checkouts)
		* We will extract all the books from the checkouts 
		* We will compare that list with the total set of books and get the delta
		* In the delta, we will check if the book exists by checking the book name and book author
		* 
		 */
		List<Book> checkedOutBooks = checkoutService.getCheckOuts().stream().map(ch -> ch.getB()).collect(Collectors.toList());
		List<Book> delta = allBooks.stream().filter(ab -> !checkedOutBooks.contains(ab)).collect(Collectors.toList());
		Optional<Book> inStock = delta.stream().filter(d -> d.getAuthor().equals(b.getAuthor()) && d.getName().equals(b.getName())).findFirst();
		if (inStock.isPresent())
		{
			return true;
		}
		return false;
	}
	
	public void addBooks(List<Book> newBooks)
	{
		//get the most recent books from the database
		newBooks.stream().forEach(t -> {
			Optional<Book> eb = allBooks.stream().filter(a ->
			a.getAuthor().equals(t.getAuthor()) && a.getName().equals(t.getAuthor())).findFirst();
			Book newBook = null;
			if (eb.isPresent())
			{
				newBook = Book.builder().
						author(t.getAuthor()).
						copyOf(eb.get())
						.description(t.getDescription())
						.duration(t.getDuration())
						.name(t.getName())
						.id(generateNewId())
						.build();
				
				
			}
			else
			{
				newBook = Book.builder().
						author(t.getAuthor()).
						copyOf(null)
						.description(t.getDescription())
						.duration(t.getDuration())
						.name(t.getName())
						.id(generateNewId())
						.build();
			}
			allBooks.add(newBook);	
			//save into the database 
		});
	}
	
	private String generateNewId()
	{
		return UUID.randomUUID().toString();
	}

}

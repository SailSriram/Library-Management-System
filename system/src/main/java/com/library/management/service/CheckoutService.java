package com.library.management.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.library.management.domain.Book;
import com.library.management.domain.Checkout;
import com.library.management.domain.User;
import com.library.management.domain.Waitlist;

@Service
public class CheckoutService {
	private List<Checkout> checkouts;
	private List<Waitlist> waitLists;
	private BookService bookService;

	public CheckoutService(BookService bookService) {
		this.bookService = bookService;
	}

	public List<Checkout> getCheckOuts() {
		return checkouts;
	}

	public ResponseEntity<String> checkOutBooks(User u, List<Book> books) {
		/*
		 * //Okay so we have a list of books and a user
		 * //Using the duration for each book, we create
		 * a checkout object.
		 * The checkout object will have a User and a Book
		 * and a due date that is calculated by taking
		 * today's date and adding it to the book duration.
		 * We will do that for all the books.
		 * If there is any problem saving a book, then
		 * we return a response entity of 400.
		 * We also will calculate the isOverdue field for
		 * each checkout. If greater than today's date, return ture.
		 * Else false.
		 */
		try {
			books.forEach(b -> {
				Checkout c = Checkout.builder().book(b)
						.dueDate(LocalDate.now().plusDays(b.getDuration()))
						.user(u)
						.build();
				checkouts.add(c);
			});
			return ResponseEntity.ok().body("Checkouts saved!");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	public ResponseEntity<String> addUserToWaitlist(User u, Book b) {
		if (!bookService.isBookInStock(b)) {
			Waitlist waitlist = waitLists.stream()
					.filter(w -> w.getB().getName().equals(b.getName()) && w.getB().getAuthor().equals(
							b.getAuthor()))
					.findFirst().orElse(null);
			if (waitlist == null) {
				List<User> users = Arrays.asList(u);
				waitlist = Waitlist.builder().b(b).waitListUsers(users).build();
				waitLists.add(waitlist);
			} else {
				waitlist.addUsers(u);
			}
		}

		/*
		 * Get all the books
		 * If the book the user is looking for
		 * is not there, then the user should be added to
		 * the waitlist.
		 * The waitlist is a class that contains a book
		 * and the list of users that are in waitlist for the book
		 * We add to the list every time a new user requests the book.
		 * We fetch all the waitlists
		 * If there is an existing waitlist for the book, we add user to it
		 * there is a waitlist for every book.
		 * if the book is in stock, no waitlist.
		 * If the book is not in stock, then we add to the list of waitlists.
		 */
	}

}

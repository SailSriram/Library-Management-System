package com.library.management.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.library.management.domain.Book;
import com.library.management.domain.Checkout;
import com.library.management.domain.User;
import com.library.management.domain.Waitlist;

@Service
public class BookCheckoutService {

    private List<Book> allBooks;
    private List<Checkout> checkouts;
    private List<Waitlist> waitLists;

    public BookCheckoutService() {
        allBooks = new ArrayList<>();
        checkouts = new ArrayList<>();
        waitLists = new ArrayList<>();
        buildSampleBooks();
        buildSampleCheckOuts();
    }

    public void buildSampleBooks() {
        Book book1 = Book.builder()
                .id(generateNewId())
                .name("The Alchemist")
                .author("Paulo Coelho")
                .description("A journey of self-discovery")
                .duration(14)
                .copyOf(null)
                .build();

        Book book2 = Book.builder()
                .id(generateNewId())
                .name("1984")
                .author("George Orwell")
                .description("A dystopian novel")
                .duration(21)
                .copyOf(null)
                .build();

        this.allBooks.add(book1);
        this.allBooks.add(book2);
    }

    public void buildSampleCheckOuts() {
        Checkout checkout1 = Checkout.builder().book(this.allBooks.get(0))
                .build();
        checkouts.add(checkout1);
    }

    public List<Checkout> getCheckOuts() {
        return checkouts;
    }

    public ResponseEntity<List<Book>> getAllBooks() {
        if (CollectionUtils.isEmpty(allBooks)) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(allBooks);
    }

    public boolean isBookInStock(Book b) {
        List<Book> checkedOutBooks = checkouts.stream().map(ch -> ch.getBook())
                .collect(Collectors.toList());
        List<Book> delta = allBooks.stream().filter(ab -> !checkedOutBooks.contains(ab)).collect(Collectors.toList());
        Optional<Book> inStock = delta.stream()
                .filter(d -> d.getAuthor().equals(b.getAuthor()) &&
                        d.getName().equals(b.getName()))
                .findFirst();
        if (inStock.isPresent()) {
            return true;
        }
        return false;
    }

    private String generateNewId() {
        return UUID.randomUUID().toString();
    }

    public void addBooks(List<Book> newBooks) {
        // get the most recent books from the database
        newBooks.stream().forEach(t -> {
            Optional<Book> eb = allBooks.stream()
                    .filter(a -> a.getAuthor().equals(t.getAuthor()) && a.getName().equals(t.getName())).findFirst();
            Book newBook = null;
            if (eb.isPresent()) {
                newBook = Book.builder().author(t.getAuthor()).copyOf(eb.get())
                        .description(t.getDescription())
                        .duration(t.getDuration())
                        .name(t.getName())
                        .id(generateNewId())
                        .build();

            } else {
                newBook = Book.builder().author(t.getAuthor()).copyOf(null)
                        .description(t.getDescription())
                        .duration(t.getDuration())
                        .name(t.getName())
                        .id(generateNewId())
                        .build();
            }
            allBooks.add(newBook);
        });
    }

    public ResponseEntity<String> checkOutBooks(User u, List<Book> books) {
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
        if (!isBookInStock(b)) {
            Waitlist waitlist = waitLists.stream()
                    .filter(w -> w.getB().getName().equals(b.getName()) && w.getB().getAuthor().equals(
                            b.getAuthor()))
                    .findFirst().orElse(null);
            if (waitlist == null) {
                List<User> users = Arrays.asList(u);
                waitlist = Waitlist.builder().b(b).waitListUsers(users).build();
                waitLists.add(waitlist);
                return new ResponseEntity<>(null);
            } else {
                waitlist.addUsers(u);
                return new ResponseEntity<>(null);
            }
        }
        return null;
    }
}

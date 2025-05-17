package com.library.management.domain;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class Checkout 
{
	private User u;
	private Book b;
	private LocalDate dueDate;
	private boolean isOverdue;
}

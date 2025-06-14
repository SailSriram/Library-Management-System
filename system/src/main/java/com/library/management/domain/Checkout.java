package com.library.management.domain;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Checkout {
	private User u;
	private Book book;
	private LocalDate dueDate;
	private boolean isOverdue;
}

package com.library.management.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Book 
{
	private int id;
	private String author;
	private String description;
	private Book copyOf;
	private int duration;
}

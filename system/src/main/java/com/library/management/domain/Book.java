package com.library.management.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Book 
{
	private String id;
	private String name;
	private String author;
	private String description;
	private Book copyOf;
	private int duration;
}

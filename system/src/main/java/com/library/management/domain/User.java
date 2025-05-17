package com.library.management.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User 
{
	private int id;
	private String username;
	private String password;
}

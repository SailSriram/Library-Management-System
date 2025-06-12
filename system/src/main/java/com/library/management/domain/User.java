package com.library.management.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
	private int id;
	private String username;
	private String password;
}

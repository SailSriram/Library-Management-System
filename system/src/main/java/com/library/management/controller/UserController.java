package com.library.management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.management.domain.*;
import com.library.management.service.UserService;

@Controller
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{username}")
	public Boolean isUserMember(@PathVariable String username) {
		return userService.checkIfUserIsMember(username);
	}

	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestParam String username, @RequestParam String password) {
		return userService.signUpUser(username, password);
	}
}

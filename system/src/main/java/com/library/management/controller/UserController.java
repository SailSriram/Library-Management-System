package com.library.management.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.library.management.domain.*;

@Controller
public class UserController {

	@GetMapping("/{username}")
	public Boolean isUserMember(@PathVariable String username)
	{
		return null;
	}
	
	@PostMapping("/addUser")
	public void addUser(@RequestBody User u)
	{
		
	}
}

package com.library.management.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.library.management.domain.User;

@Service
public class UserService {
    private List<User> libraryUsers;

    public UserService() {
        this.libraryUsers = new ArrayList<>();
    }

    public boolean checkIfUserIsMember(String username) {
        return libraryUsers.stream().anyMatch(t -> t.getUsername().equals(username));
    }

    public ResponseEntity<String> signUpUser(String username, String password) {
        if (checkIfUserIsMember(username)) {
            return ResponseEntity.badRequest().body("Username already exists! Create another one");
        }
        libraryUsers.add(User.builder().username(username).password(password).id(1).build());
        return ResponseEntity.ok().body("User created!");

        // We will check if the username already exists in
        // the system
        // If so, then we will return a ResponseEntity<String> of username already
        // exists with a 400 bad request
        // If not, then we will store the username and password
        // We will also generate a unique id
        // We will create a class called utils that can be used to generate a random id
        // We will then store that id along with username and password in libraryUsers
    }

}

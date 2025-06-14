package com.library.management.domain;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Waitlist {

    private Book b;
    private List<User> waitListUsers;

    public void addUsers(User u) {
        waitListUsers.add(u);
    }

}

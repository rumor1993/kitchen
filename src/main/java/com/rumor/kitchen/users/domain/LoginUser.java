package com.rumor.kitchen.users.domain;

import lombok.Getter;

@Getter
public class LoginUser {
    private Long id;
    private String subject;
    private String email;

    public LoginUser(Long id, String subject, String email) {
        this.id = id;
        this.subject = subject;
        this.email = email;
    }
}

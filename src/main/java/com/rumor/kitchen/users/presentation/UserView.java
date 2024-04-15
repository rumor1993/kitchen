package com.rumor.kitchen.users.presentation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserView {
    private final Long id;
    private final String name;
    private final String email;
}

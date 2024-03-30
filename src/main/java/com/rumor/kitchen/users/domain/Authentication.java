package com.rumor.kitchen.users.domain;

import com.rumor.kitchen.enumeration.Social;

import java.io.IOException;

public interface Authentication {

    String authenticate(Social social, String code) throws IOException;
}

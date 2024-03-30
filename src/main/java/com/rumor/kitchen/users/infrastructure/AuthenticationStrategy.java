package com.rumor.kitchen.users.infrastructure;

import com.rumor.kitchen.users.properties.OauthProperties;

import java.io.IOException;

public interface AuthenticationStrategy {
    String authenticate(OauthProperties oauthProperties, String code) throws IOException;
}

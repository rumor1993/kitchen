package com.rumor.kitchen.users.infrastructure;

import com.rumor.kitchen.enumeration.Social;
import com.rumor.kitchen.users.domain.Authentication;
import com.rumor.kitchen.users.domain.Jwt;
import com.rumor.kitchen.users.properties.OauthProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationImpl implements Authentication {

    private final Jwt jwt;
    private final OauthProperties oauthProperties;
    private final AuthenticationFactory authenticationFactory;

    @Override
    public String authenticate(Social social, String code) throws IOException {
        AuthenticationStrategy authentication = authenticationFactory.create(social);
        String subject = authentication.authenticate(oauthProperties, code);
        return jwt.createToken(subject);
    }
}

package com.rumor.kitchen.users.infrastructure;

import com.rumor.kitchen.enumeration.Social;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthenticationFactory {

    private final Map<Social, AuthenticationStrategy> strategy;

    @Autowired
    public AuthenticationFactory(GoogleAuthenticationStrategy googleAuthenticationStrategy) {
        this.strategy = Map.of(
                Social.GOOGLE, googleAuthenticationStrategy);
    }

    public AuthenticationStrategy create(Social social) {
        return strategy.get(social);
    }
}

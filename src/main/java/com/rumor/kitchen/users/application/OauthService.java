package com.rumor.kitchen.users.application;

import com.rumor.kitchen.enumeration.Social;
import com.rumor.kitchen.users.domain.Authentication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OauthService {

    private final Authentication authentication;

    public String authenticate(Social social, String code) throws IOException {
        return authentication.authenticate(social, code);
    }
}

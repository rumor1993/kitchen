package com.rumor.kitchen.users.presentation;

import com.rumor.kitchen.enumeration.Social;
import com.rumor.kitchen.users.application.OauthService;
import com.rumor.kitchen.users.properties.OauthProperties;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/oauth2")
@RequiredArgsConstructor
public class OauthController {
    private final OauthService oauthService;
    private final OauthProperties oauthProperties;

    @GetMapping("/{social}/login")
        public void login(@PathVariable Social social, HttpServletResponse response) throws IOException {
        response.sendRedirect(oauthProperties.getAuthenticationUrl(social));
    }

    @GetMapping("/{social}/authenticate")
    public void  authenticate(@PathVariable Social social, @RequestParam String code, HttpServletResponse response) throws IOException {
        String token = oauthService.authenticate(social, code);

        Cookie cookie = new Cookie("accessToken", token);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setDomain("rumor-lab.com");
        cookie.setDomain("localhost"); // localhost 도메인
        cookie.setHttpOnly(true);
        cookie.setMaxAge(3600);

        response.addCookie(cookie);
        response.sendRedirect(oauthProperties.getrediRectLoginUrl(social));
    }
}

package com.rumor.kitchen.users.presentation;

import com.rumor.kitchen.enumeration.Social;
import com.rumor.kitchen.users.application.OauthService;
import com.rumor.kitchen.users.properties.OauthProperties;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/oauth2")
@CrossOrigin(origins = "*")
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

        Cookie cookie = new Cookie("access-token", token);
        cookie.setSecure(true); // 이 속성과
        cookie.setAttribute("SameSite", "None"); // 이 속성 추가

        response.addCookie(cookie);
        response.sendRedirect("http://localhost:3000");
    }
}

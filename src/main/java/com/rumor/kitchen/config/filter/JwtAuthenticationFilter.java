package com.rumor.kitchen.config.filter;

import com.rumor.kitchen.users.application.UserService;
import com.rumor.kitchen.users.domain.Authentication;
import com.rumor.kitchen.users.domain.Jwt;
import com.rumor.kitchen.users.domain.User;
import com.rumor.kitchen.users.domain.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final Jwt jwt;
    private final Environment environment;


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if (Arrays.asList(environment.getActiveProfiles()).contains("local")) {
            // return true;
        }

        if (CorsUtils.isPreFlightRequest(request)) {
            return true;
        }

        String[] excludePath = {"/oauth2/", "/favicon.ico", "/boards", "/swagger-ui"};
        String path = request.getRequestURI();
        return Arrays.stream(excludePath).anyMatch(path::startsWith);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. Request Header에서 JWT 토큰 추출
        String token = jwt.extract(request);

        // 2. validateToken으로 토큰의 유효성 검사
        if (token == null) {
            throw new RuntimeException("권한 없음");
        }

        if (!jwt.validateToken(token)) {
            throw new RuntimeException("권한 없음");
        }

        filterChain.doFilter(request, response);
    }

}

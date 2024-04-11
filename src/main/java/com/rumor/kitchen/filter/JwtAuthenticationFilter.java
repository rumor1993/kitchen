package com.rumor.kitchen.filter;

import com.rumor.kitchen.users.domain.Authentication;
import com.rumor.kitchen.users.domain.Jwt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final Jwt jwt;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. Request Header에서 JWT 토큰 추출
        String token = resolveToken(request);
        System.out.println("resolveToken ### " + token);

        // 2. validateToken으로 토큰의 유효성 검사
        if (token != null && jwt.validateToken(token)) {
            // 토큰이 유효할 경우, 토큰에서 Authentication 객체를 가지고 와서 SecurityContext에 저장한다.

            try {
                String authentication = jwt.getAuthentication(token);
                System.out.println("authentication ### " + authentication);
            } catch (IllegalAccessException e) {

            }
        }

        filterChain.doFilter(request, response);
    }

    // Request Header에서 토큰 정보를 추출하기 위한 메서드
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(6);
        }
        return null;
    }

}

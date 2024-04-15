package com.rumor.kitchen.config;

import com.rumor.kitchen.config.converter.CaseInsensitiveEnumConverter;
import com.rumor.kitchen.config.resolver.UserArgumentResolver;
import com.rumor.kitchen.enumeration.Category;
import com.rumor.kitchen.enumeration.Social;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@EnableWebMvc
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final UserArgumentResolver userArgumentResolver;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 영향 받는 곳
                .allowedOriginPatterns("*") // 허용할 도메인 패턴
                .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true) // 이거는 꼭 허용해줘야 한다.
                .maxAge(1800);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArgumentResolver);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        List<Class<? extends Enum>> enums = List.of(Category.class, Social.class);
        enums.forEach(enumClass -> registry.addConverter(String.class, enumClass,
                new CaseInsensitiveEnumConverter<>(enumClass)));
    }
}

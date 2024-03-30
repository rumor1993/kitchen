package com.rumor.kitchen.users.presentation;

import com.rumor.kitchen.enumeration.Social;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SocialConverter implements Converter<String, Social> {

    @Override
    public Social convert(String source) {
        return Social.valueOf(source.toUpperCase());
    }
}

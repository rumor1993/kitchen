package com.rumor.kitchen.users.properties;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class GoogleProperties {
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String apiUrl;
    private String authenticationUrl;
    private String redirectLoginUrl;
}

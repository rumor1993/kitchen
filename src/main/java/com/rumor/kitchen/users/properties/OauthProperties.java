package com.rumor.kitchen.users.properties;

import com.rumor.kitchen.enumeration.Social;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "oauth")
@RequiredArgsConstructor
public class OauthProperties {

    private final GoogleProperties google;

    public String getAuthenticationUrl(Social social) {
        if (social == Social.GOOGLE) {
            return this.google.getAuthenticationUrl();
        }

        throw new IllegalArgumentException("not found social " + social);
    }

    public String getClientId(Social social) {
        if (social == Social.GOOGLE) {
            return this.google.getClientId();
        }

        throw new IllegalArgumentException("not found social " + social);
    }

    public String getClientSecret(Social social) {
        if (social == Social.GOOGLE) {
            return this.google.getClientSecret();
        }

        throw new IllegalArgumentException("not found social " + social);
    }
}

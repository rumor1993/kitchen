package com.rumor.kitchen.users.infrastructure;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class SocialOauthResponse {

    @Getter
    private final String accessToken;
    private final String expiresIn;
    private final String refreshToken;
    private final String scope;
    private final String tokenType;
    @Getter
    private String idToken;

    @JsonCreator
    public SocialOauthResponse(
            @JsonProperty("access_token")
            String accessToken,
            @JsonProperty("expires_in")
            String expiresIn,
            @JsonProperty("refresh_token")
            String refreshToken,
            @JsonProperty("scope")
            String scope,
            @JsonProperty("token_type")
            String tokenType,
            @JsonProperty("id_token")
            String idToken) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.scope = scope;
        this.tokenType = tokenType;
        this.idToken = idToken;
    }
}

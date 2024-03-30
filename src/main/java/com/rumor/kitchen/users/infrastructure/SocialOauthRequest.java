package com.rumor.kitchen.users.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class SocialOauthRequest {
    private final String code;
    @JsonProperty("client_id")
    private final String clientId;
    @JsonProperty("client_secret")
    private final String clientSecret;
    @JsonProperty("redirect_uri")
    private final String redirectUri;
    @JsonProperty("grant_type")
    private final String grantType = "authorization_code";


}

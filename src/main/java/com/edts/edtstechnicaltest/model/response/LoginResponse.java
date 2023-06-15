package com.edts.edtstechnicaltest.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {
    private String accessToken;
    private String tokenType;
    private int expiresIn;
}

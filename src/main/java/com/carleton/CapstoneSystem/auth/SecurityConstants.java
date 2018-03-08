package com.carleton.CapstoneSystem.auth;

public interface SecurityConstants {
    String SECRET = "KapiSecretKeyToGenJWTs";
    long EXPIRATION_TIME = 864_000_000; // 10 days
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
    String SIGN_UP_URL = "/users/sign-up";
    String LOGIN_URL = "/users/login";
}

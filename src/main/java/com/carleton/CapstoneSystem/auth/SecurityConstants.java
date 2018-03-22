package com.carleton.CapstoneSystem.auth;

public interface SecurityConstants {
    String SECRET = "SecretKeyToGenJWTs";
    long EXPIRATION_TIME = 864_000_000; // 10 days
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
    String SIGN_UP_URL = "/api/users/sign-up";
    String LOGIN_URL = "/api/users/login";
    String PROGRAMS_URL = "/api/programs";
    String PROJECTS_URL = "/api/projects";
    String GET_PROJECT_BY_ID_URL = "/api/projects/id";


}

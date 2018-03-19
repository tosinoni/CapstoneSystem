package com.carleton.CapstoneSystem.utils;

public interface RequestErrorMessages {

    String INVALID_USERNAME="Request failed. Please provide a valid username";
    String INCORRECT_PASSWORD ="Request failed. The password does not match with the username you entered";
    String NO_USER =  "Request failed. Please fill in the form";
    String NO_USERNAME = "Request failed. Please Enter A UserName";
    String NO_PASSWORD ="Request failed. Please Enter A Password for your UserName";
    String INVALID_ROLE = "Request failed. Please Enter A valid Role";
    String INVALID_EMAIL = "Request failed. Please Enter A valid EMAIL";
    String DUPLICATE_USERNAME ="Request failed. Username already exists.";
    String DUPLICATE_EMAIL ="Request failed. Email already exists.";
    String NO_FIRST_NAME ="Request failed. Please provide your first name.";
    String NO_LAST_NAME ="Request failed. Please provide your last name.";
    String NO_IDENTIFIER ="Request failed. Please provide your identification number.";
}

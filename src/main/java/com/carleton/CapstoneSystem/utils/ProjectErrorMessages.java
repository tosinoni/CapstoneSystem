package com.carleton.CapstoneSystem.utils;

public interface ProjectErrorMessages {

    String NO_NAME =  "Request failed. Please provide a project name.";
    String INVALID_ID =  "Request failed. Please provide a valid project id.";
    String NO_DESCRIPTION =  "Request failed. Please provide a project description.";
    String INVALID_NAME =  "Request failed. Project name already exists.";
    String INVALID_MIN_CAPACITY =  "Request failed. Please provide a valid min capacity for the project.";
    String INVALID_MAX_CAPACITY =  "Request failed. Please provide a valid max capacity for the project.";
    String INVALID_USER =  "Request failed. Invalid user supplied for creation of project.";
}

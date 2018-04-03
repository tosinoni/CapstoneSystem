package com.carleton.CapstoneSystem.utils;

public interface ProjectErrorMessages {

    String EMPTY_PROJECT_INFO =  "Request failed. Please provide a project id.";
    String NO_NAME =  "Request failed. Please provide a project name.";
    String INVALID_ID =  "Request failed. Please provide a valid project id.";
    String NO_DESCRIPTION =  "Request failed. Please provide a project description.";
    String INVALID_NAME =  "Request failed. Project name already exists.";
    String INVALID_MIN_CAPACITY =  "Request failed. Please provide a valid min capacity for the project.";
    String INVALID_MAX_CAPACITY =  "Request failed. Please provide a valid max capacity for the project.";
    String INVALID_CAPACITY =  "Request failed. Please provide a valid max capacity greater than min capacity.";
    String INVALID_USER =  "Request failed. Invalid user supplied for creation of project.";

    String INVALID_MIN_CAPACITY_ADD_STUDENTS =  "Request failed. The minimum number of student to be added is %d.";
    String INVALID_MAX_CAPACITY_ADD_STUDENTS =  "Request failed. The maximum number of student to be added is %d.";
    String EMPTY_STUDENT_FOR_ADD_STUDENTS =  "Request failed. Empty student provided.";
    String INVALID_STUDENT_PROVIDED_FOR_ADD_STUDENTS =  "Request failed. Invalid student provided.";
    String STUDENT_NOT_APPLIED_FOR_PROJECT =  "Request failed. Student %s has not applied for the project.";
    String STUDENT_REGISTERED_FOR_PROJECT_ALREADY =  "Request failed. Student %s has a project already.";
}

package com.carleton.CapstoneSystem.DTO;

import com.carleton.CapstoneSystem.models.Program;
import com.carleton.CapstoneSystem.models.Role;
import com.carleton.CapstoneSystem.models.WebUser;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {

    @JsonProperty
    private String firstname;

    @JsonProperty
    private String lastname;

    @JsonProperty
    private String username;

    @JsonProperty
    private String email;

    @JsonProperty
    private String token;

    @JsonProperty
    private Role role;

    @JsonProperty
    private long id;

    @JsonProperty
    private long identifier;

    @JsonProperty
    private String password;

    @JsonProperty
    private Program program;

    public UserDTO() {

    }

    public UserDTO(WebUser user) {
        this.username = user.getUserName();
        this.id = user.getId();
        this.role = user.getRole();
        this.firstname = user.getFirstName();
        this.lastname = user.getLastName();
        this.identifier = user.getIdentifier();
        this.email = user.getEmail();

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {

        this.lastname = lastname;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token) {

        this.token = token;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public Role getRole() {

        return role;
    }

    public void setRole(Role role) {

        this.role = role;
    }

    public long getIdentifier() {

        return identifier;
    }

    public void setIdentifier(long identifier)
    {
        this.identifier = identifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}

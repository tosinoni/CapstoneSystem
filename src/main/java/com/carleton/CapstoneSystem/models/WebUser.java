package com.carleton.CapstoneSystem.models;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * @author Ibrahim ALi Fawaz
 * this abstract class represents a general user of the fourth year project website. more specific roles of the user should be shown in the subclasses of the class
 */
@Entity
public  class WebUser {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique=true,name="id",nullable = false)
    private long id;

    @Column(unique=true, name="identifier", nullable = false)
    private long identifier;

    @Email
    @Column(unique=true,nullable=false)
    private String email;

    @Column(nullable = false)
    @Size(min=4)
    private String password;

    @Column(nullable=false)
    private Role role;

    @Column(name="user_name",nullable=false,unique = true)
    private String userName;

    @Column(name="first_name",nullable=false)
    private String firstName;

    @Column(name="last_name",nullable=false)
    private String lastName;



    @Column(name="program",nullable=false)
    private Program program;






    public WebUser(){

    }
    public WebUser(String userName,String password){
        this.userName=userName;
        this.password=password;
    }

    /**
     *
     * @return the user name of the user
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName to be set as the user name of the user
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }


    /**
     *
     * @return the role of this user
     */
    public Role getRole() {
        return role;
    }

    public Program getProgram() {
        return program;
    }
    public void setProgram(Program program) {
        this.program = program;
    }

    /**
     *
     * @param role to be set as the role of this user
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     *
     * @return the id of the user
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id the id to be set for the user
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     *
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email to be set for the email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password to set it to the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(long identifier) {
        this.identifier = identifier;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WebUser)) return false;
        WebUser webUser = (WebUser) o;
        return id == webUser.id && userName.equals(webUser.userName) && firstName.equals(webUser.firstName) &&
                lastName.equals(webUser.lastName) && identifier == webUser.identifier && role.equals(webUser.getRole())
                && password.equals(webUser.password) && email.equals(webUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, userName, email, password, identifier, role, identifier);
    }
}

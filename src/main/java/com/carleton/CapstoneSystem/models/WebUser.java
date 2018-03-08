package com.carleton.CapstoneSystem.models;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;
import javax.validation.constraints.Size;

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



}

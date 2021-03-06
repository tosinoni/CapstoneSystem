package com.carleton.CapstoneSystem.models;

import com.carleton.CapstoneSystem.DTO.UserDTO;
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
@Inheritance
public  class WebUser {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique=true,name="id",nullable = false)
    private long id;

    @Column(unique=true, name="identifier", nullable = false)
    protected long identifier;

    @Email
    @Column(unique=true,nullable=false)
    protected String email;

    @Column(nullable = false)
    @Size(min=4)
    protected String password;

    @Column(nullable=false)
    protected Role role;

    @Column(name="user_name",nullable=false,unique = true)
    protected String userName;

    @Column(name="first_name",nullable=false)
    protected String firstName;

    @Column(name="last_name",nullable=false)
    protected String lastName;



    @OneToOne
    protected Schedule schedule;










    public WebUser(){

    }
    public WebUser(String userName,String password){
        this.userName=userName;
        this.password=password;
    }

    public WebUser(UserDTO userDTO) {
        this.firstName = userDTO.getFirstname();
        this.lastName = userDTO.getLastname();
        this.email = userDTO.getEmail();
        this.identifier = userDTO.getIdentifier();
        this.password = userDTO.getPassword();
        this.role = userDTO.getRole();
        this.userName = userDTO.getUsername();
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
    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
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

    public WebUser copyUser(UserDTO user){
        return null;
    }
}

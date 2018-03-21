package com.carleton.CapstoneSystem.models;



import com.carleton.CapstoneSystem.DTO.UserDTO;

import javax.persistence.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A coordinator has access to all the fourth year projects, and they manage the presentations of these projects, as well as the assignment of projects to
 * students who do not have any projects.
 */
@Entity
public class Coordinator extends Professor {


    @Override
    public Coordinator copyUser(UserDTO user){
        return (Coordinator) super.copyUser(user);
    }





}

package com.carleton.CapstoneSystem.models;

import com.carleton.CapstoneSystem.DTO.UserDTO;

public enum Role {
    STUDENT(new Student()),PROFESSOR(new Professor()),COORDINATOR(new Coordinator());

    WebUser user;

    Role(WebUser user){
        this.user=user;

    }

    public WebUser createUser(UserDTO user){
        return this.user.copyUser(user);
    }





    public static boolean contains(Role role){
        for (Role existingRole : Role.values()) {
            if (existingRole.name().equals(role.name())) {
                return true;
            }
        }

        return false;
    }
}

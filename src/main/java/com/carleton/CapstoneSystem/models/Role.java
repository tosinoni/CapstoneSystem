package com.carleton.CapstoneSystem.models;

public enum Role {
    STUDENT, PROFESSOR,COORDINATOR;

    public static boolean contains(Role role){
        for (Role existingRole : Role.values()) {
            if (existingRole.name().equals(role.name())) {
                return true;
            }
        }

        return false;
    }
}

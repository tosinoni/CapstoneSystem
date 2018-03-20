package com.carleton.CapstoneSystem.models;

public enum Program {
    SOFTWARE_ENGINEERING,COMPUTER_ENGINEERING,BIOMEDICAL_ENGINEERNG;
    public static boolean contains(Program program){
        for (Program existingProgram : Program.values()) {
            if (existingProgram.name().equals(program.name())) {
                return true;
            }
        }

        return false;
    }
}

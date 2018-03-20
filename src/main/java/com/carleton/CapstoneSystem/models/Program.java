package com.carleton.CapstoneSystem.models;

public enum Program {
    Software_Engineering,Computer_Engineering,BioMedicalEngineering;
    public static boolean contains(Program program){
        for (Program existingProgram : Program.values()) {
            if (existingProgram.name().equals(program.name())) {
                return true;
            }
        }

        return false;
    }
}

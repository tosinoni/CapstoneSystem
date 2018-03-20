package com.carleton.CapstoneSystem.models;

public enum Program {
    SOFTWARE_ENGINEERING("SE"),COMPUTER_ENGINEERING("CE"),BIOMEDICAL_ENGINEERNG("BE");

     String shortcut;

     Program(String shortcut){
        this.shortcut=shortcut;

    }

    public String getShortcut(){
         return shortcut;
    }
    public static boolean contains(Program program){
        for (Program existingProgram : Program.values()) {
            if (existingProgram.name().equals(program.name())) {
                return true;
            }
        }

        return false;
    }
}

package com.carleton.CapstoneSystem.models;

public enum Program {
    SOFTWARE_ENGINEERING("SE"),COMPUTER_ENGINEERING("CSE"),BIOMEDICAL_ENGINEERNG("Biomed"),ELECTRICAL_ENGINEERING("EE")
    ,MECAHNICAL_ENGINEERING("ME"),SUSTAINABLE_AND_RENEWABLE_ENERGY_ENGINEERING("SREE"),AEROSPACE_ENGINEERING("AERO");

     String shortcut;

     Program(String shortcut){
        this.shortcut=shortcut;

    }

    public String getShortcut(){
         return shortcut;
    }
    public static boolean contains(Program program){
        for (Program existingProgram : Program.values()) {
            if (existingProgram.name().equals(program.name())|| existingProgram.getShortcut().equals(program.getShortcut())) {
                return true;
            }
        }

        return false;
    }
}

package com.carleton.CapstoneSystem.models;

public enum Grade {
    A_PLUS("A+"), A("A"), A_MINUS("A-"), B_PLUS("B+"), B("B"), B_MINUS("B-"), C_PLUS("C+"), C("C"),
    C_MINUS("C-"), D_PLUS("D+"), F("F");

    String shortcut;

    Grade(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getShortcut(){
        return shortcut;
    }
    public static boolean contains(Grade grade){
        for (Grade existingGrades : Grade.values()) {
            if (existingGrades.name().equals(grade.name())|| existingGrades.getShortcut().equals(grade.getShortcut())) {
                return true;
            }
        }

        return false;
    }

    public static Grade getGrade(String grade) {
        for (Grade existingGrade : Grade.values()) {
            if (existingGrade.name().equals(grade) || existingGrade.getShortcut().equals(grade)) {
                return existingGrade;
            }
        }

        return null;
    }
}

package com.carleton.CapstoneSystem.models;

public enum Time {
    NINE("9:00 AM to 10:00 AM"),TEN("10:00 AM to 11:00 AM"),ELEVEN("11:00 AM to 12:00 PM"),TWELVE("1:00 to 10:00"),ONE("9:00 to 10:00");

    String range;
    Time(String range){
        this.range=range;
    }
}

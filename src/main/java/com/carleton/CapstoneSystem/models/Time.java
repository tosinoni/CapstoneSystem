package com.carleton.CapstoneSystem.models;

public enum Time {
    NINE("9:00 AM to 10:00 AM"),TEN("10:00 AM to 11:00 AM"),ELEVEN("11:00 AM to 12:00 PM"),TWELVE("12:00 PM to 1:00 PM"),ONE("1:00 PM to 2:00 PM"),
    TWO("2:00 PM to 3:00 PM"),THREE("3:00 PM to 4:00 PM"),FOUR("4:00 PM to 5:00 PM");
    private static Time[] vals = values();
    String range;
    Time(String range){
        this.range=range;
    }
    public Time advance(){
            if(canAdvance()) {
                return vals[(this.ordinal() + 1) % vals.length];
            }
            return this;
    }
    public Time goBack(){
        if(canGoBack()) {
            return vals[(this.ordinal() - 1) % vals.length];
        }
        return this;
    }
    public boolean canAdvance(){
        return (this.ordinal()+1)%vals.length < vals.length;
    }
    public boolean canGoBack(){
        return (this.ordinal())%vals.length > 0;
    }

    public static Time Mean(Time presentationTime, Time presentationTime1) {
        if(Math.abs((presentationTime.ordinal()%vals.length)-(presentationTime1.ordinal()%vals.length))==1){
            return presentationTime;
        }
        return vals[((presentationTime.ordinal()%vals.length)+(presentationTime1.ordinal()%vals.length))/2];
    }

    public static Time MergeBias(Time presentationTime, Time presentationTime1) {
        if(Math.abs((presentationTime.ordinal()%vals.length)-(presentationTime1.ordinal()%vals.length))<3){
            return Mean(presentationTime,presentationTime1);
        }
        int newIndex=(2 * (presentationTime.ordinal() % vals.length) + (presentationTime1.ordinal() % vals.length)) / 3;
            if(newIndex>vals.length){
                return vals[vals.length-1];
            }
            return vals[newIndex];

    }
}

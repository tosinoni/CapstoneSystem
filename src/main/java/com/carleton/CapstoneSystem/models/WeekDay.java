package com.carleton.CapstoneSystem.models;

public enum WeekDay {
    MONDAY(12),TUESDAY(13),WEDNESDAY(14),THURSDAY(15),FRIDAY(16);

    public int getDayDate() {
        return dayDate;
    }

    int dayDate;
    WeekDay(int dayDate){
        this.dayDate=dayDate;
    }

    private static WeekDay[] vals = values();
    public WeekDay advance(){
        if(canAdvance()) {
            return vals[(this.ordinal() + 1) % vals.length];
        }
        return this;
    }
    public WeekDay goBack(){
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
}

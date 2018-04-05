package com.carleton.CapstoneSystem.DTO;

import com.carleton.CapstoneSystem.models.Month;
import com.carleton.CapstoneSystem.models.Time;
import com.carleton.CapstoneSystem.models.WeekDay;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class DayDTO {
    @JsonProperty
    private WeekDay weekDay;
    @JsonProperty
    private Month month;
    @JsonProperty
    private int dayDate;
    @JsonProperty
    private ArrayList<Time> times;



    @JsonProperty
    private Time presentationTime;

    public DayDTO(){
        times= new ArrayList<Time>();
    }


    public void addTime(Time time) {
        times.add(time);
    }

    public void removeTime(Time time) {
        times.remove(time);
    }


    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public int getDayDate() {
        return dayDate;
    }

    public void setDayDate(int dayDate) {
        this.dayDate = dayDate;
    }

    public ArrayList<Time> getTimes() {
        return times;
    }
    public Time getPresentationTime() {
        return presentationTime;
    }

    public void setPresentationTime(Time presentationTime) {
        this.presentationTime = presentationTime;
    }
}

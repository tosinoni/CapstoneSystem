package com.carleton.CapstoneSystem.models;

import com.carleton.CapstoneSystem.DTO.DayDTO;

import javax.persistence.*;
import java.util.ArrayList;
@Entity
public class ScheduleDay {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique=true,name="id",nullable = false)
    private long id;
    private WeekDay weekDay;
    private Month month;
    private int dayDate;
    private Time presentationTime;


    private ArrayList<Time> times;

    public ScheduleDay(DayDTO dayDTO){
        setWeekDay(dayDTO.getWeekDay());
        setMonth(dayDTO.getMonth());
        setDayDate(dayDTO.getDayDate());
        for(Time time:dayDTO.getTimes()){
            times.add(time);
        }
    }
    public ScheduleDay(ScheduleDay day){
        setWeekDay(day.getWeekDay());
        setMonth(day.getMonth());
        setDayDate(day.getDayDate());
        for(Time time:day.getTimes()){
            times.add(time);
        }
    }
    public ScheduleDay(){

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
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

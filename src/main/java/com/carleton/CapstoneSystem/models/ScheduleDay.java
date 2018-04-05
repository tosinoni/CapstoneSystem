package com.carleton.CapstoneSystem.models;

import com.carleton.CapstoneSystem.DTO.DayDTO;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Collections;

@Entity
public class ScheduleDay  implements Comparable<ScheduleDay>{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique=true,name="id",nullable = false)
    private long id;
    private WeekDay weekDay;
    private Month month;
    private int dayDate;
    private Time presentationTime;



    private ArrayList<Time> times=new ArrayList<Time>();

    public ScheduleDay(DayDTO dayDTO){
        setWeekDay(dayDTO.getWeekDay());
        setMonth(dayDTO.getMonth());
        setDayDate(dayDTO.getDayDate());
        for(Time time:dayDTO.getTimes()){
            times.add(time);
        }
        this.presentationTime=dayDTO.getPresentationTime();
    }
    public ScheduleDay(ScheduleDay day){
        setWeekDay(day.getWeekDay());
        setMonth(day.getMonth());
        setDayDate(day.getDayDate());
        for(Time time:day.getTimes()){
            times.add(time);
        }
        this.presentationTime=day.getPresentationTime();
    }
    public ScheduleDay(){
        this(null,null,0,new ArrayList<Time>());
    }
    public ScheduleDay(Month month, WeekDay weekDay, int dayDate, ArrayList<Time> times){
        this.month=month;
        this.weekDay=weekDay;
        this.dayDate=dayDate;
        this.times= new ArrayList<Time>();
        for(Time time:times) {
            this.times.add(time);
        }

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
        setDayDate(weekDay.getDayDate());
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


    @Override
    public int compareTo(ScheduleDay o) {
        int firstComparison= Math.abs(o.getDayDate()-getDayDate())+ Math.abs(o.getWeekDay().compareTo(weekDay));
        Collections.sort(getTimes());
        Collections.sort(o.getTimes());
        int secondComparison=0;
        if(getTimes().size()>0 && o.getTimes().size()>0) {
            secondComparison+=Math.abs(getTimes().get(0).compareTo(o.getTimes().get(0)));
        }
        int thirdComparison=0;
        if(presentationTime!=null && o.getPresentationTime()!=null){
            thirdComparison+=Math.abs(presentationTime.compareTo(o.getPresentationTime()));
        }
        return firstComparison+secondComparison+thirdComparison;
    }

    public Time getPresentationTime() {
        return presentationTime;
    }

    public void setPresentationTime(Time presentationTime) {
        this.presentationTime = presentationTime;
    }

    @Override
    public boolean equals(Object o){

        if(o instanceof ScheduleDay){
            ScheduleDay presentationDay = (ScheduleDay)o;
            return this.getWeekDay().equals(presentationDay.getWeekDay()) && this.getDayDate()==presentationDay.getDayDate() && presentationTime.equals(presentationDay.getPresentationTime());
        }

        return false;
    }
}

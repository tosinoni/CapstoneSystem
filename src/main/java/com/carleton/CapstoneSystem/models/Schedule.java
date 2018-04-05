package com.carleton.CapstoneSystem.models;

import com.carleton.CapstoneSystem.DTO.DayDTO;
import com.carleton.CapstoneSystem.DTO.ScheduleDTO;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique=true,name="id",nullable = false)
    private long id;

    public Set<ScheduleDay> getScheduleDays() {
        return scheduleDays;
    }

    @OneToMany(fetch = FetchType.EAGER)
    private Set<ScheduleDay> scheduleDays ;

    public Schedule(ScheduleDTO scheduleDTO){
        for(DayDTO day: scheduleDTO.getAllDays()){
                ScheduleDay scheduleDay = new ScheduleDay(day);
                scheduleDays.add(scheduleDay);
        }
    }
    public Schedule(){
        scheduleDays= new LinkedHashSet<ScheduleDay>() ;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

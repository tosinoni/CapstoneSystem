package com.carleton.CapstoneSystem.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;


public class ScheduleDTO {
    @JsonProperty
    private ArrayList<DayDTO> days;


    public ScheduleDTO(){
        days= new ArrayList<DayDTO>();

    }
    public void addDay(DayDTO day){
        days.add(day);
    }

    public void removeDau(DayDTO day){
        days.remove(day);
    }

    public ArrayList<DayDTO> getAllDays() {
        return days;
    }
}

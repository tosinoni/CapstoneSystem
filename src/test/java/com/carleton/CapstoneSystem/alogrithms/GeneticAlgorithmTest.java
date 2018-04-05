package com.carleton.CapstoneSystem.alogrithms;


import com.carleton.CapstoneSystem.models.Month;
import com.carleton.CapstoneSystem.models.Time;

import com.carleton.CapstoneSystem.models.ScheduleDay;
import com.carleton.CapstoneSystem.models.WeekDay;
import com.carleton.CapstoneSystem.services.SchedulingService;

import jdk.nashorn.internal.runtime.regexp.joni.constants.TargetInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.transaction.Transactional;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneticAlgorithmTest {
    @Autowired
    SchedulingService schedulingService;

    ArrayList<ScheduleDay> days1= new ArrayList<ScheduleDay>();
    ArrayList<ScheduleDay> days2= new ArrayList<ScheduleDay>();
    ArrayList<ArrayList<ScheduleDay>> days= new ArrayList<ArrayList<ScheduleDay>>();

    @Before
    public void init() throws Exception {
        ArrayList<Time> times= new ArrayList<Time>();
        times.add(Time.FOUR);
        times.add(Time.NINE);
        times.add(Time.THREE);
        times.add(Time.TWELVE);
        days1.add(createScheduleDay(Month.MARCH,12, WeekDay.MONDAY,times));
        times.remove(Time.NINE);
        times.remove(Time.THREE);
        times.remove(Time.TWELVE);
        times.add(Time.ONE);
        days1.add(createScheduleDay(Month.MARCH,13,WeekDay.TUESDAY,times));
        days.add(days1);
        times.remove(Time.ONE);
        times.add(Time.ELEVEN);
        days2.add(createScheduleDay(Month.MARCH,12,WeekDay.MONDAY,times));
        times.remove(Time.ELEVEN);
        times.remove(Time.FOUR);
        times.add(Time.THREE);
        days2.add(createScheduleDay(Month.MARCH,13,WeekDay.TUESDAY,times));
        days.add(days2);


    }

    @Test
    public void geneticAlgorithmExampleOneTest(){
        ScheduleDay presentationDay = new ScheduleDay(Month.MARCH,WeekDay.MONDAY,12,new ArrayList<Time>());
        presentationDay.setPresentationTime(Time.FOUR);
        assertEquals(schedulingService.createPresentationTime(days),presentationDay);
    }

    private ScheduleDay createScheduleDay(Month march, int i, WeekDay monday, ArrayList<Time> times) {
        return new ScheduleDay(march,monday,i,times);
    }


}

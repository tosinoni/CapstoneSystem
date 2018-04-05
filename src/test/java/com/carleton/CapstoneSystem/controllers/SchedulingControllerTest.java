package com.carleton.CapstoneSystem.controllers;

import com.carleton.CapstoneSystem.Controllers.SchedulingController;
import com.carleton.CapstoneSystem.DTO.DayDTO;
import com.carleton.CapstoneSystem.DTO.ScheduleDTO;
import com.carleton.CapstoneSystem.DTO.UserDTO;
import com.carleton.CapstoneSystem.models.*;
import com.carleton.CapstoneSystem.restfulControllers.UserController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import java.security.Principal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SchedulingControllerTest {

    @Autowired
    SchedulingController schedulingController;
    @Autowired
    UserController userController;

    ScheduleDTO scheduleDTO;
    ScheduleDTO scheduleDTO2;

    @Before
    @Rollback(true)
    public void init() throws Exception {
         scheduleDTO = new ScheduleDTO();

        DayDTO dayDTO = new DayDTO();
        dayDTO.setDayDate(12);
        dayDTO.setMonth(Month.MARCH);
        dayDTO.setWeekDay(WeekDay.MONDAY);
        dayDTO.addTime(Time.ONE);
        dayDTO.addTime(Time.TWO);
        scheduleDTO.addDay(dayDTO);
         dayDTO = new DayDTO();
        dayDTO.setDayDate(14);
        dayDTO.setMonth(Month.MARCH);
        dayDTO.setWeekDay(WeekDay.WEDNESDAY);
        dayDTO.addTime(Time.ONE);
        dayDTO.addTime(Time.TWO);
        scheduleDTO.addDay(dayDTO);
        scheduleDTO2= new ScheduleDTO();
        dayDTO = new DayDTO();
        dayDTO.setDayDate(12);
        dayDTO.setMonth(Month.MARCH);
        dayDTO.setWeekDay(WeekDay.MONDAY);
        dayDTO.addTime(Time.ONE);
        dayDTO.addTime(Time.THREE);
        scheduleDTO2.addDay(dayDTO);
        dayDTO = new DayDTO();
        dayDTO.setDayDate(14);
        dayDTO.setMonth(Month.MARCH);
        dayDTO.setWeekDay(WeekDay.WEDNESDAY);
        dayDTO.addTime(Time.THREE);
        dayDTO.addTime(Time.FOUR);
        scheduleDTO2.addDay(dayDTO);



    }

    @Test
    @Rollback(true)
    public void testAddSchedule(){
        UserDTO user= new UserDTO();
        user.setUsername("kousa11");
        user.setFirstname("Asd");
        user.setLastname("tize");
        user.setIdentifier(1221112);
        user.setPassword("adasds");
        user.setEmail("ali22@cmail.carleton.ca");
        user.setRole(Role.STUDENT);
        user.setProgram("SE");
        userController.signUp(user);
        user= new UserDTO();
        user.setUsername("kousa23");
        user.setFirstname("Asd");
        user.setIdentifier(111111);
        user.setLastname("tize");
        user.setEmail("ali111@cmail.carleton.ca");
        user.setRole(Role.STUDENT);
        user.setProgram("SE");
        user.setPassword("asdaads");
        userController.signUp(user);


        Principal principal= new Principal() {
            @Override
            public String getName() {
                return "kousa11";
            }

        };
        Response result= schedulingController.submitSchedule(principal,scheduleDTO);
        assertEquals(result.getStatus(), 200);
        principal= new Principal() {
            @Override
            public String getName() {
                return "kousa23";
            }

        };
        result= schedulingController.submitSchedule(principal,scheduleDTO2);
        assertEquals(result.getStatus(), 200);
    }






}

package com.carleton.CapstoneSystem.restfulControllers;

import com.carleton.CapstoneSystem.Controllers.SchedulingController;
import com.carleton.CapstoneSystem.DTO.ProjectDTO;
import com.carleton.CapstoneSystem.DTO.ScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;
import java.security.Principal;

@RestController
@RequestMapping("/api/schedule")
public class SchedulingResource {


    @Autowired
    SchedulingController scheduleController;

    @PostMapping("/submit")
    public Response editProject(Principal principal, @RequestBody ScheduleDTO scheduleDTO) {

        return scheduleController.submitSchedule( principal,scheduleDTO);
    }
    @PostMapping("/runAlgorithm")
    public Response runSchedulingAlgorithm(Principal principal) {

        return scheduleController.runSchedulingAlgorithm(principal);
    }
}

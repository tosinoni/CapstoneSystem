package com.carleton.CapstoneSystem.Controllers;

import com.carleton.CapstoneSystem.DTO.ScheduleDTO;

import com.carleton.CapstoneSystem.models.*;
import com.carleton.CapstoneSystem.repositories.CoordinatorRepository;
import com.carleton.CapstoneSystem.repositories.ProfessorRepository;
import com.carleton.CapstoneSystem.repositories.ProjectRepository;
import com.carleton.CapstoneSystem.repositories.StudentRepository;

import com.carleton.CapstoneSystem.utils.EmailSendingError;
import com.carleton.CapstoneSystem.utils.ProjectErrorMessages;
import com.carleton.CapstoneSystem.utils.SchedulingErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashSet;


@Controller
public class SchedulingController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    CoordinatorRepository coordinatorRepository;
    @Autowired
    ProjectRepository projectRepository;



    public Response submitSchedule(Principal principal, ScheduleDTO scheduleDTO) {
        if(scheduleDTO == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(SchedulingErrorMessages.EMPTY_SCHEDULE_INFO).build();
        }


        validatePrincipal(principal);

        saveUserIntoRepository(principal,new Schedule(scheduleDTO));
        return  Response.status(Response.Status.OK).build();
    }

    private void saveUserIntoRepository(Principal principal,Schedule schedule) {
        String user_Name=principal.getName();
        Student student = studentRepository.findByUserName(user_Name);
        Professor professor = professorRepository.findByUserName(user_Name);
        Coordinator coordinator= coordinatorRepository.findByUserName(user_Name);
        if(student!=null){
            student.setSchedule(schedule);
            studentRepository.save(student);
        }else if(professor!=null){
            professor.setSchedule(schedule);
            professorRepository.save(professor);
        }else if(coordinator!=null){
            coordinator.setSchedule(schedule);
            coordinatorRepository.save(coordinator);
        }
    }



    private void validatePrincipal(Principal principal) {
        if(principal == null) {
            throw new WebApplicationException(ProjectErrorMessages.INVALID_USER, Response.Status.BAD_REQUEST);
        }
    }


}

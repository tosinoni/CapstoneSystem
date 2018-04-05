package com.carleton.CapstoneSystem.Controllers;

import com.carleton.CapstoneSystem.DTO.ScheduleDTO;

import com.carleton.CapstoneSystem.models.*;
import com.carleton.CapstoneSystem.repositories.CoordinatorRepository;
import com.carleton.CapstoneSystem.repositories.ProfessorRepository;
import com.carleton.CapstoneSystem.repositories.ProjectRepository;
import com.carleton.CapstoneSystem.repositories.StudentRepository;

import com.carleton.CapstoneSystem.services.SchedulingService;
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
    @Autowired
    SchedulingService schedulingService;



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
    public Response runSchedulingAlgorithm(Principal principal) {
        validatePrincipal(principal);
        if(coordinatorRepository.findByUserName(principal.getName())==null){
            return Response.status(Response.Status.BAD_REQUEST).entity(EmailSendingError.NOT_COORDINATOR).build();
        }
        ArrayList<Project> projectsDB = getAllProjects();
        setPresentationTimeForEachProject(projectsDB);
        return Response.status(Response.Status.OK).build();
    }

    public ArrayList<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public void setPresentationTimeForEachProject(ArrayList<Project> projects) {
        for(Project project:projects) {
            ArrayList<Student> students = studentRepository.findAllByProjectId(project.getId());
            Professor professor = professorRepository.findByProjectsSupervisedId(project.getId());
            ArrayList<ArrayList<ScheduleDay>> usersDays= new ArrayList<ArrayList<ScheduleDay>>();

            if(students!=null ){
                if(!students.isEmpty()) {
                    for (Student student : students) {
                        addScheduleDays(usersDays, student);

                    }
                    if (professor != null) {
                        addScheduleDays(usersDays, professor);
                    }
                    ScheduleDay presentationDay = schedulingService.createPresentationTime(usersDays);
                    project.setPresentationDay(presentationDay);

                }
            }


        }

    }

    private void addScheduleDays(ArrayList<ArrayList<ScheduleDay>> usersDays, WebUser webuser) {
        LinkedHashSet<ScheduleDay> userScheduleDays = (LinkedHashSet<ScheduleDay>)webuser.getSchedule().getScheduleDays();
        ArrayList<ScheduleDay> scheduleDays= new ArrayList<ScheduleDay>();
        for(ScheduleDay scheduleDay:userScheduleDays){
            scheduleDays.add(new ScheduleDay(scheduleDay));
        }
        usersDays.add(scheduleDays);
    }

}

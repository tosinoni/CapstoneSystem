package com.carleton.CapstoneSystem.Controllers;

import com.carleton.CapstoneSystem.DTO.ProjectDTO;
import com.carleton.CapstoneSystem.DTO.StudentDTO;
import com.carleton.CapstoneSystem.models.Program;
import com.carleton.CapstoneSystem.models.Project;
import com.carleton.CapstoneSystem.models.Student;
import com.carleton.CapstoneSystem.repositories.ProfessorRepository;
import com.carleton.CapstoneSystem.repositories.ProjectRepository;
import com.carleton.CapstoneSystem.repositories.StudentRepository;
import com.carleton.CapstoneSystem.utils.ProjectErrorMessages;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.security.Principal;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    StudentRepository studentRepository;

    public Response getAllProjects() {
        Set<ProjectDTO> allProjects = new LinkedHashSet<>();
        projectRepository.findAll().forEach(project -> allProjects.add(new ProjectDTO(project)));

        return Response.status(Response.Status.OK).entity(allProjects).build();
    }

    public Response getProjectById(String id) {
        if(!StringUtils.isStrictlyNumeric(id)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ProjectErrorMessages.INVALID_ID).build();
        }

        Project project = projectRepository.findProjectById(Long.parseLong(id));

        if(project == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ProjectErrorMessages.INVALID_ID).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).entity(new ProjectDTO(project)).build();
    }

    public Response createProject(ProjectDTO projectDTO, Principal principal) {
        String invalidRequestBody = validateProjectDTO(projectDTO);

        if(!StringUtils.isNullOrEmpty(invalidRequestBody)) {
            throw new WebApplicationException(invalidRequestBody, Response.Status.BAD_REQUEST);
        }

        if(principal == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ProjectErrorMessages.INVALID_USER).build();
        }

        Project project = new Project(projectDTO.getName(), projectDTO.getDescription());
        project.setMinCapacity(Integer.parseInt(projectDTO.getMinCapacity()));
        project.setMaxCapacity(Integer.parseInt(projectDTO.getMaxCapacity()));
        project.setSupervisor(professorRepository.findByUserName(principal.getName()));


        project.setProgramsAllowed(projectDTO.getProgramsAllowed()
                .stream().map(program -> { return Program.getProgram(program);}).collect(Collectors.toSet()));

        Project projectFromDB = projectRepository.save(project);

        return Response.status(Response.Status.OK).entity(new ProjectDTO(projectFromDB)).build();
    }

    public Response applyForProject(ProjectDTO projectDTO, Principal principal) {

        if(projectDTO == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ProjectErrorMessages.EMPTY_PROJECT_INFO).build();
        }

        Project project = getProjectFromId(Long.toString(projectDTO.getId()));
        validatePrincipal(principal);

        Student student = studentRepository.findByUserName(principal.getName());

        if(student == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ProjectErrorMessages.INVALID_USER).build();
        }

        student.applyForProject(project);
        Student studentFromDb = studentRepository.save(student);

        return  Response.status(Response.Status.OK).entity(new StudentDTO(studentFromDb)).build();
    }

    private String validateProjectDTO(ProjectDTO projectDTO) {
        if(projectDTO == null || StringUtils.isNullOrEmpty(projectDTO.getName())) {
            return ProjectErrorMessages.NO_NAME;
        } else if(projectRepository.findProjectByName(projectDTO.getName()) != null) {
            return ProjectErrorMessages.INVALID_NAME;
        } else if(StringUtils.isNullOrEmpty(projectDTO.getDescription())) {
            return ProjectErrorMessages.NO_DESCRIPTION;
        } else if(!StringUtils.isStrictlyNumeric(projectDTO.getMinCapacity())) {
            return ProjectErrorMessages.INVALID_MIN_CAPACITY;
        } else if(!StringUtils.isStrictlyNumeric(projectDTO.getMaxCapacity())) {
            return ProjectErrorMessages.INVALID_MAX_CAPACITY;
        } else if(Integer.parseInt(projectDTO.getMaxCapacity()) <= Integer.parseInt(projectDTO.getMinCapacity())) {
            return ProjectErrorMessages.INVALID_CAPACITY;
        }

        return "";
    }

    private Project getProjectFromId(String id) {
        if(!StringUtils.isStrictlyNumeric(id)) {
            throw new WebApplicationException(ProjectErrorMessages.INVALID_ID, Response.Status.BAD_REQUEST);
        }

        Project project = projectRepository.findProjectById(Long.parseLong(id));

        if(project == null) {
            throw new WebApplicationException(ProjectErrorMessages.INVALID_ID, Response.Status.BAD_REQUEST);
        }

        return project;
    }

    private void validatePrincipal(Principal principal) {
        if(principal == null) {
            throw new WebApplicationException(ProjectErrorMessages.INVALID_USER, Response.Status.BAD_REQUEST);
        }
    }
}

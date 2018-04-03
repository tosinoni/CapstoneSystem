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
        Project project = getProjectFromId(projectDTO);
        validatePrincipal(principal);

        Student student = getStudentFromUsername(principal.getName());

        project.addStudentToAppliedList(student);
        Project projectFromDb = projectRepository.save(project);

        return  Response.status(Response.Status.OK).entity(new ProjectDTO(projectFromDb)).build();
    }

    public Response cancelApplicationForProject(ProjectDTO projectDTO, Principal principal) {
        Project project = getProjectFromId(projectDTO);
        validatePrincipal(principal);

        Student student = getStudentFromUsername(principal.getName());

        project.removeStudentFromAppliedList(student);
        Project projectFromDb = projectRepository.save(project);

        return  Response.status(Response.Status.OK).entity(new ProjectDTO(projectFromDb)).build();
    }

    public Response addStudents(ProjectDTO projectDTO){
        if(projectDTO == null) {
            throw new WebApplicationException(ProjectErrorMessages.EMPTY_PROJECT_INFO, Response.Status.BAD_REQUEST);
        }

        Project project = getProjectFromId(projectDTO);

        String error = validateProjectDTOForAddStudents(projectDTO, project);
        if (!StringUtils.isNullOrEmpty(error)) {
            throw new WebApplicationException(error, Response.Status.BAD_REQUEST);
        }

        Set<StudentDTO> projectMembers = projectDTO.getMembers().stream().map(studentDTO -> {
            Student student = studentRepository.findByUserName(studentDTO.getUsername());
            student.setProject(project);
            Student studentFromDb = studentRepository.save(student);
            return new StudentDTO(studentFromDb);
        }).collect(Collectors.toSet());

        return  Response.status(Response.Status.OK).entity(projectMembers).build();
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

    private String validateProjectDTOForAddStudents(ProjectDTO projectDTO, Project project) {

        if(projectDTO.getMembers() == null ||
                projectDTO.getMembers().size() < project.getMinCapacity()) {
            return String.format(ProjectErrorMessages.INVALID_MIN_CAPACITY_ADD_STUDENTS, project.getMinCapacity());
        } else if(projectDTO.getMembers().size() > project.getMaxCapacity()) {
            return String.format(ProjectErrorMessages.INVALID_MAX_CAPACITY_ADD_STUDENTS, project.getMaxCapacity());
        }

        for (StudentDTO studentDTO : projectDTO.getMembers()) {
            String errorFromValidateStudentDTO = validateStudentDTOForAddStudents(studentDTO, project);

            if(!StringUtils.isNullOrEmpty(errorFromValidateStudentDTO)) {
                return errorFromValidateStudentDTO;
            }
        }
        return "";
    }

    private String validateStudentDTOForAddStudents(StudentDTO studentDTO, Project project) {
        if (studentDTO == null) {
            return ProjectErrorMessages.EMPTY_STUDENT_FOR_ADD_STUDENTS;
        }

        Student student = studentRepository.findByUserName(studentDTO.getUsername());
        if(student == null) {
            return ProjectErrorMessages.INVALID_STUDENT_PROVIDED_FOR_ADD_STUDENTS;
        } else if (!project.getAppliedStudents().contains(student)) {
            return String.format(ProjectErrorMessages.STUDENT_NOT_APPLIED_FOR_PROJECT, student.getUserName());
        } else if(student.getProject() != null && !student.getProject().equals(project)) {
            return String.format(ProjectErrorMessages.STUDENT_REGISTERED_FOR_PROJECT_ALREADY, student.getUserName());
        }

        return "";
    }


    private Project getProjectFromId(ProjectDTO projectDTO) {
        if(projectDTO == null) {
            throw new WebApplicationException(ProjectErrorMessages.EMPTY_PROJECT_INFO, Response.Status.BAD_REQUEST);
        }

        return getProjectFromId(projectDTO.getId());
    }

    private Student getStudentFromUsername(String username) {
        Student student = studentRepository.findByUserName(username);

        if(student == null) {
            throw new WebApplicationException(ProjectErrorMessages.INVALID_USER, Response.Status.BAD_REQUEST);
        }

        return student;
    }

    private Project getProjectFromId(long id) {
        Project project = projectRepository.findProjectById(id);

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

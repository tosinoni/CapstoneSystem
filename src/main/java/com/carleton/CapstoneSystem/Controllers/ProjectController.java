package com.carleton.CapstoneSystem.Controllers;

import com.carleton.CapstoneSystem.DTO.ProjectDTO;
import com.carleton.CapstoneSystem.models.Project;
import com.carleton.CapstoneSystem.repositories.ProjectRepository;
import com.carleton.CapstoneSystem.utils.ProjectErrorMessages;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.LinkedHashSet;
import java.util.Set;

@Controller
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    public Response getAllProjects() {
        Set<ProjectDTO> allProjects = new LinkedHashSet<>();
        projectRepository.findAll().forEach(project -> allProjects.add(new ProjectDTO(project)));

        return Response.status(Response.Status.OK).entity(allProjects).build();
    }

    public Response createProject(ProjectDTO projectDTO) {
        String invalidRequestBody = validateProjectDTO(projectDTO);

        if(!StringUtils.isNullOrEmpty(invalidRequestBody)) {
            throw new WebApplicationException(invalidRequestBody, Response.Status.BAD_REQUEST);
        }

        Project project = new Project(projectDTO.getName(), projectDTO.getDescription());
        project.setMinCapacity(Integer.parseInt(projectDTO.getMinCapacity()));
        project.setMaxCapacity(Integer.parseInt(projectDTO.getMaxCapacity()));

        Project projectFromDB = projectRepository.save(project);

        return Response.status(Response.Status.OK).entity(new ProjectDTO(projectFromDB)).build();
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
        }

        return "";
    }
}

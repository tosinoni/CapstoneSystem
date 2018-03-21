package com.carleton.CapstoneSystem.restfulControllers;

import com.carleton.CapstoneSystem.Controllers.ProjectController;
import com.carleton.CapstoneSystem.DTO.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/project")
public class ProjectResource {

    @Autowired
    ProjectController projectController;

    @GetMapping
    public Response getAllProjects(){
        return projectController.getAllProjects();
    }

    @PostMapping
    public Response createProject(@RequestBody ProjectDTO projectDTO) {

        return projectController.createProject(projectDTO);
    }
}

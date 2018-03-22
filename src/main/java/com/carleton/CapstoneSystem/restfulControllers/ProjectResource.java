package com.carleton.CapstoneSystem.restfulControllers;

import com.carleton.CapstoneSystem.Controllers.ProjectController;
import com.carleton.CapstoneSystem.DTO.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.security.Principal;

@RestController
@RequestMapping("/api/projects")
public class ProjectResource {

    @Autowired
    ProjectController projectController;

    @GetMapping
    public Response getAllProjects(){
        return projectController.getAllProjects();
    }

    @PostMapping
    public Response createProject(Principal principal, @RequestBody ProjectDTO projectDTO) {

        return projectController.createProject(projectDTO, principal);
    }

    @PostMapping("/apply/{id}")
    public Response applyForProject(Principal principal, @PathVariable("id") String id) {

        return projectController.applyForProject(id, principal);
    }

    @GetMapping("/{id}")
    public Response getProjectById(@PathVariable("id") String id){
        return projectController.getProjectById(id);
    }
}

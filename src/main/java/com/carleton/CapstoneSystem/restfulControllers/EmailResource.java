package com.carleton.CapstoneSystem.restfulControllers;

import com.carleton.CapstoneSystem.Controllers.CoordinatorController;
import com.carleton.CapstoneSystem.DTO.EmailDTO;
import com.carleton.CapstoneSystem.DTO.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;
import java.security.Principal;

@RestController
@RequestMapping("/api/email")
public class EmailResource {

    @Autowired
    CoordinatorController coordinatorController;

    @PostMapping
    public Response createProject(Principal principal, @RequestBody EmailDTO emailDTO) {

        return coordinatorController.sendEmails(emailDTO,principal);
    }
}

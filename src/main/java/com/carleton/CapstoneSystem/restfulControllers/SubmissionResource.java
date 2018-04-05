package com.carleton.CapstoneSystem.restfulControllers;

import com.carleton.CapstoneSystem.Controllers.SubmissionController;
import com.carleton.CapstoneSystem.DTO.SubmissionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionResource {

    @Autowired
    SubmissionController submissionController;

    @PostMapping("/addDeliverable")
    public Response addDeliverable(@RequestBody SubmissionDTO submissionDTO) {

        return submissionController.addDeliverable(submissionDTO);
    }

    @GetMapping("/project/{id}")
    public Response getAllSubmissionsForProject(@PathVariable("id") long projectId){
        return submissionController.getAllSubmissionsForProject(projectId);
    }
}

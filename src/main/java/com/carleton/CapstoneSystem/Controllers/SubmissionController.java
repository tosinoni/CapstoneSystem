package com.carleton.CapstoneSystem.Controllers;

import com.carleton.CapstoneSystem.DTO.ProjectDTO;
import com.carleton.CapstoneSystem.DTO.SubmissionDTO;
import com.carleton.CapstoneSystem.models.Project;
import com.carleton.CapstoneSystem.models.Submission;
import com.carleton.CapstoneSystem.repositories.ProjectRepository;
import com.carleton.CapstoneSystem.repositories.SubmissionRepository;
import com.carleton.CapstoneSystem.utils.ProjectErrorMessages;
import com.carleton.CapstoneSystem.utils.SubmissionErrorMessages;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class SubmissionController {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Response addDeliverable(SubmissionDTO submissionDTO){
        String invalidRequestBody = validateSubmissionDTO(submissionDTO);

        if(!StringUtils.isNullOrEmpty(invalidRequestBody)) {
            throw new WebApplicationException(invalidRequestBody, Response.Status.BAD_REQUEST);
        }

        Project project = getProjectFromDTO(submissionDTO.getProject());

        Submission submission = new Submission(submissionDTO.getName(), project, submissionDTO.getDueDate());
        Submission submissionFromDb = submissionRepository.save(submission);

        return Response.status(Response.Status.OK).entity(new SubmissionDTO(submissionFromDb)).build();
    }

    public Response getAllSubmissionsForProject(long projectId) {
        Project project = getProjectFromId(projectId);

        Set<SubmissionDTO> allSubmissions = project.getProjectSubmissions().stream()
                .map(submission -> {
                    return new SubmissionDTO(submission);
                }).collect(Collectors.toSet());

        return Response.status(Response.Status.OK).entity(allSubmissions).build();
    }

    private Project getProjectFromDTO(ProjectDTO projectDTO) {
        if(projectDTO == null) {
            throw new WebApplicationException(ProjectErrorMessages.EMPTY_PROJECT_INFO, Response.Status.BAD_REQUEST);
        }

        return getProjectFromId(projectDTO.getId());
    }

    private Project getProjectFromId(long id) {
        Project project = projectRepository.findProjectById(id);

        if(project == null) {
            throw new WebApplicationException(ProjectErrorMessages.INVALID_ID, Response.Status.BAD_REQUEST);
        }

        return project;
    }

    private String validateSubmissionDTO(SubmissionDTO submissionDTO) {
        if(submissionDTO == null || StringUtils.isNullOrEmpty(submissionDTO.getName())) {
            return SubmissionErrorMessages.EMPTY_SUBMISSIONDTO_INFO;
        } else if(submissionDTO.getDueDate() == null) {
            return SubmissionErrorMessages.NO_DATE;
        } else if(submissionDTO.getProject() == null) {
            return SubmissionErrorMessages.NO_PROJECT;
        }

        return "";
    }
}

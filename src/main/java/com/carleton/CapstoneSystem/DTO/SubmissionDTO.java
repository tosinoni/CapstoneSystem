package com.carleton.CapstoneSystem.DTO;

import com.carleton.CapstoneSystem.models.Project;
import com.carleton.CapstoneSystem.models.Submission;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class SubmissionDTO {
    @JsonProperty
    private long id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String fileName;

    @JsonProperty
    private ProjectDTO project;

    @JsonProperty
    private Date dueDate = new Date();

    @JsonProperty
    private MultipartFile file;

    @JsonProperty
    private byte[] fileInBytes;

    public SubmissionDTO() {

    }

    public SubmissionDTO(Submission submission) {
        this.id = submission.getId();
        this.name = submission.getName();
        this.dueDate = new Date(submission.getDueDate().getTime());
        this.fileName = submission.getFileName();

        if(submission.getProject() != null) {
            Project projectForSubmission = submission.getProject();

            this.project = new ProjectDTO();
            project.setId(projectForSubmission.getId());
            project.setName(projectForSubmission.getName());
        }

        this.fileInBytes = submission.getFile();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectDTO getProject() {
        return project;
    }

    public void setProject(ProjectDTO project) {
        this.project = project;
    }

    public Date getDueDate() {
        return new Date(dueDate.getTime());
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = new Date(dueDate.getTime());
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public byte[] getFileInBytes() {
        return fileInBytes;
    }

    public void setFileInBytes(byte[] fileInBytes) {
        this.fileInBytes = fileInBytes;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

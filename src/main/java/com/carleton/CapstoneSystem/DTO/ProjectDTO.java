package com.carleton.CapstoneSystem.DTO;

import com.carleton.CapstoneSystem.models.Program;
import com.carleton.CapstoneSystem.models.Project;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectDTO {

    @JsonProperty
    private long id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private ProfessorDTO supervisor;

    @JsonProperty
    private String maxCapacity;

    @JsonProperty
    private String minCapacity;

    @JsonProperty
    private boolean isArchive;

    @JsonProperty
    private Set<String> programsAllowed = new LinkedHashSet<>();

    @JsonProperty
    private Set<StudentDTO> members = new LinkedHashSet<>();

    public ProjectDTO() {

    }

    public ProjectDTO(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
        this.minCapacity = Integer.toString(project.getMinCapacity());
        this.maxCapacity = Integer.toString(project.getMaxCapacity());
        this.isArchive = project.isArchive();
        this.supervisor = new ProfessorDTO(project.getSupervisor());

        if(project.getMembers() != null) {
           this.members = project.getMembers().stream().map(student -> {
                return new StudentDTO(student);
            }).collect(Collectors.toSet());
        }

        if(project.getProgramsAllowed() != null) {
            this.programsAllowed = project.getProgramsAllowed().stream().map(program -> {
                return program.getShortcut();
            }).collect(Collectors.toSet());
        }
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProfessorDTO getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(ProfessorDTO supervisor) {
        this.supervisor = supervisor;
    }

    public String getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(String maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getMinCapacity() {
        return minCapacity;
    }

    public void setMinCapacity(String minCapacity) {
        this.minCapacity = minCapacity;
    }

    public boolean isArchive() {
        return isArchive;
    }

    public void setArchive(boolean archive) {
        isArchive = archive;
    }

    public Set<String> getProgramsAllowed() {
        return programsAllowed;
    }

    public void setProgramsAllowed(Set<String> programsAllowed) {
        this.programsAllowed = programsAllowed;
    }

    public Set<StudentDTO> getMembers() {
        return members;
    }

    public void setMembers(Set<StudentDTO> members) {
        this.members = members;
    }
}

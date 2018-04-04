package com.carleton.CapstoneSystem.DTO;

import com.carleton.CapstoneSystem.models.Program;
import com.carleton.CapstoneSystem.models.Project;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
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
    private int maxCapacity;

    @JsonProperty
    private int minCapacity;

    @JsonProperty
    private boolean isArchive;

    @JsonProperty
    private Set<String> programsAllowed = new LinkedHashSet<>();

    private Set<Program> programsAllowedInFullFrom = new HashSet<>();

    @JsonProperty
    private Set<StudentDTO> members = new LinkedHashSet<>();

    @JsonProperty
    private Set<StudentDTO> appliedStudents = new HashSet<>();

    public ProjectDTO() {

    }

    public ProjectDTO(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
        this.minCapacity = project.getMinCapacity();
        this.maxCapacity = project.getMaxCapacity();
        this.isArchive = project.isArchive();

        if(project.getSupervisor() != null) {
            ProfessorDTO professorDTO = new ProfessorDTO();
            professorDTO.setId(project.getSupervisor().getId());
            professorDTO.setUsername(project.getSupervisor().getUserName());
            professorDTO.setFirstname(project.getSupervisor().getFirstName());
            professorDTO.setLastname(project.getSupervisor().getLastName());

            this.supervisor = professorDTO;
        }

        if(project.getMembers() != null) {
           this.members = project.getMembers().stream().map(student -> {
               StudentDTO studentDTO = new StudentDTO();
               studentDTO.setId(student.getId());
               studentDTO.setUsername(student.getUserName());
               studentDTO.setFirstname(student.getFirstName());
               studentDTO.setLastname(student.getLastName());
                return studentDTO;
            }).collect(Collectors.toSet());
        }

        if(project.getProgramsAllowed() != null) {
            this.programsAllowed = project.getProgramsAllowed().stream().map(program -> {
                return program.getShortcut();
            }).collect(Collectors.toSet());

            this.programsAllowedInFullFrom = project.getProgramsAllowed();
        }

        if(project.getAppliedStudents() != null) {
            this.appliedStudents = project.getAppliedStudents().stream().map(student -> {
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setId(student.getId());
                studentDTO.setUsername(student.getUserName());
                studentDTO.setFirstname(student.getFirstName());
                studentDTO.setLastname(student.getLastName());
                return studentDTO;
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

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getMinCapacity() {
        return minCapacity;
    }

    public void setMinCapacity(int minCapacity) {
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

    public Set<StudentDTO> getAppliedStudents() {
        return appliedStudents;
    }

    public void setAppliedStudents(Set<StudentDTO> appliedStudents) {
        this.appliedStudents = appliedStudents;
    }

    public Set<Program> getProgramsAllowedInFullFrom() {
        return programsAllowedInFullFrom;
    }

    public void setProgramsAllowedInFullFrom(Set<Program> programsAllowedInFullFrom) {
        this.programsAllowedInFullFrom = programsAllowedInFullFrom;
    }
}

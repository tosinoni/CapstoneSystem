package com.carleton.CapstoneSystem.DTO;

import com.carleton.CapstoneSystem.models.Professor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ProfessorDTO extends UserDTO{

    @JsonProperty
    private Set<ProjectDTO> projectsSupervised = new LinkedHashSet<>();

    public ProfessorDTO() {

    }

    public ProfessorDTO(Professor professor) {
        super(professor);

        if(professor != null) {
            if (professor.getProjectsSupervised() != null) {
                this.projectsSupervised = professor.getProjectsSupervised().stream().map(project -> {
                    return new ProjectDTO(project);
                }).collect(Collectors.toSet());
            }
        }
    }
}

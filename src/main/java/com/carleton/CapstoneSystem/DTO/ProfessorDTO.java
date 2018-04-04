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
                this.projectsSupervised = professor.getProjectsSupervised().stream()
                        .filter(project -> !project.isArchive()
                                || (project.isArchive() && project.getSupervisor().equals(professor)))
                        .map(project -> {
                            ProjectDTO projectDTO = new ProjectDTO();
                            projectDTO.setId(project.getId());
                            projectDTO.setName(project.getName());
                            projectDTO.setDescription(project.getDescription());
                            projectDTO.setMinCapacity(project.getMinCapacity());
                            projectDTO.setMaxCapacity(project.getMaxCapacity());

                            ProfessorDTO professorDTO = new ProfessorDTO();
                            professorDTO.setFirstname(this.getFirstname());
                            professorDTO.setLastname(this.getLastname());

                            projectDTO.setSupervisor(professorDTO);

                            projectDTO.setProgramsAllowed(project.getProgramsAllowed().stream().map(program -> {
                                return program.getShortcut();
                            }).collect(Collectors.toSet()));

                            projectDTO.setArchive(project.isArchive());
                            return projectDTO;
                        }).collect(Collectors.toSet());
            }
        }
    }

    public Set<ProjectDTO> getProjectsSupervised() {
        return projectsSupervised;
    }

    public void setProjectsSupervised(Set<ProjectDTO> projectsSupervised) {
        this.projectsSupervised = projectsSupervised;
    }
}

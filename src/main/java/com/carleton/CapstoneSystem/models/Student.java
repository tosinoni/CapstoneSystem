package com.carleton.CapstoneSystem.models;



import com.carleton.CapstoneSystem.DTO.UserDTO;
import javax.persistence.*;
import java.util.*;

@Entity
public class Student extends WebUser{
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = true)
    private Project project;

    @Column(name="program",nullable=true)
    private Program program;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Project> appliedProjects = new LinkedHashSet<Project>();




    public Student(){

    }

    public Student(UserDTO userDTO) {
        super(userDTO);
        this.program = Program.getProgram(userDTO.getProgram());
    }
    /**
     *
     * @return the program of this student
     */
    public Program getProgram() {
        return program;
    }

    /**
     *
     * @param program to be set as the program of this student
     */
    public void setProgram(Program program) {
        this.program = program;
    }



    /**
     *
     * @return the project of this student
     */
    public Project getProject() {
        return project;
    }

    /**
     *
     * @param project to set the project of this student
     */
    public void setProject(Project project) {
        this.project = project;
    }

    public void applyForProject(Project project){
        appliedProjects.add(project);
    }
    public void removeAppliedProject(Project project){
        appliedProjects.remove(project);
    }
    public boolean appliedForProject(Project project){
        return appliedProjects.contains(project);
    }

    public Set<Project> getAppliedProjects() {
        return appliedProjects;
    }

    public void setAppliedProjects(Set<Project> appliedProjects) {
        this.appliedProjects = appliedProjects;
    }

    @Override
    public boolean equals(Object o){

        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, userName, email, password, identifier, role, identifier,program);
    }
}

package com.carleton.CapstoneSystem.models;



import org.springframework.security.access.method.P;

import javax.persistence.*;
import java.util.*;

@Entity
@Inheritance
public class Student extends WebUser{
    @OneToOne(fetch = FetchType.EAGER)
    private Project project;

    @Column(name="program",nullable=true)
    private Program program;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Project> appliedProjects;




    public Student(){
        appliedProjects = new LinkedHashSet<Project>();
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

    @Override
    public boolean equals(Object o){

        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, userName, email, password, identifier, role, identifier,program);
    }


}

package com.carleton.CapstoneSystem.models;



import javax.persistence.*;
import java.util.ArrayList;

import java.util.List;
import java.util.Set;

@Entity
@Inheritance
public class Student extends WebUser{
    @OneToOne(fetch = FetchType.EAGER)
    private Project project;

    @Column(name="program",nullable=true)
    private Program program;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Project> appliedProjects;

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

    @Override
    public boolean equals(Object o){

        return super.equals(o);
    }




}

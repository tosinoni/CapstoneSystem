package com.carleton.CapstoneSystem.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.rmi.PortableRemoteObject;
import java.util.*;

@Entity
@Inheritance
public class Professor extends WebUser {
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Project> projectsSupervised;


    public Professor(){
        projectsSupervised= new HashSet<Project>();

    }


    /**
     *
     * @param p project to be added to the collection of projects this prof has
     */
    public void addProject(Project p){
        projectsSupervised.add(p);
    }

    /**
     *
     * @param p the project to be removed for the collection of projects this prof supervises
     */
    public void deleteProject(Project p){
        projectsSupervised.remove(p);
    }
    public void archiveProject(Project p){
        p.setArchive(true);
    }

}

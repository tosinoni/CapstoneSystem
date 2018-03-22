package com.carleton.CapstoneSystem.models;


import javax.persistence.*;
import java.util.*;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique = true,name="id",nullable = false)
    private long id;
    @Lob
    @Column(nullable = false)
    private String description;
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supervisor_id", nullable = false)
    private Professor supervisor;
    @Column(nullable = false,name="max_Capacity")
    private int maxCapacity;
    @Column(nullable = false,name="min_Capacity")
    private int minCapacity;
    @Column(nullable = false)
    private boolean archive;
    @ElementCollection(targetClass=Program.class)
    @Enumerated(EnumType.STRING)
    private Set<Program> programsAllowed;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private Set<Student> members;

    public Project(){
        this(null,null);
    }
    public Project(String name,String description ){

        setName(name);
        setDescription(description);
        members= new LinkedHashSet<Student>();
        programsAllowed= new LinkedHashSet<Program>();
    }

    /**
     *
     * @param program the program to be add to restricted programs.
     */
    public void addProgramsAllowed(Program program){
        programsAllowed.add(program);
    }
    /**
     *
     * @return whether this project is in archive or not
     */
    public boolean isArchive() {
        return archive;
    }
    /**
     *
     * @param archive a boolean to set a project to be in archive or remove it from archive
     */
    public void setArchive(boolean archive) {
        this.archive = archive;
    }
    /**
     *
     * @return the id of the project
     */
    public long getId() {
        return id;
    }
    /**
     *
     * @param id to be set for the id of the project
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     *
     * @return the description of the project
     */
    public String getDescription() {
        return description;
    }
    /**
     *
     * @param description to be set for the description of the project
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     *
     * @return the supervisor of this project
     */
    public Professor getSupervisor() {
        return supervisor;
    }
    /**
     *
     * @param supervisor to be set as the supervisor of this project
     */
    public void setSupervisor(Professor supervisor) {
        this.supervisor = supervisor;
    }
    /**
     *
     * @return the maximum number of students for this project
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }
    /**
     *
     * @param maxCapacity to set the maximum number of students that could take this project
     */
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    /**
     *
     * @param program to be removed from restricted programs;
     */
    public void removeProgramRestricted(Program program) {

        programsAllowed.remove(program);
    }
    public int getMinCapacity() {
        return minCapacity;
    }
    public void setMinCapacity(int minCapacity) {
        this.minCapacity = minCapacity;
    }
    public void addMember(Student s){
        members.add(s);
    }
    public void removeMember(Student s){
        members.remove(s);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Program> getProgramsAllowed() {
        return programsAllowed;
    }

    public void setProgramsAllowed(Set<Program> programsAllowed) {
        this.programsAllowed = programsAllowed;
    }

    public Set<Student> getMembers() {
        return members;
    }

    public void setMembers(Set<Student> members) {
        this.members = members;
    }

    public boolean containMember(Student student) {
        return members.contains(student);
    }
}


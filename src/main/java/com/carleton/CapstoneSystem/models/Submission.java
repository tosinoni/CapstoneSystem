package com.carleton.CapstoneSystem.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Submission {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique = true, name="id", nullable = false)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(name="file_name", nullable = false)
    private String fileName;

    @Column
    @Lob
    private byte[] file;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(name = "due_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;

    public Submission() {

    }

    public Submission(String name, Project project, Date dueDate) {
        this.name = name;
        this.project = project;
        this.dueDate = new Date(dueDate.getTime());
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

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Date getDueDate() {
        return new Date(dueDate.getTime());
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = new Date(dueDate.getTime());
    }

    @Override
    public boolean equals(Object s) {
        if (this == s) return true;
        if (!(s instanceof Submission)) return false;
        Submission submission = (Submission) s;
        return id == submission.id && name.equals(submission.name) && project.equals(submission.project) &&
                file.equals(submission.file) && dueDate.equals(submission.dueDate) && fileName.equals(submission.fileName);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, project, file, dueDate, fileName);
    }

}

package com.carleton.CapstoneSystem.repositories;

import com.carleton.CapstoneSystem.models.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.method.P;

import java.util.ArrayList;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    Project findProjectByName(String name);
    Project findProjectById(long id);
    ArrayList<Project> findAll();
}

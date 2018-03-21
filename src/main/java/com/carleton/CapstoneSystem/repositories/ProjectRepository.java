package com.carleton.CapstoneSystem.repositories;

import com.carleton.CapstoneSystem.models.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    Project findProjectByName(String name);
}

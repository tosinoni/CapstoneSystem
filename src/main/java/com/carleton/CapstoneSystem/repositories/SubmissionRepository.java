package com.carleton.CapstoneSystem.repositories;

import com.carleton.CapstoneSystem.models.Submission;
import org.springframework.data.repository.CrudRepository;

public interface SubmissionRepository extends CrudRepository<Submission, Long> {
    Submission findSubmissionByName(String name);
}

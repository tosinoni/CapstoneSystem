package com.carleton.CapstoneSystem.repositories;

import com.carleton.CapstoneSystem.models.Professor;

import javax.transaction.Transactional;

@Transactional
public interface ProfessorRepository extends UserRepository<Professor> {
    Professor findByProjectsSupervisedId(long id);
}

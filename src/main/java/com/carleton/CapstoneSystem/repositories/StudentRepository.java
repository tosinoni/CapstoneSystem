package com.carleton.CapstoneSystem.repositories;

import com.carleton.CapstoneSystem.models.Student;

import javax.transaction.Transactional;

@Transactional
public interface StudentRepository extends UserRepository<Student>{
}

package com.carleton.CapstoneSystem.repositories;

import com.carleton.CapstoneSystem.models.Student;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
public interface StudentRepository extends UserRepository<Student>{
    ArrayList<Student> findAll();
    ArrayList<Student> findAllByProjectIsNotNull();
    ArrayList<Student> findAllByProjectIsNull();
}

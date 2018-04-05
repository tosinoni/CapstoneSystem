package com.carleton.CapstoneSystem.repositories;

import com.carleton.CapstoneSystem.models.Coordinator;

import javax.transaction.Transactional;

@Transactional
public interface CoordinatorRepository extends UserRepository<Coordinator> {

}

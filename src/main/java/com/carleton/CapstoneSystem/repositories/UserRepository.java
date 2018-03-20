package com.carleton.CapstoneSystem.repositories;

import com.carleton.CapstoneSystem.models.WebUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository
        extends CrudRepository<WebUser, Long> {

    WebUser findByEmail(String email);

    WebUser findByUserName(String userName);

    WebUser findByIdentifier(long identifier);
}
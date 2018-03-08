package com.carleton.CapstoneSystem.repositories;

import com.carleton.CapstoneSystem.models.WebUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository
        extends CrudRepository<WebUser, Long> {

    public WebUser findByEmail(String email);

    public WebUser findByUserName(String userName);

}
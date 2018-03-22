package com.carleton.CapstoneSystem.repositories;

import com.carleton.CapstoneSystem.models.WebUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserRepository<T extends WebUser>
        extends CrudRepository<T, Long> {

    T findByEmail(String email);

    T findByUserName(String userName);

    T findByIdentifier(long identifier);
}
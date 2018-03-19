package com.carleton.CapstoneSystem.auth;

import com.carleton.CapstoneSystem.models.WebUser;
import com.carleton.CapstoneSystem.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        WebUser webUser = userRepository.findByUserName(username);
        if (webUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(webUser.getUserName(), webUser.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority(webUser.getRole().toString())));
    }
}

package com.carleton.CapstoneSystem.auth;

import com.carleton.CapstoneSystem.models.Coordinator;
import com.carleton.CapstoneSystem.models.Professor;
import com.carleton.CapstoneSystem.models.Student;
import com.carleton.CapstoneSystem.models.WebUser;
import com.carleton.CapstoneSystem.repositories.CoordinatorRepository;
import com.carleton.CapstoneSystem.repositories.ProfessorRepository;
import com.carleton.CapstoneSystem.repositories.StudentRepository;
import com.carleton.CapstoneSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    CoordinatorRepository coordinatorRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByUserName(username);
        Professor professor = professorRepository.findByUserName(username);
        Coordinator coordinator =coordinatorRepository.findByUserName(username);
        WebUser webUser=null;
        if(student!=null){
            webUser=student;

        }else if (professor!=null){
            webUser=professor;

        }else{

            webUser=coordinator;
        }
        if (webUser==null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(webUser.getUserName(), webUser.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority(webUser.getRole().toString())));
    }

}

package com.carleton.CapstoneSystem.Controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class ProfessorController extends SignUpLogInController{

    public ProfessorController(BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(bCryptPasswordEncoder);
    }
}

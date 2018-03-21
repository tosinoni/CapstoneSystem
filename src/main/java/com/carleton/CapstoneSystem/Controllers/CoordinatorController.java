package com.carleton.CapstoneSystem.Controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class CoordinatorController extends SignUpLogInController {
    public CoordinatorController(BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(bCryptPasswordEncoder);
    }
}

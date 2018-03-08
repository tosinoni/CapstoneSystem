package com.carleton.CapstoneSystem.restfulControllers;

import com.carleton.CapstoneSystem.Controllers.SignUpLogInController;
import com.carleton.CapstoneSystem.models.WebUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {



    @Autowired
    SignUpLogInController signUpLogInController;





    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody WebUser user) {
        return signUpLogInController.signUp(user);
    }

    @PostMapping("/login")
    public ResponseEntity logIn(@RequestBody WebUser user){
            return signUpLogInController.logIn(user);

    }
}

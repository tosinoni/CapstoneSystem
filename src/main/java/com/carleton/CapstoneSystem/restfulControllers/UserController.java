package com.carleton.CapstoneSystem.restfulControllers;

import com.carleton.CapstoneSystem.Controllers.SignUpLogInController;
import com.carleton.CapstoneSystem.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController {



    @Autowired
    SignUpLogInController signUpLogInController;





    @PostMapping("/sign-up")
    public Response signUp(@RequestBody UserDTO user) {

        return signUpLogInController.signUp(user);
    }

    @PostMapping("/login")
    public Response logIn(@RequestBody UserDTO user){
            return signUpLogInController.logIn(user);

    }

    @GetMapping("/currentUser")
    public Response getCurrentUser(Principal principal ) {
        return signUpLogInController.getCurrentUser(principal);
    }
}

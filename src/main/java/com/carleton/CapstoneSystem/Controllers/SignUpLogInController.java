package com.carleton.CapstoneSystem.Controllers;

import com.carleton.CapstoneSystem.DTO.UserDTO;
import com.carleton.CapstoneSystem.auth.JWTAuthenticationFilter;
import com.carleton.CapstoneSystem.models.Role;
import com.carleton.CapstoneSystem.models.WebUser;
import com.carleton.CapstoneSystem.repositories.UserRepository;
import com.carleton.CapstoneSystem.utils.RequestErrorMessages;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import java.util.regex.Pattern;

@Controller
public class SignUpLogInController {

    @Autowired
    UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;



    public SignUpLogInController(BCryptPasswordEncoder bCryptPasswordEncoder){
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }
    /**
     *
     * @param user the user that must be authenticated
     * @return a response to whethere the login is successful or no.
     */
    public ResponseEntity logIn(WebUser user){
        String invalidRequestBody =validateUserLogIn(user);
        if(!invalidRequestBody.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidRequestBody);
        }
        String invalidContent =validateUserContent(user);

         if (!invalidContent.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidContent);
         }

         UserDTO responseUser = new UserDTO(userRepository.findByUserName(user.getUserName()));
         responseUser.setToken(JWTAuthenticationFilter.getToken(responseUser.getUsername()));

         return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    /**
     *
     * @param user to be validate upon loging in
     * @return a descriptive string of the error message that could be caused by the input
     */
    private String validateUserLogIn(WebUser user){
        String returnMessage="";
        if(user==null){
            returnMessage=RequestErrorMessages.NO_USER;

        }else if(user.getUserName()==null){
            returnMessage=RequestErrorMessages.NO_USERNAME;
        }else if( user.getPassword()==null){
            returnMessage=RequestErrorMessages.NO_PASSWORD;
        }
        return returnMessage;
    }

    /**
     *
     * @param user which contents are to be validated
     * @return a descriptive string of the error message that could be caused by the input
     */
    private String validateUserContent(WebUser user){
        WebUser userdb=userRepository.findByUserName(user.getUserName());
            String returnMessage="";
        if(userdb==null){
            returnMessage= RequestErrorMessages.INVALID_USERNAME;
        }else if (!bCryptPasswordEncoder.matches(user.getPassword(),userdb.getPassword())) {
            returnMessage= RequestErrorMessages.INCORRECT_PASSWORD;
        }
        return returnMessage;
    }

    /**
     *
     * @param user to be validate upon siging up
     * @return a descriptive string of the error message that could be caused by the input
     */
    public ResponseEntity signUp(WebUser user){
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.OK);
        String invalidRequestBody = validateUserLogIn(user);
        if(!invalidRequestBody.isEmpty()){
            responseEntity=ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidRequestBody);
            return responseEntity;
        }

        String invalidContent =validateSignUpInput(user);

        if (!invalidContent.isEmpty()){
            responseEntity=ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidContent);
            return responseEntity;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return responseEntity;

    }

    /**
     *
     * @param user to be validate upon signing up
     * @return a descriptive message if there was an error or not
     */
    private String validateSignUpInput(WebUser user) {
        String returnMessage="";
        if (!isEmailValid(user.getEmail())) {
            returnMessage= RequestErrorMessages.INVALID_EMAIL;
        } else if(user.getRole() == null || !Role.contains(user.getRole())) {
            returnMessage= RequestErrorMessages.INVALID_ROLE;
        } else if (userRepository.findByUserName(user.getUserName()) != null){
            returnMessage = RequestErrorMessages.DUPLICATE_USERNAME;
        } else if (userRepository.findByEmail(user.getEmail()) != null){
            returnMessage = RequestErrorMessages.DUPLICATE_EMAIL;
        } else if (StringUtils.isNullOrEmpty(user.getFirstName())) {
            returnMessage = RequestErrorMessages.NO_FIRST_NAME;
        } else if (StringUtils.isNullOrEmpty(user.getLastName())) {
            returnMessage = RequestErrorMessages.NO_LAST_NAME;
        } else if (userRepository.findByIdentifier(user.getIdentifier()) != null){
            returnMessage = RequestErrorMessages.NO_IDENTIFIER;
        }

        return returnMessage;

    }

    /**
     *
     * @param email to validate
     * @return true if the email is valid, false otherwise
     */
    public  boolean isEmailValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if(email==null){
            return false;
        }
        return pat.matcher(email).matches();
    }
}

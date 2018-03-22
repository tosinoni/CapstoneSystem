package com.carleton.CapstoneSystem.controllers;

import com.carleton.CapstoneSystem.DTO.EmailDTO;
import com.carleton.CapstoneSystem.DTO.UserDTO;
import com.carleton.CapstoneSystem.models.Program;
import com.carleton.CapstoneSystem.models.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import java.security.Principal;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class TestEmailSending {
    private MockHttpServletRequestBuilder request;
    private ObjectMapper mapper;
    private ObjectWriter ow;

    @Autowired
    private MockMvc mockMvc;
    UserDTO user;
    UserDTO user2;
    UserDTO user3;
    UserDTO user4;

    @Before
    @Rollback(true)
    public void init() throws Exception{

        request = post("/users/sign-up");
        user=createUser("alifawaz@cmail.carleton.ca",12345,"fozi",Role.STUDENT);
        user2=createUser("alialsaaidi@cmail.carleton.ca",55555,"lozi",Role.STUDENT);
        user3=createUser("mohameddahrouj@cmail.carleton.ca",3333,"mozi",Role.STUDENT);
        user4=createUser("tosioni@cmail.carleton.ca",6666,"nozi",Role.STUDENT);







    }
    private UserDTO createUser(String email,long identification,String userName,Role role){
        UserDTO user = new UserDTO();
        user.setEmail(email);
        user.setFirstname("ali");
        user.setLastname("fawaz");
        user.setIdentifier(identification);
        user.setUsername(userName);
        user.setRole(role);
        user.setPassword("pasword");
        if(role.equals(Role.STUDENT)){
            user.setProgram(Program.ELECTRICAL_ENGINEERING);
        }
        return user;
    }
    private void performRegister(UserDTO user,MockHttpServletRequestBuilder request) throws Exception{
        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson= null;
        try {
            requestJson = ow.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        request.contentType(APPLICATION_JSON_UTF8)
                .content(requestJson);

        mockMvc.perform(request);
    }
}

package com.carleton.CapstoneSystem.controllers;
import com.carleton.CapstoneSystem.models.Role;
import com.carleton.CapstoneSystem.models.WebUser;
import com.carleton.CapstoneSystem.restfulControllers.UserController;
import com.carleton.CapstoneSystem.utils.RequestErrorMessages;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;




@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class SignUpLogInTest {


    private MockHttpServletRequestBuilder request;
    private ObjectMapper mapper;
    private ObjectWriter ow;
    @Autowired
    private UserController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
    @Before
    public void init()  {

        request = post("/users/sign-up");
        WebUser user = new WebUser();
        user.setPassword("password");
        user.setEmail("username@cmail.com");
        user.setUserName("fozitto");
        user.setRole(Role.STUDENT);
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



    }


    @Test
    @Rollback(true)
    public void SignUpLoginWrongPasswordTest() throws Exception {


        ResultActions result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.status().isOk());

        request = get("/users/login");
        WebUser user1 = new WebUser();
        user1.setPassword("password2");
        user1.setUserName("fozitto");
        String requestJson=ow.writeValueAsString(user1);
        request.contentType(APPLICATION_JSON_UTF8)
                .content(requestJson);
        result=mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(MockMvcResultMatchers.content().string(RequestErrorMessages.INCORRECT_PASSWORD));


    }
    @Test
    @Rollback(true)
    public void SignUpLoginNoUserNameTest() throws Exception {


        ResultActions result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.status().isOk());

        request = get("/users/login");
        WebUser user1 = new WebUser();
        user1.setPassword("password2");

        String requestJson=ow.writeValueAsString(user1);
        request.contentType(APPLICATION_JSON_UTF8)
                .content(requestJson);
        result=mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(MockMvcResultMatchers.content().string(RequestErrorMessages.NO_USERNAME));


    }
    @Test
    @Rollback(true)
    public void SignUpLoginInvalidUserNameTest() throws Exception {


        ResultActions result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.status().isOk());

        request = get("/users/login");
        WebUser user1 = new WebUser();
        user1.setUserName("lollo");
        user1.setPassword("password");

        String requestJson=ow.writeValueAsString(user1);
        request.contentType(APPLICATION_JSON_UTF8)
                .content(requestJson);

        result=mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(MockMvcResultMatchers.content().string(RequestErrorMessages.INVALID_USERNAME));


    }

    @Test
    @Rollback(true)
    public void SignUpLoginInvalidEmailTest() throws Exception {





        WebUser user1 = new WebUser();
        user1.setUserName("lollo");
        user1.setPassword("password");
        user1.setEmail("ali@sd");
        user1.setRole(Role.STUDENT);

        String requestJson=ow.writeValueAsString(user1);
        request.contentType(APPLICATION_JSON_UTF8)
                .content(requestJson);

        ResultActions result = mockMvc.perform(request);;
        result.andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(MockMvcResultMatchers.content().string(RequestErrorMessages.INVALID_EMAIL));


    }


}

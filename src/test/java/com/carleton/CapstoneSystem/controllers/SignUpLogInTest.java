package com.carleton.CapstoneSystem.controllers;
import com.carleton.CapstoneSystem.DTO.UserDTO;
import com.carleton.CapstoneSystem.models.*;
import com.carleton.CapstoneSystem.restfulControllers.UserController;
import com.carleton.CapstoneSystem.utils.RequestErrorMessages;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
import javax.ws.rs.WebApplicationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
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
    private UserDTO user;
    @Autowired
    private UserController controller;

    @Autowired
    private MockMvc mockMvc;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
    @Before
    @Rollback(true)
    public void init() throws Exception {

        request = post("/users/sign-up");
        user = new UserDTO();
        user.setPassword("password");
        user.setEmail("username@cmail.com");
        user.setUsername("fozitto");
        user.setRole(Role.PROFESSOR);
        user.setIdentifier(1223443);
        user.setFirstname("ali");
        user.setLastname("hammoud");

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






    @Test
    @Rollback(true)
    public void LoginSuccessfulTest() throws Exception{


        request = post("/users/login");
        UserDTO user1 = new UserDTO();
        user1.setPassword("password");
        user1.setUsername("fozitto");
        String requestJson= null;
        try {
            requestJson = ow.writeValueAsString(user1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        request.contentType(APPLICATION_JSON_UTF8)
                .content(requestJson);

        ResultActions result=mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.status().isOk());


    }
    @Test
    @Rollback(true)
    public void WrongPasswordLogInTest() {

        request = post("/users/login");
        UserDTO user1 = new UserDTO();
        user1.setPassword("password2");
        user1.setUsername("fozitto");

        String requestJson= null;
        try {
            requestJson = ow.writeValueAsString(user1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        request.contentType(APPLICATION_JSON_UTF8)
                .content(requestJson);

        try {
            ResultActions result =mockMvc.perform(request);
        } catch (Exception e) {
            WebApplicationException webException=(WebApplicationException)e.getCause();
            assertEquals(webException.getResponse().getStatus(),400);
            assertEquals(webException.getMessage(),RequestErrorMessages.INCORRECT_PASSWORD);
        }







    }

    @Test
    @Rollback(true)
    public void LogInNoUserNameTest() throws Exception {



        request = post("/users/login");
        WebUser user1 = new WebUser();
        user1.setPassword("password2");

        String requestJson= null;
        try {
            requestJson = ow.writeValueAsString(user1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        request.contentType(APPLICATION_JSON_UTF8)
                .content(requestJson);

        try {
            ResultActions result =mockMvc.perform(request);
        } catch (Exception e) {
            WebApplicationException webException=(WebApplicationException)e.getCause();
            assertEquals(webException.getResponse().getStatus(),400);
            assertEquals(webException.getMessage(),RequestErrorMessages.NO_USERNAME);
        }



    }

    @Test
    @Rollback(true)
    public void LoginInvalidUserNameTest() throws Exception {




        request = get("/users/login");
        WebUser user1 = new WebUser();
        user1.setUserName("lollo");
        user1.setPassword("password");

        String requestJson=ow.writeValueAsString(user1);
        request.contentType(APPLICATION_JSON_UTF8)
                .content(requestJson);
        try {
            ResultActions result =mockMvc.perform(request);
        } catch (Exception e) {
            WebApplicationException webException=(WebApplicationException)e.getCause();
            assertEquals(webException.getResponse().getStatus(),400);
            assertEquals(webException.getMessage(),RequestErrorMessages.INVALID_USERNAME);
        }
    }

    @Test
    @Rollback(true)
    public void SignUpInvalidEmailTest() throws Exception {
        user.setEmail("lala");
        String requestJson=ow.writeValueAsString(user);
        request.contentType(APPLICATION_JSON_UTF8)
                .content(requestJson);
        try {
            ResultActions result =mockMvc.perform(request);
        } catch (Exception e) {
            WebApplicationException webException=(WebApplicationException)e.getCause();
            assertEquals(webException.getResponse().getStatus(),400);
            assertEquals(webException.getMessage(),RequestErrorMessages.INVALID_EMAIL);
        }




    }
    @Test
    @Rollback(true)
    public void SignUpInvalidStudentProgramTest() throws Exception {
        user.setRole(Role.STUDENT);
        String requestJson=ow.writeValueAsString(user);
        request.contentType(APPLICATION_JSON_UTF8)
                .content(requestJson);
        try {
            ResultActions result =mockMvc.perform(request);
        } catch (Exception e) {
            WebApplicationException webException=(WebApplicationException)e.getCause();
            assertEquals(webException.getResponse().getStatus(),400);
            assertEquals(webException.getMessage(),RequestErrorMessages.INVALID_PROGRAM);
        }




    }
    @Test
    @Rollback(true)
    public void SignUpInvalidFirstNameTest() throws Exception {
        user.setFirstname(null);
        String requestJson=ow.writeValueAsString(user);
        request.contentType(APPLICATION_JSON_UTF8)
                .content(requestJson);
        try {
            ResultActions result =mockMvc.perform(request);
        } catch (Exception e) {
            WebApplicationException webException=(WebApplicationException)e.getCause();
            assertEquals(webException.getResponse().getStatus(),400);
            assertEquals(webException.getMessage(),RequestErrorMessages.NO_FIRST_NAME);
        }




    }
    @Test
    @Rollback(true)
    public void SignUpInvalidLastNameTest() throws Exception {
        user.setLastname(null);
        String requestJson=ow.writeValueAsString(user);
        request.contentType(APPLICATION_JSON_UTF8)
                .content(requestJson);
        try {
            ResultActions result =mockMvc.perform(request);
        } catch (Exception e) {
            WebApplicationException webException=(WebApplicationException)e.getCause();
            assertEquals(webException.getResponse().getStatus(),400);
            assertEquals(webException.getMessage(),RequestErrorMessages.NO_LAST_NAME);
        }




    }
    @Test
    @Rollback(true)
    public void SignUpDuplicateUserNameTest() throws Exception {
        user = new UserDTO();
        user.setPassword("password");
        user.setEmail("usernam2e@cmail.com");
        user.setUsername("fozitto");
        user.setRole(Role.PROFESSOR);
        user.setIdentifier(12232443);
        user.setFirstname("ali");
        user.setLastname("hammoud");

        String requestJson = ow.writeValueAsString(user);
        request.contentType(APPLICATION_JSON_UTF8)
                .content(requestJson);
        try {
            ResultActions result = mockMvc.perform(request);
        } catch (Exception e) {
            WebApplicationException webException = (WebApplicationException) e.getCause();
            assertEquals(webException.getResponse().getStatus(), 400);
            assertEquals(webException.getMessage(), RequestErrorMessages.DUPLICATE_USERNAME);
        }
    }
        @Test
        @Rollback(true)
        public void SignUpDuplicateEmailTest() throws Exception {
            user = new UserDTO();
            user.setPassword("password2");
            user.setEmail("username@cmail.com");
            user.setUsername("fozitto3");
            user.setRole(Role.PROFESSOR);
            user.setIdentifier(12232443);
            user.setFirstname("ali");
            user.setLastname("hammoud");

            String requestJson=ow.writeValueAsString(user);
            request.contentType(APPLICATION_JSON_UTF8)
                    .content(requestJson);
            try {
                ResultActions result =mockMvc.perform(request);
            } catch (Exception e) {
                WebApplicationException webException=(WebApplicationException)e.getCause();
                assertEquals(webException.getResponse().getStatus(),400);
                assertEquals(webException.getMessage(),RequestErrorMessages.DUPLICATE_EMAIL);
            }




    }
    @Test
    @Rollback(true)
    public void SignUpDuplicateIdentifierTest() throws Exception {
        user = new UserDTO();
        user.setPassword("password");
        user.setEmail("username2@cmail.com");
        user.setUsername("fozitto3");
        user.setRole(Role.PROFESSOR);
        user.setIdentifier(1223443);
        user.setFirstname("ali");
        user.setLastname("hammoud");

        String requestJson=ow.writeValueAsString(user);
        request.contentType(APPLICATION_JSON_UTF8)
                .content(requestJson);
        try {
            ResultActions result =mockMvc.perform(request);
        } catch (Exception e) {
            WebApplicationException webException=(WebApplicationException)e.getCause();
            assertEquals(webException.getResponse().getStatus(),400);
            assertEquals(webException.getMessage(),RequestErrorMessages.NO_IDENTIFIER);
        }




    }
    @Test
    @Rollback(true)
    public void SignUpLogInCoordinatorTest() throws Exception {
        user = new UserDTO();
        user.setPassword("password");
        user.setEmail("username2@cmail.com");
        user.setUsername("fozitto4");
        user.setRole(Role.COORDINATOR);
        user.setIdentifier(12234443);
        user.setFirstname("ali");
        user.setLastname("hammoud");

        String requestJson=ow.writeValueAsString(user);
        request.contentType(APPLICATION_JSON_UTF8)
                .content(requestJson);
        ResultActions result=null;
        try {
             result =mockMvc.perform(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.andExpect(MockMvcResultMatchers.status().isOk());
        request = post("/users/login");
        Coordinator coordinator = new Coordinator(user);
        coordinator.setUserName("fozitto3");
        coordinator.setPassword("password");
        requestJson=ow.writeValueAsString(coordinator);
        request.contentType(APPLICATION_JSON_UTF8)
                .content(requestJson);
        try {
            result =mockMvc.perform(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.andExpect(MockMvcResultMatchers.status().isOk());





    }
    @Test
    @Rollback(true)
    public void SignUpLogInStudentTest() throws Exception {
        user = new UserDTO();
        user.setPassword("password");
        user.setEmail("username3@cmail.com");
        user.setUsername("fozitto5");
        user.setRole(Role.STUDENT);
        user.setIdentifier(12134443);
        user.setFirstname("ali");
        user.setLastname("hammoud");
        user.setProgram(Program.ELECTRICAL_ENGINEERING.getShortcut());

        String requestJson=ow.writeValueAsString(user);
        request.contentType(APPLICATION_JSON_UTF8)
                .content(requestJson);
        ResultActions result=null;
        try {
            result =mockMvc.perform(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.andExpect(MockMvcResultMatchers.status().isOk());
        request = post("/users/login");
        Coordinator coordinator = new Coordinator(user);
        coordinator.setUserName("fozitto5");
        coordinator.setPassword("password");
        requestJson=ow.writeValueAsString(coordinator);
        request.contentType(APPLICATION_JSON_UTF8)
                .content(requestJson);
        try {
            result =mockMvc.perform(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.andExpect(MockMvcResultMatchers.status().isOk());





    }


}

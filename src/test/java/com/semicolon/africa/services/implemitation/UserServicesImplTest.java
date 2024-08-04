package com.semicolon.africa.services.implemitation;

import com.semicolon.africa.data.models.User;
import com.semicolon.africa.data.repositiories.UserRepo;
import com.semicolon.africa.dtos.request.CreateNoteRequest;
import com.semicolon.africa.dtos.request.LoginRequest;
import com.semicolon.africa.dtos.request.RegisterUserRequest;
import com.semicolon.africa.dtos.response.AddNoteResponse;
import com.semicolon.africa.dtos.response.LoginResponse;
import com.semicolon.africa.dtos.response.RegisterUserResponse;
import com.semicolon.africa.exception.EmailExistsException;
import com.semicolon.africa.exception.PassWordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServicesImplTest {

    @Autowired
    private UserServicesImpl userServices;

    @Autowired
    private UserRepo repo;

    @BeforeEach
    void setUp() {
        repo.deleteAll();
    }

    private RegisterUserResponse createUser(){
        RegisterUserRequest request = new RegisterUserRequest();
        User user = new User();
        request.setName("Eniola");
        request.setEmail("eniola@gmail.com");
        request.setPassword("password");
        RegisterUserResponse response =  userServices.registerUserWith(request);
        return response;
    }

    @Test
    public void testThatICanRegisterUser() {
        RegisterUserResponse response  = createUser();
        assertNotNull(response);
    }

    @Test
    public void testThatICan_tRegisterWithSameEmailThrowException(){
        createUser();
        RegisterUserRequest request = new RegisterUserRequest();
        User user = new User();
        request.setName("Eniola");
        request.setEmail("eniola@gmail.com");
        request.setPassword("password");
        assertThrows(EmailExistsException.class, ()-> userServices.registerUserWith(request));
    }

    @Test
    public void testThatUserCanLogIn(){
        createUser();
        LoginResponse response = logIn();
        assertThat(response.isLoggedIn()).isEqualTo(true);
    }
    private LoginResponse logIn(){
        LoginRequest request = new LoginRequest();
        request.setEmail("eniola@gmail.com");
        request.setPassword("password");
        return userServices.login(request);
    }

    @Test
    public void testThatInputWrongEmailThrowsException(){
        createUser();
        LoginRequest request = new LoginRequest();
        request.setEmail("eniola1@gmail.com");
        request.setPassword("password");
        assertThrows(EmailExistsException.class, ()-> userServices.login(request));
    }

    @Test
    public void testThatInputWrongPasswordThrowsException(){
        createUser();
        LoginRequest request = new LoginRequest();
        request.setEmail("eniola@gmail.com");
        request.setPassword("password1");
        assertThrows(PassWordException.class, ()-> userServices.login(request));
    }

    @Test
    public void testThatUserCanCreateNote(){
        createUser();
        LoginResponse response = logIn();
        CreateNoteRequest request = new CreateNoteRequest();
        request.setTitle("I am a note");
        request.setContent("content is filled");
        request.setEmail("eniola@gmail.com");
        request.setPassword("password");
        AddNoteResponse response1 =  userServices.createNote(request);
        assertThat(response1.getNote_id()).isNotNull();
    }

}
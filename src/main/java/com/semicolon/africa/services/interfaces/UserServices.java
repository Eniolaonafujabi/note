package com.semicolon.africa.services.interfaces;

import com.semicolon.africa.data.models.User;
import com.semicolon.africa.dtos.request.*;
import com.semicolon.africa.dtos.response.*;

import java.util.List;

public interface UserServices {
    RegisterUserResponse registerUserWith(RegisterUserRequest request);

    List<User> getAllUsers();

    LoginResponse login(LoginRequest request);

    AddNoteResponse createNote(CreateNoteRequest request);

    DeleteNoteResponse deleteNote(DeleteNoteRequest request);

    LogoutResponse logout(LogoutRequest request);
}

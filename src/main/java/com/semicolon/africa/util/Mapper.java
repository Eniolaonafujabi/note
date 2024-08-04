package com.semicolon.africa.util;

import com.semicolon.africa.data.models.Note;
import com.semicolon.africa.data.models.User;
import com.semicolon.africa.dtos.request.CreateNoteRequest;
import com.semicolon.africa.dtos.request.RegisterUserRequest;
import com.semicolon.africa.dtos.response.AddNoteResponse;
import com.semicolon.africa.dtos.response.RegisterUserResponse;

import java.time.LocalDateTime;

public class Mapper  {
    public static void map (CreateNoteRequest request, Note note){
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        note.setCreatedDate(LocalDateTime.now());
    }
    public static AddNoteResponse map (Note note){
        AddNoteResponse response = new AddNoteResponse();
        response.setNote_id(note.getId());
        response.setCreatedDate(note.getCreatedDate());
        response.setNote_title(note.getTitle());
        response.setNote_content(note.getContent());
        return response;
    }
    public static void map (RegisterUserRequest request, User newUser){
        newUser.setName(request.getName());
        newUser.setPassword(request.getPassword());
        newUser.setEmail(request.getEmail());
    }
        public static RegisterUserResponse map (User newUser){
        RegisterUserResponse response = new RegisterUserResponse();
        response.setName(newUser.getName());
        response.setPassword(newUser.getPassword());
        response.setEmail(newUser.getEmail());
        response.setId(newUser.getId());
        return response;
    }
}

package com.semicolon.africa.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private boolean isLoggedIn;
    @DBRef
    private List<Note> noteList = new ArrayList<>();
}

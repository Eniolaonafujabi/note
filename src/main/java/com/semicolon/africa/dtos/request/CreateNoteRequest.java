package com.semicolon.africa.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CreateNoteRequest {
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private String email;
    private String password;
}

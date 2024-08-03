package com.semicolon.africa.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
public class AddNoteResponse {

    private String note_id;
    private String note_title;
    private String note_content;
    private LocalDateTime createdDate;
}

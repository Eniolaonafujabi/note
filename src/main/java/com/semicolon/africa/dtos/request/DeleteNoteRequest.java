package com.semicolon.africa.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteNoteRequest {
    String title;
    String userId;
}

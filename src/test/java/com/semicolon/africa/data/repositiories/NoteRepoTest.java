package com.semicolon.africa.data.repositiories;

import com.semicolon.africa.data.models.Note;
import com.semicolon.africa.dtos.request.CreateNoteRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NoteRepoTest {
    @Autowired
    private NoteRepo noteRepo;

    @Test
    public void SaveNoteTest(){
        CreateNoteRequest request = new CreateNoteRequest();
        request.setTitle("new title");
        request.setContent("new Content");
        Note note = new Note();
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        note = noteRepo.save(note);
        assertNotNull(note.getId());
        assertEquals(1, noteRepo.count());
    }

}
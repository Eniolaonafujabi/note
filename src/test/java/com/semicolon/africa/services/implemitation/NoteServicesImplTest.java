package com.semicolon.africa.services.implemitation;

import com.semicolon.africa.data.models.Note;
import com.semicolon.africa.data.repositiories.NoteRepo;
import com.semicolon.africa.dtos.request.CreateNoteRequest;
import com.semicolon.africa.dtos.request.UpdateNoteRequest;
import com.semicolon.africa.dtos.response.AddNoteResponse;
import com.semicolon.africa.dtos.response.DeleteNoteResponse;
import com.semicolon.africa.dtos.response.UpdateNoteResponse;
import com.semicolon.africa.exception.TitleExistsException;
import com.semicolon.africa.services.implemitation.NoteServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class NoteServicesImplTest {
    @Autowired
    private NoteServicesImpl noteServices;

    @Autowired
    private NoteRepo repo;

    @BeforeEach
    void setUp() {
        repo.deleteAll();
    }

    private AddNoteResponse createNote() {
        Note note = new Note();
        CreateNoteRequest request = new CreateNoteRequest();
        request.setTitle("Title");
        request.setContent("Content");
        AddNoteResponse response = noteServices.createNote(request);
        return response;
    }
    @Test
    public void testThatICanCreateNote() {
        AddNoteResponse response = createNote();
        assertThat(response).isNotNull();
        assertThat(response.getNote_id()).isNotNull();
    }

    @Test
    public void addNoteWithSameTitle_throwsExceptionTest(){
        createNote();
        CreateNoteRequest request = new CreateNoteRequest();
        request.setTitle("Title");
        request.setContent("new content");
        assertThrows(TitleExistsException.class, ()->noteServices.createNote(request));
    }

    @Test
    public void testThatUserCanDeleteNote(){
        AddNoteResponse response = createNote();
        DeleteNoteResponse response1 = noteServices.deleteNote(response.getNote_title());
        assertThat(response1.getMessage()).contains("deleted");
    }

    @Test
    public void getAllNotesTest(){
        AddNoteResponse response = createNote();
        List<Note> notes = noteServices.getAllNotes();
        assertThat(notes.size()).isEqualTo(1L);
    }

    @Test
    public void testThatUserCanEditNote(){
        AddNoteResponse response = createNote();
        UpdateNoteRequest request = new UpdateNoteRequest();
        request.setTitle("Title");
        request.setContent("New content");
        UpdateNoteResponse response1 = noteServices.updateNoteWith(request);
        assertThat(response1.getMessage()).contains("message updated");
    }
}
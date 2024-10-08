package com.semicolon.africa.services.interfaces;

import com.semicolon.africa.data.models.Note;
import com.semicolon.africa.dtos.request.CreateNoteRequest;
import com.semicolon.africa.dtos.request.UpdateNoteRequest;
import com.semicolon.africa.dtos.response.DeleteNoteResponse;
import com.semicolon.africa.dtos.response.AddNoteResponse;
import com.semicolon.africa.dtos.response.UpdateNoteResponse;

import java.util.List;
import java.util.Optional;

public interface NoteServices {
    AddNoteResponse createNote(CreateNoteRequest request);
    Long getTotalNotes();
    UpdateNoteResponse updateNoteWith(UpdateNoteRequest request);
    DeleteNoteResponse deleteNote(String title);
    List<Note> getAllNotes();
    Note findNoteByTitle(String title);
}

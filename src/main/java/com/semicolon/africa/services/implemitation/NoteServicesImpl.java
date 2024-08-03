package com.semicolon.africa.services.implemitation;

import com.semicolon.africa.data.models.Note;
import com.semicolon.africa.data.repositiories.NoteRepo;
import com.semicolon.africa.dtos.request.CreateNoteRequest;
import com.semicolon.africa.dtos.request.UpdateNoteRequest;
import com.semicolon.africa.dtos.response.DeleteNoteResponse;
import com.semicolon.africa.dtos.response.AddNoteResponse;
import com.semicolon.africa.dtos.response.UpdateNoteResponse;
import com.semicolon.africa.exception.NoteNotFoundException;
import com.semicolon.africa.exception.TitleExistsException;
import com.semicolon.africa.services.interfaces.NoteServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.semicolon.africa.util.Mapper.map;

@Service
@AllArgsConstructor
public class NoteServicesImpl implements NoteServices {
    @Autowired
    NoteRepo repo;

    @Override
    public AddNoteResponse createNote(CreateNoteRequest request) {
        checkIfTitleExit(request.getTitle());
        Note note = new Note();
        map(request,note);
        repo.save(note);
        map(note);
        return map(note);
    }

    private void checkIfTitleExit(String title) {
        Boolean check = repo.existsByTitle(title);
        if(check)throw new TitleExistsException(title+ " exists");
    }

    @Override
    public Long getTotalNotes() {
        return repo.count();
    }

    @Override
    public UpdateNoteResponse updateNoteWith(UpdateNoteRequest request) {
        Note note = findByTitle(request.getTitle());
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        UpdateNoteResponse response = new UpdateNoteResponse();
        response.setMessage("message updated");
        return response;
    }

    private Note findByTitle(String title) {
        return repo.findByTitle(title)
                .orElseThrow(() -> new NoteNotFoundException("note not found"));
    }

    @Override
    public DeleteNoteResponse deleteNote(String title) {
        Note note = findByTitle(title);
        repo.delete(note);
        DeleteNoteResponse response = new DeleteNoteResponse();
        response.setMessage("Note deleted successfully");
        return response;
    }

    @Override
    public List<Note> getAllNotes() {
        return repo.findAll();
    }
}

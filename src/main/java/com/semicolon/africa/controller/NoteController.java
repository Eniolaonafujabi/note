package com.semicolon.africa.controller;

import com.semicolon.africa.dtos.request.CreateNoteRequest;
import com.semicolon.africa.dtos.request.UpdateNoteRequest;
import com.semicolon.africa.dtos.response.AddNoteResponse;
import com.semicolon.africa.dtos.response.ApiResponse;
import com.semicolon.africa.dtos.response.DeleteNoteResponse;
import com.semicolon.africa.dtos.response.UpdateNoteResponse;
import com.semicolon.africa.services.implemitation.NoteServicesImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/notes")
@AllArgsConstructor
public class NoteController {
    private NoteServicesImpl noteService;

    @PostMapping
    public ResponseEntity<?> addNote(@RequestBody CreateNoteRequest request) {
        try {
            AddNoteResponse response = noteService.createNote(request);
            return new ResponseEntity<>(new ApiResponse(true,response),CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(new ApiResponse(false,exception),BAD_REQUEST);
        }
    }



        @PatchMapping("/update-note")
    public ResponseEntity<?> updateNote(@RequestBody UpdateNoteRequest request){
        try{
            UpdateNoteResponse response = noteService.updateNoteWith(request);
            return new ResponseEntity<>(new ApiResponse(true,response), OK);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception),BAD_REQUEST);
        }
    }
        @DeleteMapping("/delete-note{title}")
    public ResponseEntity<?> deleteNote(@PathVariable("title") String title){
        try{
            DeleteNoteResponse response = noteService.deleteNote(title);
            return new ResponseEntity<>(new ApiResponse(true,response), OK);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception), BAD_REQUEST);
        }
    }
}

package com.semicolon.africa.data.repositiories;

import com.semicolon.africa.data.models.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NoteRepo extends MongoRepository <Note, String>{
    Boolean existsByTitle(String title);

    Optional<Note> findByTitle(String title);
}

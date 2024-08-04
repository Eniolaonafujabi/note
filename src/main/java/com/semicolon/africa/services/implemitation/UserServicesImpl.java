package com.semicolon.africa.services.implemitation;

import com.semicolon.africa.data.models.Note;
import com.semicolon.africa.data.models.User;
import com.semicolon.africa.data.repositiories.UserRepo;
import com.semicolon.africa.dtos.request.*;
import com.semicolon.africa.dtos.response.*;
import com.semicolon.africa.exception.EmailExistsException;
import com.semicolon.africa.exception.PassWordException;
import com.semicolon.africa.services.interfaces.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.semicolon.africa.util.Mapper.map;

@Service
@AllArgsConstructor
public class UserServicesImpl implements UserServices {

    private UserRepo repo;
    private NoteServicesImpl noteServices;

    @Override
    public RegisterUserResponse registerUserWith(RegisterUserRequest request) {
        validate(request.getEmail());
        User newUser = new User();
        map(request,newUser);
        repo.save(newUser);
        return map(newUser);
    }

    private void validate(String email) {
        boolean check = repo.existsByEmail(email);
        if (check) throw new EmailExistsException(email+" already exists");
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = validateLogIn(request.getEmail(),request.getPassword());
        user.setLoggedIn(true);
        repo.save(user);
        LoginResponse response = new LoginResponse();
        response.setMessage("Successfully logged in");
        response.setLoggedIn(user.isLoggedIn());
        return response;
    }

    private User validateLogIn(String email, String password) {
        User user = repo.findByEmail(email)
                .orElseThrow(()-> new EmailExistsException("Invalid Details"));
        validatePassword(user.getPassword(),password);
        return user;
    }

    private void validatePassword(String password, String passwordToBeChecked) {
        if (!password.equals(passwordToBeChecked)) {
            throw new PassWordException("Invalid Details");
        }
    }

    @Override
    public AddNoteResponse createNote(CreateNoteRequest request) {
        User user = findByEmail(request.getEmail());
        validatePassword(request.getPassword(),user.getPassword());
        AddNoteResponse response = noteServices.createNote(request);
        Note note = noteServices.findNoteByTitle(response.getNote_title());
        List<Note> notes = user.getNoteList();
        notes.add(note);
        user.setNoteList(notes);
        repo.save(user);
        return response;
    }

    @Override
    public DeleteNoteResponse deleteNote(DeleteNoteRequest request) {
        return null;
    }


    private User findByEmail(String email) {
        User user = repo.findByEmail(email)
                .orElseThrow(()-> new EmailExistsException("Invalid Details"));
        return user;
    }

    @Override
    public LogoutResponse logout(LogoutRequest request) {
        return null;
    }
}

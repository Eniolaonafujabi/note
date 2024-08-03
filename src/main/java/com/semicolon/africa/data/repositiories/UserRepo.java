package com.semicolon.africa.data.repositiories;

import com.semicolon.africa.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
}

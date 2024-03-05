package com.UniTimetableSysBackend.repository;


import com.UniTimetableSysBackend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);

}

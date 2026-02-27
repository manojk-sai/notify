package com.manoj.notify.repository;

import com.manoj.notify.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    String findEmailById(String id);
}

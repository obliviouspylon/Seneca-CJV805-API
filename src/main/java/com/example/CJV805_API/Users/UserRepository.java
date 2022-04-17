package com.example.CJV805_API.Users;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    @Query("{email:?0}")
    Optional<User> getUserByEmail(String email);

    @Query("{_id:?0,email:?1}")
    Optional<User> getUserByIDAndEmail(String id, String email);
}

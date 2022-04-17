package com.example.CJV805_API.property;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyRepository extends MongoRepository<PropertyType,String> {
    @Query(value ="{title: ?0}")
    Optional<String> checkPropertyType(String type);
}

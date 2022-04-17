package com.example.CJV805_API.listings;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingsRepository extends MongoRepository<Listings,String> {
}

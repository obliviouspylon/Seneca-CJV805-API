package com.example.CJV805_API.heroSlides;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroSlidesRepository extends MongoRepository<HeroSlide,String> {
}

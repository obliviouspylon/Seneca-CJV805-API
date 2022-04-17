package com.example.CJV805_API.heroSlides;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroSlidesService {

    private HeroSlidesRepository heroSlidesRepository;

    @Autowired
    public HeroSlidesService(HeroSlidesRepository heroSlidesRepository) {
        this.heroSlidesRepository = heroSlidesRepository;
    }

    public List<HeroSlide> getSlides(){
        return heroSlidesRepository.findAll();
    }
}

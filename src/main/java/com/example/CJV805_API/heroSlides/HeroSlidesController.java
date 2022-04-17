package com.example.CJV805_API.heroSlides;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "heroSlides")
public class HeroSlidesController {
    private final HeroSlidesService heroSlidesService;

    @Autowired
    public HeroSlidesController(HeroSlidesService heroSlidesService) {
        this.heroSlidesService = heroSlidesService;
    }

    @GetMapping
    public List<HeroSlide> getSlides(){
        return heroSlidesService.getSlides();
    }
}

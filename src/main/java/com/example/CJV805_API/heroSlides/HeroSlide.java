package com.example.CJV805_API.heroSlides;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("heroSlides")
public class HeroSlide {
    @Id
    private String _id;
    private Integer id;
    private String image;
    private String caption;

    public HeroSlide() {
    }

    public HeroSlide(Integer id, String image, String caption) {
        this.id = id;
        this.image = image;
        this.caption = caption;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Override
    public String toString() {
        return "HeroSlide{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", caption='" + caption + '\'' +
                '}';
    }
}

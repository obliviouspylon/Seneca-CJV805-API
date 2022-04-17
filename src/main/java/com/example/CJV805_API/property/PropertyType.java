package com.example.CJV805_API.property;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("property")
public class PropertyType {

    @Id
    private String _id;
    private String title;
    private String image;

    public PropertyType() {
    }

    public PropertyType(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "PropertyType{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}

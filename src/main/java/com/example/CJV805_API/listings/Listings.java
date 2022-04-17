package com.example.CJV805_API.listings;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Document("listings")
public class Listings {

    @Id
    private String _id;
    private Integer id;
    private String title;
    private String img;
    private String description;
    private Integer price;
    private String type;
    private String location;
    private Boolean bestseller;
    private Amenities Amenities;
    @Field("House Rules")
    private List<String> houseRules;

    public Listings() {
    }

    public Listings(Integer id, String title, String img, String description, Integer price, String type, String location, Boolean bestseller, com.example.CJV805_API.listings.Amenities amenities, List<String> houseRules) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.description = description;
        this.price = price;
        this.type = type;
        this.location = location;
        this.bestseller = bestseller;
        Amenities = amenities;
        this.houseRules = houseRules;
    }

    public Listings(String title, String img, String description, Integer price, String type, String location, Boolean bestseller, com.example.CJV805_API.listings.Amenities amenities, List<String> houseRules) {
        this.title = title;
        this.img = img;
        this.description = description;
        this.price = price;
        this.type = type;
        this.location = location;
        this.bestseller = bestseller;
        Amenities = amenities;
        this.houseRules = houseRules;
    }

    @JsonProperty("Amenities")
    private void unpackAmenities(Map<String, Object> amen) {
        this.Amenities = new Amenities((Boolean) amen.get("pool"), (Boolean) amen.get("breakfast"), (Boolean) amen.get("wifi"), (Boolean) amen.get("parking"), (Boolean) amen.get("airportTransfer"));
    }

    @JsonProperty("House Rules")
    private void unpackHouseRules(List<String> rules) {
        this.houseRules = rules;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getBestseller() {
        return bestseller;
    }

    public void setBestseller(Boolean bestseller) {
        this.bestseller = bestseller;
    }

    public com.example.CJV805_API.listings.Amenities getAmenities() {
        return Amenities;
    }

    public void setAmenities(com.example.CJV805_API.listings.Amenities amenities) {
        Amenities = amenities;
    }

    public List<String> getHouseRules() {
        return houseRules;
    }

    public void setHouseRules(List<String> houseRules) {
        this.houseRules = houseRules;
    }

    @Override
    public String toString() {
        return "Listings{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", bestseller=" + bestseller +
                ", Amenities=" + Amenities +
                ", House Rules=" + houseRules +
                '}';
    }

}

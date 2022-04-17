package com.example.CJV805_API.listings;

import com.example.CJV805_API.Exceptions.MissingDataException;
import com.example.CJV805_API.property.PropertyRepository;
import com.example.CJV805_API.property.PropertyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

//Shows that this is  a Service class to be used by a controller
@Service
public class ListingsService {

    private final ListingsRepository listingsRepository;
    private PropertyRepository propertyRepository;

    @Autowired
    public ListingsService(ListingsRepository listingsRepository) {
        this.listingsRepository = listingsRepository;
    }

    @Autowired
    public void PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }


    public List<Listings> getAllListings() {
        return listingsRepository.findAll();
    }

    public String addListing(Listings listing) {

        if (Objects.equals(listing.getImg(), "") || Objects.equals(listing.getImg(), null)) {
            throw new MissingDataException("Image URL");
        } else if (Objects.equals(listing.getTitle(), "") || Objects.equals(listing.getTitle(), null)) {
            throw new MissingDataException("Title");
        } else if (Objects.equals(listing.getDescription(), "") || Objects.equals(listing.getDescription(), null)) {
            throw new MissingDataException("Description");
        } else if (Objects.equals(listing.getPrice(), null)) {
            throw new MissingDataException("Price");
        } else if (listing.getPrice() < 0) {
            throw new IllegalStateException("Price cannot be negative");
        } else if (Objects.equals(listing.getType(), "") || Objects.equals(listing.getType(), null)) {
            throw new MissingDataException("Property Type");
        } else if (Objects.equals(listing.getHouseRules(), null)) {
            throw new MissingDataException("House Rules");
        } else if (Objects.equals(listing.getBestseller(), null)) {
            throw new MissingDataException("Bestseller");
        } else if (Objects.equals(listing.getAmenities(), null)) {
            throw new MissingDataException("Amenities");
        } else if (Objects.equals(listing.getLocation(), "") || Objects.equals(listing.getLocation(), null)) {
            throw new MissingDataException("Location");
        }

        Optional<String> typeCheck = propertyRepository.checkPropertyType(listing.getType());
        if (typeCheck.isEmpty()){
            throw new IllegalStateException("Not a valid Property Type");
        }

        Integer maxID = listingsRepository.getMaxID();
        listing.setId(maxID + 1);
        listingsRepository.save(listing);
        return "Property Added!";
    }

    public List<Listings> getListingsByTitleType(String title, String type) {

        if (Objects.equals(title, "") || Objects.equals(title, null)) {
            throw new MissingDataException("title");
        } else if (Objects.equals(type, "") || Objects.equals(type, null)) {
            throw new MissingDataException("Property Type");
        }

        Optional<String> typeCheck = propertyRepository.checkPropertyType(type);
        if (typeCheck.isEmpty()){
            throw new IllegalStateException("Not a valid Property Type");
        }

        Optional<List<Listings>> listingsFound = listingsRepository.getListingByTitleType(title, type);
        if (listingsFound.isEmpty()) {
            throw new IllegalStateException("No Listings Found!");
        } else {
            return listingsFound.get();
        }
    }

    public List<Listings> getBestSellers() {
        return listingsRepository.getBestSellers();
    }

    public Listings getListingById(String strId) {

        if (strId == null){
            throw new IllegalStateException("Invalid ID!");
        }
        int id;
        String ID_REGEX = "[0-9]+";
        if (!strId.matches(ID_REGEX)){
            throw new IllegalStateException("Invalid ID!");
        } else {
            id = Integer.parseInt(strId);
        }

        return listingsRepository.getListingByID(id);
    }

}

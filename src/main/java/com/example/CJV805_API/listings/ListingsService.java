package com.example.CJV805_API.listings;

import com.example.CJV805_API.Exceptions.MissingDataException;
import com.example.CJV805_API.property.PropertyRepository;
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

        checkListing(listing);

        Integer maxID = listingsRepository.getMaxID();
        listing.setId(maxID + 1);
        listingsRepository.save(listing);
        return "Listing Added!";
    }

    public List<Listings> getListingsByTitleType(String title, String type) {

        Optional<List<Listings>> listingsFound;
        if (Objects.equals(title, null) && Objects.equals(type, null)) {
            throw new IllegalStateException("Need at least a Title or Property Type!");
        } else if (Objects.equals(title, null)) {
            checkValidPropertyType(type);
            listingsFound = listingsRepository.getListingByType(type);
        } else if (Objects.equals(type, null)) {
            listingsFound = listingsRepository.getListingByTitle(title);
        } else {
            listingsFound = listingsRepository.getListingByTitleType(title, type);
        }

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

        Integer id = Str2IntID(strId);

        Optional<Listings> foundListing = listingsRepository.getListingByID(id);
        if (foundListing.isEmpty()) {
            throw new IllegalStateException("Invalid ID!");
        } else {
            return foundListing.get();
        }
    }

    public String updateListing(Listings listing) {

        checkListing(listing);

        Optional<Listings> foundListing = listingsRepository.getListingByID(listing.getId());
        if (foundListing.isEmpty()) {
            throw new IllegalStateException("Invalid ID!");
        }
        listing.set_id(foundListing.get().get_id());
        Listings listingUpdated = listingsRepository.save(listing);
        foundListing = listingsRepository.getListingByID(listing.getId());
        if (foundListing.isEmpty()) {
            throw new IllegalStateException("Invalid ID!");
        } else if (listingUpdated.toString().equals(foundListing.get().toString())) {
            return "Listing Updated!";
        } else {
            throw new IllegalStateException("Unable to Update!");
        }
    }

    public String deleteListingById(String strId, Listings listing) {

        Integer id = Str2IntID(strId);
        if (listing.get_id()==null){
            throw new IllegalStateException("Invalid ID!");
        }

        Optional<Listings> foundListing = listingsRepository.getListingByID(id);
        if (foundListing.isEmpty()) {
            throw new IllegalStateException("Invalid ID!");
        } else if (listing.get_id().equals(foundListing.get().get_id())) {
            listingsRepository.deleteById(listing.get_id());
            return ("Listing removed.");
        } else {
            throw new IllegalStateException("Unable to Delete ID!");
        }
    }

    private Integer Str2IntID(String str) {
        if (str == null) {
            throw new IllegalStateException("Invalid ID!");
        }
        String ID_REGEX = "[0-9]+";
        if (!str.matches(ID_REGEX)) {
            throw new IllegalStateException("Invalid ID!");
        } else {
            return Integer.parseInt(str);
        }
    }

    private void checkListing(Listings listing) {
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
        checkValidPropertyType(listing.getType());
    }

    private void checkValidPropertyType(String type) {
        Optional<String> typeCheck = propertyRepository.checkPropertyType(type);
        if (typeCheck.isEmpty()) {
            throw new IllegalStateException("Not a valid Property Type");
        }
    }

}

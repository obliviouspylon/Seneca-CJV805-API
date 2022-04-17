package com.example.CJV805_API.listings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Shows that this is  a Service class to be used by a controller
@Service
public class ListingsService {

    private ListingsRepository listingsRepository;

    @Autowired
    public ListingsService(ListingsRepository listingsRepository) {

        this.listingsRepository = listingsRepository;
    }

    public List<Listings> getListings() {

        return listingsRepository.findAll();
    }
}

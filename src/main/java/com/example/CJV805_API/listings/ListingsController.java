package com.example.CJV805_API.listings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "listings")
public class ListingsController {

    private final ListingsService listingsService;

    @Autowired
    public ListingsController(ListingsService listingsService) {

        this.listingsService = listingsService;
    }

    @GetMapping
    public List<Listings> getListings() {
        return listingsService.getListings();
    }
}

package com.example.CJV805_API.listings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/listings")
public class ListingsController {

    private final ListingsService listingsService;

    @Autowired
    public ListingsController(ListingsService listingsService) {

        this.listingsService = listingsService;
    }

    @GetMapping
    public List<Listings> getListingsByTitleType(@RequestParam("title") String title,@RequestParam("type") String type) {
        return listingsService.getListingsByTitleType(title, type);
    }

    @GetMapping("/all")
    public List<Listings> getAllListings() {
        return listingsService.getAllListings();
    }

    @GetMapping("/bestseller")
    public List<Listings> getBestSellers() {
        return listingsService.getBestSellers();
    }

    @PostMapping("/add")
    public String addListing(@RequestBody Listings listing){
        return listingsService.addListing(listing);
    }

    @GetMapping("/{id}")
    public Listings getListingById(@PathVariable String id) {
        return listingsService.getListingById(id);
    }

}

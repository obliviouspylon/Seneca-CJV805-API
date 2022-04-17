package com.example.CJV805_API.listings;

public class Amenities {
    private Boolean pool = false;
    private Boolean breakfast= false;
    private Boolean wifi= false;
    private Boolean parking= false;
    private Boolean airportTransfer= false;

    public Amenities() {
    }

    public Amenities(Boolean pool, Boolean breakfast, Boolean wifi, Boolean parking, Boolean airportTransfer) {
        this.pool = (pool != null && pool);
        this.breakfast = (breakfast != null && breakfast);
        this.wifi = (wifi != null && wifi);
        this.parking = (parking != null && parking);
        this.airportTransfer = (airportTransfer != null && airportTransfer);
    }

    public Boolean getPool() {
        return pool;
    }

    public void setPool(Boolean pool) {
        this.pool = pool;
    }

    public Boolean getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Boolean breakfast) {
        this.breakfast = breakfast;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
    }

    public Boolean getAirportTransfer() {
        return airportTransfer;
    }

    public void setAirportTransfer(Boolean airportTransfer) {
        this.airportTransfer = airportTransfer;
    }
}

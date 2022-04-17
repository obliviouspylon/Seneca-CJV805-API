package com.example.CJV805_API.listings;

public class Amenities {
    private Boolean pool;
    private Boolean breakfast;
    private Boolean wifi;
    private Boolean parking;
    private Boolean airportTransfer;

    public Amenities() {
    }

    public Amenities(Boolean pool, Boolean breakfast, Boolean wifi, Boolean parking, Boolean airportTransfer) {
        this.pool = pool;
        this.breakfast = breakfast;
        this.wifi = wifi;
        this.parking = parking;
        this.airportTransfer = airportTransfer;
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

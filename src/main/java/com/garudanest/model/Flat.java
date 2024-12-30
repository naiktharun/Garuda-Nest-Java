package com.garudanest.model;

public class Flat {
    private String flatNumber;
    private int adults;
    private int kids;
    private int guests; // Changed from relatives to guests

    // Getters and setters
    public String getFlatNumber() {
        return flatNumber;
    }

    public Flat(){}

    public Flat(String flatNumber, int adults, int kids, int guests) {
        this.flatNumber = flatNumber;
        this.adults = adults;
        this.kids = kids;
        this.guests = guests;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getKids() {
        return kids;
    }

    public void setKids(int kids) {
        this.kids = kids;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    // Method to calculate head count
    public double calculateHeadCount() {
        return adults + 0.5 * kids + guests;
    }
}

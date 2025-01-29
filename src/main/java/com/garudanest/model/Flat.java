package com.garudanest.model;

public class Flat {
    private String flatNumber;
    private double adults; // Changed from int to double
    private double kids;   // Changed from int to double
    private double guests; // Changed from int to double

    public Flat() {}

    public Flat(String flatNumber, double adults, double kids, double guests) {
        this.flatNumber = flatNumber;
        this.adults = adults;
        this.kids = kids;
        this.guests = guests;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public double getAdults() {
        return adults;
    }

    public void setAdults(double adults) {
        this.adults = adults;
    }

    public double getKids() {
        return kids;
    }

    public void setKids(double kids) {
        this.kids = kids;
    }

    public double getGuests() {
        return guests;
    }

    public void setGuests(double guests) {
        this.guests = guests;
    }

    // Method to calculate head count
    public double calculateHeadCount() {
        return adults + (0.5 * kids) + guests;
    }
}

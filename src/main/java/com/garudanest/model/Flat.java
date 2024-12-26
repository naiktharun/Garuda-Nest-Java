package com.garudanest.model;

public class Flat {
    private String flatNumber;
    private int adults;
    private int kids;
    private int relatives;

    public Flat() {}

    public Flat(String flatNumber, int adults, int kids, int relatives) {
        this.flatNumber = flatNumber;
        this.adults = adults;
        this.kids = kids;
        this.relatives = relatives;
    }

    public String getFlatNumber() { return flatNumber; }
    public void setFlatNumber(String flatNumber) { this.flatNumber = flatNumber; }

    public int getAdults() { return adults; }
    public void setAdults(int adults) { this.adults = adults; }

    public int getKids() { return kids; }
    public void setKids(int kids) { this.kids = kids; }

    public int getRelatives() { return relatives; }
    public void setRelatives(int relatives) { this.relatives = relatives; }

    public double calculateHeadCount() {
        return adults + (kids * 0.5) + relatives;
    }
}
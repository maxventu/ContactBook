package com.itechart.app.entity;

/**
 * Created by Maxim on 11/24/2015.
 */
public class Location extends Entity<String> {
    private String country;
    private String city;

    public Location(String postcode, String country, String city) {
        super(postcode);
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}

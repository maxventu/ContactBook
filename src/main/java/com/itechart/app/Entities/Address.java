package com.itechart.app.Entities;

/**
 * Created by Maxim on 11/24/2015.
 */
public class Address extends Entity {
    private String countryCode;
    private String city;
    private String street;
    private String house;
    private String apartment;
    private String postcode;


    public Address(String countryCode, String city, String street, String house, String apartment, String postcode) {
        this.countryCode = countryCode;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.postcode = postcode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

}

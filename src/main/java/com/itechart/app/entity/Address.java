package com.itechart.app.entity;

/**
 * Created by Maxim on 11/24/2015.
 */
public class Address extends Entity {
    private String country;
    private String city;
    private String street;
    private String house;
    private String apartment;
    private String postcode;
    private Integer contactId;


    public Address(String country, String city, String street, String house, String apartment, String postcode) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.postcode = postcode;
    }
    public Address(String country, String city, String street, String house, String apartment, String postcode, Integer contactId) {
        this(country,city,street,house,apartment,postcode);
        this.contactId=contactId;
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

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }



}

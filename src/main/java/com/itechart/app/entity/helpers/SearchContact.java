package com.itechart.app.entity.helpers;

import com.itechart.app.controller.helpers.DateHelper;

import java.util.Date;

/**
 * Created by Maxim on 12/18/2015.
 */
public class SearchContact {
    private String firstName;
    private String lastName;
    private String patronymic;
    private Boolean dateIsGreater;
    private Date dateOfBirth;
    private String sexIsMale;
    private String nationality;
    private String familyStatus;
    private String currentWorkplace;
    private String street;
    private String house;
    private String apartment;
    private String postcode;
    private String country;
    private String city;

    public SearchContact(String firstName, String lastName, String patronymic, String dateIsGreater, String dateOfBirth, String sexIsMale, String nationality, String familyStatus, String currentWorkplace, String street, String house, String apartment, String postcode, String country, String city) {
        this.firstName = getNullableString(firstName);
        this.lastName = getNullableString(lastName);
        this.patronymic = getNullableString(patronymic);
        setDateIsGreater(dateIsGreater);
        this.dateOfBirth = DateHelper.INSTANCE.getDateOfBirth(dateOfBirth);
        this.sexIsMale = getNullableString(sexIsMale);
        this.nationality = getNullableString(nationality);
        this.familyStatus = getNullableString(familyStatus);
        this.currentWorkplace = getNullableString(currentWorkplace);
        this.street = getNullableString(street);
        this.house = getNullableString(house);
        this.apartment = getNullableString(apartment);
        this.postcode = getNullableString(postcode);
        this.country = getNullableString(country);
        this.city = getNullableString(city);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Boolean getDateIsGreater() {
        return dateIsGreater;
    }

    public void setDateIsGreater(String dateIsGreater) {
        this.dateIsGreater= "1".equals(dateIsGreater);
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSexIsMale() {
        return sexIsMale;
    }

    public void setSexIsMale(String sexIsMale) {
        this.sexIsMale=sexIsMale;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
    }

    public String getCurrentWorkplace() {
        return currentWorkplace;
    }

    public void setCurrentWorkplace(String currentWorkplace) {
        this.currentWorkplace = currentWorkplace;
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

    protected String getNullableString(String st){
        if(!"".equals(st))return st;
        return null;
    }
}

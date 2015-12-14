package com.itechart.app.entity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Maxim on 11/24/2015.
 */
public class Contact extends Entity<Integer> {
    private String firstName;
    private String lastName;
    private String patronymic;
    private Date dateOfBirth;
    private Boolean sexIsMale;
    private String nationality;
    private String familyStatus;
    private String webSite;
    private String email;
    private String currentWorkplace;
    private String photoUrl;
    private String street;
    private String house;
    private String apartment;
    private String postcode;
    private String country;
    private String city;

    private ArrayList<Telephone> telephones;
    private ArrayList<Attachment> attachments;

    public Contact(Integer id,
                   String firstName,
                   String lastName,
                   String patronymic,
                   Date dateOfBirth,
                   Boolean sexIsMale,
                   String nationality,
                   String familyStatus,
                   String webSite,
                   String email,
                   String currentWorkplace,
                   String photoUrl,
                   String street,
                   String house,
                   String apartment,
                   String postcode,
                   String country,
                   String city) {
        this(id, firstName, lastName);
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.sexIsMale = sexIsMale;
        this.nationality = nationality;
        this.familyStatus = familyStatus;
        this.webSite = webSite;
        this.email = email;
        this.currentWorkplace = currentWorkplace;
        this.photoUrl = photoUrl;
        this.street=street;
        this.house = house;
        this.apartment = apartment;
        this.postcode = postcode;
        this.country = country;
        this.city = city;
    }

    public Contact(Integer id,String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Contact() {
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean isSexIsMale() {
        return sexIsMale;
    }

    public void setSexIsMale(Boolean sexIsMale) {
        this.sexIsMale = sexIsMale;
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

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrentWorkplace() {
        return currentWorkplace;
    }

    public void setCurrentWorkplace(String currentWorkplace) {
        this.currentWorkplace = currentWorkplace;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public ArrayList<Telephone> getTelephones() {
        return telephones;
    }

    public void setTelephones(ArrayList<Telephone> telephones) {
        this.telephones = telephones;
    }

    public ArrayList<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<Attachment> attachments) {
        this.attachments = attachments;
    }


    public Boolean getSexIsMale() {
        return sexIsMale;
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

    public String getFullAddress(){
        StringBuilder stringBuilder=new StringBuilder();
        if(postcode!=null)stringBuilder.append(postcode).append(" ");
        if(street!=null)stringBuilder.append(street).append(" st., ");
        if(house!=null)stringBuilder.append(house).append("-");
        if(apartment!=null)stringBuilder.append(apartment).append(", ");
        if(city!=null)stringBuilder.append(city).append(", ");
        if(country!=null)stringBuilder.append(country);
        String string = ""+stringBuilder;
        string.trim();
        if(string.lastIndexOf(",")==string.length()-2)string=string.substring(0,string.length()-2);
        if(string.lastIndexOf("-")==string.length()-2)string=string.substring(0,string.length()-2);
        return string;
    }
    public String getFullName(){
        return ((firstName!=null? firstName+" ":"")+(lastName!=null? lastName : "") +(patronymic!=null? " "+patronymic:" ")).trim();
    }
}

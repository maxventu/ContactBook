package com.itechart.app.Entities;

import java.util.Date;

/**
 * Created by Maxim on 11/24/2015.
 */
public class Contact extends Entity {
    private String firstName;
    private String lastName;
    private String patronymic;
    private Date dateOfBirth;
    private boolean sexIsMale;
    private String nationality;
    private String familyStatus;
    private String webStatus;
    private String email;
    private String currentWorkplace;
    private int addressId;
    private String photoUrl;

    public Contact(String firstName,
                   String lastName,
                   String patronymic,
                   Date dateOfBirth,
                   boolean sexIsMale,
                   String nationality,
                   String familyStatus,
                   String webStatus,
                   String email,
                   String currentWorkplace,
                   int addressId,
                   String photoUrl) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.sexIsMale = sexIsMale;
        this.nationality = nationality;
        this.familyStatus = familyStatus;
        this.webStatus = webStatus;
        this.email = email;
        this.currentWorkplace = currentWorkplace;
        this.addressId = addressId;
        this.photoUrl = photoUrl;
    }

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public boolean isSexIsMale() {
        return sexIsMale;
    }

    public void setSexIsMale(boolean sexIsMale) {
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

    public String getWebStatus() {
        return webStatus;
    }

    public void setWebStatus(String webStatus) {
        this.webStatus = webStatus;
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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}

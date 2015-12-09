package com.itechart.app.entity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Maxim on 11/24/2015.
 */
public class Contact extends Entity {
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
    private ArrayList<Telephone> telephones;
    private ArrayList<Attachment> attachments;
    private ArrayList<Address> addresses;

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
                   String photoUrl) {
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

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }
}

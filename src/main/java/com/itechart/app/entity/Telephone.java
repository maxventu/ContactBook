package com.itechart.app.entity;

/**
 * Created by Maxim on 11/24/2015.
 */
public class Telephone extends Entity {
    private String country_code;
    private String number;
    private String type;
    private String comment;

    public Telephone(String country_code, String number, String type, String comment) {
        this.country_code = country_code;
        this.number = number;
        this.type = type;
        this.comment = comment;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

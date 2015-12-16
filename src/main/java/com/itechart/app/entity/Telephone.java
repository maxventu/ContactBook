package com.itechart.app.entity;

/**
 * Created by Maxim on 11/24/2015.
 */
public class Telephone extends Entity<Integer> {
    private String countryCode;
    private String operatorCode;
    private String number;
    private String type;
    private String comment;
    private Integer contactId;

    public Telephone(String countryCode,String operatorCode, String number, String type, String comment) {
        this.countryCode = countryCode;
        this.operatorCode = operatorCode;
        this.number = number;
        this.type = type;
        this.comment = comment;
    }

    private Telephone(Integer id, String countryCode,String operatorCode, String number, String type, String comment) {
        super(id);
        this.countryCode = countryCode;
        this.operatorCode = operatorCode;
        this.number = number;
        this.type = type;
        this.comment = comment;
    }
    public Telephone(Integer id, String countryCode,String operatorCode, String number, String type, String comment, Integer contactId) {
        this(id, operatorCode, countryCode,number,type,comment);
        this.contactId = contactId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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
    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }


    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }



    public String getFullNumber(){
        return (countryCode!=null ? "+"+countryCode: "")+(operatorCode!=null ? "("+operatorCode+")": "")+""+number;
    }
}

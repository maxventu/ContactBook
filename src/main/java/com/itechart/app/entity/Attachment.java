package com.itechart.app.entity;

import java.util.Date;

/**
 * Created by Maxim on 11/24/2015.
 */
public class Attachment extends Entity<Integer>{
    private String filename;
    private Date dateUpload;
    private String comment;
    private Integer contactId;


    public Attachment(String filename, Date dateUpload, String comment) {
        super();
        this.filename = filename;
        this.dateUpload = dateUpload;
        this.comment = comment;
    }

    public Attachment(Integer id, String filename, Date dateUpload, String comment) {
        super(id);
        this.filename = filename;
        this.dateUpload = dateUpload;
        this.comment = comment;
    }
    public Attachment(Integer id, String filename, Date dateUpload, String comment,Integer contactId) {
        this(id, filename, dateUpload, comment);
        this.contactId = contactId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getDateUpload() {
        return dateUpload;
    }

    public void setDateUpload(Date dateUpload) {
        this.dateUpload = dateUpload;
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


}

package com.itechart.app.entity;

import java.util.Date;

/**
 * Created by Maxim on 11/24/2015.
 */
public class Attachment extends Entity<Integer>{
    private String filename;
    private Date date_upload;
    private String comment;
    private Integer contactId;


    public Attachment(String filename, Date date_upload, String comment) {
        super();
        this.filename = filename;
        this.date_upload = date_upload;
        this.comment = comment;
    }

    public Attachment(Integer id, String filename, Date date_upload, String comment) {
        super(id);
        this.filename = filename;
        this.date_upload = date_upload;
        this.comment = comment;
    }
    public Attachment(Integer id, String filename, Date date_upload, String comment,Integer contactId) {
        this(id, filename, date_upload, comment);
        this.contactId = contactId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getDate_upload() {
        return date_upload;
    }

    public void setDate_upload(Date date_upload) {
        this.date_upload = date_upload;
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

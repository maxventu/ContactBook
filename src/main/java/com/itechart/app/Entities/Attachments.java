package com.itechart.app.Entities;

import java.util.Date;

/**
 * Created by Maxim on 11/24/2015.
 */
public class Attachments extends Entity{
    private String filename;
    private Date date_upload;
    private String comment;

    public Attachments(String filename, Date date_upload, String comment) {
        this.filename = filename;
        this.date_upload = date_upload;
        this.comment = comment;
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
}

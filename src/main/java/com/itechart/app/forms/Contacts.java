package com.itechart.app.forms;

import com.itechart.app.entity.Contact;

import java.util.Collection;

/**
 * Created by Maxim on 12/10/2015.
 */
public class Contacts {
    public Collection getContacts() {
        return contacts;
    }

    public void setContacts(Collection<Contact> contacts) {
        this.contacts = contacts;
    }

    private Collection<Contact> contacts;

}

package com.itechart.app.controller;

import com.itechart.app.controller.helpers.AttachmentHelper;
import com.itechart.app.controller.helpers.ContactHelper;
import com.itechart.app.controller.helpers.LocationHelper;
import com.itechart.app.controller.helpers.TelephoneHelper;
import com.itechart.app.dao.AttachmentDAO;
import com.itechart.app.dao.ContactDAO;
import com.itechart.app.dao.TelephoneDAO;
import com.itechart.app.entity.Attachment;
import com.itechart.app.entity.Contact;
import com.itechart.app.entity.Location;
import com.itechart.app.entity.Telephone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Maxim on 12/10/2015.
 */
public class ContactController implements Controller {
    final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("started");
        if(isSaving(request))forwardEditedContact(request, response);
        else forwardNewContact(request,response);

    }

    private boolean isSaving(HttpServletRequest request){
        String saving = request.getParameter("save_button");
        return "save_contact".equals(saving);
    }

    private void forwardNewContact(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        String st = request.getParameter("id");
        if(st!=null)initEditContact(request,st);
        else initNewEditContact(request);
        request.getRequestDispatcher("/jsp/contactForm.jsp").forward(request,response);
    }
    private void initNewEditContact(HttpServletRequest request){
        request.setAttribute("contact", new Contact());
        request.setAttribute("telephones", new ArrayList<Telephone>());
        request.setAttribute("attachments", new ArrayList<Attachment>());
    }

    private void initEditContact(HttpServletRequest request,String id){
        Integer index = Integer.parseInt(id);
        Contact contact =  ContactDAO.INSTANCE.findEntityById(index);
        ArrayList<Telephone> telephones = TelephoneDAO.INSTANCE.findAllByContactId(index);
        ArrayList<Attachment> attachments = AttachmentDAO.INSTANCE.findAllByContactId(index);
        request.setAttribute("contact", contact);
        request.setAttribute("telephones", telephones);
        request.setAttribute("attachments", attachments);
    }

    private void forwardEditedContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String st = request.getParameter("id");
        if(!"".equals(st))updateContact(request, st);
        else createContact(request);
        request.getRequestDispatcher("/main").forward(request,response);
    }

    private void createContact(HttpServletRequest request) {
        Integer contactId = ContactDAO.INSTANCE.maxRow()+1;
        LOGGER.debug("setting up a new contact with id={}",contactId);
        Contact contact = ContactHelper.INSTANCE.getContact(request, contactId);
        ContactDAO.INSTANCE.create(contact);
        setContactInfo(request, contact, contactId);

    }

    private void updateContact(HttpServletRequest request, String contactIdStr) {
        LOGGER.debug("updating contact, id={}",contactIdStr);
        Integer contactId = Integer.parseInt(contactIdStr);
        Contact contact = ContactHelper.INSTANCE.getContact(request, contactId);
        ContactDAO.INSTANCE.update(contact);
        setContactInfo(request,contact,contactId);

    }
    private void setContactInfo(HttpServletRequest request,Contact contact, Integer contactId){
        Location location = new Location(contact.getPostcode(),contact.getCountry(),contact.getCity());
        LocationHelper.INSTANCE.updateLocation(location);
        TelephoneHelper.INSTANCE.processTelephones(request,contactId);
        AttachmentHelper.INSTANCE.processAttachments(request,contactId);
    }









}

package com.itechart.app.controller;

import com.itechart.app.dao.AttachmentDAO;
import com.itechart.app.dao.ContactDAO;
import com.itechart.app.dao.TelephoneDAO;
import com.itechart.app.entity.Attachment;
import com.itechart.app.entity.Contact;
import com.itechart.app.entity.Telephone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Maxim on 12/10/2015.
 */
public class ContactController implements Controller {
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String st = request.getParameter("id");
        if(st!=null){
            Integer id = Integer.parseInt(st);
            Contact contact =  ContactDAO.INSTANCE.findEntityById(id);
            ArrayList<Telephone> telephones = TelephoneDAO.INSTANCE.findAllByContactId(id);
            ArrayList<Attachment> attachments = AttachmentDAO.INSTANCE.findAllByContactId(id);
            request.setAttribute("contact", contact);
            request.setAttribute("telephones", telephones);
            request.setAttribute("attachments", attachments);}
        else{
            request.setAttribute("contact", new Contact());
            request.setAttribute("telephones", new ArrayList<Telephone>());
            request.setAttribute("attachments", new ArrayList<Attachment>());
        }
        request.getRequestDispatcher("/jsp/contactForm.jsp").forward(request,response);
    }
}

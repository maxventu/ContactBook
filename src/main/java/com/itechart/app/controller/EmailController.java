package com.itechart.app.controller;

import com.itechart.app.controller.helpers.ContactHelper;
import com.itechart.app.controller.helpers.Controller;
import com.itechart.app.controller.helpers.DateHelper;
import com.itechart.app.entity.Contact;
import com.itechart.app.entity.helpers.Template;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Maxim on 12/10/2015.
 */
public class EmailController implements Controller {
    private static Session session;
    final Logger log = LoggerFactory.getLogger(EmailController.class);
    private ArrayList<Template> templatesArray= null;

    private static final STGroup templates = new STGroupFile("templates.stg");;
    {
        init();
    }
    private void init(){
        templatesArray = new ArrayList<Template>();
        templatesArray.add(new Template("simple","Without template",null));
        templatesArray.add(new Template("birthday","Birthday",templates.getInstanceOf("birthday").render()));
        templatesArray.add(new Template("birthday_ru","День рождения",templates.getInstanceOf("birthday_ru").render()));
        templates.unload();
    }
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("mainFormButton");
        String templateId = request.getParameter("template");
        try{
        if("send_email".equals(command))openSendingPage(request, response);
        else{
            processEmails(request,response,templateId);

            request.getRequestDispatcher("/static/jsp/emailSuccessful.jsp").forward(request,response);
        }
        }catch (MessagingException ex){
            log.error("Something went wrong while sending email: {}", ex);
            response.sendError(500);
        }

    }

    private void processEmails(HttpServletRequest request, HttpServletResponse response, String templateId) throws MessagingException {

        log.debug("processing emails for {} template",templateId);
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        ArrayList<Contact> contacts = ContactHelper.INSTANCE.getContactsByNameWithEmail(request, "contact_id");
        ArrayList<String> emails = ContactHelper.INSTANCE.getEmails(contacts);
        if("simple".equals(templateId)){
            Message m = getMessageForEverybody(request,message,emails,subject);
            log.info("sending emails to {}",emails);
            Transport.send(m);
        }
        else if("birthday".equals(templateId) || "birthday_ru".equals(templateId)){
            Message m = null;
            String text = null;
            for(Contact contact : contacts){
                text = getMessageString(templateId,contact,message);
                m = getMessageForOne(request, text,contact.getEmail(),subject);
                log.info("sending email to {} on {}",contact.getFullName(),contact.getEmail());
                Transport.send(m);
            }
        }

    }

    private String getMessageString(String templateId,Contact contact,String body){
        String message=null;
        ST t = templates.getInstanceOf(templateId);
        t.add("full_name", contact.getFullName());
        t.add("date", DateHelper.INSTANCE.getStringDate(new Date()));
        t.add("body", body);
        message = t.render();
        log.debug("rendered message is:{}",message);
        return message;
    }

    private void openSendingPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Contact> contacts = ContactHelper.INSTANCE.getContactsByNameWithEmail(request, "choseContacts");
        if(contacts.size()>0){
            log.debug("finding templates while opening sending page");
            request.setAttribute("contacts",contacts);
            request.setAttribute("templates",templatesArray);
            request.getRequestDispatcher("/static/jsp/emailForm.jsp").forward(request,response);}
    }

    public Message getNotificationMessage(HttpServletRequest request,String recipientEmail,String letter) throws MessagingException {
        log.info("sending email to {}", recipientEmail);
        log.debug("mail to {} is {}", recipientEmail, letter);
        if(session==null)init(request);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(request.getServletContext().getInitParameter("username")));
        message.addRecipient(MimeMessage.RecipientType.TO,
                new InternetAddress(request.getServletContext().getInitParameter("adminEmail")));
        message.setSubject("Notification");
        message.setText("ContactBook notification. \n Birthdays:\n"+letter);
        return message;
    }

    public Message getMessageForOne(HttpServletRequest request,
                                          String letter,
                                          String emailAddress,
                                          String subject) throws MessagingException {
        if(session==null)init(request);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(request.getServletContext().getInitParameter("username")));
        message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(emailAddress));
        message.setSubject(subject);
        message.setText(letter);
        return message;
    }

    public Message getMessageForEverybody(HttpServletRequest request,
                                          String letter,
                                          ArrayList<String> emailAddresses,
                                          String subject) throws MessagingException {
        if(session==null)init(request);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(request.getServletContext().getInitParameter("username")));

        Address[] addresses = new Address[emailAddresses.size()];
        for(int i=0;i<addresses.length;i++)
            addresses[i] = new InternetAddress(emailAddresses.get(i));
        message.addRecipients(MimeMessage.RecipientType.TO,addresses);

        message.setSubject(subject);
        message.setText(letter);
        return message;
    }

    public static void init(final HttpServletRequest request){
        Properties props = new Properties();
        props.put("mail.smtp.auth",request.getServletContext().getInitParameter("auth"));
        props.put("mail.smtp.starttls.enable",request.getServletContext().getInitParameter("starttls.enable"));
        props.put("mail.smtp.host",request.getServletContext().getInitParameter("host"));
        props.put("mail.smtp.port",request.getServletContext().getInitParameter("port"));
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(request.getServletContext().getInitParameter("username"),
                        request.getServletContext().getInitParameter("password"));
            }
        };
        session = Session.getInstance(props,auth);
    }
}

package com.itechart.app.controller;

import com.itechart.app.controller.helpers.ContactHelper;
import com.itechart.app.controller.helpers.Controller;
import com.itechart.app.controller.helpers.DateHelper;
import com.itechart.app.entity.Contact;
import com.itechart.app.entity.helpers.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    public static EmailController INSTANCE = new EmailController();


    private static final STGroup templates = new STGroupFile("templates.stg","UTF-8",'$','$');

    private void init(){
        templatesArray = new ArrayList<Template>();
        templatesArray.add(new Template("simple","Without template",null));
        templatesArray.add(new Template("birthday","Birthday",templates.getInstanceOf("birthday")
                .add("full_name","$Full name$")
                .add("body","$Your message$")
                .add("date","$Current time$")
                .render()));
        templatesArray.add(new Template("birthday_ru","Birthday ru",templates.getInstanceOf("birthday_ru").add("full_name","$Full name$")
                .add("body","$Your message$")
                .add("date", "$Current time$")
                .render()));
        templates.unload();
    }
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(templatesArray==null)init();
        String command = request.getParameter("mainFormButton");
        String templateId = request.getParameter("template");
        try{
        if("send_email".equals(command))openSendingPage(request, response);
        else{
            processEmails(request, templateId);
            request.getRequestDispatcher("/static/jsp/emailSuccessful.jsp").forward(request,response);
        }
        }catch (MessagingException ex){
            log.error("Something went wrong while sending email: {}", ex);
            response.sendError(500);
        }
    }

    private void processEmails(HttpServletRequest request, String templateId) throws MessagingException {

        log.debug("processing emails for {} template",templateId);
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        ArrayList<Contact> contacts = ContactHelper.INSTANCE.getContactsByNameWithEmail(request, "contact_id");
        ArrayList<String> emails = ContactHelper.INSTANCE.getEmails(contacts);
        if("simple".equals(templateId)){
            MimeMessage m = getMessageForEverybody(request.getServletContext(),message,emails,subject);
            log.info("sending emails to {}",emails);
            Transport.send(m);
        }
        else if("birthday".equals(templateId) || "birthday_ru".equals(templateId)){
            MimeMessage m = null;
            String text = null;
            for(Contact contact : contacts){
                text = getMessageString(templateId,contact,message);
                m = getMessageForOne(request.getServletContext(), text,contact.getEmail(),subject);
                log.info("sending email to {} on {}",contact.getFullName(),contact.getEmail());
                Transport.send(m);
            }
        }

    }

    private String getMessageString(String templateId,Contact contact,String body){
        String message=null;
        ST t = templates.getInstanceOf(templateId)
                .add("full_name", contact.getFullName())
                .add("date", DateHelper.INSTANCE.getStringDate(new Date()))
                .add("body", body);
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
        else response.sendRedirect(FrontController.INSTANCE.getServletContext().getContextPath()+"/main");
    }

    public MimeMessage getNotificationMessage(ServletContext context,String letter) throws MessagingException {
        log.info("preparing notification");
        if(session==null)init(context);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(context.getInitParameter("username")));
        message.addRecipient(MimeMessage.RecipientType.TO,
                new InternetAddress(context.getInitParameter("adminEmail")));
        message.setContent(letter, "text/html; charset=utf-8");
        message.setSubject("Notification","UTF-8");
        return message;
    }

    public MimeMessage getMessageForOne(ServletContext context,
                                          String letter,
                                          String emailAddress,
                                          String subject) throws MessagingException {
        if(session==null)init(context);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(context.getInitParameter("username")));
        message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(emailAddress));
        message.setContent(letter, "text/html; charset=utf-8");
        message.setSubject(subject,"UTF-8");
        log.debug("email with subject {}",subject);
        return message;
    }

    public MimeMessage getMessageForEverybody(ServletContext context,
                                          String letter,
                                          ArrayList<String> emailAddresses,
                                          String subject) throws MessagingException {
        if(session==null)init(context);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(context.getInitParameter("username")));

        Address[] addresses = new Address[emailAddresses.size()];
        for(int i=0;i<addresses.length;i++)
            addresses[i] = new InternetAddress(emailAddresses.get(i));
        message.addRecipients(MimeMessage.RecipientType.TO,addresses);
        message.setContent(letter, "text/html; charset=utf-8");
        message.setSubject(subject,"UTF-8");
        return message;
    }

    private void init(final ServletContext context){
        Properties props = new Properties();
        props.put("mail.smtp.auth",context.getInitParameter("auth"));
        props.put("mail.smtp.starttls.enable",context.getInitParameter("starttls.enable"));
        props.put("mail.smtp.host",context.getInitParameter("host"));
        props.put("mail.smtp.port",context.getInitParameter("port"));
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(context.getInitParameter("username"),
                        context.getInitParameter("password"));
            }
        };
        session = Session.getInstance(props,auth);
        init();
    }

    public void sendEmailNotification(ServletContext context,ArrayList<Contact> contacts){
        String message=null;
        ST t = templates.getInstanceOf("administrator_notification");
        for(Contact cont:contacts){
            t.add("user",cont.getFullName()+" ("+cont.getId()+")");
        }
        t.add("date", DateHelper.INSTANCE.getStringDate(new Date()));
        message = t.render();
        log.debug("rendered message is:{}",message);
        try {
            MimeMessage m = getNotificationMessage(context,message);
            Transport.send(m);
        } catch (MessagingException e) {
            log.error("cannot send notification",e);
        }

    }
}

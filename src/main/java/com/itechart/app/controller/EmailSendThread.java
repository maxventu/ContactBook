package com.itechart.app.controller;

import com.itechart.app.dao.ContactDAO;
import com.itechart.app.entity.Contact;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


public class EmailSendThread extends Thread {

    final Logger log = LoggerFactory.getLogger(EmailSendThread.class);
    private ServletContext context;
    public void init(ServletContext context){
        this.context=context;
    }
    public void run() {
        while (true) {
            log.debug("start");
            long begin = System.currentTimeMillis();
            ArrayList<Contact> contacts = ContactDAO.INSTANCE.getContactsByBirthday();
            if(contacts.size()>0){
                EmailController.INSTANCE.sendEmailNotification(context,contacts);
            }
            long end = System.currentTimeMillis();
            log.debug("finish");
            try {
                sleep(DateUtils.MILLIS_PER_DAY - (end - begin));
            } catch (InterruptedException e) {
                log.error("Thread interrupted. The message may not be delivered on time", e);
            }
        }
    }
}

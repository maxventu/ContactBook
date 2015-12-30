package com.itechart.app.controller.helpers;

import com.itechart.app.dao.ContactDAO;
import com.itechart.app.entity.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Maxim on 12/16/2015.
 */
public class ContactHelper extends AbstractHelper {
    final Logger LOGGER = LoggerFactory.getLogger(ContactHelper.class);

    public static final ContactHelper INSTANCE = new ContactHelper();

    private ContactHelper(){}

    public Contact getContact(HttpServletRequest request,Integer contactId){
        LOGGER.debug("creating contact with id={}",contactId);
        List<String> strings = Arrays.asList("id", "firstName", "lastName", "patronymic", "dateOfBirth", "sexIsMale",
                "nationality", "familyStatus", "webSite", "email", "currentWorkplace", "photoUrl", "street", "house",
                "apartment", "postcode", "country", "city");
        HashMap<String,String> map = new HashMap<String, String>();
        for(String st : strings)
            map.put(st,request.getParameter(st));

        Date date = DateHelper.INSTANCE.getDateOfBirth(map.get("dateOfBirth"));
        Boolean isMale = "Not chosen".equals(map.get("sexIsMale")) ? null : "1".equals(map.get("sexIsMale"));

        Contact contact = new Contact(contactId,map.get("firstName"),map.get("lastName"),map.get("patronymic"),date,isMale,
                map.get("nationality"),map.get("familyStatus"),map.get("webSite"),map.get("email"),map.get("currentWorkplace"),map.get("photoUrl"),map.get("street"),map.get("house"),
                map.get("apartment"),map.get("postcode"),map.get("country"),map.get("city"));
        return contact;
    }

    public void deleteContacts(HttpServletRequest request){
        ArrayList<Integer> contactIds = getIntListFromArray(request.getParameterValues("choseContacts"));
        for(Integer id : contactIds){
            ContactDAO.INSTANCE.delete(id);
        }
    }
    public ArrayList<Contact> getContactsByName(HttpServletRequest request,String name){
        ArrayList<Integer> contactIds = getIntListFromArray(request.getParameterValues(name));
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        for(Integer id:contactIds){
            contacts.add(ContactDAO.INSTANCE.findEntityById(id));
        }
        return contacts;
    }
    public ArrayList<Contact> getContactsByNameWithEmail(HttpServletRequest request,String name){
        ArrayList<Integer> contactIds = getIntListFromArray(request.getParameterValues(name));
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        Contact cont = null;
        for(Integer id:contactIds){
            cont = ContactDAO.INSTANCE.findEntityById(id);
            if(cont.getEmail()!=null);
            contacts.add(cont);
        }
        return contacts;
    }

    public ArrayList<String> getEmails(ArrayList<Contact> contacts){
        ArrayList<String> emails = new ArrayList<String>();
        for(Contact cont: contacts){
            if(cont.getEmail()!=null)emails.add(cont.getEmail());
        }
        return emails;
    }

}

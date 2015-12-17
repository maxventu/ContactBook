package com.itechart.app.controller.helpers;

import com.itechart.app.entity.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Maxim on 12/16/2015.
 */
public class ContactHelper extends  EntityHelper{
    final Logger LOGGER = LoggerFactory.getLogger(ContactHelper.class);

    public static final ContactHelper INSTANCE = new ContactHelper();

    private ContactHelper(){}

    public Contact getContact(HttpServletRequest request,Integer contactId)
    {
        LOGGER.debug("creating contact with id={}",contactId);
        List<String> strings = Arrays.asList("id", "firstName", "lastName", "patronymic", "dateOfBirth", "sexIsMale",
                "nationality", "familyStatus", "webSite", "email", "currentWorkplace", "photoUrl", "street", "house",
                "apartment", "postcode", "country", "city");
        HashMap<String,String> map = new HashMap<String, String>();
        for(String st : strings)
            map.put(st,request.getParameter(st));

        Date date = DateHelper.INSTANCE.getDateOfBirth(map.get("dateOfBirth"));

        Contact contact = new Contact(contactId,map.get("firstName"),map.get("lastName"),map.get("patronymic"),date,"1".equals(map.get("sexIsMale")),
                map.get("nationality"),map.get("familyStatus"),map.get("webSite"),map.get("email"),map.get("currentWorkplace"),map.get("photoUrl"),map.get("street"),map.get("house"),
                map.get("apartment"),map.get("postcode"),map.get("country"),map.get("city"));
        return contact;
    }
}

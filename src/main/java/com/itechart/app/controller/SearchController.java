package com.itechart.app.controller;

import com.itechart.app.controller.helpers.Controller;
import com.itechart.app.dao.ContactDAO;
import com.itechart.app.entity.Contact;
import com.itechart.app.entity.helpers.SearchContact;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Maxim on 12/15/2015.
 */
public class SearchController implements Controller {
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("searchBtn");
        if("search".equals(command)) doSearch(request,response);
        else request.getRequestDispatcher("/static/jsp/search.jsp").forward(
                request, response);
    }

    private void doSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SearchContact searchContact = getSearchContactFrom(request);
        ArrayList<Contact> foundContacts = ContactDAO.INSTANCE.findContacts(searchContact);
        request.setAttribute("contacts",foundContacts);
        request.getRequestDispatcher("/static/jsp/main.jsp").forward(
                request, response);
    }
    private SearchContact getSearchContactFrom(HttpServletRequest request){
        List<String> strings = Arrays.asList(
                "firstName", "lastName", "patronymic","dateIsGreater", "dateOfBirth",
                "sexIsMale", "nationality", "familyStatus", "currentWorkplace", "street",
                "house","apartment", "postcode", "country", "city");
        ArrayList<String> params = new ArrayList<String>();
        for(String st : strings)
            params.add(request.getParameter(st));
        int i=0;
        return new SearchContact(
                params.get(i++),params.get(i++),params.get(i++),params.get(i++),params.get(i++),
                params.get(i++),params.get(i++),params.get(i++),params.get(i++),params.get(i++),
                params.get(i++),params.get(i++),params.get(i++),params.get(i++),params.get(i));

    }
}

package com.itechart.app.controller;

import com.itechart.app.dao.ContactDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by Maxim on 12/10/2015.
 */
public class MainFrameController implements Controller{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String selectedButton = request.getParameter("mainFormButton");
        redirectRequest(selectedButton,request,response);
    }
    private void redirectRequest(String type,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if("add_contact".equals(type))request.getRequestDispatcher("/ContactBook/edit").forward(request,response);
        else if("send_email".equals(type))request.getRequestDispatcher("/email").forward(request,response);
        else if("delete_contacts".equals(type)) deleteContacts(request,response);
        else initContacts(request,response);
    }

    private void initContacts(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
        Collection collection = ContactDAO.INSTANCE.findAll();
        request.setAttribute("contacts", collection);
        request.getRequestDispatcher("/jsp/main.jsp").forward(request,response);
    }

    private void deleteContacts(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

    }
}

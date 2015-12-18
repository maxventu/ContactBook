package com.itechart.app.controller;

import com.itechart.app.controller.helpers.ContactHelper;
import com.itechart.app.controller.helpers.Controller;
import com.itechart.app.dao.ContactDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by Maxim on 12/10/2015.
 */
public class MainFrameController implements Controller {

    final Logger LOGGER = LoggerFactory.getLogger(MainFrameController.class);

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String selectedButton = request.getParameter("mainFormButton");
        redirectRequest(selectedButton,request,response);
    }
    private void redirectRequest(String type,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if("add_contact".equals(type))request.getRequestDispatcher("/ContactBook/edit").forward(request,response);
        else if("send_email".equals(type))request.getRequestDispatcher("/email").forward(request, response);
        else if("delete_contacts".equals(type)) deleteContacts(request,response);
        else initContacts(request,response);
    }

    private void initContacts(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
        Integer currentPage = 1;
        Integer numberOfElements = 5;
        String curPageStr = request.getParameter("page");
        String numbOfEl = request.getParameter("numberOfElements");
        LOGGER.info("init contacts current Page={}, number of elements={}",curPageStr,numbOfEl);
        if(curPageStr!=null)
            currentPage = Integer.parseInt(curPageStr);
        if(numbOfEl!=null)
            numberOfElements = Integer.parseInt(numbOfEl);
        Collection collection = ContactDAO.INSTANCE.getContactsByPage(currentPage,numberOfElements);
        request.setAttribute("currentPage",currentPage);
        Integer numberOfNotDeleted = ContactDAO.INSTANCE.countNotDeleted();
        request.setAttribute("maxPageNumber",numberOfNotDeleted/numberOfElements+(numberOfNotDeleted%numberOfElements==0?0:1));
        request.setAttribute("numberOfElements",numberOfElements);
        request.setAttribute("contacts", collection);
        request.getRequestDispatcher("/jsp/main.jsp").forward(request,response);
    }

    private void deleteContacts(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        ContactHelper.INSTANCE.deleteContacts(request);
        initContacts(request,response);
    }
}

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

        Collection collection = ContactDAO.INSTANCE.findAll();

        request.setAttribute("contacts", collection);
        request.getRequestDispatcher("/jsp/main.jsp").forward(request,response);
    }
}

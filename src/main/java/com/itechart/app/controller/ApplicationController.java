package com.itechart.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Maxim on 11/28/2015.
 */
public class ApplicationController implements Controller{
    final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("URL: {}",request.getRequestURL());
        logger.info("query: {}",request.getQueryString());
        String email = request.getParameter("inputEmail");
        request.setAttribute("email",email);
        request.getRequestDispatcher("result.jsp").forward(request,response);

    }
}

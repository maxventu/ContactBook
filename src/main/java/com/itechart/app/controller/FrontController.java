package com.itechart.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Maxim on 11/25/2015.
 */
@WebServlet("/ContactBook/*")
public class FrontController extends HttpServlet {
    final Logger LOGGER = LoggerFactory.getLogger(FrontController.class);

    public static FrontController INSTANCE;

    public static ServletContext getFrontControllerServletContext(){
        return INSTANCE.getServletContext();
    }

    private ApplicationController applicationController;

    public void init(ServletConfig config) throws
            ServletException {
        super.init(config);
        INSTANCE = this;
        applicationController = new ApplicationController();
    }

    public void destroy() {
    }

    protected void execute(HttpServletRequest
                                   request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        LOGGER.debug("started");
        applicationController.processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, java.io.IOException {
        execute(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, java.io.IOException {
        execute(request, response);
    }
}

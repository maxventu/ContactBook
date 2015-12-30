package com.itechart.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Maxim on 11/25/2015.
 */

public class FrontController extends HttpServlet {
    final Logger LOGGER = LoggerFactory.getLogger(FrontController.class);

    public static FrontController INSTANCE;

    private MyScheduler scheduler;
    private ApplicationController applicationController;

    public void init(ServletConfig config) throws
            ServletException {
        LOGGER.debug("initializing front controller");
        super.init(config);
        scheduler = new MyScheduler();
        scheduler.init(getServletContext());
        INSTANCE = this;
        applicationController = new ApplicationController();

    }

    public void destroy() {
        scheduler.finish();
    }

    protected void execute(HttpServletRequest
                                   request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        LOGGER.debug("started");
        try{
        applicationController.processRequest(request,response);
        }
        catch(Exception e){
            response.sendError(500);
        }
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

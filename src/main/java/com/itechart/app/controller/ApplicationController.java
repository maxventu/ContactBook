package com.itechart.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Maxim on 11/28/2015.
 */
public class ApplicationController implements Controller{
    final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    final private HashMap<String,Controller> mapOfControllers;
    {
        mapOfControllers=new HashMap<String, Controller>();
        mapOfControllers.put("/main",new MainFrameController());
        mapOfControllers.put("/edit",new ContactController());
        mapOfControllers.put("/update",new ContactController());
        mapOfControllers.put("/email",new EmailController());
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ErrorHandler errorHandler = new ErrorHandler();
        LOGGER.debug("query: {}", request.getQueryString());
        Controller controller = mapOfControllers.get(request.getPathInfo());
        if(controller!=null)
        controller.processRequest(request,response);
        else response.sendError(404);

    }
}

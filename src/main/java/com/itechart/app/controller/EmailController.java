package com.itechart.app.controller;

import com.itechart.app.controller.helpers.AbstractHelper;
import com.itechart.app.controller.helpers.Controller;
import com.itechart.app.dao.AbstractDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Maxim on 12/10/2015.
 */
public class EmailController implements Controller {
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);
        ArrayList<Integer> contactIds = AbstractHelper.getIntListFromArray(request.getParameterValues("receiverId"));
        String template = request.getParameter("templateId");
        if("simple".equals(template)){
            String subject = request.getParameter("subject");
            String message = request.getParameter("message");
        }
        else{
            Integer templateId = Integer.parseInt(template);
        }
        request.getRequestDispatcher("/jsp/emailForm.jsp").forward(request,response);
    }
}

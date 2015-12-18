package com.itechart.app.controller;

import com.itechart.app.controller.helpers.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Maxim on 12/10/2015.
 */
public class EmailController implements Controller {
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/static/jsp/emailForm.jsp").forward(request,response);
    }
}

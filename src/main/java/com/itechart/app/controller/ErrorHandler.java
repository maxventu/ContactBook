package com.itechart.app.controller;

import com.itechart.app.controller.helpers.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Maxim on 11/29/2015.
 */
public class ErrorHandler implements Controller {
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/static/jsp/error.jsp").forward(request,response);
    }
}

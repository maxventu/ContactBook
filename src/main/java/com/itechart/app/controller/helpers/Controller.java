package com.itechart.app.controller.helpers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Maxim on 11/29/2015.
 */
public interface Controller {
    void processRequest(HttpServletRequest
                                       request, HttpServletResponse response)
            throws ServletException, java.io.IOException;
}

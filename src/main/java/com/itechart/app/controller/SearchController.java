package com.itechart.app.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Maxim on 12/15/2015.
 */
public class SearchController implements Controller {
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/search.jsp").forward(
                request, response);
    }
}

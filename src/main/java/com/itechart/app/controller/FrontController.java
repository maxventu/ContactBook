package com.itechart.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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
    final Logger logger = LoggerFactory.getLogger(FrontController.class);

    private ApplicationController applicationController;
    // Initializes the servlet.
    public void init(ServletConfig config) throws
            ServletException {
        super.init(config);
        applicationController = new ApplicationController();
    }

    // Destroys the servlet.
    public void destroy() {
    }

    /** Processes requests for both HTTP
     * <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void execute(HttpServletRequest
                                   request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        logger.debug("started");
        // Set response content type
        //response.setContentType("text/html");
        applicationController.processRequest(request,response);
        // Actual logic goes here.
//        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
  //      view.include(request,response);
        //view.forward(request,response);
        //PrintWriter out = response.getWriter();
        //out.println("<h1>" + message + "</h1>");
    }

    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, java.io.IOException {
        execute(request, response);
    }

    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, java.io.IOException {
        execute(request, response);
    }

    /** Returns a short description of the servlet */
    public String getServletInfo() {
        return "Front Controller Pattern" +
                " Servlet Front Strategy Example";
    }

    protected void dispatch(HttpServletRequest request,
                            HttpServletResponse response,
                            String page)
            throws  javax.servlet.ServletException,
            java.io.IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}

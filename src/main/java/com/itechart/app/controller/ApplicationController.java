package com.itechart.app.controller;

import com.itechart.app.controller.helpers.Controller;
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
public class ApplicationController implements Controller {
    final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    final private HashMap<String,Controller> mapOfControllers;
    {
        mapOfControllers=new HashMap<String, Controller>();
        mapOfControllers.put("/",new MainFrameController());
        mapOfControllers.put("/main",new MainFrameController());
        mapOfControllers.put("/edit",new ContactController());
        mapOfControllers.put("/update",new ContactController());
        mapOfControllers.put("/email",new EmailController());
        mapOfControllers.put("/upload_avatar",new AvatarController());
        mapOfControllers.put("/avatar",new AvatarController());
        mapOfControllers.put("/upload_attachment",new AttachmentController());
        mapOfControllers.put("/attachment",new AttachmentController());
        mapOfControllers.put("/attachments",new AttachmentController());
        mapOfControllers.put("/search",new SearchController());
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("path: {}, query: {}", request.getPathInfo(), request.getQueryString());
        Controller controller = mapOfControllers.get(request.getPathInfo());
        LOGGER.debug("controller {}",controller);
        try{if (controller != null)
            controller.processRequest(request, response);
        else try {
            request.getRequestDispatcher("/static/jsp" + request.getPathInfo() + ".jsp").forward(
                    request, response);
        } catch (Exception e) {
            response.sendError(404);
        }
        }catch (Exception ex){
            request.getRequestDispatcher("/static/jsp/error.jsp").forward(
                    request, response);
        }
    }
}

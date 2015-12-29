package com.itechart.app.controller;

import com.itechart.app.controller.helpers.Controller;
import com.itechart.app.controller.helpers.Upload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by Maxim on 12/15/2015.
 */
public class AvatarController extends Upload implements Controller {
    final Logger LOGGER = LoggerFactory.getLogger(AvatarController.class);

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        if("/upload_avatar".equals(request.getPathInfo())) uploadAvatar(request, response);
        else if("/avatar".equals(request.getPathInfo())) showAvatar(request, response);

    }

    private void uploadAvatar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }
        ServletFileUpload upload = getFileUpload();
        LOGGER.debug("saving avatar image");
        String contactId=null;
        String uploadPath = getAvatarDirectoryPath();

        try{
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (item.isFormField()) {
                        if("cont_id".equals(item.getFieldName())){
                            contactId=item.getString();
                            LOGGER.debug("avatar uploading for contact {}",contactId);
                        }
                    }
                }

                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        if(item.getSize()==0){
                            request.setAttribute("avatarURL","http://res.cloudinary.com/goodcloud/image/upload/v1449943378/ContactBook/default_avatar.png");
                            request.setAttribute("avatarLoaded","true");

                            request.getRequestDispatcher("/static/jsp/partial/avatar/avatarAnswer.jsp").forward(
                                    request, response);
                            return;
                        }
                        LOGGER.debug("trying to save item {}",item.getSize());
                        String fileName = new File(item.getName()).getName();
                        fileName = contactId+"."+ FilenameUtils.getExtension(fileName);
                        String filePath = uploadPath + File.separator + fileName;
                        LOGGER.debug("trying to save file to {}",filePath);
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                        LOGGER.debug("file is saved to {}",filePath);
                        request.setAttribute("avatarURL",AVATAR_DIRECTORY  + "?filename=" + fileName);
                        request.setAttribute("avatarLoaded","true");

                    }
                }
            }
        }

        catch (Exception e){
            request.setAttribute("avatarLoaded",
                    "exception"+e);
        }

        request.getRequestDispatcher("/static/jsp/partial/avatar/avatarAnswer.jsp").forward(
                request, response);

    }
    private void showAvatar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filename = request.getParameter("filename");
        File file = new File(getAvatarDirectoryPath(), filename);
        response.setHeader("Content-Type", FrontController.INSTANCE.getServletContext().getMimeType(filename));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
        Files.copy(file.toPath(), response.getOutputStream());
    }

}


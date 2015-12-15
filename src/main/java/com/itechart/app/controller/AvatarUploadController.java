package com.itechart.app.controller;

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
import java.util.List;

/**
 * Created by Maxim on 12/15/2015.
 */
public class AvatarUploadController extends Upload implements Controller {
    final Logger LOGGER = LoggerFactory.getLogger(AvatarUploadController.class);
    // upload settings
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }
        ServletFileUpload upload = getFileUpload();

        String contactId=null;
        String fromProjectAvatarPath =File.separator + UPLOAD_DIRECTORY+ File.separator+AVATAR_DIRECTORY;
        String uploadPath = FrontController.INSTANCE.getServletContext().getRealPath("");

        try{
            createDirectoryIfNotExists(uploadPath);

            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (item.isFormField()) {
                        if("contact_id".equals(item.getFieldName())){
                            contactId=item.getString();
                        }
                    }
                }

                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        fileName = contactId+"."+ FilenameUtils.getExtension(fileName);
                        String filePath = uploadPath + fromProjectAvatarPath + File.separator + fileName;
                        File storeFile = new File(filePath);

                        // saves the file on disk
                        item.write(storeFile);
                        request.setAttribute("avatarURL",fromProjectAvatarPath + File.separator + fileName);
                        request.setAttribute("avatarLoaded","true");

                    }
                }
            }

        }

        catch (Exception e){
            request.setAttribute("avatarLoaded",
                    "exception"+e);
        }
        // redirects client to message page
        request.getRequestDispatcher("/jsp/partial/avatar/avatarAnswer.jsp").forward(
                request, response);

    }
}

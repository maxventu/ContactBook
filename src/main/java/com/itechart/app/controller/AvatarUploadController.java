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
        LOGGER.debug("saving avatar image");
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
                            LOGGER.debug("avatar uploading for contact {}",contactId);
                        }
                    }
                }

                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        fileName = contactId+"."+ FilenameUtils.getExtension(fileName);
                        String filePath = uploadPath + fromProjectAvatarPath + File.separator + fileName;
                        LOGGER.debug("trying to save file to {}",filePath);
                        createDirectoryIfNotExists(uploadPath + fromProjectAvatarPath);
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

        request.getRequestDispatcher("/static/jsp/partial/avatar/avatarAnswer.jsp").forward(
                request, response);

    }
}


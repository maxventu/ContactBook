package com.itechart.app.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

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
public class AvatarUploadController implements Controller {
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final String AVATAR_DIRECTORY = "avatar";

    // upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 5; // 5MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);

        String contactId=null;
        String fromProjectPath =File.separator + UPLOAD_DIRECTORY+ File.separator+AVATAR_DIRECTORY;
        String uploadPath = FrontController.INSTANCE.getServletContext().getRealPath("");

        try{
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            // parses the request's content to extract file data

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
                        String filePath = uploadPath + fromProjectPath + File.separator + fileName;
                        File storeFile = new File(filePath);

                        // saves the file on disk
                        item.write(storeFile);
                        request.setAttribute("avatarURL",fromProjectPath + File.separator + fileName);
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


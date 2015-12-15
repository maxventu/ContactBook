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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Maxim on 12/15/2015.
 */
public class AttachmentUploadController extends Upload implements Controller {
    final Logger LOGGER = LoggerFactory.getLogger(AvatarUploadController.class);
    // upload settings
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        LOGGER.debug("attachment starts uploading");
        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }
        ServletFileUpload upload = getFileUpload();

        String contactId="";
        String comment="";
        String filename="";
        String uploadPath = FrontController.INSTANCE.getServletContext().getRealPath("");

        try{
            //createDirectoryIfNotExists(uploadPath);

            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (item.isFormField()) {
                        if("contact_id".equals(item.getFieldName())){
                            contactId=item.getString();
                        }
                        else if("attachment_filename".equals(item.getFieldName())){
                            filename = item.getString();
                        }
                        else if("attachment_comment".equals(item.getFieldName())){
                            comment = item.getString("UTF-8");
                        }
                    }
                }
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date now = new Date();
                        String date = sdfDate.format(now);
                        String fileName = new File(item.getName()).getName();

                        fileName = date.replace(" ", "_").replace("-","").replace(":","")+"."+ FilenameUtils.getExtension(fileName);
                        String fromProjectAvatarPath = getPathToFile(contactId);

                        String filePath = uploadPath + fromProjectAvatarPath + File.separator + fileName;
                        createDirectoryIfNotExists(uploadPath+ fromProjectAvatarPath);
                        File storeFile = new File(filePath);
                        LOGGER.debug("start saving file");
                        item.write(storeFile);
                        LOGGER.debug("end saving file");
                        request.setAttribute("attachmentURL",getPathToFile(contactId) + File.separator + fileName);
                        request.setAttribute("attachmentLoaded","true");
                        request.setAttribute("attachmentDate",date);
                        request.setAttribute("attachmentComment",comment);
                        request.setAttribute("attachmentFileName",filename);

                    }
                }
            }

        }catch (Exception e){
           LOGGER.error("something bad happened while uploading",e);
        }
        // redirects client to message page
        request.getRequestDispatcher("/jsp/partial/attachment/attachmentAnswer.jsp").forward(
                request, response);

    }
    private String getPathToFile(String contactId){
        return File.separator + UPLOAD_DIRECTORY+ File.separator+ATTACHMENT_DIRECTORY + File.separator + contactId;
    }
}

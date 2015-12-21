package com.itechart.app.controller.helpers;

import com.itechart.app.controller.FrontController;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;

/**
 * Created by Maxim on 12/15/2015.
 */
public class Upload {
    protected static final String CONTACT_BOOK = "/ContactBook";
    protected static final String UPLOAD_DIRECTORY = "static" + File.separator+"contact_files";
    protected static final String AVATAR_DIRECTORY = "avatar";
    protected static final String ATTACHMENT_DIRECTORY = "attachment";

    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 5;  // 5MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    protected ServletFileUpload getFileUpload(){
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        return upload;
    }

    protected void createDirectoryIfNotExists(String uploadPath){
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
    }


    public static String getUploadDirectoryPath(){
        return FrontController.INSTANCE.getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
    }
    public static String getAttachmentDirectoryPath(){
        return FrontController.INSTANCE.getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY + File.separator + ATTACHMENT_DIRECTORY;
    }

    public static String getRealAttachmentPath(String contactId,String fileName){
        return getAttachmentDirectoryPath()+ File.separator + contactId + File.separator + fileName;
    }


}

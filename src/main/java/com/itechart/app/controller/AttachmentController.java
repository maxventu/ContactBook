package com.itechart.app.controller;

import com.itechart.app.controller.helpers.AttachmentHelper;
import com.itechart.app.controller.helpers.Controller;
import com.itechart.app.controller.helpers.DateHelper;
import com.itechart.app.controller.helpers.Upload;
import com.itechart.app.dao.AttachmentDAO;
import com.itechart.app.dao.ContactDAO;
import com.itechart.app.entity.Attachment;
import com.itechart.app.entity.Contact;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Maxim on 12/15/2015.
 */
public class AttachmentController extends Upload implements Controller {
    final Logger LOGGER = LoggerFactory.getLogger(AttachmentController.class);
    // upload settings
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String contactId = request.getParameter("id");

        if(contactId!=null && !"".equals(contactId)) {
            if("/attachments".equals(request.getPathInfo()))showAttachments(request, response, contactId);
            //else if("/upload_attachment".equals(request.getPathInfo()))uploadAttachment(request,response,Integer.parseInt(contactId));
            else downloadAttachment(request, response, contactId);
        }
        else if("/upload_attachment".equals(request.getPathInfo())){
            uploadAttachment(request,response);
        }
    }

    private void showAttachments(HttpServletRequest request, HttpServletResponse response,String contactId) throws ServletException, IOException {
        Integer contId = Integer.parseInt(contactId);
        Collection<Attachment> attachments = AttachmentDAO.INSTANCE.findAllByContactId(contId);
        Contact contact = ContactDAO.INSTANCE.findEntityById(contId);
        request.setAttribute("attachments",attachments);
        request.setAttribute("contact",contact);
        request.getRequestDispatcher("/static/jsp/attachments.jsp").forward(
                request, response);
    }

    private void downloadAttachment(HttpServletRequest request, HttpServletResponse response, String contactId) throws IOException, ServletException {
        String fileId = request.getParameter("file");
        if(fileId!=null && !"".equals(fileId))
        {
            LOGGER.debug("show attachment contact id={}, file id={}",contactId,fileId);
            Integer id = Integer.parseInt(fileId);
            Attachment attachment = AttachmentDAO.INSTANCE.findEntityById(id);
            File file = AttachmentHelper.INSTANCE.getFile(getAttachmentDirectoryPath() + File.separator + contactId, attachment.getDateUploadAsId());
            ServletOutputStream out = AttachmentHelper.INSTANCE.getOutputStream(response, "application/octet-stream", attachment.getFilename() + "." + FilenameUtils.getExtension(file.getName()));
            byte[] buf = new byte[8192];

            FileInputStream is = new FileInputStream(file);
            int c = 0;

            while ((c = is.read(buf, 0, buf.length)) > 0) {
                out.write(buf, 0, c);
                out.flush();
            }

            out.close();
            is.close();
        }
    }

    private void uploadAttachment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("attachment starts uploading");
        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must have enctype=multipart/form-data.");
            writer.flush();
            return;
        }
        ServletFileUpload upload = getFileUpload();

        String comment="";
        String filename="";
        Integer contId = null;


        try{
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (item.isFormField()) {
                        if("attachment_filename".equals(item.getFieldName())){
                            filename = item.getString("UTF-8");
                        }
                        else if("attachment_comment".equals(item.getFieldName())){
                            comment = item.getString("UTF-8");
                        }
                        else if("cont_id".equals(item.getFieldName())){
                            contId = Integer.parseInt(item.getString("UTF-8"));
                        }
                    }
                }
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        Date now = new Date();
                        String date = DateHelper.INSTANCE.getStringDate(now);
                        String fileName = new File(item.getName()).getName();

                        if(contId==null)contId = ContactDAO.INSTANCE.maxRow()+1;
                        fileName = DateHelper.INSTANCE.getDateId(now)+"."+ FilenameUtils.getExtension(fileName);
                        String filePath = getAttachmentDirectoryPath()+ File.separator + contId;
                        createDirectoryIfNotExists(filePath);
                        File storeFile = new File(getRealAttachmentPath(""+contId,fileName));
                        LOGGER.debug("start saving file");
                        item.write(storeFile);
                        LOGGER.debug("end saving file");
                        //request.setAttribute("attachmentURL",getPathToFile(contactId,fileName));
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
        request.getRequestDispatcher("/static/jsp/partial/attachment/attachmentAnswer.jsp").forward(
                request, response);

    }


}

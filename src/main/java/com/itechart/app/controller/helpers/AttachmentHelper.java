package com.itechart.app.controller.helpers;

import com.itechart.app.dao.AttachmentDAO;
import com.itechart.app.entity.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Maxim on 12/16/2015.
 */
public class AttachmentHelper extends AbstractHelper {
    final Logger LOGGER = LoggerFactory.getLogger(AttachmentHelper.class);


    public static final AttachmentHelper INSTANCE = new AttachmentHelper();

    private AttachmentHelper(){}

    public void processAttachments(HttpServletRequest request,Integer contactId){
        LOGGER.debug("processing attachments");
        ArrayList<Integer> att_id = getIntListFromArray(request.getParameterValues("att_id"));
        String[] att_filename = request.getParameterValues("att_filename");
        String[] att_date_upload = request.getParameterValues("att_date_upload");
        String[] att_comment = request.getParameterValues("att_comment");
        HashMap<Integer,Attachment> attachmentHashMap = new HashMap<Integer, Attachment>();
        for(int i=0;i<att_id.size();i++)
            attachmentHashMap.put(att_id.get(i), new Attachment(att_id.get(i), att_filename[i],
                    DateHelper.INSTANCE.getDateFromString(att_date_upload[i]), att_comment[i], contactId));

        ArrayList<Attachment> newAttachmentsArray = getAttachmentsByCriterionFromRequest("newAttachments", request, attachmentHashMap);
        ArrayList<Attachment> updatedAttachmentsArray = getAttachmentsByCriterionFromRequest("updatedAttachments", request, attachmentHashMap);

        deleteAttachments(getIntListFromArray(request.getParameterValues("deletedAttachments")));
        createAttachments(newAttachmentsArray);
        updateAttachments(updatedAttachmentsArray);
    }

    private void deleteAttachments(ArrayList<Integer> attachment_ids){
        LOGGER.debug("deleting attachments");
        for(Integer id : attachment_ids)
        {
            deleteAttachmentFromDisk(id);
            AttachmentDAO.INSTANCE.delete(id);
        }

    }

    private void deleteAttachmentFromDisk(Integer id){
        Attachment att = AttachmentDAO.INSTANCE.findEntityById(id);
        File file = null;
        if(att!=null)
            file = AttachmentHelper.INSTANCE.getFile(Upload.getAttachmentDirectoryPath() + File.separator + att.getContactId(), att.getDateUploadAsId());
        if(file!=null)
            file.delete();
    }


    private void updateAttachments(ArrayList<Attachment> attachments){
        LOGGER.debug("updating attachments");
        for(Attachment att : attachments)
            AttachmentDAO.INSTANCE.update(att);
    }
    private void createAttachments(ArrayList<Attachment> attachments){
        LOGGER.debug("creating attachments");
        Integer i = AttachmentDAO.INSTANCE.maxRow();
        i++;
        for(Attachment att : attachments)
        {
            att.setId(i);
            AttachmentDAO.INSTANCE.create(att);
            i++;
        }
    }

    private ArrayList<Attachment> getAttachmentsByCriterionFromRequest(String criterion,HttpServletRequest request, HashMap<Integer,Attachment> attachmentHashMap){
        return getSpecificAttachmentsArray(getIntListFromArray(request.getParameterValues(criterion)),attachmentHashMap);
    }

    private ArrayList<Attachment> getSpecificAttachmentsArray(ArrayList<Integer> ids, HashMap<Integer,Attachment> attachmentHashMap){
        ArrayList<Attachment> specificArray = new ArrayList<Attachment>();
        Attachment attachment = null;
        for(Integer id : ids){
            attachment = attachmentHashMap.get(id);
            if(attachment!=null)specificArray.add(attachment);
        }
        return specificArray;
    }
    public ServletOutputStream getOutputStream(HttpServletResponse response,String contentType,String headerFileName) throws IOException {
        response.setContentType(contentType);
        response.setHeader("Content-Disposition","attachment;filename="+headerFileName);
        return response.getOutputStream();
    }
    public File getFile(String directory,String filename){
        File dir = new File(directory);
        File[] listOfFiles = dir.listFiles();
        if(filename!=null && !"".equals(filename))
        for(File f:listOfFiles) {
            if(f.getName().startsWith(filename))return f;
        }
        return null;
    }

}

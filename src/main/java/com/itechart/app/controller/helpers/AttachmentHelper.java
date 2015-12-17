package com.itechart.app.controller.helpers;

import com.itechart.app.dao.AttachmentDAO;
import com.itechart.app.entity.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
            attachmentHashMap.put(att_id.get(i), new Attachment(att_id.get(i), att_filename[i], getDateFromString(att_date_upload[i]), att_comment[i], contactId));

        ArrayList<Attachment> newAttachmentsArray = getAttachmentsByCriterionFromRequest("newAttachments", request, attachmentHashMap);
        ArrayList<Attachment> updatedAttachmentsArray = getAttachmentsByCriterionFromRequest("newAttachments", request, attachmentHashMap);

        deleteAttachments(getIntListFromArray(request.getParameterValues("deletedAttachments")));
        createAttachments(newAttachmentsArray);
        updateAttachments(updatedAttachmentsArray);
    }

    private void deleteAttachments(ArrayList<Integer> attachment_ids){
        LOGGER.debug("deleting attachments");
        for(Integer id : attachment_ids)
            AttachmentDAO.INSTANCE.delete(id);
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
    private Date getDateFromString(String string){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=null;
        try {
            date = format.parse(string);
        } catch (ParseException e) {
            LOGGER.error("error while getting date from string",e);
        }
        return date;
    }
}

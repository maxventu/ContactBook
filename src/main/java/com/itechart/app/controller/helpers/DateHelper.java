package com.itechart.app.controller.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Maxim on 12/17/2015.
 */
public class DateHelper {
    final Logger LOGGER = LoggerFactory.getLogger(DateHelper.class);
    public static final DateHelper INSTANCE = new DateHelper();
    private DateHelper(){}

    public Timestamp getTimestamp(Date date){
        LOGGER.debug("getting timestamp for {}",date);
        if(date!=null)
            return new Timestamp(date.getTime());
        return null;
    }

    public Date getDate(Timestamp timestamp){
        LOGGER.debug("getting date for {}",timestamp);
        if(timestamp!=null)
            return new Date(timestamp.getTime());
        return null;
    }

    public Date getDateOfBirth(String dateString){
        LOGGER.debug("getting date for {}",dateString);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            if(dateString!=null && !"".equals(dateString))
                date = df.parse(dateString);
        } catch (ParseException e) {
            LOGGER.error("parsing date of birth",e);
        }
        return date;
    }

    public java.sql.Date getSqlDate(Date date){
        LOGGER.debug("getting sqlDate for {}",date);
        if (date!=null)
            return new java.sql.Date(date.getTime());
        return null;
    }

    public String getStringDate(Date date){
        LOGGER.debug("getting string date for {}",date);
        if(date != null){
            SimpleDateFormat sdfDate = new SimpleDateFormat("mm:ss dd-MM-yyyy");
            return sdfDate.format(date);
        }
        return null;
    }

    public String getDateId(Date date){
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmssSS");
        return sdfDate.format(date);
    }
}

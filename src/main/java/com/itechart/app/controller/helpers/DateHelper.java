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
        if(date!=null){
            LOGGER.debug("getting timestamp for {}",date);
            return new Timestamp(date.getTime());
        }
        return null;
    }

    public Date getDate(Timestamp timestamp){
        if(timestamp!=null){
            LOGGER.debug("getting date for {}",timestamp);
            return new Date(timestamp.getTime());
        }
        return null;
    }

    public Date getDateOfBirth(String dateString){

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            if(dateString!=null && !"".equals(dateString))
            {
                LOGGER.debug("getting date for {}",dateString);
                date = df.parse(dateString);
            }
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
        if(date != null){
            LOGGER.debug("getting string date for {}",date);
            SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
            return sdfDate.format(date);
        }
        return null;
    }

    public String getDateId(Date date){
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdfDate.format(date);
    }

    public Date getDateFromString(String string){
        DateFormat format = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        Date date=null;
        try {
            if(string!=null && !"".equals(string))
            date = format.parse(string);
        } catch (ParseException e) {
            LOGGER.error("error while getting date from string",e);
        }
        return date;
    }
}

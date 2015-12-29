package com.itechart.app.controller;

import com.itechart.app.dao.ContactDAO;
import com.itechart.app.entity.Contact;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.util.ArrayList;

import static org.quartz.CronScheduleBuilder.dailyAtHourAndMinute;
import static org.quartz.DateBuilder.evenHourDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by Maxim on 12/21/2015.
 */
public class MyScheduler implements Job {
    final Logger log = LoggerFactory.getLogger(MyScheduler.class);
    private Scheduler scheduler;
    private ServletContext context;
    public void init(ServletContext context){
        this.context = context;
        if(scheduler==null)
        try{
            log.debug("start initializing");
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(MyScheduler.class)
                .withIdentity("myJob", "group1")
                .build();
        // Trigger the job to run now, and then every 40 seconds
            /*Trigger trigger = newTrigger()
                    .withIdentity("myTrigger", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(40)
                            .repeatForever())
                    .build();*/
            Trigger trigger = newTrigger()
                    .withIdentity("trigger3", "group1")
                    .withSchedule(dailyAtHourAndMinute(15, 0))
                    .forJob(job)
                    .build();
            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);
        }
        catch (Exception ex) {
            log.error("something bad happened while initializing scheduler", ex);
        }
    }


    public void finish() {
if(scheduler!=null)
        try {
            scheduler.shutdown();
        } catch (Exception e) {
            log.error("something bad happened while shutting down scheduler",e);
        }
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ArrayList<Contact> contacts = ContactDAO.INSTANCE.getContactsByBirthday();
        if(contacts.size()>0){
            EmailController.INSTANCE.sendEmailNotification(context,contacts);
        }
        log.debug("scheduler works fine");
    }
}

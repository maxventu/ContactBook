package com.itechart.app.controller;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public static MyScheduler INSTANCE;
    private Scheduler scheduler;

    public void init(){
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
            Trigger trigger = newTrigger()
                    .withIdentity("myTrigger", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(40)
                            .repeatForever())
                    .build();
            /*trigger = newTrigger()
                    .withIdentity("trigger3", "group1")
                    .withSchedule(dailyAtHourAndMinute(3, 42))
                    .forJob(job)
                    .build();*/
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
        log.debug("scheduler works fine");
    }
}

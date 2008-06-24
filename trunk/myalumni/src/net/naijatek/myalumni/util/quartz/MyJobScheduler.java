/* 
 * Copyright 2005 OpenSymphony 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may obtain a copy 
 * of the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations 
 * under the License.
 * 
 */

package net.naijatek.myalumni.util.quartz;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.impl.StdSchedulerFactory;


/**
 * This is scheduling a Quartz using Cron Triggers.
 *
 * @author Bill Kratzer
 */
public class MyJobScheduler {

    private SchedulerFactory sf = null;
    private Scheduler sched = null;
    private static Log log = LogFactory.getLog(MyJobScheduler.class);


    public MyJobScheduler() {
    }


    /**
     * 	Expression  	   	Meaning
		"0 0 12 * * ?" 	  			Fire at 12pm (noon) every day
		"0 15 10 ? * *" 	  		Fire at 10:15am every day
		"0 15 10 * * ?" 	  		Fire at 10:15am every day
		"0 15 10 * * ? *" 	  		Fire at 10:15am every day
		"0 15 10 * * ? 2005" 	  	Fire at 10:15am every day during the year 2005
		"0 * 14 * * ?" 	  			Fire every minute starting at 2pm and ending at 2:59pm, every day
		"0 0/5 14 * * ?" 	  		Fire every 5 minutes starting at 2pm and ending at 2:55pm, every day
		"0 0/5 14,18 * * ?" 	  	Fire every 5 minutes starting at 2pm and ending at 2:55pm, AND fire every 5 minutes starting at 6pm and ending at 6:55pm, every day
		"0 0-5 14 * * ?" 	  		Fire every minute starting at 2pm and ending at 2:05pm, every day
		"0 10,44 14 ? 3 WED" 	  	Fire at 2:10pm and at 2:44pm every Wednesday in the month of March.
		"0 15 10 ? * MON-FRI" 	  	Fire at 10:15am every Monday, Tuesday, Wednesday, Thursday and Friday
		"0 15 10 15 * ?" 	  		Fire at 10:15am on the 15th day of every month
		"0 15 10 L * ?" 	  		Fire at 10:15am on the last day of every month
		"0 15 10 ? * 6L" 	  		Fire at 10:15am on the last Friday of every month
		"0 15 10 ? * 6L" 	  		Fire at 10:15am on the last Friday of every month
		"0 15 10 ? * 6L 2002-2005" 	Fire at 10:15am on every last friday of every month during the years 2002, 2003, 2004 and 2005
		"0 15 10 ? * 6#3" 	  		Fire at 10:15am on the third Friday of every month
     * @throws Exception
     */
    public void run() throws Exception {

        log.debug("------- Initializing -------------------");

        // First we must get a reference to a scheduler
        sf = new StdSchedulerFactory();
        sched = sf.getScheduler();

        log.debug("------- Initialization Complete --------");

        log.debug("------- Scheduling Jobs ----------------");

        // jobs can be scheduled before sched.start() has been called

        // job 1 will run every day at 2AM
        JobDetail job = new JobDetail("BirthdayWisher", "myalumni", BirthdayWishJob.class);
        //CronTrigger trigger = new CronTrigger("trigger1", "myalumni", "BirthdayWisher", "myalumni", "0 0 2am * * ?");
        CronTrigger trigger = new CronTrigger("trigger1", "myalumni", "BirthdayWisher", "myalumni", "0 16 23 ? * *");
        
        sched.addJob(job, true);
        Date ft = sched.scheduleJob(trigger);
        log.info("===>" + job.getFullName() + " has been scheduled to run at: " + ft + " and repeat based on expression: " +  trigger.getCronExpression());

        log.debug("------- Starting Scheduler ----------------");

        // All of the jobs have been added to the scheduler, but none of the
        // jobs
        // will run until the scheduler has been started
        sched.start();

        log.info("------- Started Scheduler -----------------");

        //log.info("------- Shutting Down ---------------------");
        //sched.shutdown(true);
        //log.info("------- Shutdown Complete -----------------");

        SchedulerMetaData metaData = sched.getMetaData();
        log.info("Executed " + metaData.numJobsExecuted() + " jobs.");

    }

    public void stopJob(final boolean flag) {
        try {
            // sleep for 10 seconds
            //Thread.sleep(10L * 1000L);
            log.debug("------- Shutting Down ---------------------");
            sched.shutdown(flag);
            log.debug("------- Shutdown Complete -----------------");
        } catch (SchedulerException sce) {
            log.fatal("Error in starting job(s)... " + sce.getMessage());
        } catch (Exception e) {
            log.fatal("Error in starting job(s)... " + e.getMessage());
        }
    }


    public void startJob() {
        try {
            // sleep for 60 seconds
            //Thread.sleep(60L * 1000L);
            log.debug("------- Starting Scheduler ----------------");
            // jobs don't start firing until start() has been called...
            sched.start();
            log.debug("------- Started Scheduler -----------------");
        } catch (SchedulerException sce) {
            log.fatal("Error in starting job(s)... " + sce.getMessage());
        } catch (Exception e) {
            log.fatal("Error in starting job(s)... " + e.getMessage());
        }
    }


    public boolean isShutdown() {
    boolean status ;
        try {
            log.debug("------- Checking if it is shutdown -----------------");
            status =  sched.isShutdown();
        } catch (Exception e) {
            status = true;
            log.fatal("Error in isShutdown ... " + e.getMessage());
        }
        return status;
    }


    public static void main(final String[] args) throws Exception {

        MyJobScheduler example = new MyJobScheduler();
        example.run();
        log.debug("end...");
    }

}

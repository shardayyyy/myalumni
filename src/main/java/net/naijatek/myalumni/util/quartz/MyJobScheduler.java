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
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;


public class MyJobScheduler {

    private SchedulerFactory sf = null;
    private Scheduler sched = null;
    private static Log logger = LogFactory.getLog(MyJobScheduler.class);


    public MyJobScheduler() {
    }


    /**
     * 
     * 	Field Name 	 Position 	 Mandatory 	 Allowed Values 	 Special Characters
		Seconds 		1 			Yes 		0-59 				, - * /
		Minutes 		2 			Yes 		0-59 				, - * /
		Hours 			3 			Yes 		1-23 				, - * /
		Day of Month 	4 			Yes 		1-31 				, - * ? / L W C
		Month 			5 			Yes 		1-12 or JAN-DEC 		, - * /
		Day of Week 	6 			Yes 		1-7 or SUN-SAT 		, - * ? / L C #
		Year 			7 			No 			empty, 1970-2099 	, - * / 
     * 
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

    	logger.debug("------- Initializing -------------------");

        // First we must get a reference to a scheduler
        sf = new StdSchedulerFactory();
        sched = sf.getScheduler();

        logger.debug("------- Scheduling Jobs ----------------");

        // jobs can be scheduled before sched.start() has been called

        // job 1 will run every day at 2AM
        JobDetail job = new JobDetail("BirthdayWisher", "myalumni", BirthdayWishJob.class);
        //CronTrigger trigger = new CronTrigger("trigger1", "myalumni", "BirthdayWisher", "myalumni", "0 5 1 * * ?");
        CronTrigger trigger = new CronTrigger("trigger1", "myalumni", "BirthdayWisher", "myalumni", "0 0-15 14 * * ?");
        
        sched.addJob(job, true);
        Date ft = sched.scheduleJob(trigger);
        logger.info("===>" + job.getFullName() + " has been scheduled to run at: " + ft + " and repeat based on expression: " +  trigger.getCronExpression());

        logger.debug("------- Starting Scheduler ----------------");

        // All of the jobs have been added to the scheduler, but none of the
        // jobs
        // will run until the scheduler has been started
        sched.start();

        logger.info("------- Started Scheduler -----------------");


    }

//    public void stopJob(final boolean flag) {
//        try {
//            // sleep for 10 seconds
//            //Thread.sleep(10L * 1000L);
//            logger.debug("------- Shutting Down ---------------------");
//            sched.shutdown(flag);
//            logger.debug("------- Shutdown Complete -----------------");
//        } catch (SchedulerException sce) {
//        	logger.fatal("Error in starting job(s)... " + sce.getMessage());
//        } catch (Exception e) {
//        	logger.fatal("Error in starting job(s)... " + e.getMessage());
//        }
//    }
//
//
//    public void startJob() {
//        try {
//            // sleep for 60 seconds
//            //Thread.sleep(60L * 1000L);
//        	logger.debug("------- Starting Scheduler ----------------");
//            // jobs don't start firing until start() has been called...
//            sched.start();
//            logger.debug("------- Started Scheduler -----------------");
//        } catch (SchedulerException sce) {
//        	logger.fatal("Error in starting job(s)... " + sce.getMessage());
//        } catch (Exception e) {
//        	logger.fatal("Error in starting job(s)... " + e.getMessage());
//        }
//    }


//    public boolean isShutdown() {
//    boolean status ;
//        try {
//        	logger.debug("------- Checking if it is shutdown -----------------");
//            status =  sched.isShutdown();
//        } catch (Exception e) {
//            status = true;
//            logger.fatal("Error in isShutdown ... " + e.getMessage());
//        }
//        return status;
//    }
//
//
//    public static void main(final String[] args) throws Exception {
//
//        MyJobScheduler example = new MyJobScheduler();
//        example.run();
//        logger.debug("end...");
//    }

}

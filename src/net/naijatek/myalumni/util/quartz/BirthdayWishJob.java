/*
 * ====================================================================
 * Copyright (C) 1997-2008 by Naijatek.com
 *
 * All copyright notices regarding MyAlumni MUST remain 
 * intact in the scripts and in the outputted HTML.
 * The "powered by" text/logo with a link back to
 * http://www.naijatek.com in 
 * the footer of the pages MUST remain visible when the pages
 * are viewed on the internet or intranet.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * Support can be obtained from support forums at:
 * http://www.naijatek.com/myalumni/forum
 *
 * Correspondence and Marketing Questions can be sent to:
 * info at naijatek com
 *
 * <p>Title: MyAlumni </p>
 * <p>Description: This system helps keep alive the line of communications between alumni/alumnus</p>
 * <p>Copyright: Copyright (c) 1997-2008</p>
 * <p>Company: Naijatek Solutions (http://www.naijatek.com)</p>
 * @author Folashade Adeyosoye (shardayyy@naijatek.com)
 * @version 1.0
 */

package net.naijatek.myalumni.util.quartz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.domain.SystemConfigVO;
import net.naijatek.myalumni.modules.common.service.IMemberService;
import net.naijatek.myalumni.modules.common.service.ISystemConfigService;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.mail.SendMailUtil;
import net.naijatek.myalumni.util.utilities.StringUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoaderServlet;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * <p>
 * This is just a simple job that gets fired off many times by example 1
 * </p>
 *
 */
public class BirthdayWishJob extends ContextLoaderServlet implements Job {

	private static Log logger = LogFactory.getLog(BirthdayWishJob.class);

	/**
	 * Quartz requires a public empty constructor so that the
	 * scheduler can instantiate the class whenever it needs.
	 */
	public BirthdayWishJob() {
	}

	/**
	 * <p>
	 * Called by the <code>{@link org.quartz.Scheduler}</code> when a
	 * <code>{@link org.quartz.Trigger}</code> fires that is associated with
	 * the <code>Job</code>.
	 * </p>
	 * 
	 * @throws JobExecutionException
	 *             if there is an exception while executing the job.
	 */
	public void execute(JobExecutionContext context)
			throws JobExecutionException {

        try{
                    WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
                    IMemberService memService = (IMemberService) wac.getBean(BaseConstants.SERVICE_MEMBER_LOOKUP);  
                    ISystemConfigService sysConfigService = (ISystemConfigService) wac.getBean(BaseConstants.SERVICE_SYSTEM_CONFIG);
            
                    SystemConfigVO sysConfigVO = sysConfigService.getSystemConfig();
            
                    // This job simply prints out its job name and the
                    // date and time that it is running
                    //String jobName = context.getJobDetail().getFullName();
                    //logger.info("===> BirthdayWishJob says: " + jobName + " executing at " + new Date());
            
                    Calendar now = new GregorianCalendar();
                    int today = now.get(java.util.Calendar.DATE);
            
                    // get the list of birthday people
                    List<MemberVO> members = new ArrayList<MemberVO>();
                    members = memService.getTodayBirthdayMembers(StringUtil.convertToAlphaMonth(now.get(Calendar.MONTH) + 1), String.valueOf(today));
                    System.out.println("Getting memebrs...");
                    for (MemberVO memberVO : members ){
                    	if (memberVO.getEmail() != null && memberVO.getEmail().length() > 0){
                    		//SendMailUtil.sendBirthdayWish(memberVO, sysConfigVO);
                    		System.out.println("Sent birthday wishes to " + memberVO.getFullName());
                    	}
                    }
                    
        }
        catch (Exception e ){
            logger.fatal("Schedular failed. " + e.getMessage());
            e.printStackTrace();
        }
               
	}
        
        
 

}
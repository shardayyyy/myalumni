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
package net.naijatek.myalumni.framework.spring;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import net.naijatek.myalumni.modules.common.domain.*;
import net.naijatek.myalumni.modules.common.helper.DropDownCacheBuilder;
import net.naijatek.myalumni.modules.common.service.ISystemConfigService;
import net.naijatek.myalumni.modules.common.service.ISystemTaskService;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.SystemConfigConstants;
import net.naijatek.myalumni.util.quartz.MyJobScheduler;
import net.naijatek.myalumni.util.utilities.AppProp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ContextLoaderServlet;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Controller
public class MyAlumniStartupServlet implements ApplicationContextAware {

	private static Log logger = LogFactory.getLog(MyAlumniStartupServlet.class);

	private final AppProp appProp = AppProp.getInstance();

	private static Appliactioncontext ctx;
	
	
    // @see:
    // net.naijatek.myalumni.util.date.DateConverterUtil::getDatePattern()
    private String dateFormatPattern = SystemConfigConstants.DATE_FORMAT_JSP;
    private String dateTimeFormatPattern = SystemConfigConstants.DATE_TIME_FORMAT_JSP;

    private String ORGANIZATION_NAME = "";
    private String ORGANIZATION_SHORT_NAME  = "";
    private String ORG_EMAIL = "";
    private String ORGANIZATION_ABOUTUS = "";
    private String ORGANIZATION_INTRO = "";
    private String ALBUM_URL = "";
    private String FORUM_URL = "";
    private String SERVER_URL = "";
    private String LOGO_NAME = "";
    private String CSS_TYPE = "";
    private String hasDorm = "";
    private String FIRST_STARTUP = "";
    private ScrollVO luScrollVO = new ScrollVO();

    private List<DropDownVO> luAdminSearchCategory = new ArrayList<DropDownVO>();
    private List<DropDownVO> luSearchCategory = new ArrayList<DropDownVO>();



    private List<DropDownVO> luGender = new ArrayList<DropDownVO>();
    private List<DropDownVO> luAdminAction = new ArrayList<DropDownVO>();
    private List<DropDownVO> luApprovalAction = new ArrayList<DropDownVO>();
    private List<DropDownVO> luAccountStatus = new ArrayList<DropDownVO>();
    private List<DropDownVO> luStatus = new ArrayList<DropDownVO>();

    @Autowired
    private ISystemConfigService sysConfigService;

    @Autowired
    private ISystemTaskService startupService;

	//private MyJobScheduler sched = null;


    @Override
    public setApplicationcontext(ApplicationContext appContext) throws BeansException{
    	ctx = appContext;
    }
    
    @override
    public static ApplicationContext getApplicationContext(){
    	return ctx;
    }
    
    
    
    
    @PostConstruct
	protected void initSystem() throws ServletException {
		// Initialize the persistence service
		
		try {
			//sched = new MyJobScheduler();

			// Starting the schedular jobs
			//sched.run();
			
			setupCache();
			
		} catch (Exception e) {
			logger.fatal("ERROR - in initSystem(), exception is thrown - "
					+ e.toString());
			e.printStackTrace();
			throw new ServletException(e.toString());
		}
	}



	private void setupCache() throws Exception {

		SystemConfigVO sysConfigVO = sysConfigService.getSystemConfig();

        ORGANIZATION_NAME = sysConfigVO.getOrganizationName();
        ORGANIZATION_SHORT_NAME = sysConfigVO.getOrganizationShortName();
        ORG_EMAIL = sysConfigVO.getOrgEmail();
        ALBUM_URL = sysConfigVO.getAlbumUrl();
        FORUM_URL = sysConfigVO.getForumUrl();
        SERVER_URL = sysConfigVO.getServerUrl();
        LOGO_NAME = sysConfigVO.getLogoFileName();
        CSS_TYPE = "default";

		
		hasDorm = sysConfigVO.getHasDormitory();

        if (sysConfigVO.getHasDormitory() == null || sysConfigVO.getHasDormitory().length() == 0) {
            hasDorm = BaseConstants.BOOLEAN_NO;
        }

		setupDatabaseCache(startupService, sysConfigService, hasDorm);
		setupOtherCache(hasDorm);
		
		if (sysConfigVO.getSystemConfigId() == null){
			FIRST_STARTUP = BaseConstants.BOOLEAN_YES;
		}
		else{
            FIRST_STARTUP = BaseConstants.BOOLEAN_NO;
		}
		
	}

	/**
	 * Loads static information such as drop down menu's into the application
	 * context
	 * 
	 * @throws Exception
	 */
	private void setupDatabaseCache(final ISystemTaskService startupService,
			final ISystemConfigService sysConfigService, String hasDorm)
			throws Exception {

		// load the scroll
        luScrollVO = sysConfigService.getLatestScroll();





		logger.debug("Database Cache loaded...");
	}

	/**
	 * Loads static information such as drop down menu's into the application
	 * context
	 * 
	 * @throws Exception
	 */
	private void setupOtherCache(final String hasDormitory) throws Exception {

		DropDownCacheBuilder ddb = new DropDownCacheBuilder();
        luAdminSearchCategory = ddb.buildSearchOptions(hasDormitory, true);
        luSearchCategory = ddb.buildSearchOptions(hasDormitory, false);

		// --------------------------------------------------




        // Gender
        luGender.add(new DropDownVO(appProp.getValue("label.male"), BaseConstants.GENDER_MALE));
        luGender.add(new DropDownVO(appProp.getValue("label.female"),BaseConstants.GENDER_FEMALE));
        luGender.add(new DropDownVO(appProp.getValue("label.unknown"),BaseConstants.GENDER_UNKNOWN));


		// Search Category
        luAdminAction.add(new DropDownVO(BaseConstants.ADMIN_ACTION_DELETE,BaseConstants.ADMIN_ACTION_DELETE));
        luAdminAction.add(new DropDownVO(BaseConstants.ADMIN_ACTION_ACTIVATE,BaseConstants.ADMIN_ACTION_ACTIVATE));
        luAdminAction.add(new DropDownVO(BaseConstants.ADMIN_ACTION_MODIFY,BaseConstants.ADMIN_ACTION_MODIFY));
        luAdminAction.add(new DropDownVO(BaseConstants.ADMIN_ACTION_DEACTIVATE,BaseConstants.ADMIN_ACTION_DEACTIVATE));
        luAdminAction.add(new DropDownVO(BaseConstants.ADMIN_ACTION_LOCK,BaseConstants.ADMIN_ACTION_LOCK));


		// Approval Category
        luApprovalAction.add(new DropDownVO(BaseConstants.MODIFY_IT,BaseConstants.MODIFY_IT));
        luApprovalAction.add(new DropDownVO(BaseConstants.APPROVE_IT,BaseConstants.APPROVE_IT));
        luApprovalAction.add(new DropDownVO(BaseConstants.DEACTIVATE_IT,BaseConstants.DEACTIVATE_IT));
        luApprovalAction.add(new DropDownVO(BaseConstants.DECLINE_IT,BaseConstants.DECLINE_IT));
        luApprovalAction.add(new DropDownVO(BaseConstants.DELETE_IT,BaseConstants.DELETE_IT));




		// Account Status
        luAccountStatus.add(new DropDownVO(appProp.getValue("label.statusactive"), BaseConstants.ACCOUNT_ACTIVE));
        luAccountStatus.add(new DropDownVO(appProp.getValue("label.accountdeactivated"),BaseConstants.ACCOUNT_DEACTIVATED));
        luAccountStatus.add(new DropDownVO(appProp.getValue("label.accountlocked"),BaseConstants.ACCOUNT_LOCKED));
        luAccountStatus.add(new DropDownVO(appProp.getValue("label.accountnew"),BaseConstants.ACCOUNT_UNAPPROVED));


		// Status
        luStatus.add(new DropDownVO(appProp.getValue("label.statusactive"),BaseConstants.ACTIVE));
        luStatus.add(new DropDownVO(appProp.getValue("label.statusinactive"),BaseConstants.INACTIVE));


		logger.debug("Other Cache loaded...");
	}

}

/*
 * ====================================================================
 * Copyright (C) 1997-2008 by Naijatek.com
 *
 * All copyright notices regarding MyAlumni Board MUST remain 
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
 * <p>Title: MyAlumni Board </p>
 * <p>Description: This system helps keep alive the line of communications between alumni/alumnus</p>
 * <p>Copyright: Copyright (c) 1997-2008</p>
 * <p>Company: Naijatek Solutions (http://www.naijatek.com)</p>
 * @author Folashade Adeyosoye (shardayyy@naijatek.com)
 * @version 1.0
 */
package net.naijatek.myalumni.framework.spring;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import net.naijatek.myalumni.modules.common.domain.ScrollVO;
import net.naijatek.myalumni.modules.common.domain.SystemConfigVO;
import net.naijatek.myalumni.modules.common.helper.DropDownCacheBuilder;
import net.naijatek.myalumni.modules.common.service.ISystemConfigService;
import net.naijatek.myalumni.modules.common.service.ISystemTaskService;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.SystemConfigConstants;
import net.naijatek.myalumni.util.quartz.MyJobScheduler;
import net.naijatek.myalumni.util.utilities.AppProp;
import net.naijatek.myalumni.util.utilities.SystemProp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;
import org.springframework.web.context.ContextLoaderServlet;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class MyAlumniStartupServlet extends ContextLoaderServlet {

	private static Log logger = LogFactory.getLog(MyAlumniStartupServlet.class);

	private final SystemProp sysProp = SystemProp.getInstance();

	private final AppProp appProp = AppProp.getInstance();

	private MyJobScheduler sched = null;

	public void init() throws ServletException {
		logger.info("MyAlumniStartupServlet init()...");
		super.init();
		initSystem();
	}

	protected void initSystem() throws ServletException {
		// Initialize the persistence service
		String runSchedular = sysProp.getValue("FIRE_SCHEDULER");
		try {
			if (runSchedular.equalsIgnoreCase("true")) {
				sched = new MyJobScheduler();

				// Starting the schedular jobs
				sched.run();
			}
			setupCache();
		} catch (Exception e) {
			logger.fatal("ERROR - in initSystem(), exception is thrown - "
					+ e.toString());
			e.printStackTrace();
			throw new ServletException(e.toString());
		}
	}

	private void setupCache() throws Exception {

		// @see:
		// net.naijatek.myalumni.util.date.DateConverterUtil::getDatePattern()
		getServletContext().setAttribute(
				BaseConstants.LOOKUP_DATE_FORMAT_PATTERN,
				SystemConfigConstants.DATE_FORMAT_JSP);
		getServletContext().setAttribute(
				BaseConstants.LOOKUP_DATE_TIME_FORMAT_PATTERN,
				SystemConfigConstants.DATE_TIME_FORMAT_JSP);

		WebApplicationContext wac = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
		ISystemTaskService startupService = (ISystemTaskService) wac
				.getBean(BaseConstants.SERVICE_SYSTEM_TASK_LOOKUP);
		ISystemConfigService sysConfigService = (ISystemConfigService) wac
				.getBean(BaseConstants.SERVICE_SYSTEM_CONFIG);

		SystemConfigVO sysConfigVO = sysConfigService.getSystemConfig();
		getServletContext().setAttribute(BaseConstants.ORGANIZATION_NAME, sysConfigVO.getOrganizationName());
		getServletContext().setAttribute(BaseConstants.ORGANIZATION_SHORT_NAME, sysConfigVO.getOrganizationShortName());
		getServletContext().setAttribute(BaseConstants.ALBUM_URL, sysConfigVO.getAlbumUrl());
		getServletContext().setAttribute(BaseConstants.FORUM_URL, sysConfigVO.getForumUrl());
		getServletContext().setAttribute(BaseConstants.SERVER_URL, sysConfigVO.getServerUrl());
		getServletContext().setAttribute(BaseConstants.ORG_EMAIL, sysConfigVO.getOrgEmail());
		
		
		String hasDorm = sysConfigVO.getHasDormitory();

		setupDatabaseCache(startupService, sysConfigService, hasDorm);
		setupOtherCache(hasDorm);
		
		if (sysConfigVO.getSystemConfigId() == null){
			getServletContext().setAttribute(BaseConstants.FIRST_STARTUP, BaseConstants.BOOLEAN_YES);
		}
		else{
			getServletContext().setAttribute(BaseConstants.FIRST_STARTUP, BaseConstants.BOOLEAN_NO);
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
		ScrollVO scrollVO = sysConfigService.getLatestScroll();
		getServletContext().setAttribute(BaseConstants.SCROLL_VO, scrollVO);

		if (hasDorm == null || hasDorm.length() == 0) {
			hasDorm = BaseConstants.BOOLEAN_NO;
		}
		getServletContext().setAttribute(BaseConstants.HAS_DORMITORY, hasDorm);

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
		List<LabelValueBean> adminSearchCategory = ddb.buildSearchOptions(
				hasDormitory, true);
		List<LabelValueBean> searchCategory = ddb.buildSearchOptions(
				hasDormitory, false);

		getServletContext()
				.setAttribute(BaseConstants.LIST_OF_ADMIN_SEARCH_OPTIONS,
						adminSearchCategory);
		getServletContext().setAttribute(
				BaseConstants.LIST_OF_MEMBER_SEARCH_OPTIONS, searchCategory);

		// --------------------------------------------------

		// Gender
		List<LabelValueBean> gender = new ArrayList<LabelValueBean>();
		gender.add(new LabelValueBean(appProp.getValue("label.male"),
				BaseConstants.GENDER_MALE));
		gender.add(new LabelValueBean(appProp.getValue("label.female"),
				BaseConstants.GENDER_FEMALE));
		gender.add(new LabelValueBean(appProp.getValue("label.unknown"),
				BaseConstants.GENDER_UNKNOWN));
		getServletContext().setAttribute(BaseConstants.LIST_OF_GENDER_OPTIONS,
				gender);

		// Search Category
		List<LabelValueBean> adminAction = new ArrayList<LabelValueBean>();
		adminAction.add(new LabelValueBean(BaseConstants.ADMIN_ACTION_DELETE,
				BaseConstants.ADMIN_ACTION_DELETE));
		adminAction.add(new LabelValueBean(BaseConstants.ADMIN_ACTION_ACTIVATE,
				BaseConstants.ADMIN_ACTION_ACTIVATE));
		adminAction.add(new LabelValueBean(BaseConstants.ADMIN_ACTION_MODIFY,
				BaseConstants.ADMIN_ACTION_MODIFY));
		adminAction.add(new LabelValueBean(
				BaseConstants.ADMIN_ACTION_DEACTIVATE,
				BaseConstants.ADMIN_ACTION_DEACTIVATE));
		adminAction.add(new LabelValueBean(BaseConstants.ADMIN_ACTION_LOCK,
				BaseConstants.ADMIN_ACTION_LOCK));
		getServletContext().setAttribute(BaseConstants.LIST_OF_ADMIN_OPTIONS,
				adminAction);

		// Approval Category
		List<LabelValueBean> approvalAction = new ArrayList<LabelValueBean>();
		approvalAction.add(new LabelValueBean(BaseConstants.MODIFY_IT,
				BaseConstants.MODIFY_IT));
		approvalAction.add(new LabelValueBean(BaseConstants.APPROVE_IT,
				BaseConstants.APPROVE_IT));
		approvalAction.add(new LabelValueBean(BaseConstants.DEACTIVATE_IT,
				BaseConstants.DEACTIVATE_IT));
		approvalAction.add(new LabelValueBean(BaseConstants.DECLINE_IT,
				BaseConstants.DECLINE_IT));
		approvalAction.add(new LabelValueBean(BaseConstants.DELETE_IT,
				BaseConstants.DELETE_IT));
		getServletContext().setAttribute(
				BaseConstants.LIST_OF_APPROVAL_OPTIONS, approvalAction);

		// Account Status
		List<LabelValueBean> accountStatus = new ArrayList<LabelValueBean>();
		accountStatus.add(new LabelValueBean(appProp
				.getValue("label.statusactive"), BaseConstants.ACCOUNT_ACTIVE));
		accountStatus.add(new LabelValueBean(appProp
				.getValue("label.accountdeactivated"),
				BaseConstants.ACCOUNT_DEACTIVATED));
		accountStatus
				.add(new LabelValueBean(
						appProp.getValue("label.accountlocked"),
						BaseConstants.ACCOUNT_LOCKED));
		accountStatus
				.add(new LabelValueBean(appProp.getValue("label.accountnew"),
						BaseConstants.ACCOUNT_UNAPPROVED));
		getServletContext().setAttribute(BaseConstants.LOOKUP_ACCOUNT_STATUS,
				accountStatus);

		// Status
		List<LabelValueBean> status = new ArrayList<LabelValueBean>();
		status.add(new LabelValueBean(appProp.getValue("label.statusactive"),
				BaseConstants.ACTIVE));
		status.add(new LabelValueBean(appProp.getValue("label.statusinactive"),
				BaseConstants.INACTIVE));
		getServletContext().setAttribute(BaseConstants.LOOKUP_STATUS, status);

		logger.debug("Other Cache loaded...");
	}

}

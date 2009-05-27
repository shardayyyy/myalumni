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
package net.naijatek.myalumni.modules.admin.presentation.action;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.naijatek.myalumni.framework.struts.MyAlumniBaseException;
import net.naijatek.myalumni.framework.struts.MyAlumniDispatchAction;
import net.naijatek.myalumni.modules.admin.presentation.form.ErrorLogForm;
import net.naijatek.myalumni.modules.admin.presentation.form.SystemConfigForm;
import net.naijatek.myalumni.modules.common.domain.ClassNewsVO;
import net.naijatek.myalumni.modules.common.domain.EmailExceptionVO;
import net.naijatek.myalumni.modules.common.domain.ErrorLogVO;
import net.naijatek.myalumni.modules.common.domain.ReminisceVO;
import net.naijatek.myalumni.modules.common.domain.ScrollVO;
import net.naijatek.myalumni.modules.common.domain.SystemConfigVO;
import net.naijatek.myalumni.modules.common.domain.TwitterVO;
import net.naijatek.myalumni.modules.common.helper.BaseSystemHelper;
import net.naijatek.myalumni.modules.common.helper.DropDownCacheBuilder;
import net.naijatek.myalumni.modules.common.presentation.form.ClassNewsForm;
import net.naijatek.myalumni.modules.common.presentation.form.ReminisceForm;
import net.naijatek.myalumni.modules.common.presentation.form.ScrollForm;
import net.naijatek.myalumni.modules.common.presentation.form.SystemForm;
import net.naijatek.myalumni.modules.common.service.IClassNewsService;
import net.naijatek.myalumni.modules.common.service.IErrorLogService;
import net.naijatek.myalumni.modules.common.service.IReminisceService;
import net.naijatek.myalumni.modules.common.service.ISystemConfigService;
import net.naijatek.myalumni.modules.common.service.ISystemTaskService;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.FileHelper;
import net.naijatek.myalumni.util.SystemConfigConstants;
import net.naijatek.myalumni.util.utilities.FileUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;
import org.joda.time.DateTime;




public class MaintainSystemModuleAction extends MyAlumniDispatchAction {

	private Log logger = LogFactory.getLog(this.getClass());

	private IErrorLogService logService;
	private ISystemConfigService systemConfigService;
	private IClassNewsService classNewsService;
	private ISystemTaskService sysService;
	private IReminisceService reminisceService;
	
	

	/**
	 * Instantiates the service classes
	 * @param configService
	 */
	public MaintainSystemModuleAction(IErrorLogService logService,
			ISystemConfigService systemConfigService,
			IClassNewsService classNewsService,
			ISystemTaskService sysService,
			IReminisceService reminisceService) {
		super();
		this.logService = logService;
		this.systemConfigService = systemConfigService;
		this.classNewsService = classNewsService;
		this.sysService = sysService;
		this.reminisceService = reminisceService;
	}

	// ----------------------------------
	// ERROR LOG
	//-----------------------------------  
	public ActionForward listErrorLogs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("in listErrorLogs...");
		listErrorLogsHelper(request);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	public ActionForward viewErrorLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("in viewErrorLog...");

		ErrorLogForm logForm = (ErrorLogForm) form;
		ErrorLogVO logVO = logService.getErrorLog(logForm.getErrorLogId());
		setRequestObject(request, BaseConstants.OBJECT_VO, logVO);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	public ActionForward purgeLogHistory(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("in purgeLogHistory...");
		logService.deleteAllErrorLogs();
		listErrorLogsHelper(request);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	public ActionForward batchErrorLog(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("in batchErrorLog...");
		
		listErrorLogsHelper(request);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}
	
	
	public ActionForward deleteErrorLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("in deleteErrorLog...");
		ErrorLogForm logForm = (ErrorLogForm) form;
		logService.deleteErrorLog(logForm.getErrorLogId());
		listErrorLogsHelper(request);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	private void listErrorLogsHelper(HttpServletRequest request)
			throws Exception {
		logger.debug("in listErrorLogsHelper...");
		List logs = logService.getAllErrorLogs();
		setRequestObject(request, BaseConstants.LIST_OF_ERROR_LOGS, logs);
	}

	
	//**********************************************************************
	//******************  Org ABOUT US  ********************************
	//**********************************************************************   

	public ActionForward prepareUpdateOrgAboutUs(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("in prepareUpdateOrgAboutUs...");
		saveToken(request);
		SystemConfigForm orgInfoForm = (SystemConfigForm) form;
		SystemConfigVO orgInfoVO = systemConfigService.getOrgInfo();
		BeanUtils.copyProperties(orgInfoForm, orgInfoVO);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	public ActionForward updateOrgAboutUs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("in updateOrgAboutUs...");
		if (!isTokenValid(request)) {
			return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
		}
		ActionMessages msgs = new ActionMessages();
		SystemConfigForm orgInfoForm = (SystemConfigForm) form;
		SystemConfigVO orgInfoVO = new SystemConfigVO();
		BeanUtils.copyProperties(orgInfoVO, orgInfoForm);

		orgInfoVO.setLastModifiedBy(getLastModifiedBy(request));

		systemConfigService.updateOrgAboutUs(orgInfoVO.getOrgAboutUs(), getLastModifiedBy(request));
		msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.record.updated"));
		saveMessages(request, msgs);
		resetToken(request);	
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	
	
	//**********************************************************************
	//******************  Org Intro  ********************************
	//**********************************************************************   

	public ActionForward prepareUpdateOrgIntro(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("in prepareUpdateOrgIntro...");
		saveToken(request);
		SystemConfigForm orgInfoForm = (SystemConfigForm) form;
		SystemConfigVO orgInfoVO = systemConfigService.getOrgInfo();
		BeanUtils.copyProperties(orgInfoForm, orgInfoVO);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	public ActionForward updateOrgIntro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("in updateOrgIntro...");
		if (!isTokenValid(request)) {
			return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
		}
		ActionMessages msgs = new ActionMessages();
		SystemConfigForm orgInfoForm = (SystemConfigForm) form;
		SystemConfigVO orgInfoVO = new SystemConfigVO();
		BeanUtils.copyProperties(orgInfoVO, orgInfoForm);

		orgInfoVO.setLastModifiedBy(getLastModifiedBy(request));

		systemConfigService.updateOrgIntro(orgInfoVO.getOrgIntro(), getLastModifiedBy(request));
		msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.record.updated"));
		saveMessages(request, msgs);
		resetToken(request);	
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	
	
	
	//**********************************************************************
	//******************  Org Info  ********************************
	//**********************************************************************   

	public ActionForward prepareUpdateOrgInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("in prepareUpdateOrgInfo...");
		saveToken(request);
		SystemConfigForm orgInfoForm = (SystemConfigForm) form;
		SystemConfigVO orgInfoVO = systemConfigService.getOrgInfo();
		BeanUtils.copyProperties(orgInfoForm, orgInfoVO);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	public ActionForward updateOrgInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("in updateOrgInfo...");
		if (!isTokenValid(request)) {
			return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
		}
		ActionMessages msgs = new ActionMessages();
		SystemConfigForm orgInfoForm = (SystemConfigForm) form;
		SystemConfigVO orgInfoVO = new SystemConfigVO();
		BeanUtils.copyProperties(orgInfoVO, orgInfoForm);

		orgInfoVO.setLastModifiedBy(getLastModifiedBy(request));

		systemConfigService.updateOrgInfo(orgInfoVO);
		msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.record.updated"));
		saveMessages(request, msgs);
		resetToken(request);
		ServletContext sCtx = request.getSession().getServletContext();
	    sCtx.setAttribute(BaseConstants.ORGANIZATION_NAME, orgInfoVO.getOrganizationName());	
	    sCtx.setAttribute(BaseConstants.ORGANIZATION_SHORT_NAME, orgInfoVO.getOrganizationShortName());	
	    sCtx.setAttribute(BaseConstants.ORG_EMAIL, orgInfoVO.getOrgEmail());	
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	
	
	//**********************************************************************
	//******************  RSS Feed  ********************************
	//**********************************************************************   

	public ActionForward prepareUpdateRssFeed(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("in prepareUpdateRssFeed...");
		saveToken(request);
		SystemConfigForm rssForm = (SystemConfigForm) form;
		SystemConfigVO rssVO = systemConfigService.getRssFeedSource();
		BeanUtils.copyProperties(rssForm, rssVO);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	public ActionForward updateRssFeed(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("in updateRssFeed...");
		if (!isTokenValid(request)) {
			return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
		}
		ActionMessages msgs = new ActionMessages();
		SystemConfigForm rssForm = (SystemConfigForm) form;
		systemConfigService.updateRssFeedUrl(rssForm.getRssUrl(), rssForm.getRssHeader(), getLastModifiedBy(request));
		msgs.add(BaseConstants.INFO_KEY, new ActionMessage(
				"message.record.updated"));
		saveMessages(request, msgs);
		resetToken(request);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	//**********************************************************************
	//******************  SERVER URL  ********************************
	//**********************************************************************   

	public ActionForward prepareUpdateServerUrl(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("in prepareUpdateServerUrl...");
		saveToken(request);
		SystemConfigForm rssForm = (SystemConfigForm) form;
		SystemConfigVO systemSetup = systemConfigService.getSystemConfig();
		BeanUtils.copyProperties(rssForm, systemSetup);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	public ActionForward updateServerUrl(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("in updateServerUrl...");
		if (!isTokenValid(request)) {
			return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
		}
		ActionMessages msgs = new ActionMessages();
		SystemConfigForm rssForm = (SystemConfigForm) form;
		
		ServletContext sCtx = request.getSession().getServletContext();
		systemConfigService.updateServerUrl(rssForm.getServerUrl(), getLastModifiedBy(request));
		sCtx.setAttribute(BaseConstants.SERVER_URL, rssForm.getServerUrl());
		msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.record.updated"));
		saveMessages(request, msgs);
		resetToken(request);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	
	//**********************************************************************
	//******************  Twitter Credential  ********************************
	//**********************************************************************   

	public ActionForward prepareUpdateTwitterCred(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("in prepareUpdateTwitterCred...");
		saveToken(request);
		SystemConfigForm configForm = (SystemConfigForm) form;
		SystemConfigVO systemSetup = systemConfigService.getSystemConfig();
		BeanUtils.copyProperties(configForm, systemSetup);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	public ActionForward updateTwitterCred(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("in updateTwitterCred...");
		if (!isTokenValid(request)) {
			return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
		}
		ActionMessages msgs = new ActionMessages();
		SystemConfigForm configForm = (SystemConfigForm) form;
		
		TwitterVO twitterVO = new TwitterVO();
		twitterVO.setTwitteruser(configForm.getTwitteruser());
		twitterVO.setTwitterpswd(configForm.getTwitterpswd());
		twitterVO.setLastModifiedBy(getLastModifiedBy(request));
				
		systemConfigService.updateTwitterCredentials(twitterVO);
		
		msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.record.updated"));
		saveMessages(request, msgs);
		resetToken(request);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}
	
	
	//**********************************************************************
	//******************  ALBUM URL  ********************************
	//**********************************************************************   

	public ActionForward prepareUpdateAlbumUrl(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("in prepareAlbumUrl...");
		saveToken(request);
		SystemConfigForm rssForm = (SystemConfigForm) form;
		SystemConfigVO systemSetup = systemConfigService.getSystemConfig();
		BeanUtils.copyProperties(rssForm, systemSetup);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	public ActionForward updateAlbumUrl(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("in updateAlbumUrl...");
		
		ServletContext sCtx = request.getSession().getServletContext();
		
		if (!isTokenValid(request)) {
			return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
		}
		ActionMessages msgs = new ActionMessages();
		SystemConfigForm rssForm = (SystemConfigForm) form;

		systemConfigService.updateAlbumUrl(rssForm.getAlbumUrl(), getLastModifiedBy(request));
		sCtx.setAttribute(BaseConstants.ALBUM_URL, rssForm.getAlbumUrl());
		msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.record.updated"));
		saveMessages(request, msgs);
		resetToken(request);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}
	
	//**********************************************************************
	//******************  FORUM URL  ********************************
	//**********************************************************************   

	public ActionForward prepareUpdateForumUrl(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("in prepareUpdateForumUrl...");
		saveToken(request);
		SystemConfigForm rssForm = (SystemConfigForm) form;
		SystemConfigVO systemSetup = systemConfigService.getSystemConfig();
		BeanUtils.copyProperties(rssForm, systemSetup);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	public ActionForward updateForumUrl(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("in updateForumUrl...");
		
		ServletContext sCtx = request.getSession().getServletContext();
		
		if (!isTokenValid(request)) {
			return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
		}
		ActionMessages msgs = new ActionMessages();
		SystemConfigForm rssForm = (SystemConfigForm) form;

		systemConfigService.updateForumUrl(rssForm.getForumUrl(), getLastModifiedBy(request));
		sCtx.setAttribute(BaseConstants.FORUM_URL, rssForm.getForumUrl());
		msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.record.updated"));
		saveMessages(request, msgs);
		resetToken(request);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}	
	
	//**********************************************************************
	//******************  DORMITORY  ********************************
	//**********************************************************************   

	public ActionForward prepareUpdateDormitory(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.debug("in prepareUpdateDormitory...");
		saveToken(request);
		SystemConfigForm sysForm = (SystemConfigForm) form;
		String hasDormitory = systemConfigService.getDormitory();
		sysForm.setHasDormitory(hasDormitory);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	public ActionForward updateDormitory(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("in updateDormitory...");
		
		if (!isTokenValid(request)) {
			return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
		}
		
		ActionMessages msgs = new ActionMessages();
		SystemConfigForm sysForm = (SystemConfigForm) form;
		SystemConfigVO sysVO = new SystemConfigVO();
		BeanUtils.copyProperties(sysVO, sysForm);

		systemConfigService.updateDormitory(sysVO);
		if (sysVO.getHasDormitory() != null){
			setServletContextObject(request, BaseConstants.HAS_DORMITORY, sysVO.getHasDormitory());
		}
		msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.record.updated"));
		saveMessages(request, msgs);
		resetToken(request);
	     DropDownCacheBuilder ddb = new DropDownCacheBuilder();
	     List<LabelValueBean> adminSearchCategory = ddb.buildSearchOptions(sysVO.getHasDormitory(), true);
	     List<LabelValueBean> searchCategory = ddb.buildSearchOptions(sysVO.getHasDormitory(), false);
	     
	     setServletContextObject(request, BaseConstants.LIST_OF_ADMIN_SEARCH_OPTIONS, adminSearchCategory);
	     setServletContextObject(request, BaseConstants.LIST_OF_MEMBER_SEARCH_OPTIONS,searchCategory);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	
	//**********************************************************************
	//******************  BIRTHDAY NOTIFICATION  ********************************
	//**********************************************************************   

	public ActionForward prepareUpdateBirthdayNotification(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.debug("in prepareUpdateBirthdayNotification...");
		saveToken(request);
		SystemConfigForm sysForm = (SystemConfigForm) form;
		String birthdayNotification = systemConfigService.getBirthdayNotification();
		sysForm.setBirthdayNotification(birthdayNotification);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	public ActionForward updateBirthdayNotification(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("in updateBirthdayNotification...");
		
		if (!isTokenValid(request)) {
			return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
		}
		
		ActionMessages msgs = new ActionMessages();
		SystemConfigForm sysForm = (SystemConfigForm) form;
		SystemConfigVO systemConfigVO = new SystemConfigVO();
		BeanUtils.copyProperties(systemConfigVO, sysForm);

		systemConfigService.updateBirthdayNotification(systemConfigVO);
		msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.record.updated"));
		saveMessages(request, msgs);
		resetToken(request);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}


	//**********************************************************************
	//******************  Session Timeout  ********************************
	//**********************************************************************   
	public ActionForward prepareUpdateSessionTimeOut(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("in prepareUpdateSessionTimeOut...");
		saveToken(request);
		SystemConfigForm systemForm = (SystemConfigForm) form;
		int timeout = systemConfigService.getSessionTimeOut();
		systemForm.setSessionTimeout(String.valueOf(timeout));
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	public ActionForward updateSessionTimeOut(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("in updateSessionTimeOut...");
		if (!isTokenValid(request)) {
			return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
		}
		ActionMessages msgs = new ActionMessages();
		SystemConfigForm systemForm = (SystemConfigForm) form;

		systemConfigService.updateSessionTimeOut(systemForm.getSessionTimeout(), getLastModifiedBy(request));
		msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.record.updated"));
		saveMessages(request, msgs);
		resetToken(request);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	//**********************************************************************
	//******************  VALIDATE SYSTEM CONFIGURATION ********************
	//**********************************************************************   
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward prepareValidateSystemConfig(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

        logger.debug("in prepareValidateSystemConfig...");  
        saveToken(request);
		
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	
	   public ActionForward validateSystemConfig(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

	        logger.debug("in validateSystemConfig...");
	        if ( !isTokenValid(request) ) {
	            return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
	        }  
	        
	        if (!adminSecurityCheck(request)) {
	        	return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
	        }
	        
	        ActionMessages errors = new ActionMessages();
	        errors = new BaseSystemHelper().validateSystemConfig();
			if (errors.isEmpty()){
				 errors.add(BaseConstants.INFO_KEY, new ActionMessage("core.errorcode.00999"));
			}	        
			saveMessages(request, errors);
			


			return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}       	
	
	
	//**********************************************************************
	//******************  DATABASE BACK UP  ********************************
	//**********************************************************************   
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listDatabaseBackup(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (!adminSecurityCheck(request)) {
			return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
		}

		ActionMessages errors = new ActionMessages();
		
		File backupDir = new File(getSysProp().getValue("BACKUP.FILEPATH"));
		
		if (!backupDir.exists() || !backupDir.isDirectory()){
            errors.add(BaseConstants.ERROR_KEY, new ActionMessage("core.errorcode.00705"));
            saveErrors(request, errors);
            return mapping.getInputForward();
		}
		
		listDatabaseBackupHelper(request);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward databaseBackup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (!adminSecurityCheck(request)) {
			return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
		}
		ActionMessages msgs = new ActionMessages();
		DateTime dtime = new DateTime(new Date());
		String dateStr = dtime.getMonthOfYear() + "_" + dtime.getDayOfMonth() + "_" + dtime.getYear() + "_" + dtime.getHourOfDay() + "_" + dtime.getMinuteOfHour() + "_" + dtime.getSecondOfMinute(); 
		
		try{
			sysService.systemDatabaseBackup(getSysProp().getValue("BACKUP.FILEPATH") + dateStr + ".sql");
		}
        catch(MyAlumniBaseException ex){
        	msgs.add(BaseConstants.ERROR_KEY, new ActionMessage(ex.getMessage()));
            saveMessages(request, msgs);
            return mapping.getInputForward();
        }    
        
        msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.backupsuccessful", dateStr + ".sql"));
        saveMessages(request, msgs);

        listDatabaseBackupHelper(request);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteDatabaseBackup(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (!adminSecurityCheck(request)) {
			return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
		}
		ActionMessages msgs = new ActionMessages();
		SystemForm sysForm = (SystemForm)form;
		String file = getSysProp().getValue("BACKUP.FILEPATH") + sysForm.getLogFileName();
		File sqlFile = new File(file);
		
		if (sqlFile.exists() && sqlFile.isFile()){
				sqlFile.delete();
				msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.successfullydeleted", sysForm.getLogFileName()));
				saveMessages(request, msgs);
		}
		else{
			msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.unabletodelete", file));
			saveMessages(request, msgs);
		}

		listDatabaseBackupHelper(request);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}
	
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward viewDatabaseBackup(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (!adminSecurityCheck(request)) {
			return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
		}
		
		SystemForm sysForm = (SystemForm)form;
		
		if (sysForm.getLogType() == null || sysForm.getLogType().length() == 0) {
			sysForm.setLogType("ALL");
		}

		File file = new File(getSysProp().getValue("BACKUP.FILEPATH") + sysForm.getLogFileName());
		sysForm.setLineCount("5000") ;
		String[] contentLog = FileUtil.getLastLines(file, Integer.parseInt(sysForm.getLineCount()), sysForm.getLogType());
		
//		List<String> al = new ArrayList<String>(contentLog.length);
//		for (int j=0;j<contentLog.length;j++) {
//		    al.add(contentLog[j]);
//		}
//		
		setSessionObject(request, BaseConstants.LOG_CONTENT, contentLog);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}
	

	/**
	 * 
	 * @param request
	 * @throws Exception
	 */
	private void listDatabaseBackupHelper(HttpServletRequest request) throws Exception {
        File backupDir = new File(getSysProp().getValue("BACKUP.FILEPATH"));
        List<FileHelper> backups = new ArrayList<FileHelper>();
		backups = FileUtil.getDirFileNameLength(backupDir, ".sql");
		setRequestObject(request, "sqlBackups", backups);
	}
	

	
	//**********************************************************************
	//******************  EMAIL EXCEPTIONS  ********************************
	//**********************************************************************   
	public ActionForward maintainEmail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if (!adminSecurityCheck(request)) {
			return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
		}

		String task = request.getParameter("task");
		String id = request.getParameter("id");
		EmailExceptionVO email = new EmailExceptionVO();
		email.setEmail_Id(id);

		if (task.equals("list")) {
			List list = sysService.getAllEmailExceptions();
			setSessionObject(request, "emailList", list);
		} else if (task.equals("delete")) {
			sysService.deleteLogEmailException(email);
			setSessionObject(request, "emailList", sysService
					.getAllEmailExceptions());
		} else if (task.equals("resend")) {
			email = sysService.getEmailException(id);

			//         SendMailUtil.resendEmail(email);
			setSessionObject(request, "emailList", sysService
					.getAllEmailExceptions());

		}

		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	
	//**********************************************************************
	//******************  VIEW LOG 4 J SYSTEM LOG  ********************************
	//**********************************************************************  
	
	public ActionForward listSystemLogs(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (!adminSecurityCheck(request)) {
			return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
		}

		ActionMessages errors = new ActionMessages();
		
		File systemLogDir = new File(getSysProp().getValue("LOGFILE.FILEPATH"));
		
		if (!systemLogDir.exists() || !systemLogDir.isDirectory()){
            errors.add(BaseConstants.ERROR_KEY, new ActionMessage("core.errorcode.00710"));
            saveErrors(request, errors);
            return mapping.getInputForward();
		}
		
		listSystemLogHelper(request);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}

	
	public ActionForward deleteSystemLog(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (!adminSecurityCheck(request)) {
			return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
		}
		ActionMessages msgs = new ActionMessages();
		SystemForm sysForm = (SystemForm)form;
		String file = getSysProp().getValue("LOGFILE.FILEPATH") + sysForm.getLogFileName();
		File logFile = new File(file);
		
		if (logFile.exists() && logFile.isFile()){
				logFile.delete();
				msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.successfullydeleted", sysForm.getLogFileName()));
				saveMessages(request, msgs);
		}
		else{
			msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.unabletodelete", file));
			saveMessages(request, msgs);
		}

		listSystemLogHelper(request);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}
	

	public ActionForward viewSystemLogs(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (!adminSecurityCheck(request)) {
			return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
		}
		
		SystemForm sysForm = (SystemForm)form;
		int lineCount = 0;
		try {
			lineCount = Integer.parseInt(sysForm.getLineCount()); // 25, 50 100 200 400 800)
		} catch (Exception e) {
			if (lineCount == 0) {
				lineCount = 50;
			}
		}

		if (lineCount > 5000) {
			lineCount = 5000;
		}
		
		if (sysForm.getLogType() == null || sysForm.getLogType().length() == 0) {
			sysForm.setLogType("ALL");
		}

		File logFile = new File(getSysProp().getValue("LOGFILE.FILEPATH") + sysForm.getLogFileName());
		sysForm.setLineCount(String.valueOf(lineCount)) ;
		String[] contentLog = FileUtil.getLastLines(logFile, lineCount, sysForm.getLogType());
		
		String humanSize = FileUtil.getHumanSize(logFile.length());
		sysForm.setLogFileSize(String.valueOf(logFile.length()));
		sysForm.setLogFileHumanSize(humanSize);
		
		setSessionObject(request, BaseConstants.LOG_CONTENT, contentLog);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}
	
	
/*	public ActionForward viewLog2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String logfileName = "";
		String defaultFileName = "myalumni.log";

		if (!adminSecurityCheck(request)) {
			return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
		}

		SystemForm sysForm = (SystemForm)form;
		
		ActionMessages errors = new ActionMessages();
		SystemProp sysprop = SystemProp.getInstance();
		File logPath = new File(sysprop.getValue("LOGFILE.FILEPATH"));
		
		if (!logPath.exists() || !logPath.isDirectory()){
            errors.add(BaseConstants.ERROR_KEY, new ActionMessage("core.errorcode.00706"));
            saveErrors(request, errors);
            return mapping.getInputForward();
		}
		
		loadLogFiles(request, logPath);

		String logType = sysForm.getLogType();
		
		if (logType == null || logType.length() == 0) {
			logType = "ALL";
		}

		logfileName = sysForm.getLogFileName();
		File logFile = null;
		if (logfileName == null || logfileName.length() == 0) {
			logfileName = defaultFileName;
			
			logFile = new File(logPath + File.separator + logfileName);
			String humanSize = FileUtil.getHumanSize(logFile.length());
			sysForm.setLogFileName(defaultFileName);
			sysForm.setLogFileSize(String.valueOf(logFile.length()));
			sysForm.setLogFileHumanSize(humanSize);
		}
		else{
			logFile = new File(logPath + File.separator + logfileName);
			String humanSize = FileUtil.getHumanSize(logFile.length());
			sysForm.setLogFileName(defaultFileName);
			sysForm.setLogFileSize(String.valueOf(logFile.length()));
			sysForm.setLogFileHumanSize(humanSize);
		}

		

		if (logFile.exists() || logFile.length() == 0) { //?
			if (!logFile.canRead()) {
				errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.filenoread", logfileName));
				saveMessages(request, errors);
				return mapping.getInputForward();
			}
			int lineCount = 0;
			try {
				lineCount = Integer.parseInt(sysForm.getLineCount()); // 25, 50 100 200 400 800)
			} catch (Exception e) {
				if (lineCount == 0) {
					lineCount = 50;
				}
			}

			if (lineCount > 5000) {
				lineCount = 5000;
			}
			String[] contentLog = FileUtil.getLastLines(logFile, lineCount, sysForm.getLogType());
			setSessionObject(request, BaseConstants.LOG_CONTENT, contentLog);
		} else {
			errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.filenoexist", logfileName));
			saveMessages(request, errors);
			return mapping.getInputForward();
		}

		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}*/

	
	/**
	 * 
	 * @param request
	 * @throws Exception
	 */
	private void listSystemLogHelper(HttpServletRequest request) throws Exception {
        File logDir = new File(getSysProp().getValue("LOGFILE.FILEPATH"));
        List<FileHelper> logs = new ArrayList<FileHelper>();
        logs = FileUtil.getDirFileNameLength(logDir, ".log");
		setRequestObject(request, "systemLogs", logs);
	}
	
	//--------------------------------------------------------------------------
	//--
	//--                   P R I V A T E   M E T H O D S
	//--
	//--------------------------------------------------------------------------
/*
	private void loadLogFiles(final HttpServletRequest request, File logPath) {
	
		List<LabelValueBean> logList = new ArrayList<LabelValueBean>();
		if (logPath.exists()) {
			String[] logFiles = logPath.list();
			for (String element : logFiles) {
				if (element.startsWith("myalumni.log.") || element.endsWith(".log")) {
					logList.add(new LabelValueBean(element, element));
				}
			}
		}
		setSessionObject(request, "logList", logList);
	}*/
	
	//**********************************************************************
	//******************  MAINTAIN SCROLL  ********************************
	//********************************************************************** 	
	
    public ActionForward maintainScroll(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
    		HttpServletResponse response) throws Exception {

        ServletContext sCtx = request.getSession().getServletContext();
         

		List<ScrollVO> allScrolls = new ArrayList<ScrollVO>();

		// check to see if the user logged on is a member
		if (!adminSecurityCheck(request)) {
			return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
		}

		ScrollForm scrollForm = (ScrollForm) form;


		if (scrollForm.getType().equalsIgnoreCase("list")) {
			allScrolls = systemConfigService.getAllScrolls();
			setSessionObject(request, BaseConstants.LIST_OF_SCROLLS, allScrolls);
		} 
		else if (scrollForm.getType().equalsIgnoreCase("update")) {
			systemConfigService.updateScroll(scrollForm.getScrollId(), getLastModifiedBy(request));
			sCtx.setAttribute(BaseConstants.SCROLL_VO, systemConfigService.getLatestScroll());
		} 
		else if (scrollForm.getType().equalsIgnoreCase("new")) {
			ScrollVO scrollVO = new ScrollVO();
			BeanUtils.copyProperties(scrollVO, scrollForm);
			scrollVO.setLastModifiedBy(getLastModifiedBy(request));
			scrollVO.setScrollId(null);
			systemConfigService.addScroll(scrollVO);				
			sCtx.setAttribute(BaseConstants.SCROLL_VO, scrollVO);
			allScrolls = systemConfigService.getAllScrolls();
			setSessionObject(request, BaseConstants.LIST_OF_SCROLLS, allScrolls);

		}
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}
   
	//**********************************************************************
	//******************  CLASS NEWS       ********************************
	//********************************************************************** 	   
    public ActionForward prepareAddClassNews(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in prepareAddClassNews...");  
        saveToken(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    
    public ActionForward listClassNews(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in listClassNews...");       
        getClassNewsHelper(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }    

    public ActionForward viewClassNews(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in viewClassNews...");       
        ClassNewsForm classNewsForm = (ClassNewsForm) form;
        ClassNewsVO classNewsVO = new ClassNewsVO();
        classNewsVO = classNewsService.findById(classNewsForm.getClassNewsId());
        BeanUtils.copyProperties(classNewsForm, classNewsVO );
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }    
 
    public ActionForward updateClassNews(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in updateClassNews...");
        if ( !isTokenValid(request) ) {
            return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
        }          
        ClassNewsForm classNewsForm = (ClassNewsForm) form;
        ClassNewsVO classNewsVO = new ClassNewsVO();
        BeanUtils.copyProperties(classNewsVO, classNewsForm);
        classNewsVO.setLastModifiedBy(getLastModifiedBy(request));
        classNewsService.merge(classNewsVO);
        getClassNewsHelper(request);
        resetToken(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }


    public ActionForward addClassNews(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in addClassNews...");
        if ( !isTokenValid(request) ) {
            return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
        }          
		if (!adminSecurityCheck(request)) {
			return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
		}        
        ClassNewsForm classNewsForm = (ClassNewsForm) form;
        ClassNewsVO classNewsVO = new ClassNewsVO();
        BeanUtils.copyProperties(classNewsVO, classNewsForm);
        classNewsVO.setLastModifiedBy(getLastModifiedBy(request));
        classNewsVO.setAuthorId(getCurrentUserId(request));
        classNewsService.save(classNewsVO);
        getClassNewsHelper(request);
        resetToken(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    
    public ActionForward prepareUpdateClassNews(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in prepareUpdateClassNews...");    
        saveToken(request);
        ClassNewsForm classNewsForm = (ClassNewsForm) form;
        ClassNewsVO classNewsVO = classNewsService.findById(classNewsForm.getClassNewsId());
        BeanUtils.copyProperties(classNewsForm, classNewsVO);
        getClassNewsHelper(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    


    public ActionForward deleteClassNews(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in deleteClassNews...");  
		if (!adminSecurityCheck(request)) {
			return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
		}        
        ClassNewsForm classNewsForm = (ClassNewsForm) form;
        classNewsService.softDelete(classNewsForm.getClassNewsId(), getLastModifiedBy(request));
        getClassNewsHelper(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }

    private void getClassNewsHelper(HttpServletRequest request){
        List<ClassNewsVO> tasks =  classNewsService.findAll();
        setRequestObject(request, BaseConstants.LIST_OF_CLASSNEWS, tasks);        
    }
    
	//**********************************************************************
	//******************  REMINISCE       ********************************
	//********************************************************************** 	   
    public ActionForward prepareAddReminisce(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in prepareAddReminisce...");  
        saveToken(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    
    public ActionForward listReminisce(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in listReminisce...");       
        getReminisceHelper(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }    

    public ActionForward viewReminisce(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in viewReminisce...");       
        ReminisceForm classNewsForm = (ReminisceForm) form;
        ReminisceVO classNewsVO = new ReminisceVO();
        classNewsVO = reminisceService.findById(classNewsForm.getReminisceId());
        BeanUtils.copyProperties(classNewsForm, classNewsVO );
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }    
 
    public ActionForward updateReminisce(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in updateReminisce...");
        if ( !isTokenValid(request) ) {
            return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
        }          
        
        ReminisceForm classNewsForm = (ReminisceForm) form;
        ReminisceVO classNewsVO = new ReminisceVO();
        BeanUtils.copyProperties(classNewsVO, classNewsForm);
        classNewsVO.setLastModifiedBy(getLastModifiedBy(request));
        reminisceService.merge(classNewsVO);
        getReminisceHelper(request);
        resetToken(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }


    public ActionForward addReminisce(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in addReminisce...");
        if ( !isTokenValid(request) ) {
            return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
        }          
		if (!adminSecurityCheck(request)) {
			return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
		}        
        ReminisceForm classNewsForm = (ReminisceForm) form;
        ReminisceVO classNewsVO = new ReminisceVO();
        BeanUtils.copyProperties(classNewsVO, classNewsForm);
        classNewsVO.setLastModifiedBy(getLastModifiedBy(request));
        classNewsVO.setAuthorId(getCurrentUserId(request));
        reminisceService.save(classNewsVO);
        getReminisceHelper(request);
        resetToken(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    
    public ActionForward prepareUpdateReminisce(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in prepareUpdateReminisce...");    
        saveToken(request);
        ReminisceForm classNewsForm = (ReminisceForm) form;
        ReminisceVO classNewsVO = reminisceService.findById(classNewsForm.getReminisceId());
        BeanUtils.copyProperties(classNewsForm, classNewsVO);
        getReminisceHelper(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    


    public ActionForward deleteReminisce(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in deleteReminisce...");        
		if (!adminSecurityCheck(request)) {
			return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
		}        
        ReminisceForm classNewsForm = (ReminisceForm) form;
        reminisceService.softDelete(classNewsForm.getReminisceId(), getLastModifiedBy(request));
        getReminisceHelper(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }

    private void getReminisceHelper(HttpServletRequest request){
        List<ReminisceVO> tasks =  reminisceService.findAll();
        setRequestObject(request, BaseConstants.LIST_OF_REMINISCE, tasks);        
    }
    
    
	public ActionForward setupIntialization(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("in setupIntialization...");
		//if (!isTokenValid(request)) {
		//	return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
		//}
		ActionMessages msgs = new ActionMessages();
		SystemConfigForm systemConfigForm = (SystemConfigForm) form;
		SystemConfigVO systemConfigVO = new SystemConfigVO();
		BeanUtils.copyProperties(systemConfigVO, systemConfigForm);

		systemConfigService.setupIntialization(systemConfigVO);
		msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.record.updated"));
		saveMessages(request, msgs);
		ServletContext sCtx = request.getSession().getServletContext();
	    sCtx.setAttribute(BaseConstants.ORGANIZATION_NAME, systemConfigVO.getOrganizationName());	
	    sCtx.setAttribute(BaseConstants.ORGANIZATION_SHORT_NAME, systemConfigVO.getOrganizationShortName());	
	    sCtx.setAttribute(BaseConstants.ORG_EMAIL, systemConfigVO.getOrgEmail());
		sCtx.setAttribute(BaseConstants.ALBUM_URL, systemConfigVO.getAlbumUrl());
		sCtx.setAttribute(BaseConstants.FORUM_URL, systemConfigVO.getForumUrl());
		sCtx.setAttribute(BaseConstants.SERVER_URL, systemConfigVO.getServerUrl());
		sCtx.setAttribute(BaseConstants.FIRST_STARTUP, BaseConstants.BOOLEAN_NO);
		
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}    
	
	//**********************************************************************
	//******************  LOGO       ********************************
	//********************************************************************** 	
	
	public ActionForward prepareUploadLogo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("in prepareUploadLogo...");
		ActionMessages msgs = new ActionMessages();
		SystemConfigForm systemConfigForm = (SystemConfigForm) form;
		SystemConfigVO systemConfigVO = new SystemConfigVO();
		systemConfigVO = systemConfigService.getSystemConfig();
		BeanUtils.copyProperties(systemConfigForm, systemConfigVO);		
		
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}  	
	
	
	public ActionForward uploadLogo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("in uploadLogo...");

		if (!adminSecurityCheck(request)) {
			return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
		}		
		SystemConfigForm systemConfigForm = (SystemConfigForm) form;
		String fileAllowedTypes = SystemConfigConstants.CONTENT_TYPE;
		int maxFileSize = SystemConfigConstants.LOGO_MAX_SIZE;		
		ActionMessages msgs = new ActionMessages();
		FormFile formFile = systemConfigForm.getLogoUpload();
		int height = SystemConfigConstants.LOGO_HEIGHT;
		int width = SystemConfigConstants.LOGO_WIDTH;
		msgs = validateUploadFile(request, formFile, fileAllowedTypes, maxFileSize, true, height, false, width);
		
		if (msgs.isEmpty()){
			// upload the file and update database
			try{
				String logoDir = getSysProp().getValue("LOGO.FILEPATH");			
				uploadFromLocalDrive(formFile, logoDir);
			}
			catch(Exception e){
				msgs.add(BaseConstants.WARN_KEY, new ActionMessage("error.cantupload"));
			}
			
			SystemConfigVO systemConfigVO = systemConfigService.getSystemConfig();
			systemConfigVO.setLogoFileName(formFile.getFileName());
			systemConfigService.uploadLogo(systemConfigVO);		
			ServletContext sCtx = request.getSession().getServletContext();
			sCtx.setAttribute(BaseConstants.LOGO_NAME, systemConfigVO.getLogoFileName());
		}
		
		saveMessages(request, msgs);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}  		
	
	public ActionForward removeLogo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("in removeLogo...");
		if (!adminSecurityCheck(request)) {
			return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
		}
		
		
		ActionMessages msgs = new ActionMessages();
		String logoFileName = new String();
		SystemConfigVO systemConfigVO = new SystemConfigVO();
		systemConfigVO = systemConfigService.getSystemConfig();
		logoFileName = systemConfigVO.getLogoFileName();
		systemConfigVO.setLogoFileName(null);
		systemConfigService.uploadLogo(systemConfigVO);
		
		//delete actual logo from file system
		String logoDir = getSysProp().getValue("LOGO.FILEPATH");
		File f = new File(logoDir + logoFileName);
		if (!f.isDirectory() && f.exists())
			f.delete();
		
		ServletContext sCtx = request.getSession().getServletContext();
		sCtx.setAttribute(BaseConstants.LOGO_NAME, null);		
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}  	
		
}
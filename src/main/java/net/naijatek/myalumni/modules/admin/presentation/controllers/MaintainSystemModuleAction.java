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
package net.naijatek.myalumni.modules.admin.presentation.controllers;


import net.naijatek.myalumni.framework.exceptions.CreateException;
import net.naijatek.myalumni.framework.exceptions.DuplicateEmailException;
import net.naijatek.myalumni.framework.exceptions.DuplicateMemberException;
import net.naijatek.myalumni.framework.extensions.MyAlumniBaseController;
import net.naijatek.myalumni.framework.extensions.MyAlumniBaseException;
import net.naijatek.myalumni.modules.admin.presentation.form.ErrorLogForm;
import net.naijatek.myalumni.modules.admin.presentation.form.SystemConfigForm;
import net.naijatek.myalumni.modules.common.domain.*;
import net.naijatek.myalumni.modules.common.helper.BaseSystemHelper;
import net.naijatek.myalumni.modules.common.helper.DropDownCacheBuilder;
import net.naijatek.myalumni.modules.common.presentation.form.SystemForm;
import net.naijatek.myalumni.modules.common.service.*;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.FileHelper;
import net.naijatek.myalumni.util.SystemConfigConstants;
import net.naijatek.myalumni.util.encryption.base64.Base64Coder;
import net.naijatek.myalumni.util.mail.SendMailUtil;
import net.naijatek.myalumni.util.utilities.FileUtil;
import net.naijatek.myalumni.util.utilities.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin/system")
public class MaintainSystemModuleAction extends MyAlumniBaseController {

    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private IErrorLogService logService;

    @Autowired
    private ISystemConfigService systemConfigService;

    @Autowired
    private IClassNewsService classNewsService;

    @Autowired
    private ISystemTaskService sysService;

    @Autowired
    private IReminisceService reminisceService;

    @Autowired
    private IMemberService memberService;

    @Autowired
    private IMessengerService messengerService;

    @Autowired
    private IMessageFolderService mfService;


    /**
     * Instantiates the service classes
     */
/*	public MaintainSystemModuleAction(IErrorLogService logService,
			ISystemConfigService systemConfigService,
			IClassNewsService classNewsService,
			ISystemTaskService sysService,
			IReminisceService reminisceService,
			IMemberService memberService,
			IMessengerService messengerService,
			IMessageFolderService mfService) {
		super();
		this.logService = logService;
		this.systemConfigService = systemConfigService;
		this.classNewsService = classNewsService;
		this.sysService = sysService;
		this.reminisceService = reminisceService;
		this.memberService = memberService;
		this.messengerService = messengerService;
		this.mfService = mfService;
		
	}*/

    // ----------------------------------
    // ERROR LOG
    //-----------------------------------
    @RequestMapping(value = "/listErrorLogs", method = RequestMethod.POST)
    public ModelAndView listErrorLogs(@ModelAttribute("member") ErrorLogVO errorLogVO, BindingResult result, SessionStatus status) throws
            Exception {
        logger.debug("in listErrorLogs...");
        ModelAndView mv = new ModelAndView(BaseConstants.FWD_SUCCESS);
        listErrorLogsHelper(mv);
        return mv;
    }

    @RequestMapping(value = "/viewErrorLog", method = RequestMethod.POST)
    public ModelAndView viewErrorLog(@ModelAttribute("member") ErrorLogVO errorLogVO, BindingResult result, SessionStatus status) throws
            Exception {
        logger.debug("in viewErrorLog...");
        ModelAndView mv = new ModelAndView(BaseConstants.FWD_SUCCESS);
        errorLogVO = logService.getErrorLog(errorLogVO.getErrorLogId());
        mv.addObject( BaseConstants.OBJECT_VO, errorLogVO);
        return mv;
    }

    @RequestMapping(value = "/purgeLogHistory", method = RequestMethod.POST)
    public ModelAndView purgeLogHistory(@ModelAttribute("member") ErrorLogVO errorLogVO, BindingResult result, SessionStatus status) throws
            Exception {
        logger.debug("in purgeLogHistory...");
        ModelAndView mv = new ModelAndView(BaseConstants.FWD_SUCCESS);
        logService.deleteAllErrorLogs();
        listErrorLogsHelper(mv);
        return mv;
    }

    @RequestMapping(value = "/batchErrorLog", method = RequestMethod.POST)
    public ModelAndView batchErrorLog(@ModelAttribute("member") ErrorLogVO errorLogVO, BindingResult result, SessionStatus status) throws
            Exception {
        logger.debug("in batchErrorLog...");
        ModelAndView mv = new ModelAndView(BaseConstants.FWD_SUCCESS);
        listErrorLogsHelper(mv);
        return mv;
    }


    @RequestMapping(value = "/deleteErrorLog", method = RequestMethod.POST)
    public ModelAndView deleteErrorLog(@ModelAttribute("member") ErrorLogVO errorLogVO, BindingResult result, SessionStatus status) throws
            Exception {
        logger.debug("in deleteErrorLog...");
        ModelAndView mv = new ModelAndView(BaseConstants.FWD_SUCCESS);
        logService.deleteErrorLog(errorLogVO.getErrorLogId());
        listErrorLogsHelper(mv);
        return mv;
    }


    private void listErrorLogsHelper(ModelAndView mv)
            throws Exception {
        logger.debug("in listErrorLogsHelper...");
        List logs = logService.getAllErrorLogs();
        //setRequestObject(request, BaseConstants.LIST_OF_ERROR_LOGS, logs);
        mv.addObject(BaseConstants.LIST_OF_ERROR_LOGS, logs);
    }


    //**********************************************************************
    //******************  Org ABOUT US  ********************************
    //**********************************************************************

    @RequestMapping(value = "/prepareUpdateOrgAboutUs", method = RequestMethod.POST)
    public ModelAndView prepareUpdateOrgAboutUs(@ModelAttribute("member") SystemConfigVO orgInfoVO, BindingResult result, SessionStatus status) throws
            Exception {

        logger.debug("in prepareUpdateOrgAboutUs...");
        ModelAndView mv = new ModelAndView(BaseConstants.FWD_SUCCESS);
        //saveToken(request);
        SystemConfigForm orgInfoForm = (SystemConfigForm) form;
        orgInfoVO = systemConfigService.getOrgInfo();
        BeanUtils.copyProperties(orgInfoForm, orgInfoVO);
        return mv;
    }

    @RequestMapping(value = "/updateOrgAboutUs", method = RequestMethod.POST)
    public ModelAndView updateOrgAboutUs(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

        logger.debug("in updateOrgAboutUs...");
        if (!isTokenValid(request)) {
            return new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
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
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    //**********************************************************************
    //******************  Org Intro  ********************************
    //**********************************************************************

    @RequestMapping(value = "/prepareUpdateOrgIntro", method = RequestMethod.POST)
    public ModelAndView prepareUpdateOrgIntro(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {
        logger.debug("in prepareUpdateOrgIntro...");
        saveToken(request);
        SystemConfigForm orgInfoForm = (SystemConfigForm) form;
        SystemConfigVO orgInfoVO = systemConfigService.getOrgInfo();
        BeanUtils.copyProperties(orgInfoForm, orgInfoVO);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }

    @RequestMapping(value = "/updateOrgIntro", method = RequestMethod.POST)
    public ModelAndView updateOrgIntro(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {
        logger.debug("in updateOrgIntro...");
        if (!isTokenValid(request)) {
            return new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
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
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    //**********************************************************************
    //******************  Org Info  ********************************
    //**********************************************************************
    @RequestMapping(value = "/prepareUpdateOrgInfo", method = RequestMethod.POST)
    public ModelAndView prepareUpdateOrgInfo(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

        logger.debug("in prepareUpdateOrgInfo...");
        saveToken(request);
        SystemConfigForm orgInfoForm = (SystemConfigForm) form;
        SystemConfigVO orgInfoVO = systemConfigService.getOrgInfo();
        BeanUtils.copyProperties(orgInfoForm, orgInfoVO);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }

    @RequestMapping(value = "/updateOrgInfo", method = RequestMethod.POST)
    public ModelAndView updateOrgInfo(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

        logger.debug("in updateOrgInfo...");
        if (!isTokenValid(request)) {
            return new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
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
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    //**********************************************************************
    //******************  RSS Feed  ********************************
    //**********************************************************************

    @RequestMapping(value = "/prepareUpdateRssFeed", method = RequestMethod.POST)
    public ModelAndView prepareUpdateRssFeed(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

        logger.debug("in prepareUpdateRssFeed...");
        saveToken(request);
        SystemConfigForm rssForm = (SystemConfigForm) form;
        SystemConfigVO rssVO = systemConfigService.getRssFeedSource();
        BeanUtils.copyProperties(rssForm, rssVO);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }

    @RequestMapping(value = "/updateRssFeed", method = RequestMethod.POST)
    public ModelAndView updateRssFeed(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {
        logger.debug("in updateRssFeed...");
        if (!isTokenValid(request)) {
            return new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
        }
        ActionMessages msgs = new ActionMessages();
        SystemConfigForm rssForm = (SystemConfigForm) form;
        systemConfigService.updateRssFeedUrl(rssForm.getRssUrl(), rssForm.getRssHeader(), getLastModifiedBy(request));
        msgs.add(BaseConstants.INFO_KEY, new ActionMessage(
                "message.record.updated"));
        saveMessages(request, msgs);
        resetToken(request);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }

    //**********************************************************************
    //******************  SERVER URL  ********************************
    //**********************************************************************

    @RequestMapping(value = "/prepareUpdateServerUrl", method = RequestMethod.POST)
    public ModelAndView prepareUpdateServerUrl(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

        logger.debug("in prepareUpdateServerUrl...");
        saveToken(request);
        SystemConfigForm rssForm = (SystemConfigForm) form;
        SystemConfigVO systemSetup = systemConfigService.getSystemConfig();
        BeanUtils.copyProperties(rssForm, systemSetup);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }

    @RequestMapping(value = "/updateServerUrl", method = RequestMethod.POST)
    public ModelAndView updateServerUrl(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

        logger.debug("in updateServerUrl...");
        if (!isTokenValid(request)) {
            return new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
        }
        ActionMessages msgs = new ActionMessages();
        SystemConfigForm rssForm = (SystemConfigForm) form;

        ServletContext sCtx = request.getSession().getServletContext();
        systemConfigService.updateServerUrl(rssForm.getServerUrl(), getLastModifiedBy(request));
        sCtx.setAttribute(BaseConstants.SERVER_URL, rssForm.getServerUrl());
        msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.record.updated"));
        saveMessages(request, msgs);
        resetToken(request);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    //**********************************************************************
    //******************  Twitter Credential  ********************************
    //**********************************************************************

    @RequestMapping(value = "/prepareUpdateTwitterCred", method = RequestMethod.POST)
    public ModelAndView prepareUpdateTwitterCred(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

        logger.debug("in prepareUpdateTwitterCred...");
        saveToken(request);
        SystemConfigForm configForm = (SystemConfigForm) form;
        SystemConfigVO systemSetup = systemConfigService.getSystemConfig();
        BeanUtils.copyProperties(configForm, systemSetup);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }

    @RequestMapping(value = "/updateTwitterCred", method = RequestMethod.POST)
    public ModelAndView updateTwitterCred(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

        logger.debug("in updateTwitterCred...");
        if (!isTokenValid(request)) {
            return new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
        }
        ActionMessages msgs = new ActionMessages();
        SystemConfigForm configForm = (SystemConfigForm) form;

        TwitterVO twitterVO = new TwitterVO();
        twitterVO.setTwitteruser(configForm.getTwitteruser());
        twitterVO.setTwitterpswd(Base64Coder.encodeString(configForm.getTwitterpswd()));
        twitterVO.setLastModifiedBy(getLastModifiedBy(request));

        systemConfigService.updateTwitterCredentials(twitterVO);

        msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.record.updated"));
        saveMessages(request, msgs);
        resetToken(request);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    //**********************************************************************
    //******************  ALBUM URL  ********************************
    //**********************************************************************

    @RequestMapping(value = "/prepareUpdateAlbumUrl", method = RequestMethod.POST)
    public ModelAndView prepareUpdateAlbumUrl(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {
        logger.debug("in prepareAlbumUrl...");
        saveToken(request);
        SystemConfigForm rssForm = (SystemConfigForm) form;
        SystemConfigVO systemSetup = systemConfigService.getSystemConfig();
        BeanUtils.copyProperties(rssForm, systemSetup);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }

    @RequestMapping(value = "/updateAlbumUrl", method = RequestMethod.POST)
    public ModelAndView updateAlbumUrl(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

        logger.debug("in updateAlbumUrl...");

        ServletContext sCtx = request.getSession().getServletContext();

        if (!isTokenValid(request)) {
            return new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
        }
        ActionMessages msgs = new ActionMessages();
        SystemConfigForm rssForm = (SystemConfigForm) form;

        systemConfigService.updateAlbumUrl(rssForm.getAlbumUrl(), getLastModifiedBy(request));
        sCtx.setAttribute(BaseConstants.ALBUM_URL, rssForm.getAlbumUrl());
        msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.record.updated"));
        saveMessages(request, msgs);
        resetToken(request);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }

    //**********************************************************************
    //******************  FORUM URL  ********************************
    //**********************************************************************

    @RequestMapping(value = "/prepareUpdateForumUrl", method = RequestMethod.POST)
    public ModelAndView prepareUpdateForumUrl(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

        logger.debug("in prepareUpdateForumUrl...");
        saveToken(request);
        SystemConfigForm rssForm = (SystemConfigForm) form;
        SystemConfigVO systemSetup = systemConfigService.getSystemConfig();
        BeanUtils.copyProperties(rssForm, systemSetup);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }

    @RequestMapping(value = "/updateForumUrl", method = RequestMethod.POST)
    public ModelAndView updateForumUrl(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {
        logger.debug("in updateForumUrl...");

        ServletContext sCtx = request.getSession().getServletContext();

        if (!isTokenValid(request)) {
            return new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
        }
        ActionMessages msgs = new ActionMessages();
        SystemConfigForm rssForm = (SystemConfigForm) form;

        systemConfigService.updateForumUrl(rssForm.getForumUrl(), getLastModifiedBy(request));
        sCtx.setAttribute(BaseConstants.FORUM_URL, rssForm.getForumUrl());
        msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.record.updated"));
        saveMessages(request, msgs);
        resetToken(request);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }

    //**********************************************************************
    //******************  DORMITORY  ********************************
    //**********************************************************************

    @RequestMapping(value = "/prepareUpdateDormitory", method = RequestMethod.POST)
    public ModelAndView prepareUpdateDormitory(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

        logger.debug("in prepareUpdateDormitory...");
        saveToken(request);
        SystemConfigForm sysForm = (SystemConfigForm) form;
        String hasDormitory = systemConfigService.getDormitory();
        sysForm.setHasDormitory(hasDormitory);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }

    @RequestMapping(value = "/updateDormitory", method = RequestMethod.POST)
    public ModelAndView updateDormitory(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {
        logger.debug("in updateDormitory...");

        if (!isTokenValid(request)) {
            return new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
        }

        ActionMessages msgs = new ActionMessages();
        SystemConfigForm sysForm = (SystemConfigForm) form;
        SystemConfigVO sysVO = new SystemConfigVO();
        BeanUtils.copyProperties(sysVO, sysForm);

        systemConfigService.updateDormitory(sysVO);
        if (sysVO.getHasDormitory() != null) {
            setServletContextObject(request, BaseConstants.HAS_DORMITORY, sysVO.getHasDormitory());
        }
        msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.record.updated"));
        saveMessages(request, msgs);
        resetToken(request);
        DropDownCacheBuilder ddb = new DropDownCacheBuilder();
        List<LabelValueBean> adminSearchCategory = ddb.buildSearchOptions(sysVO.getHasDormitory(), true);
        List<LabelValueBean> searchCategory = ddb.buildSearchOptions(sysVO.getHasDormitory(), false);

        setServletContextObject(request, BaseConstants.LIST_OF_ADMIN_SEARCH_OPTIONS, adminSearchCategory);
        setServletContextObject(request, BaseConstants.LIST_OF_MEMBER_SEARCH_OPTIONS, searchCategory);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    //**********************************************************************
    //******************  BIRTHDAY NOTIFICATION  ********************************
    //**********************************************************************

    @RequestMapping(value = "/prepareUpdateBirthdayNotification", method = RequestMethod.POST)
    public ModelAndView prepareUpdateBirthdayNotification(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

        logger.debug("in prepareUpdateBirthdayNotification...");
        saveToken(request);
        SystemConfigForm sysForm = (SystemConfigForm) form;
        String birthdayNotification = systemConfigService.getBirthdayNotification();
        sysForm.setBirthdayNotification(birthdayNotification);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }

    @RequestMapping(value = "/updateBirthdayNotification", method = RequestMethod.POST)
    public ModelAndView updateBirthdayNotification(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {
        logger.debug("in updateBirthdayNotification...");

        if (!isTokenValid(request)) {
            return new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
        }

        ActionMessages msgs = new ActionMessages();
        SystemConfigForm sysForm = (SystemConfigForm) form;
        SystemConfigVO systemConfigVO = new SystemConfigVO();
        BeanUtils.copyProperties(systemConfigVO, sysForm);

        systemConfigService.updateBirthdayNotification(systemConfigVO);
        msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.record.updated"));
        saveMessages(request, msgs);
        resetToken(request);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    //**********************************************************************
    //******************  Session Timeout  ********************************
    //**********************************************************************

    @RequestMapping(value = "/prepareUpdateSessionTimeOut", method = RequestMethod.POST)
    public ModelAndView prepareUpdateSessionTimeOut(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {
        logger.debug("in prepareUpdateSessionTimeOut...");
        saveToken(request);
        SystemConfigForm systemForm = (SystemConfigForm) form;
        int timeout = systemConfigService.getSessionTimeOut();
        systemForm.setSessionTimeout(String.valueOf(timeout));
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }

    @RequestMapping(value = "/updateSessionTimeOut", method = RequestMethod.POST)
    public ModelAndView updateSessionTimeOut(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {
        logger.debug("in updateSessionTimeOut...");
        if (!isTokenValid(request)) {
            return new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
        }
        ActionMessages msgs = new ActionMessages();
        SystemConfigForm systemForm = (SystemConfigForm) form;

        systemConfigService.updateSessionTimeOut(systemForm.getSessionTimeout(), getLastModifiedBy(request));
        msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.record.updated"));
        saveMessages(request, msgs);
        resetToken(request);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }

    //**********************************************************************
    //******************  VALIDATE SYSTEM CONFIGURATION ********************
    //**********************************************************************

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/prepareValidateSystemConfig", method = RequestMethod.POST)
    public ModelAndView prepareValidateSystemConfig(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

        logger.debug("in prepareValidateSystemConfig...");
        saveToken(request);

        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    @RequestMapping(value = "/validateSystemConfig", method = RequestMethod.POST)
    public ModelAndView validateSystemConfig(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

        logger.debug("in validateSystemConfig...");
        if (!isTokenValid(request)) {
            return new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
        }

        if (!adminSecurityCheck(request)) {
            return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
        }

        ActionMessages errors = new ActionMessages();
        errors = new BaseSystemHelper().validateSystemConfig();
        if (errors.isEmpty()) {
            errors.add(BaseConstants.INFO_KEY, new ActionMessage("core.errorcode.00999"));
        }
        saveMessages(request, errors);


        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    //**********************************************************************
    //******************  DATABASE BACK UP  ********************************
    //**********************************************************************

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/listDatabaseBackup", method = RequestMethod.POST)
    public ModelAndView listDatabaseBackup(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

        if (!adminSecurityCheck(request)) {
            return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
        }

        ActionMessages errors = new ActionMessages();

        File backupDir = new File(getSysProp().getValue("BACKUP.FILEPATH"));

        if (!backupDir.exists() || !backupDir.isDirectory()) {
            errors.add(BaseConstants.ERROR_KEY, new ActionMessage("core.errorcode.00705"));
            saveErrors(request, errors);
            return mapping.getInputForward();
        }

        listDatabaseBackupHelper(request);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/databaseBackup", method = RequestMethod.POST)
    public ModelAndView databaseBackup(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

        if (!adminSecurityCheck(request)) {
            return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
        }
        ActionMessages msgs = new ActionMessages();
        DateTime dtime = new DateTime(new Date());
        String dateStr = dtime.getMonthOfYear() + "_" + dtime.getDayOfMonth() + "_" + dtime.getYear() + "_" + dtime.getHourOfDay() + "_" + dtime.getMinuteOfHour() + "_" + dtime.getSecondOfMinute();

        try {
            sysService.systemDatabaseBackup(getSysProp().getValue("BACKUP.FILEPATH") + dateStr + ".sql");
        } catch (MyAlumniBaseException ex) {
            msgs.add(BaseConstants.ERROR_KEY, new ActionMessage(ex.getMessage()));
            saveMessages(request, msgs);
            return mapping.getInputForward();
        }

        msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.backupsuccessful", dateStr + ".sql"));
        saveMessages(request, msgs);

        listDatabaseBackupHelper(request);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/deleteDatabaseBackup", method = RequestMethod.POST)
    public ModelAndView deleteDatabaseBackup(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

        if (!adminSecurityCheck(request)) {
            return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
        }
        ActionMessages msgs = new ActionMessages();
        SystemForm sysForm = (SystemForm) form;
        String file = getSysProp().getValue("BACKUP.FILEPATH") + sysForm.getLogFileName();
        File sqlFile = new File(file);

        if (sqlFile.exists() && sqlFile.isFile()) {
            sqlFile.delete();
            msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.successfullydeleted", sysForm.getLogFileName()));
            saveMessages(request, msgs);
        } else {
            msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.unabletodelete", file));
            saveMessages(request, msgs);
        }

        listDatabaseBackupHelper(request);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/viewDatabaseBackup", method = RequestMethod.POST)
    public ModelAndView viewDatabaseBackup(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

        if (!adminSecurityCheck(request)) {
            return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
        }

        SystemForm sysForm = (SystemForm) form;

        if (sysForm.getLogType() == null || sysForm.getLogType().length() == 0) {
            sysForm.setLogType("ALL");
        }

        File file = new File(getSysProp().getValue("BACKUP.FILEPATH") + sysForm.getLogFileName());
        sysForm.setLineCount("5000");
        String[] contentLog = FileUtil.getLastLines(file, Integer.parseInt(sysForm.getLineCount()), sysForm.getLogType());

//		List<String> al = new ArrayList<String>(contentLog.length);
//		for (int j=0;j<contentLog.length;j++) {
//		    al.add(contentLog[j]);
//		}
//		
        setSessionObject(request, BaseConstants.LOG_CONTENT, contentLog);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    /**
     * @param request
     * @throws Exception
     */

    @RequestMapping(value = "/listDatabaseBackupHelper", method = RequestMethod.POST)
    public ModelAndView listDatabaseBackupHelper(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {
        File backupDir = new File(getSysProp().getValue("BACKUP.FILEPATH"));
        List<FileHelper> backups = new ArrayList<FileHelper>();
        backups = FileUtil.getDirFileNameLength(backupDir, ".sql");
        setRequestObject(request, "sqlBackups", backups);
    }


    //**********************************************************************
    //******************  EMAIL EXCEPTIONS  ********************************
    //**********************************************************************

    @RequestMapping(value = "/maintainEmail", method = RequestMethod.POST)
    public ModelAndView maintainEmail(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

        if (!adminSecurityCheck(request)) {
            return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
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

        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    //**********************************************************************
    //******************  VIEW LOG 4 J SYSTEM LOG  ********************************
    //**********************************************************************

    @RequestMapping(value = "/listSystemLogs", method = RequestMethod.POST)
    public ModelAndView listSystemLogs(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

        if (!adminSecurityCheck(request)) {
            return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
        }

        ActionMessages errors = new ActionMessages();

        File systemLogDir = new File(getSysProp().getValue("LOGFILE.FILEPATH"));

        if (!systemLogDir.exists() || !systemLogDir.isDirectory()) {
            errors.add(BaseConstants.ERROR_KEY, new ActionMessage("core.errorcode.00710"));
            saveErrors(request, errors);
            return mapping.getInputForward();
        }

        listSystemLogHelper(request);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    @RequestMapping(value = "/deleteSystemLog", method = RequestMethod.POST)
    public ModelAndView deleteSystemLog(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

        if (!adminSecurityCheck(request)) {
            return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
        }
        ActionMessages msgs = new ActionMessages();
        SystemForm sysForm = (SystemForm) form;
        String file = getSysProp().getValue("LOGFILE.FILEPATH") + sysForm.getLogFileName();
        File logFile = new File(file);

        if (logFile.exists() && logFile.isFile()) {
            logFile.delete();
            msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.successfullydeleted", sysForm.getLogFileName()));
            saveMessages(request, msgs);
        } else {
            msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.unabletodelete", file));
            saveMessages(request, msgs);
        }

        listSystemLogHelper(request);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    @RequestMapping(value = "/viewSystemLogs", method = RequestMethod.POST)
    public ModelAndView viewSystemLogs(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

        if (!adminSecurityCheck(request)) {
            return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
        }

        SystemForm sysForm = (SystemForm) form;
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
        sysForm.setLineCount(String.valueOf(lineCount));
        String[] contentLog = FileUtil.getLastLines(logFile, lineCount, sysForm.getLogType());

        String humanSize = FileUtil.getHumanSize(logFile.length());
        sysForm.setLogFileSize(String.valueOf(logFile.length()));
        sysForm.setLogFileHumanSize(humanSize);

        setSessionObject(request, BaseConstants.LOG_CONTENT, contentLog);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


/*	public ModelAndView viewLog2(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String logfileName = "";
        String defaultFileName = "myalumni.log";

        if (!adminSecurityCheck(request)) {
            return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
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

        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }*/


    /**
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

    @RequestMapping(value = "/maintainScroll", method = RequestMethod.POST)
    public ModelAndView maintainScroll(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {

            ServletContext sCtx = request.getSession().getServletContext();


            List<ScrollVO> allScrolls = new ArrayList<ScrollVO>();

            // check to see if the user logged on is a member
            if (!adminSecurityCheck(request)) {
                return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
            }

            ScrollForm scrollForm = (ScrollForm) form;


            if (scrollForm.getType().equalsIgnoreCase("list")) {
                allScrolls = systemConfigService.getAllScrolls();
                setSessionObject(request, BaseConstants.LIST_OF_SCROLLS, allScrolls);
            } else if (scrollForm.getType().equalsIgnoreCase("update")) {
                systemConfigService.updateScroll(scrollForm.getScrollId(), getLastModifiedBy(request));
                sCtx.setAttribute(BaseConstants.SCROLL_VO, systemConfigService.getLatestScroll());
            } else if (scrollForm.getType().equalsIgnoreCase("new")) {
                ScrollVO scrollVO = new ScrollVO();
                BeanUtils.copyProperties(scrollVO, scrollForm);
                scrollVO.setLastModifiedBy(getLastModifiedBy(request));
                scrollVO.setScrollId(null);
                systemConfigService.addScroll(scrollVO);
                sCtx.setAttribute(BaseConstants.SCROLL_VO, scrollVO);
                allScrolls = systemConfigService.getAllScrolls();
                setSessionObject(request, BaseConstants.LIST_OF_SCROLLS, allScrolls);

            }
            return new ModelAndView(BaseConstants.FWD_SUCCESS);
        }

        //**********************************************************************
        //******************  CLASS NEWS       ********************************
        //**********************************************************************

        @RequestMapping(value = "/prepareAddClassNews", method = RequestMethod.POST)
        public ModelAndView prepareAddClassNews (@ModelAttribute("member")MemberVO memberVO, BindingResult
        result, SessionStatus status)throws
        Exception {
                logger.debug("in prepareAddClassNews...");
                saveToken(request);
                return new ModelAndView(BaseConstants.FWD_SUCCESS);
            }


            @RequestMapping(value = "/listClassNews", method = RequestMethod.POST)
            public ModelAndView listClassNews (@ModelAttribute("member")MemberVO memberVO, BindingResult
            result, SessionStatus status)throws
            Exception {
            logger.debug("in listClassNews...");
            getClassNewsHelper(request);
            return new ModelAndView(BaseConstants.FWD_SUCCESS);
        }

                @RequestMapping(value = "/viewClassNews", method = RequestMethod.POST)
                public ModelAndView viewClassNews (@ModelAttribute("member")MemberVO memberVO, BindingResult
                result, SessionStatus status)throws
                Exception {
            logger.debug("in viewClassNews...");
            ClassNewsForm classNewsForm = (ClassNewsForm) form;
            ClassNewsVO classNewsVO = new ClassNewsVO();
            classNewsVO = classNewsService.findById(classNewsForm.getClassNewsId());
            BeanUtils.copyProperties(classNewsForm, classNewsVO);
            return new ModelAndView(BaseConstants.FWD_SUCCESS);
        }

                    @RequestMapping(value = "/updateClassNews", method = RequestMethod.POST)
                    public ModelAndView updateClassNews (@ModelAttribute("member")MemberVO memberVO, BindingResult
                    result, SessionStatus status)throws
                    Exception {
            logger.debug("in updateClassNews...");
            if (!isTokenValid(request)) {
                return new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
            }
            ClassNewsForm classNewsForm = (ClassNewsForm) form;
            ClassNewsVO classNewsVO = new ClassNewsVO();
            BeanUtils.copyProperties(classNewsVO, classNewsForm);
            classNewsVO.setLastModifiedBy(getLastModifiedBy(request));
            classNewsService.merge(classNewsVO);
            getClassNewsHelper(request);
            resetToken(request);
            return new ModelAndView(BaseConstants.FWD_SUCCESS);
        }


                        @RequestMapping(value = "/addClassNews", method = RequestMethod.POST)
                        public ModelAndView addClassNews (@ModelAttribute("member")MemberVO memberVO, BindingResult
                        result, SessionStatus status)throws
                        Exception {
            logger.debug("in addClassNews...");
            if (!isTokenValid(request)) {
                return new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
            }
            if (!adminSecurityCheck(request)) {
                return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
            }
            ClassNewsForm classNewsForm = (ClassNewsForm) form;
            ClassNewsVO classNewsVO = new ClassNewsVO();
            BeanUtils.copyProperties(classNewsVO, classNewsForm);
            classNewsVO.setLastModifiedBy(getLastModifiedBy(request));
            classNewsVO.setAuthorId(getCurrentUserId(request));
            classNewsService.save(classNewsVO);
            getClassNewsHelper(request);
            resetToken(request);
            return new ModelAndView(BaseConstants.FWD_SUCCESS);
        }


                            @RequestMapping(value = "/prepareUpdateClassNews", method = RequestMethod.POST)
                            public ModelAndView prepareUpdateClassNews (@ModelAttribute("member")MemberVO memberVO, BindingResult
                            result, SessionStatus status)throws
                            Exception {
            logger.debug("in prepareUpdateClassNews...");
            saveToken(request);
            ClassNewsForm classNewsForm = (ClassNewsForm) form;
            ClassNewsVO classNewsVO = classNewsService.findById(classNewsForm.getClassNewsId());
            BeanUtils.copyProperties(classNewsForm, classNewsVO);
            getClassNewsHelper(request);
            return new ModelAndView(BaseConstants.FWD_SUCCESS);
        }


                                @RequestMapping(value = "/deleteClassNews", method = RequestMethod.POST)
                                public ModelAndView deleteClassNews (@ModelAttribute("member")MemberVO memberVO, BindingResult
                                result, SessionStatus status)throws
                                Exception {
            logger.debug("in deleteClassNews...");
            if (!adminSecurityCheck(request)) {
                return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
            }
            ClassNewsForm classNewsForm = (ClassNewsForm) form;
            classNewsService.softDelete(classNewsForm.getClassNewsId(), getLastModifiedBy(request));
            getClassNewsHelper(request);
            return new ModelAndView(BaseConstants.FWD_SUCCESS);
        }

        private void getClassNewsHelper (HttpServletRequest request){
            List<ClassNewsVO> tasks = classNewsService.findAll();
            setRequestObject(request, BaseConstants.LIST_OF_CLASSNEWS, tasks);
        }

        //**********************************************************************
        //******************  REMINISCE       ********************************
        //**********************************************************************

                                @RequestMapping(value = "/prepareAddReminisce", method = RequestMethod.POST)
                                public ModelAndView prepareAddReminisce (@ModelAttribute("member")MemberVO memberVO, BindingResult
                                result, SessionStatus status)throws
                                Exception {
            logger.debug("in prepareAddReminisce...");
            saveToken(request);
            return new ModelAndView(BaseConstants.FWD_SUCCESS);
        }


                                    @RequestMapping(value = "/listReminisce", method = RequestMethod.POST)
                                    public ModelAndView listReminisce (@ModelAttribute("member")MemberVO memberVO, BindingResult
                                    result, SessionStatus status)throws
                                    Exception {
            logger.debug("in listReminisce...");
            getReminisceHelper(request);
            return new ModelAndView(BaseConstants.FWD_SUCCESS);
        }

                                        @RequestMapping(value = "/viewReminisce", method = RequestMethod.POST)
                                        public ModelAndView viewReminisce (@ModelAttribute("member")MemberVO memberVO, BindingResult
                                        result, SessionStatus status)throws
                                        Exception {
            logger.debug("in viewReminisce...");
            ReminisceForm classNewsForm = (ReminisceForm) form;
            ReminisceVO classNewsVO = new ReminisceVO();
            classNewsVO = reminisceService.findById(classNewsForm.getReminisceId());
            BeanUtils.copyProperties(classNewsForm, classNewsVO);
            return new ModelAndView(BaseConstants.FWD_SUCCESS);
        }

                                            @RequestMapping(value = "/updateReminisce", method = RequestMethod.POST)
                                            public ModelAndView updateReminisce (@ModelAttribute("member")MemberVO memberVO, BindingResult
                                            result, SessionStatus status)throws
                                            Exception {
            logger.debug("in updateReminisce...");
            if (!isTokenValid(request)) {
                return new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
            }

            ReminisceForm classNewsForm = (ReminisceForm) form;
            ReminisceVO classNewsVO = new ReminisceVO();
            BeanUtils.copyProperties(classNewsVO, classNewsForm);
            classNewsVO.setLastModifiedBy(getLastModifiedBy(request));
            reminisceService.merge(classNewsVO);
            getReminisceHelper(request);
            resetToken(request);
            return new ModelAndView(BaseConstants.FWD_SUCCESS);
        }


                                                @RequestMapping(value = "/addReminisce", method = RequestMethod.POST)
                                                public ModelAndView addReminisce (@ModelAttribute("member")MemberVO memberVO, BindingResult
                                                result, SessionStatus status)throws
                                                Exception {
            logger.debug("in addReminisce...");
            if (!isTokenValid(request)) {
                return new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
            }
            if (!adminSecurityCheck(request)) {
                return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
            }
            ReminisceForm classNewsForm = (ReminisceForm) form;
            ReminisceVO classNewsVO = new ReminisceVO();
            BeanUtils.copyProperties(classNewsVO, classNewsForm);
            classNewsVO.setLastModifiedBy(getLastModifiedBy(request));
            classNewsVO.setAuthorId(getCurrentUserId(request));
            reminisceService.save(classNewsVO);
            getReminisceHelper(request);
            resetToken(request);
            return new ModelAndView(BaseConstants.FWD_SUCCESS);
        }


                                                    @RequestMapping(value = "/prepareUpdateReminisce", method = RequestMethod.POST)
                                                    public ModelAndView prepareUpdateReminisce (@ModelAttribute("member")MemberVO memberVO, BindingResult
                                                    result, SessionStatus status)throws
                                                    Exception {
            logger.debug("in prepareUpdateReminisce...");
            saveToken(request);
            ReminisceForm classNewsForm = (ReminisceForm) form;
            ReminisceVO classNewsVO = reminisceService.findById(classNewsForm.getReminisceId());
            BeanUtils.copyProperties(classNewsForm, classNewsVO);
            getReminisceHelper(request);
            return new ModelAndView(BaseConstants.FWD_SUCCESS);
        }


                                                        @RequestMapping(value = "/deleteReminisce", method = RequestMethod.POST)
                                                        public ModelAndView deleteReminisce (@ModelAttribute("member")MemberVO memberVO, BindingResult
                                                        result, SessionStatus status)throws
                                                        Exception {
            logger.debug("in deleteReminisce...");
            if (!adminSecurityCheck(request)) {
                return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
            }
            ReminisceForm classNewsForm = (ReminisceForm) form;
            reminisceService.softDelete(classNewsForm.getReminisceId(), getLastModifiedBy(request));
            getReminisceHelper(request);
            return new ModelAndView(BaseConstants.FWD_SUCCESS);
        }

        private void getReminisceHelper (HttpServletRequest request){
            List<ReminisceVO> tasks = reminisceService.findAll();
            setRequestObject(request, BaseConstants.LIST_OF_REMINISCE, tasks);
        }


                                                        @RequestMapping(value = "/setupIntialization", method = RequestMethod.POST)
                                                        public ModelAndView setupIntialization (@ModelAttribute("member")MemberVO memberVO, BindingResult
                                                        result, SessionStatus status)throws
                                                        Exception {
            logger.debug("in setupIntialization...");

            //if (!isTokenValid(request)) {
            //	return new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
            //}
            ServletContext sCtx = request.getSession().getServletContext();
            ActionMessages msgs = new ActionMessages();
            SystemConfigForm systemConfigForm = (SystemConfigForm) form;
            SystemConfigVO systemConfigVO = new SystemConfigVO();
            BeanUtils.copyProperties(systemConfigVO, systemConfigForm);

            try {
                DateTime dt = new DateTime();
                int year = dt.getYear();

                // Admin
                //INSERT INTO MYALUMNI_MEMBERS_TBL (USER_NAME,MEMBER_ID,MEMBER_STATUS,MEMBER_PASSWORD,EMAIL,FIRST_IP_ADDRESS,LAST_IP_ADDRESS,
                //CREATION_DATE,LAST_LOGON_DATE,TITLE,NICK_NAME,FIRST_NAME,LAST_NAME,GENDER,FIRST_EMAIL,COUNTRY,YEAR_IN,YEAR_OUT,IS_ADMIN,HIDE_EMAIL,HIDE_ADDRESS,HIDE_PHONE,HIDE_IM,PROMPT_CHANGE,LASTMODIFICATION,LASTMODIFIED_BY,LASTMODIFIED_DATE ) VALUES ('sysadmin', '999999999999999999999999999999','A','X03MO1qnZdYdgyfeuILPmQ==','myalumni@naijatek.com','127.0.0.1','127.0.0.1',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'1000000004999','NickName','FirstName','LastName','U','myalumni@naijatek.com','1000000003999','2007','2007','Y','Y','Y','Y','Y','Y','A','SYSTEM',CURRENT_TIMESTAMP);

                MemberVO memberVO = new MemberVO();
                memberVO.setMemberUserName(systemConfigVO.getMemberUserName());
                memberVO.setLastName(systemConfigVO.getMemberLastName());
                memberVO.setFirstName(systemConfigVO.getMemberFirstName());
                memberVO.setEmail(systemConfigVO.getMemberEmail());
                memberVO.setMemberPassword(systemConfigVO.getMemberPassword());

                memberVO.setNickName("");
                memberVO.setGender(BaseConstants.GENDER_UNKNOWN);
                memberVO.setCountryId("1000000003999");
                memberVO.setTitleId("1000000004999");
                memberVO.setCareerId("1000000001999");
                memberVO.setDormitoryId("1000000005999");

                memberVO.setYearIn(year);
                memberVO.setYearOut(year);
                memberVO.setLastModifiedBy("system");

                // create admin
                memberService.createAdminMember(memberVO, request);

                final String memberId = memberVO.getMemberId();
                String[] s = new String[0];
                memberVO.setLstSelectedIMs(s);

                // Messengers
                List<MessengerVO> messengers = new ArrayList<MessengerVO>();
                MessengerVO mesgerVO = null;
                for (String str : memberVO.getLstSelectedIMs()) {
                    mesgerVO = new MessengerVO();
                    mesgerVO.setLastModifiedBy(memberVO.getMemberUserName());
                    mesgerVO.setMemberId(memberId);
                    mesgerVO.setLookupCodeId(str);
                    messengers.add(mesgerVO);
                }
                messengerService.saveAll(messengers, memberId);

                // Message Folders
                mfService.createMemberMessageFolders(memberId, SystemConfigConstants.MESSAGE_FOLDERS, memberVO.getMemberUserName());

                StringBuffer message = new StringBuffer();
                message.append("Thank you " + StringUtil.capitalize(memberVO.getFirstName()) + " " + StringUtil.capitalize(memberVO.getLastName()) + " for setting up MyAlumni and Welcome to " + systemConfigVO.getOrganizationName() + "'s owns space in cyberspace.");

                setSessionObject(request, BaseConstants.MESSAGE, message.toString());

                // send email to registrant
                try {
                    SendMailUtil.sendWelcomeNotice(memberVO.getEmail(), memberVO.getMemberUserName(), systemConfigVO);
                } catch (Exception ex) {
                    logger.error(ex.getMessage());
                    msgs.add(BaseConstants.FATAL_KEY, new ActionMessage("error.mailserver"));
                    saveMessages(request, msgs);
                }


                // Scroll
                ScrollVO scrollVO = new ScrollVO();
                scrollVO.setLastModifiedBy("system");
                scrollVO.setScrollId(null);
                scrollVO.setPriority(BaseConstants.BOOLEAN_YES);
                scrollVO.setScrollText("Welcome to " + systemConfigVO.getOrganizationName());
                systemConfigService.addScroll(scrollVO);
                sCtx.setAttribute(BaseConstants.SCROLL_VO, scrollVO);


                systemConfigService.setupIntialization(systemConfigVO);

                sCtx.setAttribute(BaseConstants.ORGANIZATION_NAME, systemConfigVO.getOrganizationName());
                sCtx.setAttribute(BaseConstants.ORGANIZATION_SHORT_NAME, systemConfigVO.getOrganizationShortName());
                sCtx.setAttribute(BaseConstants.ORG_EMAIL, systemConfigVO.getOrgEmail());
                sCtx.setAttribute(BaseConstants.ALBUM_URL, systemConfigVO.getAlbumUrl());
                sCtx.setAttribute(BaseConstants.FORUM_URL, systemConfigVO.getForumUrl());
                sCtx.setAttribute(BaseConstants.SERVER_URL, systemConfigVO.getServerUrl());
                sCtx.setAttribute(BaseConstants.FIRST_STARTUP, BaseConstants.BOOLEAN_NO);

            } catch (DuplicateMemberException e) {
                msgs.add(BaseConstants.WARN_KEY, new ActionMessage("error.duplicate.member"));
                saveMessages(request, msgs);
                logger.info("DUPLICATE USER NAME - " + e.getMessage());
                return mapping.getInputForward();
            } catch (DuplicateEmailException e) {
                msgs.add(BaseConstants.WARN_KEY, new ActionMessage("error.duplicate.email"));
                saveMessages(request, msgs);
                logger.info("DUPLICATE EMAIL - " + e.getMessage());
                return mapping.getInputForward();
            } catch (CreateException e) {
                msgs.add(BaseConstants.WARN_KEY, new ActionMessage("errors.technical.difficulty"));
                saveMessages(request, msgs);
                logger.fatal("SYSTEM ERROR - " + e.getMessage());
                return mapping.getInputForward();
            } catch (Exception ex) {
                msgs.add(BaseConstants.FATAL_KEY, new ActionMessage("errors.technical.difficulty"));
                saveMessages(request, msgs);
                logger.fatal("SYSTEM ERROR - " + ex.getMessage());
                return mapping.getInputForward();
            }
            return new ModelAndView(BaseConstants.FWD_SUCCESS);
        }

        //**********************************************************************
        //******************  LOGO       ********************************
        //**********************************************************************

                                                            @RequestMapping(value = "/prepareUploadLogo", method = RequestMethod.POST)
                                                            public ModelAndView prepareUploadLogo (@ModelAttribute("member")MemberVO memberVO, BindingResult
                                                            result, SessionStatus status)throws
                                                            Exception {
            logger.debug("in prepareUploadLogo...");
            //ActionMessages msgs = new ActionMessages();
            SystemConfigForm systemConfigForm = (SystemConfigForm) form;
            SystemConfigVO systemConfigVO = new SystemConfigVO();
            systemConfigVO = systemConfigService.getSystemConfig();
            BeanUtils.copyProperties(systemConfigForm, systemConfigVO);

            return new ModelAndView(BaseConstants.FWD_SUCCESS);
        }


                                                                @RequestMapping(value = "/uploadLogo", method = RequestMethod.POST)
                                                                public ModelAndView uploadLogo (@ModelAttribute("member")MemberVO memberVO, BindingResult
                                                                result, SessionStatus status)throws
                                                                Exception {
            logger.debug("in uploadLogo...");

            if (!adminSecurityCheck(request)) {
                return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
            }
            SystemConfigForm systemConfigForm = (SystemConfigForm) form;
            String fileAllowedTypes = SystemConfigConstants.CONTENT_TYPE;
            int maxFileSize = SystemConfigConstants.LOGO_MAX_SIZE;
            ActionMessages msgs = new ActionMessages();
            FormFile formFile = systemConfigForm.getLogoUpload();
            int height = SystemConfigConstants.LOGO_HEIGHT;
            int width = SystemConfigConstants.LOGO_WIDTH;
            msgs = validateUploadFile(request, formFile, fileAllowedTypes, maxFileSize, true, height, false, width);

            if (msgs.isEmpty()) {
                // upload the file and update database
                try {
                    String logoDir = getSysProp().getValue("LOGO.FILEPATH");
                    uploadFromLocalDrive(formFile, formFile.getFileName(), logoDir);
                } catch (Exception e) {
                    msgs.add(BaseConstants.WARN_KEY, new ActionMessage("error.cantupload"));
                }

                SystemConfigVO systemConfigVO = systemConfigService.getSystemConfig();
                systemConfigVO.setLogoFileName(formFile.getFileName());
                systemConfigService.uploadLogo(systemConfigVO);
                ServletContext sCtx = request.getSession().getServletContext();
                sCtx.setAttribute(BaseConstants.LOGO_NAME, systemConfigVO.getLogoFileName());
            }

            saveMessages(request, msgs);
            return new ModelAndView(BaseConstants.FWD_SUCCESS);
        }

                                                                    @RequestMapping(value = "/removeLogo", method = RequestMethod.POST)
                                                                    public ModelAndView removeLogo (@ModelAttribute("member")MemberVO memberVO, BindingResult
                                                                    result, SessionStatus status)throws
                                                                    Exception {
            logger.debug("in removeLogo...");
            if (!adminSecurityCheck(request)) {
                return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
            }


            //ActionMessages msgs = new ActionMessages();
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
            return new ModelAndView(BaseConstants.FWD_SUCCESS);
        }

    }
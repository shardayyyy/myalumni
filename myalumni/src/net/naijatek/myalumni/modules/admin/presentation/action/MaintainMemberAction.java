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
package net.naijatek.myalumni.modules.admin.presentation.action;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.naijatek.myalumni.framework.struts.MyAlumniDispatchAction;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.domain.MessengerVO;
import net.naijatek.myalumni.modules.common.domain.StatisticsVO;
import net.naijatek.myalumni.modules.common.domain.SystemConfigVO;
import net.naijatek.myalumni.modules.common.domain.XlatDetailVO;
import net.naijatek.myalumni.modules.common.presentation.form.MemberForm;
import net.naijatek.myalumni.modules.common.service.IClassNewsService;
import net.naijatek.myalumni.modules.common.service.IMemberService;
import net.naijatek.myalumni.modules.common.service.IMessageFolderService;
import net.naijatek.myalumni.modules.common.service.IMessengerService;
import net.naijatek.myalumni.modules.common.service.IPrivateMessageService;
import net.naijatek.myalumni.modules.common.service.ISystemConfigService;
import net.naijatek.myalumni.modules.common.service.IXlatService;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.mail.SendMailUtil;
import net.naijatek.myalumni.util.utilities.StringUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class MaintainMemberAction  extends MyAlumniDispatchAction{
    
    private static Log logger = LogFactory.getLog(MaintainMemberAction.class);

    private IClassNewsService classNewsService;
    private IMemberService memService; 
    private IPrivateMessageService pmService; 
    private IMessageFolderService mfService ; 
    private ISystemConfigService sysConfigSerivce;
    private IXlatService xlatService;
    private IMessengerService messengerService;    
    
    public MaintainMemberAction(final IMemberService memService, final IClassNewsService classNewsService, 
    		final IPrivateMessageService pmService, final IMessageFolderService mfService,
    		final ISystemConfigService sysConfigSerivce, IXlatService xlatService, IMessengerService messengerService) {
        this.memService = memService;
        this.classNewsService = classNewsService;
        this.pmService = pmService;
        this.mfService = mfService;
        this.sysConfigSerivce = sysConfigSerivce;
        this.xlatService = xlatService;
        this.messengerService = messengerService;
    }
    
    public ActionForward displayStatistics(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws
        Exception {

      StatisticsVO stats = new StatisticsVO();

      if (!adminSecurityCheck(request)){
        return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
      }
      
     stats = memService.getAllStatistics();

      setRequestObject(request,"stats",stats);
      return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }   
    
    
    public ActionForward listMembers(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws
        Exception {

      
      String action = null;
      List<MemberVO> membersList = new ArrayList<MemberVO>();
      int rowsToReturn = 0;

      if (isCancelled(request)){
        return mapping.findForward(BaseConstants.FWD_CANCEL);
      }

      if (!adminSecurityCheck(request)){
        return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
      }

      MemberForm memForm = (MemberForm)form;
        
      String adminAction = memForm.getAdminAction();
      String adminDisplay = memForm.getAdminDisplay();
      adminDisplay = StringUtil.safeString(adminDisplay);
      int offset = 0 ; 


      action = "";
      if (adminAction != null && adminAction.length() > 0){
        if ( adminAction.equalsIgnoreCase(BaseConstants.ADMIN_LIST_ALL)){
          membersList = memService.adminGetAllMembers(offset, rowsToReturn);
          setupAdminDesktop(request, memService,  classNewsService,  pmService);
          action = BaseConstants.FWD_SUCCESS;
        }
        else if(adminAction.equalsIgnoreCase(BaseConstants.ADMIN_LIST_ONE)){
          membersList = memService.adminGetOneMembersByUserName(memForm.getMemberUserName());
          action = BaseConstants.FWD_LIST_DETAILS;
        }
        else if(adminAction.equalsIgnoreCase(BaseConstants.FWD_ALL_MEMBERS) && adminDisplay.equalsIgnoreCase(BaseConstants.FWD_LIST_DETAILS)){
          membersList = memService.adminGetAllMembers(offset, rowsToReturn);
          action = BaseConstants.FWD_LIST_DETAILS;
        }
        else if(adminAction.equalsIgnoreCase(BaseConstants.FWD_ALL_MEMBERS) && adminDisplay.equalsIgnoreCase(BaseConstants.FWD_LIST_MINI)){  
          membersList = memService.adminGetAllMembers(offset, rowsToReturn);
          action = BaseConstants.FWD_SUCCESS;
        }
      }
      else{
        ActionMessages errors = new ActionMessages();
        errors.add(BaseConstants.ERROR_KEY, new ActionMessage("errors.technical.difficulty"));
        saveMessages(request, errors);
        return mapping.getInputForward();
      }



      setRequestObject(request, "adminAction", adminAction);
      setRequestObject(request, "adminDisplay", adminDisplay);
      setRequestObject(request, BaseConstants.LIST_OF_MEMBERS,  membersList);
      return mapping.findForward(action);

    }    
    
    public ActionForward maintainMember(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws
        Exception {


      MemberVO memberVO = null;
      String action = null;
      ActionMessages errors = new ActionMessages();

      if (isCancelled(request)){
        return mapping.findForward(BaseConstants.FWD_CANCEL);
      }

      if (!adminSecurityCheck(request)){
        return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
      }

      SystemConfigVO sysConfigVO = sysConfigSerivce.getSystemConfig();
      MemberForm memberForm = (MemberForm)form;
      String adminAction = memberForm.getAdminAction();
      String memberUserName = memberForm.getMemberUserName();
      String deleteConfirm = memberForm.getDeleteConfirm();

      if (adminAction != null && adminAction.length() > 0){
        if ( adminAction.equalsIgnoreCase(BaseConstants.ADMIN_ACTION_ACTIVATE)){
          logger.debug("ACTIVATE ACCOUNT - " + memberUserName);
          
          //get the members status b4 updating
          memberVO = memService.getMemberProfileByUserName(memberUserName);
          
         
          // we only want to refresh the list if the user is newly registered
          if (memberVO.getMemberStatus().equalsIgnoreCase(BaseConstants.ACCOUNT_UNAPPROVED)){
             // send a welcome email
            SendMailUtil.sendWelcomeNotice(memberVO.getEmail(), memberVO.getMemberUserName(),getLocale(request).getLanguage(),sysConfigVO);
          }
          
          memService.activateMemberByUserName(memberUserName, getLastModifiedBy(request));
          
          setupAdminDesktop(request, memService, classNewsService, pmService);
          action = BaseConstants.FWD_SUCCESS;
        }
        else if(adminAction.equalsIgnoreCase(BaseConstants.ADMIN_ACTION_MODIFY)){
          logger.debug("FWD_MODIFY ACCOUNT - " + memberUserName);
          memberVO = memService.getMemberProfileByUserName(memberUserName);
          BeanUtils.copyProperties(memberForm, memberVO);        
          memberForm.setAdminAction("true");
          action = BaseConstants.FWD_MODIFY;

          // MESSENGER
          try{
              // IM
            	  List<XlatDetailVO> availableMessengers = xlatService.getActiveGroupDetails(BaseConstants.GROUP_INSTANT_MESSENGERS);
            	  List<XlatDetailVO> selectedMessengers = messengerService.getActiveMemberMessengers(memberVO.getMemberId());
            	  List<XlatDetailVO> filteredAvailableIMs = filterMessengers(availableMessengers, selectedMessengers) ;
            	  
                  setSessionObject(request, BaseConstants.LU_AVAILABLE_IMS, filteredAvailableIMs);
                  setSessionObject(request, BaseConstants.LU_SELECTED_IMS, selectedMessengers);              	  
              }
              catch(Exception e){
            	  logger.debug(e.getMessage());
                  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00709"));
                  saveMessages(request, errors);
            	  return mapping.getInputForward();
              }          
          
          
        }
        else if(adminAction.equalsIgnoreCase(BaseConstants.ADMIN_ACTION_DEACTIVATE)){
          logger.debug("DEACTIVATE ACCOUNT - " + memberUserName);
          memService.deactivateMemberByUserName(memberUserName, getLastModifiedBy(request));
          setupAdminDesktop(request, memService, classNewsService, pmService);
          action = BaseConstants.FWD_SUCCESS;
        }
        else if(adminAction.equalsIgnoreCase(BaseConstants.ADMIN_ACTION_DELETE)){
          logger.debug("DELETE ACCOUNT - " +  memberUserName);
          deleteConfirm = StringUtil.safeString(deleteConfirm);
          if ( deleteConfirm.length() == 0){
            setRequestObject(request, "confirm", "show");
            setupAdminDesktop(request, memService, classNewsService, pmService);

            errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.confirdelete"));
            saveMessages(request, errors);
            memberVO = memService.getMemberProfileByUserName(memberUserName);
            BeanUtils.copyProperties(memberForm, memberVO);  
            return mapping.getInputForward();
          }else{
            String unActivatePattern = getSysProp().getValue("UNDELETEABLE_USERNAME_PATTERN");

            boolean validName = true;
            StringTokenizer st = new StringTokenizer(unActivatePattern, ",");
            memberUserName = memberUserName.toLowerCase();
            while (st.hasMoreTokens()) {
              if (memberUserName.equals(st.nextToken().toLowerCase())) {
                validName = false;
              }
            }

            if( validName ){
              memService.deleteMemberByUserName(memberUserName);
              //also delete the mail folders
               mfService.deleteMemberMessageFolders(memberUserName);
            }
            else{
              memberVO = memService.getMemberProfileByUserName(memberUserName);
              BeanUtils.copyProperties(memberForm, memberVO);            
              memberForm.setAdminAction("true");
              memberVO = memService.getMemberProfileByUserName(memberUserName);
              BeanUtils.copyProperties(memberForm, memberVO);  
              errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.nondeletable", memberUserName));
              saveMessages(request, errors);
              return mapping.getInputForward();
            }
            setupAdminDesktop(request, memService, classNewsService, pmService);
            action = BaseConstants.FWD_SUCCESS;
          }
        }
        else if(adminAction.equalsIgnoreCase(BaseConstants.ADMIN_ACTION_LOCK)){
          logger.debug("LOCK ACCOUNT - " + memberUserName);
          memService.lockMemberByUserName(memberUserName, getLastModifiedBy(request));
          setupAdminDesktop(request, memService, classNewsService, pmService);
          action = BaseConstants.FWD_SUCCESS;
        }
      }
      else{
        errors.add(BaseConstants.FATAL_KEY, new ActionMessage("errors.technical.difficulty"));
        saveMessages(request, errors);
        return mapping.getInputForward();
      }

      return mapping.findForward(action);

    }  
    
    
    public ActionForward searchForMembers(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws
       Exception {
     
     List<MemberVO> membersArrayList;// = new ArrayList();
     int searchCount = 0 ;

     if (!adminSecurityCheck(request)) {
       return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
     }

     String isAdmin = BaseConstants.BOOLEAN_YES;
     MemberForm memberForm = (MemberForm) form;

     membersArrayList = baseMemberSearch(memberForm, request, searchCount, memService, isAdmin);
     setRequestObject(request, BaseConstants.LIST_OF_MEMBERS, membersArrayList);
     return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    
    public ActionForward updateMemberProfile(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws
        Exception {

      if (isCancelled(request)){
        return mapping.findForward(BaseConstants.FWD_CANCEL);
      }

      if (!adminSecurityCheck(request)){
        return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
      }
      
      MemberForm memberForm = (MemberForm) form;
      MemberVO memberVO = new MemberVO();
      BeanUtils.copyProperties(memberVO, memberForm);

      memService.updateMemberProfile(memberVO, getLastModifiedBy(request));
      
      ActionMessages msgs = new ActionMessages();
      
      // Messengers
      String memberId = memberVO.getMemberId();
 
      List<MessengerVO> messengers = new ArrayList<MessengerVO>();
      MessengerVO mesgerVO = null;
      for(String str : memberVO.getLstSelectedIMs()){
      	mesgerVO = new MessengerVO();
      	mesgerVO.setLastModifiedBy(getLastModifiedBy(request));
      	mesgerVO.setMemberId(memberId);
      	mesgerVO.setLookupCodeId(str);
      	messengers.add(mesgerVO);
      }
      messengerService.saveAll(messengers, memberId);
      
      
      msgs.add(BaseConstants.INFO_KEY, new ActionMessage("message.memberupdated"));
      saveMessages(request, msgs);
      

      setupAdminDesktop(request, memService, classNewsService, pmService);
      return mapping.findForward(BaseConstants.FWD_SUCCESS);

    }    
    
    
    
    public ActionForward displayMiniProfile(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws
        Exception {


      MemberVO memberVO = null;

      if (!adminSecurityCheck(request)){
        return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
      }
      
      MemberForm memberForm = (MemberForm) form;
      memberVO = memService.getMemberProfileByUserName(memberForm.getMemberUserName());
  
      if (memberVO == null){
    	  memberVO = new MemberVO();
      }
      setRequestObject(request, BaseConstants.MEMBER_PROFILE, memberVO);
      return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }  
    
    
    
    public ActionForward viewMemberProfile(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws
        Exception {


      MemberVO memberVO = null;

      if (!adminSecurityCheck(request)){
        return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
      }
      
      MemberForm memberForm = (MemberForm) form;
      memberVO = memService.getMemberProfileByUserName(memberForm.getMemberUserName());
  
      if (memberVO == null){
    	  memberVO = new MemberVO();
      }
      BeanUtils.copyProperties(memberForm, memberVO);
	  List<XlatDetailVO> selectedMessengers = messengerService.getActiveMemberMessengers(memberVO.getMemberId());
	  memberForm.setMessengers(selectedMessengers);
	  setRequestObject(request, BaseConstants.MEMBER_PROFILE, memberVO);  // to display date using fmt
      return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }  
    
    
    public ActionForward unlockMemberAccount(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (!adminSecurityCheck(request)) {
			return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
		}

		MemberForm memberForm = (MemberForm) form;
		ActionMessages msgs = new ActionMessages();

		if (!memService.unLockMemberAccount(memberForm.getMemberUserName(), getLastModifiedBy(request))) {
			msgs.add(BaseConstants.FATAL_KEY, new ActionMessage("message.membernotfound"));
		}

		return mapping.findForward(BaseConstants.FWD_SUCCESS);
	} 
    
    
    public ActionForward displayMyDesktop(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws
        Exception {

      if (isCancelled(request)) {
        return mapping.findForward(BaseConstants.FWD_CANCEL);
      }

      if (!adminSecurityCheck(request)) {
        return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
      }
      
      String tab = request.getParameter("t"); 
      if (tab == null){
    	  tab = BaseConstants.FWD_DASHBOARD_MODULE;
      }
    	 
      
      
      if (tab.equalsIgnoreCase(BaseConstants.FWD_DASHBOARD_MODULE)){
    	  setupAdminDesktop(request, memService, classNewsService, pmService);
      }
      else if (tab.equalsIgnoreCase(BaseConstants.FWD_MEMBERS_MODULE)){
    	  //perform any extra tasks to display page
      }
      else if (tab.equalsIgnoreCase(BaseConstants.FWD_SECURITY_MODULE)){
//    	perform any extra tasks to display page
      }
      else if (tab.equalsIgnoreCase(BaseConstants.FWD_GENERAL_MODULE)){
//    	perform any extra tasks to display page
      }
      else if (tab.equalsIgnoreCase(BaseConstants.FWD_SYSTEM_MODULE)){
//    	perform any extra tasks to display page
    	  ActionMessages errors = new ActionMessages();
    	  
    	  SystemConfigVO sysConfig = sysConfigSerivce.getSystemConfig();
    	  
    	  if (sysConfig.getHasDormitory() == null || sysConfig.getHasDormitory().length() == 0){
    		  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00801"));
    	  }
    	  
    	  if (sysConfig.getOrganizationName() == null || sysConfig.getOrganizationName().length() == 0){
    		  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00802"));
    	  }
    	  
    	  if (sysConfig.getOrgEmail() == null || sysConfig.getOrgEmail().length() == 0){
    		  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00803"));
    	  }
    	  
    	  if (sysConfig.getOrgFirstYear() == null || sysConfig.getOrgFirstYear().length() == 0){
    		  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00804"));
    	  }
    	  
    	  if (sysConfig.getRssUrl() == null || sysConfig.getRssUrl().length() == 0){
    		  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00805"));
    	  }
    	  
    	  if (sysConfig.getServerUrl() == null || sysConfig.getServerUrl().length() == 0){
    		  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00806"));
    	  }
    	  
    	  if (sysConfig.getSessionTimeout() == null || sysConfig.getSessionTimeout().length() == 0){
    		  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00807"));
    	  }
    	  
    	  if (sysConfig.getOrgAboutUs() == null || sysConfig.getOrgAboutUs().length() == 0){
    		  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00808"));
    	  }    	  
    	  saveMessages(request, errors);
    	  
      }
      else if (tab.equalsIgnoreCase(BaseConstants.FWD_ADMIN_MODULE)){
//    	perform any extra tasks to display page
      }
      else{
    	  setupAdminDesktop(request, memService, classNewsService, pmService);
    	  tab = BaseConstants.FWD_DASHBOARD_MODULE;
      }
    	  
      
      
      return mapping.findForward(tab);

    }        
 
    
    
    
    
 }

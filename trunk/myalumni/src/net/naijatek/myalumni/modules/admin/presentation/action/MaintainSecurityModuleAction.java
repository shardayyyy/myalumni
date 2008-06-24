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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.naijatek.myalumni.framework.exceptions.DuplicateEmailException;
import net.naijatek.myalumni.framework.exceptions.DuplicateMemberException;
import net.naijatek.myalumni.framework.exceptions.MailServerException;
import net.naijatek.myalumni.framework.struts.MyAlumniDispatchAction;
import net.naijatek.myalumni.modules.common.domain.LoginHistoryVO;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.domain.SystemConfigVO;
import net.naijatek.myalumni.modules.common.presentation.form.MemberForm;
import net.naijatek.myalumni.modules.common.service.IErrorLogService;
import net.naijatek.myalumni.modules.common.service.IMemberService;
import net.naijatek.myalumni.modules.common.service.ISystemConfigService;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.encryption.Encoder;
import net.naijatek.myalumni.util.mail.SendMailUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class MaintainSecurityModuleAction extends MyAlumniDispatchAction{

    private Log logger = LogFactory.getLog(this.getClass());
    
    private IMemberService memberService;
    private IErrorLogService logService;
    private ISystemConfigService sysConfigSerivce;


    /**
     * Instantiates the service classes
     * @param memberService
     * @param logService
     */
    public MaintainSecurityModuleAction(IMemberService memberService, IErrorLogService logService,ISystemConfigService sysConfigSerivce  ) {
        super();
        this.memberService = memberService;
        this.logService = logService;
        this.sysConfigSerivce = sysConfigSerivce;
    }

    
    //**********************************************************************
    //******************      ACCESS HISTORY       ************************
    //**********************************************************************  
    public ActionForward listAccessHistory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in listAccessHistory...");
        listAccessHistoryHelper(request);       
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    public ActionForward purgeAllAccessLogs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in purgeAllAccessLogs...");
        logService.deleteAllAccessLogs();
        listAccessHistoryHelper(request);       
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }    
    
    public void listAccessHistoryHelper(HttpServletRequest request) throws Exception {
        logger.debug("in listAccessHistoryHelper...");
        List<LoginHistoryVO> history = memberService.getAllAccessTrails();
        if (history == null)
            history = new ArrayList<LoginHistoryVO>();
        setRequestObject(request, BaseConstants.LIST_OF_ACCESS_HISTORY, history);        
    }    
        
    
    
    // ----------------------------------
    //    MANAGE USER 
    //-----------------------------------
    public ActionForward filterUsersByAlphabelt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in filterUsersByAlphabelt..."); 
        listUserHelper(request, form);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    public ActionForward resetMemberPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in resetMemberPassword..."); 
        ActionMessages message = new ActionMessages(); 
        MemberForm userForm = (MemberForm)form;
        memberService.resetPassword(userForm.getMemberId(), getLocale(request).getLanguage(), getLastModifiedBy(request));
        message.add(BaseConstants.INFO_KEY, new ActionMessage("message.resetpassword"));
        saveMessages(request, message);          
        listUserHelper(request, form);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }

    public ActionForward listUsers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in listUsers...");        
        listUserHelper(request, form);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    public ActionForward prepareAddUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in prepareAddUser...");
        saveToken(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    public ActionForward addUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in addUser...");  
        ActionMessages messages = new ActionMessages();
        
        if ( !isTokenValid(request) ) {
            return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
        }
        MemberForm userForm = (MemberForm)form;
        MemberVO memberVO = new MemberVO();
        BeanUtils.copyProperties(memberVO, userForm);
        
        memberVO.setLastModifiedBy(getLastModifiedBy(request));
        memberVO.setMemberPassword(Encoder.getMD5_Base64(memberVO.getMemberPassword()));
        try{
        	memberService.addUser(memberVO);
        }
        catch (DuplicateMemberException e) {
        	messages.add(BaseConstants.WARN_KEY, new ActionMessage("error.duplicate.member"));
	        saveMessages(request, messages);
	        logger.info("DUPLICATE USER NAME - " + e.getMessage());
	        return mapping.getInputForward();
	      }
	      catch (DuplicateEmailException e) {
	    	messages.add(BaseConstants.WARN_KEY, new ActionMessage("error.duplicate.email"));
	        saveMessages(request, messages);
	        logger.info("DUPLICATE EMAIL - " + e.getMessage());
	        return mapping.getInputForward();
	      }
          
          
        listUserHelper(request, form);      
        resetToken(request);  
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    public ActionForward prepareUpdateUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in prepareUpdateUser...");
        saveToken(request);
        MemberForm memberForm = (MemberForm)form;
        MemberVO memberVO = memberService.getUser(memberForm.getMemberId());
        BeanUtils.copyProperties(memberForm, memberVO);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    public ActionForward updateUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in updateUser...");        
        if ( !isTokenValid(request) ) {
            return mapping.findForward(BaseConstants.FWD_INVALID_TOKEN);
        }
        
        ActionMessages messages = new ActionMessages();
        MemberForm memberForm = (MemberForm)form;
        MemberVO memberVO = new MemberVO();
        BeanUtils.copyProperties(memberVO, memberForm);
        
        memberVO.setLastModifiedBy(getLastModifiedBy(request));
        
        if (memberVO.getPromptChange().equals(BaseConstants.BOOLEAN_YES)){
        	memberVO.setMemberPassword(Encoder.getMD5_Base64(memberVO.getMemberPassword()));
        }
        else{
        	MemberVO tmpUserVO = memberService.getUser(memberForm.getMemberId());
        	memberVO.setMemberPassword(tmpUserVO.getMemberPassword());
        }
        
        try{
        	memberService.updateUser(memberVO);
        }
        catch (DuplicateEmailException e) {
        	 messages.add(BaseConstants.WARN_KEY, new ActionMessage("error.duplicate.email"));
            saveMessages(request, messages);
            logger.info("DUPLICATE EMAIL - " + e.getMessage());
            return mapping.getInputForward();
          }
        
        
        String reasonforUpdate = "TODO: NEED REASON (Update Done by Administrator)";
 
		try{
			SystemConfigVO sysConfigVO = sysConfigSerivce.getSystemConfig();
			SendMailUtil.sendProfileChangeNotificationMail(memberVO.getEmail(), memberVO.getFullName(), getLocale(request).getLanguage(), sysConfigVO, reasonforUpdate);
			messages.add(BaseConstants.INFO_KEY, new ActionMessage("message.userprofile.updated"));
		}
		catch(MailServerException e){
			messages.add(BaseConstants.ERROR_KEY, new ActionMessage("errors.account.mailserver"));
		}
		saveErrors(request, messages);         
         
        listUserHelper(request, form);
        resetToken(request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }    
           
    public ActionForward viewUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in viewUser...");
        MemberForm memberForm = (MemberForm)form;
        MemberVO memberVO = memberService.getUser(memberForm.getMemberId());
        setRequestObject(request, BaseConstants.OBJECT_VO, memberVO);
        
        // get the list of user login access
        List<LoginHistoryVO> loginAccess = memberService.getAccessTrailsByUserName(memberForm.getMemberId());
        setRequestObject(request, BaseConstants.LIST_OF_USERS_LOGIN_HISTORY, loginAccess);
        
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    
    private void listUserHelper(HttpServletRequest request, ActionForm form) {
        logger.debug("in listUserHelper...");
        MemberForm memberForm = (MemberForm)form;
        
        if (memberForm.getAlpha() == null){
        	memberForm.setAlpha("a");
        	memberForm.setSearchCategory(BaseConstants.FIRST_NAME);
        }
        
        List<MemberVO> members = memberService.filterUsersByAlphabelt(memberForm.getAlpha(), memberForm.getSearchCategory(), BaseConstants.BOOLEAN_YES);
        setRequestObject(request, BaseConstants.LIST_OF_USERS, members);    
        setRequestObject(request, BaseConstants.LIST_OF_USERS_COUNT, members.size());
    }
    
  //  private void listUserHelper(HttpServletRequest request) {
  //          logger.debug("in listUserHelper...");
  //          List users = memberService.getAllMembers();
  //          setRequestObject(request, BaseConstants.LIST_OF_USERS, users);
   //         setRequestObject(request, BaseConstants.LIST_OF_USERS_COUNT, BaseConstants.DEFAULT_DISPLAY_SIZE);
  //  }
    
        
        
     // ----------------------------------
     //    ASSIGN ROLE TO USER ACCESS HISTORY 
     //-----------------------------------
     public ActionForward displayAssignRoleToUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
         logger.debug("in displayAssignRoleToUser...");        
         
         removeSessionObject(request, BaseConstants.ASSIGN_ROLE_AVAILABLE_USERS);
         removeSessionObject(request, BaseConstants.ASSIGN_ROLE_SELECTED_USERS);
         
         List<MemberVO> availableUsers = memberService.getAllMembers();
         List<MemberVO> selectedUsers = new ArrayList<MemberVO>();
         
         setSessionObject(request, BaseConstants.ASSIGN_ROLE_AVAILABLE_USERS, availableUsers);
         setSessionObject(request, BaseConstants.ASSIGN_ROLE_SELECTED_USERS, selectedUsers);
         
         return mapping.findForward(BaseConstants.FWD_SUCCESS);
     }
             
        
    public ActionForward displayUsersByRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in displayUsersByRole...");        
        MemberForm memberForm = (MemberForm)form;   
        displayUsersByRoleHelper(memberForm.getIsAdmin(), request);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    
    private void displayUsersByRoleHelper(String roleId, HttpServletRequest request){
         
        List<MemberVO> availableUsers = memberService.getAllMembers();
        List<MemberVO> selectedUsers = memberService.getRoleUsers(roleId);
        
        // NEED TO TRIM THE AVALABLE LIST TO REMOVE THE SELECTED ONES
        List<MemberVO> trimmedAvailableUsers = trimAvailableUsersList(availableUsers, selectedUsers);
        
        setSessionObject(request, BaseConstants.ASSIGN_ROLE_AVAILABLE_USERS, trimmedAvailableUsers);
        setSessionObject(request, BaseConstants.ASSIGN_ROLE_SELECTED_USERS, selectedUsers);        
    }
    

    private List<MemberVO> trimAvailableUsersList(List<MemberVO> availableUsers, List<MemberVO> selectedUsers){
        List<MemberVO> trimmedAvailableList = new ArrayList<MemberVO>(); 
        boolean found = false;
        
        // remove all selected users from available users
        for (MemberVO oneAvailableUser: availableUsers){
            
            // take the one selected user and run thru the list of selected user, in order to remove if a match is found
            for (MemberVO oneSelectedUser: selectedUsers){	
                if (oneSelectedUser.getMemberId().equals(oneAvailableUser.getMemberId())){  // if match
                        found = true;
                }
            }
            
             if (!found){
                 if (!trimmedAvailableList.contains(oneAvailableUser)){  // and its not already in the trimmed list
                     trimmedAvailableList.add(oneAvailableUser);    // add it to the trimmed list
                 }
             }               
            found = false;            
        }
        return trimmedAvailableList;
    }
    
    public ActionForward assignRoleToUsers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in assignRoleToUsers...");        
        ActionMessages message = new ActionMessages(); 
        MemberForm memberForm = (MemberForm)form;        
        memberService.updateRoleUsers(memberForm.getIsAdmin(), memberForm.getLstSelectedUsers(), getLastModifiedBy(request));
        message.add(BaseConstants.INFO_KEY, new ActionMessage("message.rolesupdated"));
        displayUsersByRoleHelper(memberForm.getIsAdmin(), request);
        saveMessages(request, message);  
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }    
    
}
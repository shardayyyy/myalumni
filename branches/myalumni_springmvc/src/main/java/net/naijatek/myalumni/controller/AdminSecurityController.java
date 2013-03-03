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
package net.naijatek.myalumni.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.naijatek.myalumni.controller.exceptions.DuplicateEmailException;
import net.naijatek.myalumni.controller.exceptions.DuplicateMemberException;
import net.naijatek.myalumni.controller.exceptions.MailServerException;
import net.naijatek.myalumni.entity.LoginHistoryVO;
import net.naijatek.myalumni.entity.MemberVO;
import net.naijatek.myalumni.entity.SystemConfigVO;
import net.naijatek.myalumni.controller.helper.MyAlumniMessages;
import net.naijatek.myalumni.service.IErrorLogService;
import net.naijatek.myalumni.service.IMemberService;
import net.naijatek.myalumni.service.ISystemConfigService;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.encryption.Encoder;
import net.naijatek.myalumni.util.mail.SendMailUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/security")
public class AdminSecurityController extends MyAlumniBaseController {

    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private IMemberService memberService;

    @Autowired
    private IErrorLogService logService;

    @Autowired
    private ISystemConfigService sysConfigSerivce;


    /**
     * Instantiates the service classes
     * @param memberService
     * @param logService
     */
    /*public MaintainSecurityModuleAction(IMemberService memberService, IErrorLogService logService,ISystemConfigService sysConfigSerivce  ) {
        super();
        this.memberService = memberService;
        this.logService = logService;
        this.sysConfigSerivce = sysConfigSerivce;
    }*/

    
    //**********************************************************************
    //******************      ACCESS HISTORY       ************************
    //**********************************************************************  
    public ModelAndView listAccessHistory(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in listAccessHistory...");
        listAccessHistoryHelper(request);       
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }
    
    public ModelAndView purgeAllAccessLogs(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in purgeAllAccessLogs...");
        logService.deleteAllAccessLogs();
        listAccessHistoryHelper(request);       
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
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
    public ModelAndView filterUsersByAlphabelt(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in filterUsersByAlphabelt..."); 
        listUserHelper(request, form);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }
    
    public ModelAndView resetMemberPassword(BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in resetMemberPassword..."); 
        MyAlumniMessages message = new MyAlumniMessages();
        result.
        result.addError();

        MemberVO userForm = (MemberVO)form;
        memberService.resetPassword(userForm.getMemberId(), getLastModifiedBy(request));
        message.add(BaseConstants.INFO_KEY, new ActionMessage("message.resetpassword"));
        saveMessages(request, message);          
        listUserHelper(request, form);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }

    public ModelAndView listUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in listUsers...");        
        listUserHelper(request, form);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }
    
    public ModelAndView prepareAddUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in prepareAddUser...");
        saveToken(request);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }
    
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in addUser...");  
        MyAlumniMessages messages = new MyAlumniMessages();
        
        if ( !isTokenValid(request) ) {
            return new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
        }
        MemberVO userForm = (MemberVO)form;
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
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }
    
    public ModelAndView prepareUpdateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in prepareUpdateUser...");
        saveToken(request);
        MemberVO memberForm = (MemberVO)form;
        MemberVO memberVO = memberService.getUser(memberForm.getMemberId());
        BeanUtils.copyProperties(memberForm, memberVO);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }
    
    public ModelAndView updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in updateUser...");        
        if ( !isTokenValid(request) ) {
            return new ModelAndView(BaseConstants.FWD_INVALID_TOKEN);
        }
        
        MyAlumniMessages messages = new MyAlumniMessages();
        MemberVO memberForm = (MemberVO)form;
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
			SendMailUtil.sendProfileChangeNotificationMail(memberVO.getEmail(), memberVO.getFullName(), sysConfigVO, reasonforUpdate);
			messages.add(BaseConstants.INFO_KEY, new ActionMessage("message.userprofile.updated"));
		}
		catch(MailServerException e){
			messages.add(BaseConstants.ERROR_KEY, new ActionMessage("errors.account.mailserver"));
		}
		saveErrors(request, messages);         
         
        listUserHelper(request, form);
        resetToken(request);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }    
           
    public ModelAndView viewUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in viewUser...");
        MemberVO memberForm = (MemberVO)form;
        MemberVO memberVO = memberService.getUser(memberForm.getMemberId());
        setRequestObject(request, BaseConstants.OBJECT_VO, memberVO);
        
        // get the list of user login access
        List<LoginHistoryVO> loginAccess = memberService.getAccessTrailsByUserName(memberForm.getMemberId());
        setRequestObject(request, BaseConstants.LIST_OF_USERS_LOGIN_HISTORY, loginAccess);
        
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }
    
    
    private void listUserHelper(HttpServletRequest request, ActionForm form) {
        logger.debug("in listUserHelper...");
        MemberVO memberForm = (MemberVO)form;
        
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
     public ModelAndView displayAssignRoleToUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
         logger.debug("in displayAssignRoleToUser...");        
         
         removeSessionObject(request, BaseConstants.ASSIGN_ROLE_AVAILABLE_USERS);
         removeSessionObject(request, BaseConstants.ASSIGN_ROLE_SELECTED_USERS);
         
         List<MemberVO> availableUsers = memberService.getAllMembers();
         List<MemberVO> selectedUsers = new ArrayList<MemberVO>();
         
         setSessionObject(request, BaseConstants.ASSIGN_ROLE_AVAILABLE_USERS, availableUsers);
         setSessionObject(request, BaseConstants.ASSIGN_ROLE_SELECTED_USERS, selectedUsers);
         
         return new ModelAndView(BaseConstants.FWD_SUCCESS);
     }
             
        
    public ModelAndView displayUsersByRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in displayUsersByRole...");        
        MemberVO memberForm = (MemberVO)form;   
        displayUsersByRoleHelper(memberForm.getIsAdmin(), request);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
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
    
    public ModelAndView assignRoleToUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in assignRoleToUsers...");        
        MyAlumniMessages message = new MyAlumniMessages(); 
        MemberVO memberForm = (MemberVO)form;        
        memberService.updateRoleUsers(memberForm.getIsAdmin(), memberForm.getLstSelectedUsers(), getLastModifiedBy(request));
        message.add(BaseConstants.INFO_KEY, new ActionMessage("message.rolesupdated"));
        displayUsersByRoleHelper(memberForm.getIsAdmin(), request);
        saveMessages(request, message);  
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }    
    
}
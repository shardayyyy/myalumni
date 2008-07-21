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
package net.naijatek.myalumni.modules.common.presentation.action;
	
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.naijatek.myalumni.framework.exceptions.NotLoginException;
import net.naijatek.myalumni.framework.exceptions.UserAccountException;
import net.naijatek.myalumni.framework.struts.MyAlumniDispatchAction;
import net.naijatek.myalumni.framework.struts.MyAlumniUserContainer;
import net.naijatek.myalumni.modules.common.domain.LoginHistoryVO;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.helper.OnlineUserManager;
import net.naijatek.myalumni.modules.common.helper.ReasonCodes;
import net.naijatek.myalumni.modules.common.presentation.form.LoginForm;
import net.naijatek.myalumni.modules.common.presentation.form.MemberForm;
import net.naijatek.myalumni.modules.common.service.IClassNewsService;
import net.naijatek.myalumni.modules.common.service.IMemberService;
import net.naijatek.myalumni.modules.common.service.IPrivateMessageService;
import net.naijatek.myalumni.modules.common.service.ISystemConfigService;
import net.naijatek.myalumni.modules.common.service.IUserAccountService;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.SystemConfigConstants;
import net.naijatek.myalumni.util.encryption.Encoder;
import net.naijatek.myalumni.util.mail.SendMailUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

	
	public class SecurityAction extends MyAlumniDispatchAction {
	
	    private IUserAccountService securityService;
	    private ISystemConfigService sysConfigService;
	    private IPrivateMessageService privateMessageService;
	    private IMemberService memService;
	    private IClassNewsService classNewsService;
	    
	    private static Log logger = LogFactory.getLog(SecurityAction.class);
	
	    public SecurityAction(IUserAccountService securityService, ISystemConfigService sysConfigService, IPrivateMessageService privateMessageService, 
	    						IMemberService memService, IClassNewsService classNewsService) {
	        this.securityService = securityService;
	        this.sysConfigService = sysConfigService;
	        this.privateMessageService = privateMessageService;
	        this.memService = memService;
	        this.classNewsService = classNewsService;
	    }
	
	
	    public ActionForward login(ActionMapping mapping, ActionForm form, 
	                               HttpServletRequest request, 
	                               HttpServletResponse response) throws Exception {
	
	        HttpSession session = request.getSession(false);
	        Integer counter = (Integer)session.getAttribute("loginCounter");
	        int loginCounter = 0;
	        String currentIP = request.getRemoteAddr();
	        ActionMessages errors = new ActionMessages();
	
	
	        LoginForm loginForm = (LoginForm)form;

	        
	        String username = loginForm.getMemberUserName();
	        String password = loginForm.getMemberPassword();
	        
	        
	        MemberVO token = null;
	        LoginHistoryVO accessHistory = null;
	        
	        ServletContext sCtx = request.getSession().getServletContext();
	        WebApplicationContext wCtx = WebApplicationContextUtils.getWebApplicationContext(sCtx);        
	        MyAlumniUserContainer container = (MyAlumniUserContainer)wCtx.getBean("userContainer");     
	
	
	        logger.info("Login attempt --> , [ " + username +   " ][ " + currentIP + "]");
	
	
	        if (counter == null) {
	            session.setAttribute("loginCounter", new Integer(loginCounter));
	            session.setAttribute("loginUserCounter", username);
	        } else {
	            loginCounter = counter.intValue();
	        }
	
	        // login and store it in the session
	        accessHistory = createAccessHistory(request, username);  
	        
	        
	        try {
                token = securityService.login(username, password);
                token.setLoginSuccessfull(true);
                accessHistory.setLoginStatus(BaseConstants.LOGIN_PASS);
                accessHistory.setReasonCode(ReasonCodes.SUCCESS);
    	        
    	        
    	        // Prompt user to change password
    	        if (token.getPromptChange().equals(BaseConstants.BOOLEAN_YES)){
    	        	
    	        	loginForm.setMemberUserName(token.getMemberUserName());
    	        	loginForm.setMemberPassword("");
    	        	loginForm.setMemberTempPassword("");
    	        	loginForm.setMemberPasswordConfirm("");                
    	        	 
    	        	session.invalidate();
                    errors.add(BaseConstants.INFO_KEY, new ActionMessage("errors.login.resetpassword"));
                    saveMessages(request, errors);
                    accessHistory.setLoginStatus(BaseConstants.LOGIN_FAIL);
                    accessHistory.setReasonCode(ReasonCodes.CHANGE_PASSWORD);
        	        securityService.addAccessTrail(accessHistory);
                    return mapping.findForward(BaseConstants.FWD_EXPIRED_PASSWORD);
    	        }
    	        
    	        
    	        // Cant find roles
    	        if (token.getIsAdmin() == null || (!token.getIsAdmin().equals(BaseConstants.BOOLEAN_NO) & !token.getIsAdmin().equals(BaseConstants.BOOLEAN_YES))){
    	            errors.add(BaseConstants.ERROR_KEY, new ActionMessage("errors.login.role"));
    	            saveMessages(request, errors);
                    accessHistory.setLoginStatus(BaseConstants.LOGIN_FAIL);
                    accessHistory.setReasonCode(ReasonCodes.NO_ROLES_FOUND);
        	        securityService.addAccessTrail(accessHistory);
    	            return mapping.getInputForward();
    	        }     
    	        
    	        
    	        
	        } catch (UserAccountException e) {
	        	//token.setLoginSuccessfull(false);
	            if (e.getExceptionReason() == NotLoginException.ACCOUNT_DEACTIVATED) {
	                session.invalidate();
	                errors.add(BaseConstants.WARN_KEY, new ActionMessage("errors.account.deactivated"));
	                saveMessages(request, errors);
	                logger.info("ACCOUNT DEACTIVATED : " + username);
                    accessHistory.setLoginStatus(BaseConstants.LOGIN_FAIL);
                    accessHistory.setReasonCode(ReasonCodes.ACCOUNT_DEACTIVATED);
	    	        securityService.addAccessTrail(accessHistory);
	                return mapping.getInputForward();
	            } 	        	
	            if (e.getExceptionReason() == NotLoginException.ACCOUNT_LOCKED) {
	                session.invalidate();
	                errors.add(BaseConstants.WARN_KEY, new ActionMessage("errors.account.locked"));
	                saveMessages(request, errors);
	                logger.info("ACCOUNT LOCKED : " + username);
                    accessHistory.setLoginStatus(BaseConstants.LOGIN_FAIL);
                    accessHistory.setReasonCode(ReasonCodes.ACCOUNT_LOCKED);
	    	        securityService.addAccessTrail(accessHistory);
	                return mapping.getInputForward();
	            } 
	            else if (e.getExceptionReason() == NotLoginException.WRONG_PASSWORD) {
	                //session.invalidate();
	                errors.add(BaseConstants.WARN_KEY, new ActionMessage("errors.password.mismatch", currentIP));
	                saveMessages(request, errors);
	                logger.info("INVALID PASSWORD : " + username);
                    accessHistory.setLoginStatus(BaseConstants.LOGIN_FAIL);
                    accessHistory.setReasonCode(ReasonCodes.INVALID_CREDENTIAL);
                    
	                // increment failed login counter
	                // if the same user contineously try to login , counter increases
	                // if a diff user from the prev user, but same session, counter resets
	                if (username.equals(session.getAttribute("loginUserCounter"))) {
	                    loginCounter++;
	                } else {
	                    loginCounter = 0;
	                    session.setAttribute("loginCounter", new Integer(loginCounter));
	                }
	
	                // Maximum number of time a user can try to login unsuccessfully
	                int userMaxLogin = Integer.parseInt(getSysProp().getValue("USER_MAX_LOGIN"));
	                if (loginCounter >= userMaxLogin) {
	                    logger.warn(username + " : User has exceeded maximum number of login attempts");
	                    logger.warn("User account has been disabled. Please contact System Administrator");
	
	                    // deactivating user account
	                    if (securityService.lockMemberAccount(username)) {
	                        session.invalidate();
	                        errors.add(BaseConstants.WARN_KEY, new ActionMessage("errors.account.locked"));
	                        logger.info("ACCOUNT LOCKED :  IP: (" + currentIP + ") " + username);
	                        accessHistory.setLoginStatus(BaseConstants.LOGIN_FAIL);
	                        accessHistory.setReasonCode(ReasonCodes.ACCOUNT_LOCKED);
	    	    	        securityService.addAccessTrail(accessHistory);
	    	    	        return mapping.getInputForward();
	                    }
	                } else {
	                    session.setAttribute("loginCounter", new Integer(loginCounter));
	                }
	                
	    	        securityService.addAccessTrail(accessHistory);
	    	        return mapping.getInputForward();
	            }
	            else if (e.getExceptionReason() == NotLoginException.WRONG_USERNAME) {
	                logger.info("INVALID USERNAME: IP: (" + currentIP + ") " + username + " User login attempt has failed. Count = " + loginCounter);
                    accessHistory.setLoginStatus(BaseConstants.LOGIN_FAIL);
                    accessHistory.setReasonCode(ReasonCodes.ACCOUNT_INVALID);
	                errors.add(BaseConstants.WARN_KEY, new ActionMessage("errors.password.mismatch"));
	                saveMessages(request, errors);
	                logger.info("UNSUCCESSFULL FWD_LOGIN - Invalid login  IP: (" + currentIP + ") " + username);
                    accessHistory.setLoginStatus(BaseConstants.LOGIN_FAIL);
                    accessHistory.setReasonCode(ReasonCodes.INVALID_CREDENTIAL);
	    	        securityService.addAccessTrail(accessHistory);
	    	        return mapping.getInputForward();
	            } 
	            else if (e.getExceptionReason() == NotLoginException.ACCOUNT_UNAPPROVED) {
	                errors.add(BaseConstants.WARN_KEY, new ActionMessage("errors.account.notapproved"));
	                saveMessages(request, errors);
	                logger.info("UNSUCCESSFULL FWD_LOGIN - Account not approved yet. :  IP: (" + currentIP + ") " + username);
                    accessHistory.setLoginStatus(BaseConstants.LOGIN_FAIL);
                    accessHistory.setReasonCode(ReasonCodes.ACCOUNT_UNAPPROVED);
	                securityService.addAccessTrail(accessHistory);
	    	        return mapping.getInputForward();
	            } 
	            
	        }
	        

	        
	        if (token.isLoginSuccessfull()) {
	            //clear out any old session info
	            session = request.getSession(false);
	            if (session != null) {
	                session.invalidate();
	            }
	
	            
	            // Create a new session for this user
	            session = request.getSession(true);  
	            

	            // place users container in session
	            container.setToken(token);
	            setSessionUserContainer(request, container);
	            
	            	
	            setupOtherTasks(request, container, token);
		          
	            int sessionTimeout = setupSessionTimeout(session);
	
	            // adding the user user the list of online users
	            token.setLastRequestTime(new Date());	      
	            OnlineUserManager manager = OnlineUserManager.getInstance();
	            manager.addOnlineUser(token, sessionTimeout);
	            //sCtx.setAttribute("onlineusers", manager.getOnlineUsers(sessionTimeout));
	            setServletContextObject(request, "onlineusers", manager.getOnlineUsers(sessionTimeout));
	           
	            
	            session.setAttribute(BaseConstants.IS_ONLINE, BaseConstants.BOOLEAN_YES);
	            session.setAttribute(BaseConstants.IS_ADMIN, token.getIsAdmin());
	            
	            // ADMIN
	            String context = request.getPathInfo();
	            
	            if (context.startsWith("/admin/") && token.getIsAdmin().equals(BaseConstants.BOOLEAN_YES)){
	            		setupAdminDesktop(request, memService, classNewsService, privateMessageService);
	            		securityService.addAccessTrail(accessHistory);
	            }
	            else if (context.startsWith("/member/") && 
	            		(token.getIsAdmin().equals(BaseConstants.BOOLEAN_YES) ||
	            		 token.getIsAdmin().equals(BaseConstants.BOOLEAN_NO))){
	            	 	securityService.addAccessTrail(accessHistory);	            	 	
	            }
	            else{
                    accessHistory.setLoginStatus(BaseConstants.LOGIN_FAIL);
                    accessHistory.setReasonCode(ReasonCodes.ACCOUNT_UNAUTHORIZED);
	                securityService.addAccessTrail(accessHistory);	
                    errors.add(BaseConstants.WARN_KEY, new ActionMessage("errors.account.notenoughrights"));
                    saveMessages(request, errors);
                    logger.info("ACCOUNT UNAUTHORIZED :  IP: (" + currentIP + ") " + username);	                
	                return mapping.getInputForward();
	            }
	            
	            return mapping.findForward(BaseConstants.FWD_SUCCESS);
	            
	        } else {
	            errors.add(BaseConstants.FATAL_KEY, new ActionMessage("errors.technical.difficulty"));
	            saveMessages(request, errors);
	            return mapping.getInputForward();
	        }
	    }
	
	    //--------------------------------------------------------------------------
	    //--
	    //--                   P R I V A T E   M E T H O D S
	    //--
	    //--------------------------------------------------------------------------
	    private int setupSessionTimeout(HttpSession session){
            int sessionTimeout =  sysConfigService.getSessionTimeOut() ;
        	
            try {
                session.setMaxInactiveInterval(sessionTimeout);
            } catch (NumberFormatException nfe) {
                logger.fatal("Unable to parse session timeout information from system file.");
                sessionTimeout = 900;
                session.setMaxInactiveInterval(sessionTimeout); // default is 15 mins. 15*60
            }    	    	
            
            return sessionTimeout;
	    }

	    
	    
	    
	    /**
	     * Sets up other tasks 
	     *
	     * @throws Exception
	     * @param request HttpServletRequest
	     */
	    private void setupOtherTasks(final HttpServletRequest request, MyAlumniUserContainer container, MemberVO token) throws Exception {
	    	container.setNewMailCount(privateMessageService.getMailCountByUserName(token.getMemberId(), BaseConstants.PM_STATUS_NEW));
	    }
	
	
	    
	    /**
	     * Performs the logging out of users
	     *
	     * @param mapping ActionMapping
	     * @param form ActionForm
	     * @param request HttpServletRequest
	     * @param response HttpServletResponse
	     * @throws Exception
	     * @return ActionForward
	     */
	public ActionForward logout(ActionMapping mapping, ActionForm form, 
	                             HttpServletRequest request, 
	                             HttpServletResponse response) throws Exception {
	
	   HttpSession session = request.getSession(true);
	
	   int sessionTimeout = setupSessionTimeout(session);
	   MemberVO token = getCurrentLoggedInUser(request);
	   if (token != null){
		   OnlineUserManager manager = OnlineUserManager.getInstance();
		   manager.removeOnlineUser(token.getMemberUserName());
		   ServletContext sCtx = request.getSession().getServletContext();
		   sCtx.setAttribute("onlineusers", manager.getOnlineUsers(sessionTimeout));
	   }
	   
	   if (session != null) {
	       session.removeAttribute(BaseConstants.USER_CONTAINER);
	       logger.info("User successfully logged out...");
	       session.invalidate();
	   }
	   
	   return (mapping.findForward(BaseConstants.FWD_SUCCESS));
	}
	
	
	/**
	* This methods updates the user password.
	* 
	* @param mapping
	* @param form
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	public ActionForward changePassword(ActionMapping mapping, ActionForm form, 
	                             HttpServletRequest request, 
	                             HttpServletResponse response) throws Exception {
	                             
	   ActionMessages messages = new ActionMessages();
	   LoginForm loginForm = (LoginForm)form;     
	   
	   String username = getUserContainer(request).getToken().getMemberUserName();
	   String currentPassword = loginForm.getMemberTempPassword();
	   String newPassword = loginForm.getMemberPasswordConfirm();
	   
	   try{
	       securityService.changePassword(username, currentPassword, newPassword);
	   }
	   catch(UserAccountException e){
	       if (e.getErrorCode() == NotLoginException.WRONG_USERNAME) {
	           messages.add(BaseConstants.ERROR_KEY, new ActionMessage("errors.login.username"));
	       }
	       else if (e.getErrorCode() == NotLoginException.WRONG_PASSWORD) {
	           messages.add(BaseConstants.ERROR_KEY, new ActionMessage("errors.login.password"));
	       }
	       else if (e.getErrorCode() == NotLoginException.SAME_PASSWORD) {
	           messages.add(BaseConstants.ERROR_KEY, new ActionMessage("errors.login.samepassword"));
	       } 
	       saveMessages(request, messages); 
	       return (mapping.getInputForward());                            
	   }
	
	   messages.add(BaseConstants.INFO_KEY, new ActionMessage("message.password.updated"));
	   saveMessages(request, messages);
	   return mapping.getInputForward();
	}
	
	
	/**
	* This methods sends an email to the user about the lost password.
	* 
	* @param mapping
	* @param form
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	public ActionForward forgotPassword(ActionMapping mapping, ActionForm form, 
	                             HttpServletRequest request, 
	                             HttpServletResponse response) throws Exception {
	                             
	   ActionMessages messages = new ActionMessages();
	       
	   LoginForm loginForm = (LoginForm)form;     
	   String username = loginForm.getMemberUserName();
	   
	   try{
	       securityService.notifyPassword(username, getLocale(request).getLanguage(), request);
	       messages.add(BaseConstants.INFO_KEY, new ActionMessage("errors.account.resetinst"));
	   }
	   catch(UserAccountException e){
	       if (e.getErrorCode() == NotLoginException.USER_NOT_FOUND){
	       		messages.add(BaseConstants.ERROR_KEY, new ActionMessage("errors.account.notfound"));
	       }
	       else if (e.getErrorCode() == NotLoginException.ACCOUNT_UNAPPROVED){
	       		messages.add(BaseConstants.ERROR_KEY, new ActionMessage("errors.account.notapproved"));
	       }
	       else if (e.getErrorCode() == NotLoginException.ACCOUNT_DEACTIVATED){
		       	messages.add(BaseConstants.ERROR_KEY, new ActionMessage("errors.account.deactivated"));
		   }
	       else if (e.getErrorCode() == NotLoginException.ACCOUNT_LOCKED){
	       		messages.add(BaseConstants.ERROR_KEY, new ActionMessage("errors.account.locked"));
	       }
	       else {
	       		messages.add(BaseConstants.ERROR_KEY, new ActionMessage("errors.account.mailserver"));
	       }
	       saveMessages(request, messages);
	       return (mapping.getInputForward());            
	   }
	   saveMessages(request, messages);
	   return (mapping.findForward(BaseConstants.FWD_SUCCESS));
	}
	
	
	/**
	* This methods sends an email to the user about the forgotten username.
	* 
	* @param mapping
	* @param form
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	public ActionForward forgotUserName(ActionMapping mapping, ActionForm form, 
	                             HttpServletRequest request, 
	                             HttpServletResponse response) throws Exception {
	                             
	   ActionMessages messages = new ActionMessages();
	       
	   LoginForm loginForm = (LoginForm)form;     
	   String email = loginForm.getEmail();
	   
	   try{
	       securityService.notifyUserName(email, getLocale(request).getLanguage(), request);
	       messages.add(BaseConstants.INFO_KEY, new ActionMessage("errors.account.resetinst"));
	   }
	   catch(UserAccountException e){
	       if (e.getErrorCode() == NotLoginException.USER_NOT_FOUND){
	       		messages.add(BaseConstants.ERROR_KEY, new ActionMessage("errors.account.notfound"));
	       }
	       else if (e.getErrorCode() == NotLoginException.ACCOUNT_UNAPPROVED){
	       		messages.add(BaseConstants.ERROR_KEY, new ActionMessage("errors.account.notapproved"));
	       }
	       else if (e.getErrorCode() == NotLoginException.ACCOUNT_DEACTIVATED){
		       	messages.add(BaseConstants.ERROR_KEY, new ActionMessage("errors.account.deactivated"));
		   }
	       else if (e.getErrorCode() == NotLoginException.ACCOUNT_LOCKED){
	       		messages.add(BaseConstants.ERROR_KEY, new ActionMessage("errors.account.locked"));
	       }
	       else {
	       		messages.add(BaseConstants.ERROR_KEY, new ActionMessage("errors.account.mailserver"));
	       }
	       saveMessages(request, messages);
	       return (mapping.getInputForward());            
	   }
	   saveMessages(request, messages);
	   return (mapping.findForward(BaseConstants.FWD_SUCCESS));
	}
	
	
	
	/**
	* Update Expired Password, this method is called when the user is forced to change password
	* @param mapping
	* @param form
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	public ActionForward updateExpiredPassword(ActionMapping mapping, ActionForm form, 
	                             HttpServletRequest request, 
	                             HttpServletResponse response) throws Exception {
	                             
	   ActionMessages messages = new ActionMessages();
	   LoginForm loginForm = (LoginForm)form;     
	   String username = loginForm.getMemberUserName();
	   String password = loginForm.getMemberPassword();
	   String temppassword = loginForm.getMemberTempPassword();        
	   
	   try{
	       securityService.updateExpiredPassword(username, password, temppassword);
	   }
	   catch(UserAccountException e){
	       if (e.getErrorCode() == NotLoginException.USER_NOT_FOUND){
	           messages.add(BaseConstants.ERROR_KEY, new ActionMessage("errors.account.notfound"));
	       }
	       else if (e.getErrorCode() == NotLoginException.ACCOUNT_UNAPPROVED){
	           messages.add(BaseConstants.ERROR_KEY, new ActionMessage("errors.account.notapproved"));
	       }
	       else if (e.getErrorCode() == NotLoginException.ACCOUNT_LOCKED){
	           messages.add(BaseConstants.ERROR_KEY, new ActionMessage("errors.account.locked"));
	       }
	       else if (e.getErrorCode() == NotLoginException.WRONG_PASSWORD){
	           messages.add(BaseConstants.ERROR_KEY, new ActionMessage("errors.account.invalidcurrpswd"));
	       }  
	       else if (e.getErrorCode() == NotLoginException.SAME_PASSWORD) {
	           messages.add(BaseConstants.ERROR_KEY, new ActionMessage("errors.login.samepassword"));
	       } 
	       saveMessages(request, messages);
	       return mapping.getInputForward();
	   }        
	   
	   
	   messages.add(BaseConstants.INFO_KEY, new ActionMessage("message.password.updated"));;
	   //saveMessages(request, messages);
	   return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}
	
	
	
	
	    //--------------------------------------------------------------------------
	    //--
	    //--                   P R I V A T E   M E T H O D S
	    //--
	    //--------------------------------------------------------------------------
	    
	    private LoginHistoryVO createAccessHistory(HttpServletRequest req, String username){
	        LoginHistoryVO accessHistory = new LoginHistoryVO();
	        accessHistory.setUserName(username);
	        accessHistory.setUserAgent(getLocale(req).getLanguage());
	        accessHistory.setSourceIP(req.getRemoteAddr());
	        accessHistory.setRequestTime(new Date()); 
	        return accessHistory;
	      }
	    
	    
	    
	    //--------------------------------------------------------------------------
	    //--
	    //--               ACTIVATION  M E T H O D S
	    //--
	    //--------------------------------------------------------------------------
	    
	    public ActionForward activateMemberAccount(ActionMapping mapping,
	                                       ActionForm form,
	                                       HttpServletRequest request,
	                                       HttpServletResponse response) throws
	        Exception {

//
//	      MemberForm memberForm = (MemberForm) form;
//	      String memberUserName = memberForm.getMemberUserName().trim();
//	      String activationCode = memberForm.getActivationCode().trim();
//	      String memberEmail = memberForm.getEmail().trim();
//
//
//	      if (memService.isActivationCodeCorrect(activationCode, memberUserName, memberEmail)) {
//	        memberForm.setActivationCode(activationCode);
//	        memberForm.setEmail(memberEmail);
//	      }
//	      else {
//	        ActionMessages errors = new ActionMessages();
//	        errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.invalidactivationcode"));
//	        saveMessages(request, errors);
//	        return mapping.getInputForward();
//	      }
	    	
	        ActionMessages errors = new ActionMessages();
	        errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.invalidactivationcode"));
	        saveMessages(request, errors);	    	
	      return mapping.findForward(BaseConstants.FWD_SUCCESS);
	    }    
	    
	    
	    
	    
	    public ActionForward finalizeActivateAccount( ActionMapping mapping,
	                                  ActionForm form,
	                                  HttpServletRequest request,
	                                  HttpServletResponse response )
	      throws Exception {
//	        MemberForm memForm = (MemberForm)form;
//	        String memberTempUserName = memForm.getMemberTempUserName();
//	        String memberUserName = memForm.getMemberUserName();
//	        String activationCode = memForm.getActivationCode();
//	        String memberEmail = memForm.getEmail();
//	        //String memberPassword = memForm.getMemberPassword();
//	    
//	        if ( !Encoder.getMD5_Base64(memberEmail).equals(activationCode) ){
//	          ActionMessages errors = new ActionMessages();
//	          errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.activationerror"));
//	          saveMessages(request, errors);
//	             return mapping.getInputForward();
//	        }
//	    
//	        if ( memService.isMemberAvailableByUserName(memberUserName)){
//	          ActionMessages errors = new ActionMessages();
//	          errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.duplicate.member"));
//	          saveMessages(request, errors);
//	             return mapping.getInputForward();
//	        }
//	    
//	    
//	        // TODO 
//	        //memService.updateMemberUserName(memberTempUserName, memberUserName, memberEmail, getLastModifiedBy(request));
//	        logger.info("ACCOUNT ACTIVATED : " + memberUserName + " " + memberEmail );
//	    
//	        StringBuffer message = new StringBuffer();
//	        message.append("Your account is ready, login in the upper right hand corner.");
//	        setRequestObject(request, BaseConstants.MESSAGE, message.toString());
//	    
	        ActionMessages errors = new ActionMessages();
	        errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.invalidactivationcode"));
	        saveMessages(request, errors);		    	
	        return mapping.findForward(BaseConstants.FWD_SUCCESS);
	    }    
	    
	    
	    
	    public ActionForward getActivationInstructions(ActionMapping mapping,
	                                       ActionForm form,
	                                       HttpServletRequest request,
	                                       HttpServletResponse response) throws
	        Exception {
	        
//	        ActionMessages errors = new ActionMessages();
//	        
//	        
//	        if (isCancelled(request)){
//	            return mapping.findForward(BaseConstants.FWD_CANCEL);
//	        }
//	        
//	        MemberForm memberForm = (MemberForm) form;
//	        String email = memberForm.getEmail();
//	        
//	        if (memService.isMemberAvailableByEmail(email)) {
//	            boolean activated = memService.isAccountActivatedByEmail(email);
//	            if (activated) {
//	                errors.add(BaseConstants.INFO_KEY, new ActionMessage("errors.account.alreadyactivated"));
//	                saveMessages(request, errors);
//	                return mapping.getInputForward();
//	           }
//	           else if (!activated){
//	               errors.add(BaseConstants.WARN_KEY, new ActionMessage("errors.account.notapproved"));
//	               saveMessages(request, errors);
//	               return mapping.getInputForward();               
//	           }
//	           else {
//	                SendMailUtil.sendActivationInstructions(email, memService.getMemberUserNameByEmail(email));
//	           }
//	        
//	            StringBuffer message = new StringBuffer();
//	            message.append("Your account activation instructions has been sent to " + email + ".");
//	            setRequestObject(request, BaseConstants.MESSAGE, message.toString());
//	        
//	        
//	        }
//	        else {
//	            errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.noemail", email));
//	            saveMessages(request, errors);
//	            return mapping.getInputForward();
//	        }
	    	
	    	
	        ActionMessages errors = new ActionMessages();
	        errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.invalidactivationcode"));
	        saveMessages(request, errors);		    	
	    	return mapping.findForward(BaseConstants.FWD_SUCCESS);
	    }  

	    
	}

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
package net.naijatek.myalumni.modules.members.presentation.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.naijatek.myalumni.framework.struts.MyAlumniDispatchAction;
import net.naijatek.myalumni.framework.struts.MyAlumniUserContainer;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.domain.PrivateMessageVO;
import net.naijatek.myalumni.modules.common.domain.SystemConfigVO;
import net.naijatek.myalumni.modules.common.helper.PrivateMessageHelper;
import net.naijatek.myalumni.modules.common.presentation.form.PrivateMessageForm;
import net.naijatek.myalumni.modules.common.service.IMemberService;
import net.naijatek.myalumni.modules.common.service.IPrivateMessageService;
import net.naijatek.myalumni.modules.common.service.ISystemConfigService;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.SystemConfigConstants;
import net.naijatek.myalumni.util.mail.SendMailUtil;
import net.naijatek.myalumni.util.utilities.DateFormatUtil;
import net.naijatek.myalumni.util.utilities.StringUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class MaintainPrivateMessageAction  extends MyAlumniDispatchAction{

    private IPrivateMessageService pmService;
    private IMemberService memService;
    private ISystemConfigService sysConfigSerivce;
    
    
    private static Log logger = LogFactory.getLog(MaintainPrivateMessageAction.class);
    
    public MaintainPrivateMessageAction(final IMemberService memService,
    		final IPrivateMessageService pmService, ISystemConfigService sysConfigSerivce) {
        this.pmService = pmService;
        this.memService = memService;
        this.sysConfigSerivce = sysConfigSerivce;
    }
    
    
    public ActionForward deleteMail(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws
        Exception {

      if (isCancelled(request)) {
        return mapping.findForward(BaseConstants.FWD_CANCEL);
      }
      
      MemberVO token = getCurrentLoggedInUser(request);  
      
      // check to see if the user logged on is a member
      if (!memberSecurityCheck(request, token)) {
        return mapping.findForward(BaseConstants.FWD_LOGIN);
      }
      
      logger.debug("Deleting mail...");
      
  
        String[] mailArray = new String[0];    

        PrivateMessageForm pmForm = (PrivateMessageForm)form;
        String memberId = token.getMemberId();
        String privMsgsAction = pmForm.getPrivMsgsAction();

        String privAdminDelete =  StringUtil.safeString(pmForm.getPrivAdminDelete());
        String privAdminMove = StringUtil.safeString(pmForm.getPrivAdminMove());


        if ( privAdminDelete.equalsIgnoreCase("yes") || privAdminMove.equalsIgnoreCase("yes")){
              // check to see if the user logged on is a member
              if (!adminSecurityCheck(request, token)) {
                  return mapping.findForward(BaseConstants.FWD_ADMIN_LOGIN);
              }       
              memberId = BaseConstants.ADMIN_USERNAME_ID;
        }

         mailArray = request.getParameterValues("messageId");

        if (mailArray == null) {
          ActionMessages errors = new ActionMessages();
          errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.selectOne"));
          saveMessages(request, errors);
          return mapping.getInputForward();
        }

        /**
         * DELETE MAIL
         */
        if (privMsgsAction.equalsIgnoreCase("delete")){
        	pmService.deleteMail(mailArray, memberId, getLastModifiedBy(request));
            doMailDuties(token, request);
        }
        /**
         * MOVE MAIL
         */
        else  if (privMsgsAction.equalsIgnoreCase("move")){
        	String toFolder = pmForm.getFolderName();
          	pmService.moveMail(mailArray, memberId, toFolder, getLastModifiedBy(request));
            doMailDuties(token, request);
        }
        /**
         * EMPTY TRASH
         */
        else if (privMsgsAction.equalsIgnoreCase("empty")){
          	pmService.deleteMail(mailArray, memberId, getLastModifiedBy(request));
            doMailDuties(token, request);
        }

        else {
           ActionMessages errors = new ActionMessages();
           errors.add(BaseConstants.FATAL_KEY,  new ActionMessage("errors.technical.difficulty"));
           saveMessages(request, errors);
           return mapping.getInputForward();
         }

         return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }


    private void doMailDuties(final MemberVO token, final HttpServletRequest request){
    	MyAlumniUserContainer container = (MyAlumniUserContainer)request.getSession().getAttribute(BaseConstants.USER_CONTAINER);
        PrivateMessageHelper pmHelper = pmService.getMessageCenter(token.getMemberId(), BaseConstants.FOLDER_INBOX, container );
        setSessionObject(request, BaseConstants.MESSAGE_CENTER , pmHelper);
    }
    
    //done
    public ActionForward listMailBox(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws
        Exception {

        
      MemberVO token = getCurrentLoggedInUser(request);
      
      // check to see if the user logged on is a member
      if (!memberSecurityCheck(request, token)) {
        return mapping.findForward(BaseConstants.FWD_LOGIN);
      }
  
      
      MyAlumniUserContainer container = (MyAlumniUserContainer)request.getSession().getAttribute(BaseConstants.USER_CONTAINER);
      PrivateMessageHelper pmHelper = pmService.getMessageCenter(token.getMemberId(), BaseConstants.FOLDER_INBOX, container );
      setSessionObject(request, BaseConstants.MESSAGE_CENTER , pmHelper);
      return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }    
    
    
    public ActionForward displayMailFolder(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws
        Exception {

       MemberVO token = getCurrentLoggedInUser(request);
    	
      // check to see if the user logged on is a member
      if (!memberSecurityCheck(request, token)) {
        return mapping.findForward(BaseConstants.FWD_LOGIN);
      }
      
      PrivateMessageForm pmForm =  (PrivateMessageForm)form;     
      String folderName = pmForm.getType();

      /**
       * Checking for specific folders for now.
       */
      if (folderName.equalsIgnoreCase(BaseConstants.FOLDER_INBOX) ||
          folderName.equalsIgnoreCase(BaseConstants.FOLDER_SENT) ||
          folderName.equalsIgnoreCase(BaseConstants.FOLDER_TRASH) ||
          folderName.equalsIgnoreCase(BaseConstants.FOLDER_STORAGE))
      {
    	  MyAlumniUserContainer container = (MyAlumniUserContainer)request.getSession().getAttribute(BaseConstants.USER_CONTAINER);
          PrivateMessageHelper pmHelper = pmService.getMessageCenter(token.getMemberId(), folderName, container );
          setSessionObject(request, BaseConstants.MESSAGE_CENTER , pmHelper);
      }
      else{
        ActionMessages errors = new ActionMessages();
        errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.folderdoesnotexist"));
        saveMessages(request, errors);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
      }
      return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }    
    
    
    
    public ActionForward prepareContactMessage( ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response )
      throws Exception {

    MemberVO token = getCurrentLoggedInUser(request);

    // check to see if the user logged on is a member
    if (!memberSecurityCheck(request, token)){
      return mapping.findForward(BaseConstants.FWD_LOGIN);
    }

    PrivateMessageForm pmForm = (PrivateMessageForm) form;

    pmForm.setMessageToUserId(pmForm.getMessageToUserId());
    pmForm.setMessageFromUserId(token.getMemberId());
    MemberVO memberVO = memService.getMemberProfileByMemberId(pmForm.getMessageToUserId());
    pmForm.setToMemberFirstName(memberVO.getFirstName());
    pmForm.setToMemberLastName(memberVO.getLastName());
    
    pmForm.setSubject(token.getFirstName() + " " + token.getLastName() + " " + BaseConstants.CONTACT_SUBJECT);  

    return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }    
    
    
    public ActionForward prepareReplyMessage(ActionMapping mapping, ActionForm form, 
                                       HttpServletRequest request, 
                                       HttpServletResponse response) throws Exception {

        MemberVO token = getCurrentLoggedInUser(request);

        // check to see if the user logged on is a member
        if (!memberSecurityCheck(request, token)) {
            return mapping.findForward(BaseConstants.FWD_LOGIN);
        }

        PrivateMessageForm pmForm = (PrivateMessageForm)form;

        String memberId = token.getMemberId();
        String mailId = pmForm.getMessageId();
        PrivateMessageVO pmVO = pmService.prepareReply(memberId, mailId);

        pmVO.setMessageToUserId(pmVO.getMessageFromMember().getMemberId());        
        pmVO.setMessageFromUserId(token.getMemberId());
        
        BeanUtils.copyProperties(pmForm, pmVO);
        
        if (!pmForm.getSubject().startsWith("Re: ")) {
        	pmForm.setSubject("Re: " + pmForm.getSubject());
        }
        
        pmForm.setMessageText("\n\n\n------------------------------------------------\n" + 
                pmVO.getMessageFromMember().getFirstName() + " " + pmVO.getMessageFromMember().getLastName() + 
                " wrote on " + DateFormatUtil.getDateYYYYMMMDDHHMMA(pmVO.getMessageDate()) + ":\n\n" + pmVO.getMessageText());
        
        pmForm.setToMemberFirstName(pmVO.getMessageFromMember().getFirstName());
        pmForm.setToMemberLastName(pmVO.getMessageFromMember().getLastName());
        
        return mapping.findForward(BaseConstants.FWD_SUCCESS);

    }    
    
    
    public ActionForward prepareEmailWebmaster( ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response )
      throws Exception {

    boolean guest = false;
    String adminLastName = "Administrator";


    String messageFromUserId = "";
    String messageToUserId = "";
    
    String toMemberFirstName = "";
    String toMemberLastName = "";

    String fromFirstName = "";
    String fromLastName = "";

    MemberVO token = getCurrentLoggedInUser(request);


    // check to see if the user logged on is a member
    if (token == null){
      guest = true;
    }

    PrivateMessageForm pmForm = (PrivateMessageForm)form;

    messageToUserId = BaseConstants.ADMIN_USERNAME_ID;
    toMemberFirstName = "";
    toMemberLastName = adminLastName;

    if (!guest) {
    	messageFromUserId = token.getMemberId();
    	fromFirstName = token.getFirstName();
    	fromLastName = token.getLastName();
    }
    else {
    	messageFromUserId = BaseConstants.GUEST_USERNAME_ID;
    	fromFirstName = ""; // BaseConstants.GUEST_USERNAME;
    	fromLastName = "";
    }

    pmForm.setMessageFromUserId(messageFromUserId);
    pmForm.setFromMemberFirstName(fromFirstName);
    pmForm.setFromMemberLastName(fromLastName);

    pmForm.setMessageToUserId(messageToUserId);
    pmForm.setToMemberFirstName(toMemberFirstName);
    pmForm.setToMemberLastName(toMemberLastName);
    return mapping.findForward(BaseConstants.FWD_SUCCESS);

    }    
    
    
    public ActionForward readOneMail( ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response )
      throws Exception {

     MemberVO token = getCurrentLoggedInUser(request);
        
    // check to see if the user logged on is a member
    if (!memberSecurityCheck(request,token)){
      return mapping.findForward(BaseConstants.FWD_LOGIN);
    }    

    PrivateMessageForm pmForm = (PrivateMessageForm) form;
    String memberId = token.getMemberId();
    String mailId =  pmForm.getMessageId();
    PrivateMessageVO pmVO = pmService.readOneMailByMemberId(memberId, mailId, getLastModifiedBy(request));
   
    
    if (pmVO != null){
        BeanUtils.copyProperties(pmForm, pmVO);
        pmForm.setFromMemberLastName(pmVO.getMessageFromMember().getLastName());
        pmForm.setFromMemberFirstName(pmVO.getMessageFromMember().getFirstName());
        pmForm.setMessageDate(DateFormatUtil.getDateYYYYMMMDDHHMMA(pmVO.getMessageDate()));
    }

    return mapping.findForward(BaseConstants.FWD_SUCCESS);

    }    




    public ActionForward contactAndReplyMail( ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response )
      throws Exception {

    	MemberVO token = getCurrentLoggedInUser(request);

	    // check to see if the user logged on is a member
	    if (!memberSecurityCheck(request, token)){
	      return mapping.findForward(BaseConstants.FWD_LOGIN);
	    }
	
	
	    if (isCancelled(request)){
	       return mapping.findForward(BaseConstants.FWD_CANCEL);
	    }
	
	    PrivateMessageForm pmForm = (PrivateMessageForm) form;
	    PrivateMessageVO pmVO = new PrivateMessageVO();
	    PrivateMessageVO pmVOCopy = new PrivateMessageVO();
	    BeanUtils.copyProperties(pmVO, pmForm);
	    BeanUtils.copyProperties(pmVOCopy, pmForm);
	
	    String toMemberEmail = memService.getMemberEmailByMemberId(pmVO.getMessageToUserId());
	    SystemConfigVO sysConfigVO = sysConfigSerivce.getSystemConfig();
	        
	
	    if (pmVO.getType().equals("contact")) {
	      if (!memService.isAccountActivatedByMemberId(pmVO.getMessageToUserId())) {
	          ActionMessages errors = new ActionMessages();
	          errors.add(BaseConstants.FATAL_KEY, new ActionMessage("message.mail.unactivatedmember"));
	          saveMessages(request, errors);
	          return mapping.getInputForward();
	      }
	      else {
	        int quota = pmService.getQuotaRatioByMemberId(pmVO.getMessageToUserId());
	        if (quota < SystemConfigConstants.MAIL_QUOTA ){
	        	pmVO.setMessageId(null);
	        	pmVOCopy.setMessageId(null);
	        	pmService.contactMail(pmVO, getLastModifiedBy(request), getCurrentIPAddress(request));
	        	SendMailUtil.memberNewMessageNotification(pmVO, sysConfigVO, toMemberEmail);
	        	//copy to sent
		        if (pmForm.getCopyMe().equalsIgnoreCase(BaseConstants.BOOLEAN_YES)){
		            pmService.copyMeOnContactMail(pmVOCopy, getLastModifiedBy(request),getCurrentIPAddress(request));
		        }
	        }
	        else{
	          ActionMessages errors = new ActionMessages();
	          errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.mailboxfull"));
	          saveMessages(request, errors);
	        }
	      }
	    }
	    else if (pmVO.getType().equals("reply")) {
	    	pmVO.setMessageFromUserId(token.getMemberId());
	      int quota = pmService.getQuotaRatioByMemberId(pmVO.getMessageToUserId());
	      if (quota < SystemConfigConstants.MAIL_QUOTA ){
	    	pmVO.setMessageId(null); // setting to null becasue its a considered a new
	        pmService.replyMail(pmVO, getLastModifiedBy(request),getCurrentIPAddress(request));
	        SendMailUtil.memberNewMessageNotification(pmVO, sysConfigVO, toMemberEmail);
	        //copy to sent
	        if (pmForm.getCopyMe().equalsIgnoreCase(BaseConstants.BOOLEAN_YES)){
	          pmService.copyMeOnReplyMail(pmVOCopy, getLastModifiedBy(request),getCurrentIPAddress(request));
	        }
	      }
	      else{
	    	// TODO: Maybe save the message as a draft to be sent later
	        ActionMessages errors = new ActionMessages();
	        errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.mailboxfull"));
	        saveMessages(request, errors);
	      }
	    }
	    else {
	      ActionMessages errors = new ActionMessages();
	      errors.add(BaseConstants.FATAL_KEY, new ActionMessage("errors.technical.difficulty"));
	      saveMessages(request, errors);
	      return mapping.getInputForward();
	    }
      

	    MyAlumniUserContainer container = (MyAlumniUserContainer)request.getSession().getAttribute(BaseConstants.USER_CONTAINER);
	    PrivateMessageHelper pmHelper = pmService.getMessageCenter(token.getMemberId(), BaseConstants.FOLDER_INBOX, container );
	    setSessionObject(request, BaseConstants.MESSAGE_CENTER , pmHelper);
	
	    return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }    
    
    
    
    // done
    public ActionForward emailWebmaster( ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response )
      throws Exception {

    MemberVO token = new MemberVO();

    PrivateMessageForm pmForm = (PrivateMessageForm)form;
    PrivateMessageVO pmVO = new PrivateMessageVO();

    BeanUtils.copyProperties(pmVO, pmForm);
   
    
    if(pmVO.getType().equals("contact")){
    	if (pmVO.getMessageFromUserId().equalsIgnoreCase(BaseConstants.GUEST_USERNAME_ID)){
    		pmService.contactAdminMail(pmVO, BaseConstants.GUEST_USERNAME_ID, getCurrentIPAddress(request));
    	}
    	else{
    		pmService.contactAdminMail(pmVO, getLastModifiedBy(request), getCurrentIPAddress(request));
    	}
    }
    else if (pmVO.getType().equals("reply")){
    	if (pmVO.getMessageFromUserId().equalsIgnoreCase(BaseConstants.GUEST_USERNAME_ID)){
    	       pmVO.setMessageFromUserId(BaseConstants.GUEST_USERNAME_ID);
    	       pmService.replyMail(pmVO, BaseConstants.GUEST_USERNAME_ID, getCurrentIPAddress(request));
    	}
    	else{
    	       pmVO.setMessageFromUserId(token.getMemberId());
    	       pmService.replyMail(pmVO, getLastModifiedBy(request), getCurrentIPAddress(request));
    	}    	

    }
    else{
      ActionMessages errors = new ActionMessages();
      errors.add(BaseConstants.FATAL_KEY, new ActionMessage("errors.technical.difficulty"));
      saveMessages(request, errors);
      return mapping.getInputForward();
    }

    StringBuffer message = new StringBuffer();
    message.append("Thank you, Message has been sent to the Administrator, expect a response within the next 24hrs.");
    setRequestObject(request, BaseConstants.MESSAGE, message.toString());
    
    // send email to admin notifying of email
    SystemConfigVO sysConfigVO = sysConfigSerivce.getSystemConfig();
    SendMailUtil.adminNewMessageNotification(sysConfigVO, sysConfigVO.getOrgEmail());
    
    
    


    return mapping.findForward(BaseConstants.FWD_SUCCESS);

    }    
}

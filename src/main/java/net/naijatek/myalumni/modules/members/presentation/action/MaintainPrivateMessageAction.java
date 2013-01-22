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

import net.naijatek.myalumni.framework.extensions.MyAlumniBaseController;
import net.naijatek.myalumni.framework.extensions.MyAlumniUserContainer;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.domain.PrivateMessageVO;
import net.naijatek.myalumni.modules.common.domain.SystemConfigVO;
import net.naijatek.myalumni.modules.common.helper.MyAlumniMessage;
import net.naijatek.myalumni.modules.common.helper.MyAlumniMessages;
import net.naijatek.myalumni.modules.common.helper.PrivateMessageHelper;
import net.naijatek.myalumni.modules.common.service.IMemberService;
import net.naijatek.myalumni.modules.common.service.IPrivateMessageService;
import net.naijatek.myalumni.modules.common.service.ISystemConfigService;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.SystemConfigConstants;
import net.naijatek.myalumni.util.mail.SendMailUtil;
import net.naijatek.myalumni.util.utilities.DateFormatUtil;
import net.naijatek.myalumni.util.utilities.StringUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MaintainPrivateMessageAction  extends MyAlumniBaseController {

    @Autowired
    private IPrivateMessageService pmService;

    @Autowired
    private IMemberService memService;

    @Autowired
    private ISystemConfigService sysConfigSerivce;
    
    
    private static Log logger = LogFactory.getLog(MaintainPrivateMessageAction.class);
    
/*    public MaintainPrivateMessageAction(final IMemberService memService,
    		final IPrivateMessageService pmService, ISystemConfigService sysConfigSerivce) {
        this.pmService = pmService;
        this.memService = memService;
        this.sysConfigSerivce = sysConfigSerivce;
    }*/

    @RequestMapping(value="/deleteMail", method= RequestMethod.POST)
    public ModelAndView deleteMail(@ModelAttribute("privateMessage")PrivateMessageVO privateMessageVO, BindingResult result, SessionStatus status,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws
            Exception {

//      if (isCancelled(request)) {
//        return new ModelAndView(BaseConstants.FWD_CANCEL);
//      }
      
      MemberVO token = getCurrentLoggedInUser(request);  
      
      // check to see if the user logged on is a member
      if (!memberSecurityCheck(request, token)) {
        return new ModelAndView(BaseConstants.FWD_LOGIN);
      }
      
      logger.debug("Deleting mail...");
      
  
        String[] mailArray = new String[0];    

        String memberId = token.getMemberId();
        String privMsgsAction = privateMessageVO.getPrivMsgsAction();

        String privAdminDelete =  StringUtil.safeString(privateMessageVO.getPrivAdminDelete());
        String privAdminMove = StringUtil.safeString(privateMessageVO.getPrivAdminMove());


        if ( privAdminDelete.equalsIgnoreCase("yes") || privAdminMove.equalsIgnoreCase("yes")){
              // check to see if the user logged on is a member
              if (!adminSecurityCheck(request, token)) {
                  return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
              }       
              memberId = BaseConstants.ADMIN_USERNAME_ID;
        }

         mailArray = request.getParameterValues("messageId");

        if (mailArray == null) {
          MyAlumniMessages errors = new MyAlumniMessages();
          errors.add(BaseConstants.WARN_KEY, new MyAlumniMessage("error.selectOne"));
          saveMessages(request, errors);
          return new ModelAndView(BaseConstants.FWD_REDISPLAY);
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
        	String toFolder = privateMessageVO.getFolderName();
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
           MyAlumniMessages errors = new MyAlumniMessages();
           errors.add(BaseConstants.FATAL_KEY,  new MyAlumniMessage("errors.technical.difficulty"));
           saveMessages(request, errors);
           return new ModelAndView(BaseConstants.FWD_REDISPLAY);
         }

         return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    private void doMailDuties(final MemberVO token, final HttpServletRequest request){
    	MyAlumniUserContainer container = (MyAlumniUserContainer)request.getSession().getAttribute(BaseConstants.USER_CONTAINER);
        PrivateMessageHelper pmHelper = pmService.getMessageCenter(token.getMemberId(), BaseConstants.FOLDER_INBOX, container );
        setSessionObject(request, BaseConstants.MESSAGE_CENTER , pmHelper);
    }

    @RequestMapping(value="/listMailBox", method= RequestMethod.POST)
    public ModelAndView listMailBox(@ModelAttribute("privateMessage")PrivateMessageVO privateMessageVO, BindingResult result, SessionStatus status,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws
            Exception {

        
      MemberVO token = getCurrentLoggedInUser(request);
      
      // check to see if the user logged on is a member
      if (!memberSecurityCheck(request, token)) {
        return new ModelAndView(BaseConstants.FWD_LOGIN);
      }
  
      
      MyAlumniUserContainer container = (MyAlumniUserContainer)request.getSession().getAttribute(BaseConstants.USER_CONTAINER);
      PrivateMessageHelper pmHelper = pmService.getMessageCenter(token.getMemberId(), BaseConstants.FOLDER_INBOX, container );
      setSessionObject(request, BaseConstants.MESSAGE_CENTER , pmHelper);
      return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    @RequestMapping(value="/displayMailFolder", method= RequestMethod.POST)
    public ModelAndView displayMailFolder(@ModelAttribute("privateMessage")PrivateMessageVO privateMessageVO, BindingResult result, SessionStatus status,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws
            Exception {
  

       MemberVO token = getCurrentLoggedInUser(request);
    	
      // check to see if the user logged on is a member
      if (!memberSecurityCheck(request, token)) {
        return new ModelAndView(BaseConstants.FWD_LOGIN);
      }

      String folderName = privateMessageVO.getType();

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
        MyAlumniMessages errors = new MyAlumniMessages();
        errors.add(BaseConstants.WARN_KEY, new MyAlumniMessage("error.folderdoesnotexist"));
        saveMessages(request, errors);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
      }
      return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    @RequestMapping(value="/prepareContactMessage", method= RequestMethod.POST)
    public ModelAndView prepareContactMessage(@ModelAttribute("privateMessage")PrivateMessageVO privateMessageVO, BindingResult result, SessionStatus status,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws
            Exception {



    MemberVO token = getCurrentLoggedInUser(request);

    // check to see if the user logged on is a member
    if (!memberSecurityCheck(request, token)){
      return new ModelAndView(BaseConstants.FWD_LOGIN);
    }

    privateMessageVO.setMessageToUserId(privateMessageVO.getMessageToUserId());
    privateMessageVO.setMessageFromUserId(token.getMemberId());
    MemberVO memberVO = memService.getMemberProfileByMemberId(privateMessageVO.getMessageToUserId());
    privateMessageVO.setToMemberFirstName(memberVO.getFirstName());
    privateMessageVO.setToMemberLastName(memberVO.getLastName());

    privateMessageVO.setSubject(token.getFirstName() + " " + token.getLastName() + " " + BaseConstants.CONTACT_SUBJECT);

    return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    @RequestMapping(value="/prepareComposePrivateMessage", method= RequestMethod.POST)
    public ModelAndView prepareComposePrivateMessage(@ModelAttribute("privateMessage")PrivateMessageVO privateMessageVO, BindingResult result, SessionStatus status,
                                              HttpServletRequest request,
                                              HttpServletResponse response) throws
            Exception {


		MemberVO token = getCurrentLoggedInUser(request);
        MyAlumniMessages errors = new MyAlumniMessages();
		
		
		// check to see if the user logged on is a member
		if (!memberSecurityCheck(request, token)) {
			return new ModelAndView(BaseConstants.FWD_LOGIN);
		}

        privateMessageVO.setMessageToUserId(privateMessageVO.getMessageToUserName());
        privateMessageVO.setMessageFromUserId(token.getMemberId());
	    MemberVO memberVO = memService.getMemberProfileByMemberId(privateMessageVO.getMessageToUserId());
	    
	    if (memberVO != null){
            privateMessageVO.setToMemberFirstName(memberVO.getFirstName());
            privateMessageVO.setToMemberLastName(memberVO.getLastName());
	    }
	    else{
	        errors.add(BaseConstants.WARN_KEY, new MyAlumniMessage("message.membernotfound"));
	        saveMessages(request, errors);	   
	        return new ModelAndView(BaseConstants.FWD_REDISPLAY);	        
	    }

        privateMessageVO.setSubject(token.getFirstName() + " " + token.getLastName() + " " + BaseConstants.CONTACT_SUBJECT);
		
		return new ModelAndView(BaseConstants.FWD_SUCCESS);

}

    @RequestMapping(value="/prepareReplyMessage", method= RequestMethod.POST)
    public ModelAndView prepareReplyMessage(@ModelAttribute("privateMessage")PrivateMessageVO privateMessageVO, BindingResult result, SessionStatus status,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response) throws
            Exception {


        MemberVO token = getCurrentLoggedInUser(request);

        // check to see if the user logged on is a member
        if (!memberSecurityCheck(request, token)) {
            return new ModelAndView(BaseConstants.FWD_LOGIN);
        }

        String memberId = token.getMemberId();
        String mailId = privateMessageVO.getMessageId();
        PrivateMessageVO pmVO = pmService.prepareReply(memberId, mailId);

        pmVO.setMessageToUserId(pmVO.getMessageFromMember().getMemberId());        
        pmVO.setMessageFromUserId(token.getMemberId());

        
        if (!privateMessageVO.getSubject().startsWith("Re: ")) {
            privateMessageVO.setSubject("Re: " + privateMessageVO.getSubject());
        }

        privateMessageVO.setMessageText("\n\n\n------------------------------------------------\n" +
                pmVO.getMessageFromMember().getFirstName() + " " + pmVO.getMessageFromMember().getLastName() + 
                " wrote on " + DateFormatUtil.getDateYYYYMMMDDHHMMA(pmVO.getMessageDate()) + ":\n\n" + pmVO.getMessageText());

        privateMessageVO.setToMemberFirstName(pmVO.getMessageFromMember().getFirstName());
        privateMessageVO.setToMemberLastName(pmVO.getMessageFromMember().getLastName());
        
        return new ModelAndView(BaseConstants.FWD_SUCCESS);

    }

    @RequestMapping(value="/prepareEmailWebmaster", method= RequestMethod.POST)
    public ModelAndView prepareEmailWebmaster(@ModelAttribute("privateMessage")PrivateMessageVO privateMessageVO, BindingResult result, SessionStatus status,
                                            HttpServletRequest request,
                                            HttpServletResponse response) throws
            Exception {


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

    privateMessageVO.setMessageFromUserId(messageFromUserId);
    privateMessageVO.setFromMemberFirstName(fromFirstName);
    privateMessageVO.setFromMemberLastName(fromLastName);

    privateMessageVO.setMessageToUserId(messageToUserId);
    privateMessageVO.setToMemberFirstName(toMemberFirstName);
    privateMessageVO.setToMemberLastName(toMemberLastName);
    return new ModelAndView(BaseConstants.FWD_SUCCESS);

    }

    @RequestMapping(value="/readOneMail", method= RequestMethod.POST)
    public ModelAndView readOneMail(@ModelAttribute("privateMessage")PrivateMessageVO privateMessageVO, BindingResult result, SessionStatus status,
                                              HttpServletRequest request,
                                              HttpServletResponse response) throws
            Exception {

     MemberVO token = getCurrentLoggedInUser(request);
        
    // check to see if the user logged on is a member
    if (!memberSecurityCheck(request,token)){
      return new ModelAndView(BaseConstants.FWD_LOGIN);
    }    

    String memberId = token.getMemberId();
    String mailId =  privateMessageVO.getMessageId();
    PrivateMessageVO pmVO = pmService.readOneMailByMemberId(memberId, mailId, getLastModifiedBy(request));
   
    
    if (pmVO != null){
        privateMessageVO.setFromMemberLastName(pmVO.getMessageFromMember().getLastName());
        privateMessageVO.setFromMemberFirstName(pmVO.getMessageFromMember().getFirstName());
        //privateMessageVO.setMessageDate(DateFormatUtil.getDateYYYYMMMDDHHMMA(pmVO.getMessageDate()));
        privateMessageVO.setMessageDate(pmVO.getMessageDate());
    }

    return new ModelAndView(BaseConstants.FWD_SUCCESS);

    }



    @RequestMapping(value="/contactAndReplyMail", method= RequestMethod.POST)
    public ModelAndView contactAndReplyMail(@ModelAttribute("privateMessage")PrivateMessageVO privateMessageVO, BindingResult result, SessionStatus status,
                                              HttpServletRequest request,
                                              HttpServletResponse response) throws
            Exception {


    	MemberVO token = getCurrentLoggedInUser(request);

	    // check to see if the user logged on is a member
	    if (!memberSecurityCheck(request, token)){
	      return new ModelAndView(BaseConstants.FWD_LOGIN);
	    }
	
	
//	    if (isCancelled(request)){
//	       return new ModelAndView(BaseConstants.FWD_CANCEL);
//	    }

	    //PrivateMessageVO pmVO = new PrivateMessageVO();
	    //PrivateMessageVO pmVOCopy = new PrivateMessageVO();
        PrivateMessageVO pmVO = privateMessageVO;
        PrivateMessageVO pmVOCopy = privateMessageVO;
//	    BeanUtils.copyProperties(pmVO, pmForm);
//	    BeanUtils.copyProperties(pmVOCopy, pmForm);
	
	    String toMemberEmail = memService.getMemberEmailByMemberId(pmVO.getMessageToUserId());
	    SystemConfigVO sysConfigVO = sysConfigSerivce.getSystemConfig();
	        
	
	    if (pmVO.getType().equals("contact")) {
	      if (!memService.isAccountActivatedByMemberId(pmVO.getMessageToUserId())) {
	          MyAlumniMessages errors = new MyAlumniMessages();
	          errors.add(BaseConstants.FATAL_KEY, new MyAlumniMessage("message.mail.unactivatedmember"));
	          saveMessages(request, errors);
	          return new ModelAndView(BaseConstants.FWD_REDISPLAY);
	      }
	      else {
	        int quota = pmService.getQuotaRatioByMemberId(pmVO.getMessageToUserId());
	        if (quota < SystemConfigConstants.MAIL_QUOTA ){
	        	pmVO.setMessageId(null);
	        	pmVOCopy.setMessageId(null);
	        	pmService.contactMail(pmVO, getLastModifiedBy(request), getCurrentIPAddress(request));
	        	SendMailUtil.memberNewMessageNotification(pmVO, sysConfigVO, toMemberEmail);
	        	//copy to sent
		        if (privateMessageVO.getCopyMe().equalsIgnoreCase(BaseConstants.BOOLEAN_YES)){
		            pmService.copyMeOnContactMail(pmVOCopy, getLastModifiedBy(request),getCurrentIPAddress(request));
		        }
	        }
	        else{
	          MyAlumniMessages errors = new MyAlumniMessages();
	          errors.add(BaseConstants.WARN_KEY, new MyAlumniMessage("error.mailboxfull"));
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
	        if (pmVO.getCopyMe().equalsIgnoreCase(BaseConstants.BOOLEAN_YES)){
	          pmService.copyMeOnReplyMail(pmVOCopy, getLastModifiedBy(request),getCurrentIPAddress(request));
	        }
	      }
	      else{
	    	// TODO: Maybe save the message as a draft to be sent later
	        MyAlumniMessages errors = new MyAlumniMessages();
	        errors.add(BaseConstants.WARN_KEY, new MyAlumniMessage("error.mailboxfull"));
	        saveMessages(request, errors);
	      }
	    }
	    else {
	      MyAlumniMessages errors = new MyAlumniMessages();
	      errors.add(BaseConstants.FATAL_KEY, new MyAlumniMessage("errors.technical.difficulty"));
	      saveMessages(request, errors);
	      return new ModelAndView(BaseConstants.FWD_REDISPLAY);
	    }
      

	    MyAlumniUserContainer container = (MyAlumniUserContainer)request.getSession().getAttribute(BaseConstants.USER_CONTAINER);
	    PrivateMessageHelper pmHelper = pmService.getMessageCenter(token.getMemberId(), BaseConstants.FOLDER_INBOX, container );
	    setSessionObject(request, BaseConstants.MESSAGE_CENTER , pmHelper);
	
	    return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }



    @RequestMapping(value="/emailWebmaster", method= RequestMethod.POST)
    public ModelAndView emailWebmaster(@ModelAttribute("privateMessage")PrivateMessageVO privateMessageVO, BindingResult result, SessionStatus status,
                                            HttpServletRequest request,
                                            HttpServletResponse response) throws
            Exception {


    MemberVO token = new MemberVO();
    PrivateMessageVO pmVO = new PrivateMessageVO();
    
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
      MyAlumniMessages errors = new MyAlumniMessages();
      errors.add(BaseConstants.FATAL_KEY, new MyAlumniMessage("errors.technical.difficulty"));
      saveMessages(request, errors);
      return new ModelAndView(BaseConstants.FWD_REDISPLAY);
    }

    StringBuffer message = new StringBuffer();
    message.append("Thank you, Message has been sent to the Administrator, expect a response within the next 24hrs.");
    setRequestObject(request, BaseConstants.MESSAGE, message.toString());
    
    // send email to admin notifying of email
    SystemConfigVO sysConfigVO = sysConfigSerivce.getSystemConfig();
    SendMailUtil.adminNewMessageNotification(sysConfigVO, sysConfigVO.getOrgEmail());
    
    
    


    return new ModelAndView(BaseConstants.FWD_SUCCESS);

    }    
}

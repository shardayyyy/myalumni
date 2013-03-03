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


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.naijatek.myalumni.framework.MyAlumniUserContainer;
import net.naijatek.myalumni.entity.MemberVO;
import net.naijatek.myalumni.entity.PrivateMessageVO;
import net.naijatek.myalumni.entity.SystemConfigVO;
import net.naijatek.myalumni.controller.helper.PrivateMessageHelper;
import net.naijatek.myalumni.service.IMemberService;
import net.naijatek.myalumni.service.IPrivateMessageService;
import net.naijatek.myalumni.service.ISystemConfigService;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.mail.SendMailUtil;
import net.naijatek.myalumni.util.utilities.DateFormatUtil;
import net.naijatek.myalumni.util.utilities.StringUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/message")
public class AdminPrivateMessageController extends MyAlumniBaseController {

    @Autowired
    private IPrivateMessageService pmService;

    @Autowired
    private IMemberService memService;

    @Autowired
    private ISystemConfigService sysConfigSerivce;
    
    //private static Log logger = LogFactory.getLog(MaintainPrivateMessageAction.class);

/*    public MaintainPrivateMessageAction(final IMemberService memService, final IPrivateMessageService pmService, ISystemConfigService sysConfigSerivce) {
        this.pmService = pmService;
        this.memService = memService;
        this.sysConfigSerivce = sysConfigSerivce;
    }*/
    
	public ModelAndView emailMember(HttpServletRequest request,
		            HttpServletResponse response )
		throws Exception {
		
		// check to see if the user logged on is a member
		if (!adminSecurityCheck(request)){
		return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
		}

//        if (isCancelled(request)) {
//            return new ModelAndView(BaseConstants.FWD_CANCEL);
//        }

        //PrivateMessageVO pmForm = (PrivateMessageForm)form;
		PrivateMessageVO pmVO = new PrivateMessageVO();
		//BeanUtils.copyProperties(pmVO, pmForm);
		SystemConfigVO sysConfigVO = sysConfigSerivce.getSystemConfig();
		
		
		SendMailUtil.sendEmail(pmVO, sysConfigVO);
        //ActionMessages errors = new ActionMessages();
        //errors.add(BaseConstants.INFO_KEY, new ActionMessage("message.emailsent"));
        //saveMessages(request, errors);
		return new ModelAndView(BaseConstants.FWD_SUCCESS);
	}    

    
    
    
    public ModelAndView displayMailFolder(HttpServletRequest request,
                                       HttpServletResponse response, @ModelAttribute("pmVO")PrivateMessageVO privateMessageVO) throws
        Exception {


      // check to see if the user logged on is a member
      if (!adminSecurityCheck(request)) {
        return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
      }

      String adminUserId = BaseConstants.ADMIN_USERNAME_ID;
      String folderType = privateMessageVO.getType();

        /**
         * Checking for specific folders for now.
         */
        if (folderType.equalsIgnoreCase(BaseConstants.FOLDER_INBOX) ||
            folderType.equalsIgnoreCase(BaseConstants.FOLDER_SENT) ||
            folderType.equalsIgnoreCase(BaseConstants.FOLDER_TRASH) ||
            folderType.equalsIgnoreCase(BaseConstants.FOLDER_STORAGE))
        {
        	MyAlumniUserContainer container = (MyAlumniUserContainer)request.getSession().getAttribute(BaseConstants.USER_CONTAINER);
            PrivateMessageHelper pmHelper = pmService.getAdminMessageCenter(adminUserId, folderType, container );
            setSessionObject(request, BaseConstants.MESSAGE_CENTER , pmHelper);
        }
        else{
          //ActionMessages errors = new ActionMessages();
          //errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.folderdoesnotexist"));
          //saveMessages(request, errors);
          return new ModelAndView(BaseConstants.FWD_SUCCESS);
        }
      return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }    
    
    
    public ModelAndView prepareReplyMail( PrivateMessageVO pmForm, HttpServletRequest request,
                                  HttpServletResponse response )
      throws Exception {

	    // check to see if the user logged on is a member
	    if (!adminSecurityCheck(request)){
	      return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
	    }

	    String mailId = pmForm.getMessageId();
	    PrivateMessageVO pmVO = pmService.prepareAdminReply(BaseConstants.ADMIN_USERNAME_ID, mailId);
	
	
	    BeanUtils.copyProperties(pmForm, pmVO);
	    
	    if( !pmForm.getSubject().startsWith("Re: ")){
	    	pmForm.setSubject("Re: " + pmForm.getSubject());
	      }
	    
	    pmForm.setMessageText("\n\n\n------------------------------------------------\n" + pmVO.getMessageToMember().getFirstName() + " " + pmVO.getMessageToMember().getLastName() + " wrote on " + DateFormatUtil.getDateYYYYMMMDDHHMMA(pmVO.getMessageDate()) + ":\n\n" +  pmVO.getMessageText());
		
	    pmForm.setMessageToUserId(pmVO.getMessageToMember().getMemberId());        
	    pmForm.setMessageFromUserId(BaseConstants.ADMIN_USERNAME_ID);
	    
	    // set message to name
	    if (pmVO.getMessageFromUserId().equals(BaseConstants.GUEST_USERNAME_ID)){
		    pmForm.setToMemberLastName(pmVO.getGuestName());
		    pmForm.setToMemberFirstName("");
	    }
	    else{
		    pmForm.setToMemberLastName(pmVO.getMessageToMember().getLastName());
		    pmForm.setToMemberFirstName(pmVO.getMessageToMember().getFirstName());	    	
	    }

	      
	    return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }    
    
    
    public ModelAndView prepareContactMessage( HttpServletRequest request,
                                  HttpServletResponse response, @ModelAttribute("pmVO")PrivateMessageVO privateMessageVO )
      throws Exception {

	    MemberVO userVO = getCurrentLoggedInUser(request);
    	//ActionMessages errors = new ActionMessages();
	
	    // check to see if the user logged on is a member
	    if (!adminSecurityCheck(request)){
	      return new ModelAndView(BaseConstants.FWD_LOGIN);
	    }
	
	    //PrivateMessageForm pmForm = (PrivateMessageForm) form;
	    MemberVO messageToMemberVO = memService.findById(privateMessageVO.getMessageToUserId());
	    
	    if (messageToMemberVO == null){
			//errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.selectOne"));
			//saveMessages(request, errors);
            return new ModelAndView(BaseConstants.FWD_REDISPLAY); //return mapping.getInputForward();
	    }


        privateMessageVO.setMessageToUserId(privateMessageVO.getMessageToUserId());
        privateMessageVO.setToMemberFirstName(StringUtil.capitalize(messageToMemberVO.getFirstName()));
        privateMessageVO.setToMemberLastName(StringUtil.capitalize(messageToMemberVO.getLastName()));

        privateMessageVO.setFromMemberFirstName(StringUtil.capitalize(userVO.getFirstName()));
        privateMessageVO.setFromMemberLastName(StringUtil.capitalize(userVO.getLastName()));
        privateMessageVO.setMessageFromUserId(userVO.getMemberId());
	    
	    //dynaForm.setFromEmail(StringUtil.safeString(dynaForm.getGuestEmail()));   //set already
        privateMessageVO.setSubject(userVO.getFirstName() + " " + userVO.getLastName() + " [WebAdmin] " + BaseConstants.CONTACT_SUBJECT);
	
	    return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }    
    
    
    public ModelAndView readOneMail(HttpServletRequest request,
                                       HttpServletResponse response, @ModelAttribute("pmVO")PrivateMessageVO privateMessageVO) throws Exception {

        //PrivateMessageForm pmForm = (PrivateMessageForm)form;

        // check to see if the user logged on is a member
        if (!adminSecurityCheck(request)) {
            return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
        }
        
        String mailId = privateMessageVO.getMessageId();
        PrivateMessageVO pmVO = pmService.readOneAdminMailByMemberId(BaseConstants.ADMIN_USERNAME_ID, mailId, getLastModifiedBy(request));
        //BeanUtils.copyProperties(pmForm, pmVO);
        //privateMessageVO.setMessageDate(DateFormatUtil.getDateYYYYMMMDDHHMMA(pmVO.getMessageDate()));
        privateMessageVO.setMessageDate(pmVO.getMessageDate());
        privateMessageVO.setFromMemberFirstName(pmVO.getMessageFromMember().getFirstName());
        privateMessageVO.setFromMemberLastName(pmVO.getMessageFromMember().getLastName());
        
        MyAlumniUserContainer container = (MyAlumniUserContainer)request.getSession().getAttribute(BaseConstants.USER_CONTAINER);
        PrivateMessageHelper pmHelper = pmService.getAdminMessageCenter(BaseConstants.ADMIN_USERNAME_ID, BaseConstants.FOLDER_INBOX, container );
        setSessionObject(request, BaseConstants.MESSAGE_CENTER , pmHelper);

        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    } 




    public ModelAndView contactAndReplyMail(HttpServletRequest request,
                                  HttpServletResponse response, @ModelAttribute("pmVO")PrivateMessageVO privateMessageVO )
      throws Exception {


	    // check to see if the user logged on is a member
	    if (!adminSecurityCheck(request)){
	      return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
	    }

      //PrivateMessageForm dynaForm = (PrivateMessageForm) form;
      //PrivateMessageVO pmVO = new PrivateMessageVO();
      
      //BeanUtils.copyProperties(pmVO, dynaForm);
      
      //xxx   String toMemberEmail = memService.getMemberEmailByMemberId(pm.getGuestEmail());
      SystemConfigVO sysConfigVO = sysConfigSerivce.getSystemConfig();
      
    String toMemberEmail = new String();
    
    if(privateMessageVO.getType().equals("contact")){
    	if (privateMessageVO.getMessageToUserId() != null && privateMessageVO.getMessageToUserId() != ""){
    		toMemberEmail = memService.getMemberEmailByMemberId(privateMessageVO.getMessageToUserId());
            privateMessageVO.setToWebmaster(BaseConstants.BOOLEAN_NO);
    		pmService.contactMail(privateMessageVO, getLastModifiedBy(request),getCurrentIPAddress(request));
    		SendMailUtil.memberNewMessageNotification(privateMessageVO, sysConfigVO, toMemberEmail);
    	}
    	else{
    		toMemberEmail = privateMessageVO.getGuestEmail();
    		SendMailUtil.sendEmail(privateMessageVO, sysConfigVO);
    	}  
    }
    else if (privateMessageVO.getType().equals("reply")){
      String guestEmail = StringUtil.safeString(privateMessageVO.getGuestEmail());
      if ( guestEmail.length() > 0 ){
        SendMailUtil.sendEmail(privateMessageVO, sysConfigVO);
      }else{
    	  toMemberEmail = memService.getMemberEmailByMemberId(privateMessageVO.getMessageToUserId());
          privateMessageVO.setToWebmaster(BaseConstants.BOOLEAN_NO);
    	  pmService.replyMail(privateMessageVO, getLastModifiedBy(request),getCurrentIPAddress(request));
    	  SendMailUtil.memberNewMessageNotification(privateMessageVO, sysConfigVO, toMemberEmail);
      }
    }
    else{
      //ActionMessages errors = new ActionMessages();
      //errors.add(BaseConstants.ERROR_KEY,
      //           new ActionMessage("errors.technical.difficulty"));
      //saveMessages(request, errors);
        return new ModelAndView(BaseConstants.FWD_REDISPLAY); //return mapping.getInputForward();
    }

    MyAlumniUserContainer container = (MyAlumniUserContainer)request.getSession().getAttribute(BaseConstants.USER_CONTAINER);
    PrivateMessageHelper pmHelper = pmService.getAdminMessageCenter(BaseConstants.ADMIN_USERNAME_ID, BaseConstants.FOLDER_INBOX, container );
    setSessionObject(request, BaseConstants.MESSAGE_CENTER , pmHelper);

    return new ModelAndView(BaseConstants.FWD_SUCCESS);

    }  
    
    public ModelAndView deleteMail(HttpServletRequest request,
            HttpServletResponse response, @ModelAttribute("pmVO")PrivateMessageVO privateMessageVO) throws Exception {

    	//ActionMessages errors = new ActionMessages();
    	
		//if (isCancelled(request)) {
		//	return new ModelAndView(BaseConstants.FWD_CANCEL);
		//}
		
		MemberVO token = getCurrentLoggedInUser(request);  
		
		// check to see if the user logged on is a member
		if (!memberSecurityCheck(request, token)) {
			return new ModelAndView(BaseConstants.FWD_LOGIN);
		}

		
		
		String[] mailArray = new String[0];    
		
		//PrivateMessageForm pmForm = (PrivateMessageForm)form;
		String memberId = token.getMemberId();
		String privMsgsAction = privateMessageVO.getPrivMsgsAction();
		
		//String privAdminDelete =  StringUtil.safeString(pmForm.getPrivAdminDelete());
		//String privAdminMove = StringUtil.safeString(pmForm.getPrivAdminMove());
		
		
		//if ( privAdminDelete.equalsIgnoreCase("yes") || privAdminMove.equalsIgnoreCase("yes")){
			// check to see if the user logged on is a member
			if (!adminSecurityCheck(request, token)) {
				return new ModelAndView(BaseConstants.FWD_ADMIN_LOGIN);
			}       
				
			memberId = BaseConstants.ADMIN_USERNAME_ID;
		//}
		
			mailArray = request.getParameterValues("messageId");
			
			if (mailArray == null) {
				//errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.selectOne"));
				//saveMessages(request, errors);
                return new ModelAndView(BaseConstants.FWD_REDISPLAY); //return mapping.getInputForward();
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
				//errors.add(BaseConstants.FATAL_KEY,  new ActionMessage("errors.technical.difficulty"));
				//saveMessages(request, errors);
                return new ModelAndView(BaseConstants.FWD_REDISPLAY); //return mapping.getInputForward();
			}
		
			return new ModelAndView(BaseConstants.FWD_SUCCESS);
		}
		
		
		private void doMailDuties(final MemberVO token, final HttpServletRequest request){
			MyAlumniUserContainer container = (MyAlumniUserContainer)request.getSession().getAttribute(BaseConstants.USER_CONTAINER);
			PrivateMessageHelper pmHelper = pmService.getAdminMessageCenter(BaseConstants.ADMIN_USERNAME_ID, BaseConstants.FOLDER_INBOX, container );
			setSessionObject(request, BaseConstants.MESSAGE_CENTER , pmHelper);
		}
}

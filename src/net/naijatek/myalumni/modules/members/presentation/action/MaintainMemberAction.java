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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.naijatek.myalumni.framework.exceptions.BadInputException;
import net.naijatek.myalumni.framework.exceptions.CreateException;
import net.naijatek.myalumni.framework.exceptions.DuplicateEmailException;
import net.naijatek.myalumni.framework.exceptions.DuplicateMemberException;
import net.naijatek.myalumni.framework.struts.MyAlumniDispatchAction;
import net.naijatek.myalumni.framework.struts.MyAlumniUserContainer;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.domain.MessengerVO;
import net.naijatek.myalumni.modules.common.domain.SystemConfigVO;
import net.naijatek.myalumni.modules.common.domain.XlatDetailVO;
import net.naijatek.myalumni.modules.common.presentation.form.MemberForm;
import net.naijatek.myalumni.modules.common.service.IMemberService;
import net.naijatek.myalumni.modules.common.service.IMessageFolderService;
import net.naijatek.myalumni.modules.common.service.IMessengerService;
import net.naijatek.myalumni.modules.common.service.IPrivateMessageService;
import net.naijatek.myalumni.modules.common.service.ISystemConfigService;
import net.naijatek.myalumni.modules.common.service.IXlatService;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.SystemConfigConstants;
import net.naijatek.myalumni.util.encryption.Encoder;
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
import org.apache.struts.upload.FormFile;



public class MaintainMemberAction extends MyAlumniDispatchAction{

    private IMemberService memService;
    private IPrivateMessageService pmService; 
    private IMessageFolderService mfService ; 
    private IXlatService xlatService;
    private ISystemConfigService sysConfigSerivce;
    private IMessengerService messengerService;
    
    private static Log logger = LogFactory.getLog(MaintainMemberAction.class);

    
    public MaintainMemberAction(final IMemberService memService,
    		final IPrivateMessageService pmService, final IMessageFolderService mfService, IXlatService xlatService,
    		final ISystemConfigService sysConfigSerivce, IMessengerService messengerService) {
        this.memService = memService;      
        this.pmService = pmService;
        this.mfService = mfService;
        this.xlatService = xlatService;
        this.sysConfigSerivce = sysConfigSerivce;
        this.messengerService = messengerService;
    }
    


    
    /**
     * Searchs for member
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward genericAjaxSearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("in genericAjaxSearch...");
        MemberForm memForm = (MemberForm)form;
        String searchCriteria = memForm.getSearchCriteria();
        String searchWord = ""; 
        String ajaxFormat = memForm.getAjaxFormat();

        
    	if(memForm.getApproach() != null){
    		
    		if (searchCriteria.equals(BaseConstants.FIRST_NAME)){
    			searchWord = memForm.getFirstName();
    		}
    		else if (searchCriteria.equals(BaseConstants.LAST_NAME)){
    			searchWord = memForm.getLastName();
    		} 
    		else if (searchCriteria.equals(BaseConstants.MAIDEN_NAME)){
    			searchWord = memForm.getMaidenName();
    		} 
    		else if (searchCriteria.equals(BaseConstants.NICK_NAME)){
    			searchWord = memForm.getNickName();
    		}   
    		else if (searchCriteria.equals(BaseConstants.FULL_NAME)){
    			searchWord = memForm.getMessageToUserName();
    		}      		
    		
    		if (ajaxFormat.equals(BaseConstants.AJAX_FORMAT_STRING)){
    			List<String> result = memService.genericAjaxSearch(searchWord, searchCriteria);
    			request.setAttribute("result", result);
    			request.getRequestDispatcher(BaseConstants.FWD_AJAX_JSP).forward(request, response);
    		}
    		else if (ajaxFormat.equals(BaseConstants.AJAX_FORMAT_OBJECT)){
    			List<MemberVO> result = memService.genericAjaxSearchObjects(searchWord, searchCriteria);
    			request.setAttribute("result", result);
    			request.getRequestDispatcher(BaseConstants.FWD_AJAX_JSP_OBJECT).forward(request, response);
    		}
    		
    		
    		return null;
       	}

        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }

    
    
    
    public ActionForward displayMiniProfile(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws
        Exception {
    
      MemberForm memberForm = (MemberForm)form;
      String memberUserName = memberForm.getMemberUserName();
      MemberVO memberVO = memService.getMemberProfileByUserName(memberUserName);
      setRequestObject(request, "profile", memberVO);
      return mapping.findForward(BaseConstants.FWD_SUCCESS);

    }
    
    
    
    
    public ActionForward prepareUpdateMemberProfile(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws
        Exception {

        MemberVO token =   getCurrentLoggedInUser(request);
    	
        // check to see if the user logged on is a member
        if (!memberSecurityCheck(request, token)){
        	return mapping.findForward(BaseConstants.FWD_LOGIN);
        }


      MemberForm memberForm = (MemberForm) form;
      MemberVO memberVO = memService.getMemberProfileByUserName(token.getMemberUserName());
      BeanUtils.copyProperties(memberForm, memberVO);
      
      try{
      // IM
    	  List<XlatDetailVO> availableMessengers = xlatService.getActiveGroupDetails(BaseConstants.GROUP_INSTANT_MESSENGERS);
    	  List<XlatDetailVO> selectedMessengers = messengerService.getActiveMemberMessengers(token.getMemberId());
    	  List<XlatDetailVO> filteredAvailableIMs = filterMessengers(availableMessengers, selectedMessengers) ;
    	  
          setSessionObject(request, BaseConstants.LU_AVAILABLE_IMS, filteredAvailableIMs);
          setSessionObject(request, BaseConstants.LU_SELECTED_IMS, selectedMessengers);              	  
      }
      catch(Exception e){
    	  logger.debug(e.getMessage());
          ActionMessages errors = new ActionMessages();
          errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00709"));
          saveMessages(request, errors);
    	  return mapping.getInputForward();
      }
      

      return mapping.findForward(BaseConstants.FWD_SUCCESS);

    }
    

    
    public ActionForward addMember( ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response )
      throws Exception {
        
      ActionMessages errors = new ActionMessages();
      SystemConfigVO sysConfigVO = sysConfigSerivce.getSystemConfig();
      
      try{    	     	  
        MemberForm memberForm = (MemberForm) form;
        MemberVO memberVO = new MemberVO();
        BeanUtils.copyProperties(memberVO, memberForm);
        
        memberVO.setLastModifiedBy(memberVO.getMemberUserName());
        
        // Member
        memService.createMember(memberVO, request);
        ////////
       final String memberId = memberVO.getMemberId();
        // Messengers
        List<MessengerVO> messengers = new ArrayList<MessengerVO>();
        MessengerVO mesgerVO = null;
        for(String str : memberVO.getLstSelectedIMs()){
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
        message.append("Thank you " + StringUtil.capitalize(memberVO.getFirstName()) + " " + StringUtil.capitalize(memberVO.getLastName())  + " for registering and Welcome to " + sysConfigVO.getOrganizationName()  + "'s owns space in cyberspace.");
        message.append("Your account should be active within the next 24 hours. So please try logging into the system as soon as you get your activation confirmation email.");
        setSessionObject(request, BaseConstants.MESSAGE,  message.toString());

        // send email to registrant
        try {
        	SendMailUtil.sendWelcomeNotice(memberVO.getEmail(), memberVO.getMemberUserName(),sysConfigVO);
        }
        catch (Exception ex) {
          logger.error(ex.getMessage());
          errors.add(BaseConstants.FATAL_KEY, new ActionMessage("error.mailserver"));
          saveMessages(request, errors);
          return mapping.findForward(BaseConstants.FWD_SUCCESS);
        }
        
        // send email to administrator about new registrant
        try {
        	SendMailUtil.notifyAdminAboutNewMember(memberVO, sysConfigVO);
        }
        catch (Exception ex) {
          logger.error(ex.getMessage());
          errors.add(BaseConstants.FATAL_KEY, new ActionMessage("error.mailserver"));
          saveMessages(request, errors);
          return mapping.findForward(BaseConstants.FWD_SUCCESS);
        }
        
      }
      catch (DuplicateMemberException e) {
        errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.duplicate.member"));
        saveMessages(request, errors);
        logger.info("DUPLICATE USER NAME - " + e.getMessage());
        return mapping.getInputForward();
      }
      catch (DuplicateEmailException e) {
        errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.duplicate.email"));
        saveMessages(request, errors);
        logger.info("DUPLICATE EMAIL - " + e.getMessage());
        return mapping.getInputForward();
      }
      catch (CreateException e) {
        errors.add(BaseConstants.WARN_KEY, new ActionMessage("errors.technical.difficulty"));
        saveMessages(request, errors);
        logger.fatal("SYSTEM ERROR - " + e.getMessage());
        return mapping.getInputForward();
      }
      catch(Exception ex){
           errors.add(BaseConstants.FATAL_KEY, new ActionMessage("errors.technical.difficulty"));
           saveMessages(request, errors);
           logger.fatal("SYSTEM ERROR - " + ex.getStackTrace());
           return mapping.getInputForward();
      }
      return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }    
    
    
    

    public ActionForward prepareDeleteMyMemberProfile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	setSessionObject(request, "ipaddress", getCurrentIPAddress(request));
    	return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}    
    

    public ActionForward deleteMyMemberProfile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
           
        memService.softDelete(getCurrentUserId(request), getLastModifiedBy(request));
        ActionMessages errors = new ActionMessages();
        errors.add(BaseConstants.INFO_KEY, new ActionMessage("core.errorcode.00713"));
        saveMessages(request, errors);
    	return mapping.findForward(BaseConstants.FWD_SUCCESS);
	}        
    
    
    
    
    public ActionForward validateMemberUserName(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		MemberForm memberForm = (MemberForm) form;

		if (memberForm.getApproach() != null) {
			boolean available = memService.isMemberAvailableByUserName(memberForm.getMemberUserName());
			boolean allowed = false;
				
			String memberUserName = memberForm.getMemberUserName();
			try {
				StringUtil.checkGoodName(memberUserName);
				allowed = true;
			} catch (BadInputException e) {
				allowed = false;
			}

			String unActivatePattern = getSysProp().getValue("DEFAULT_USERNAME_PATTERN");
			
			StringTokenizer st = new StringTokenizer(unActivatePattern, ",");
			while (st.hasMoreTokens()) {
				if (memberUserName.startsWith(st.nextToken())) {
					allowed = false;
				}
			}										
			
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");

			if (available || !allowed) {
				response.getWriter().write("<message>false</message>");
			} else {
				response.getWriter().write("<message>true</message>");
			}
		} 
		return null;
	}    
    
    
    public ActionForward searchForMembers(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws
       Exception {

     List<MemberVO> membersArrayList = new ArrayList<MemberVO>();
     int searchCount = 0 ;
     
     String isAdmin = BaseConstants.BOOLEAN_NO;

     MemberForm memberForm = (MemberForm) form;
            	
	 membersArrayList = baseMemberSearch(memberForm, request, searchCount, memService, isAdmin);
	
	 setRequestObject(request, BaseConstants.LIST_OF_MEMBERS , membersArrayList);			      

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

      ActionMessages msgs = new ActionMessages();


      MemberVO token = getCurrentLoggedInUser(request);

      // check to see if the user logged on is a member
      if (!memberSecurityCheck(request, token)){
        return mapping.findForward(BaseConstants.FWD_LOGIN);
      }

      MemberForm memberForm = (MemberForm) form;
      MemberVO memberVO = new MemberVO();
      BeanUtils.copyProperties(memberVO, memberForm);
      memberVO.setMemberUserName(token.getMemberUserName());
      memberVO.setMemberId(token.getMemberId());
      memService.updateMemberProfile(memberVO, getLastModifiedBy(request));
      
      // Messengers
      final String memberId = memberVO.getMemberId();
 
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
      return mapping.findForward(BaseConstants.FWD_SUCCESS);

    }   
    
    
    public ActionForward deleteAvatar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws
            	Exception {

        MemberVO token = getCurrentLoggedInUser(request);

        // check to see if the user logged on is a member
        if (!memberSecurityCheck(request, token)){
          return mapping.findForward(BaseConstants.FWD_LOGIN);
        }
        
        
		ActionMessages errors = new ActionMessages();
		String avatarName = token.getAvatar();
			
		try{
			//
			// removed from database
			//
			memService.deleteMemberAvatar(token.getAvatar(), token.getMemberUserName(), getLastModifiedBy(request));
			
			//
			//removed from file system
			//
			String avatarDir = getSysProp().getValue("AVATAR.FILEPATH");
			File f = new File(avatarDir + File.separator + avatarName);
			if (f.exists() && f.isFile())
				f.delete();
				
			//
			// remove from session
			//
			MyAlumniUserContainer container = getUserContainer(request);
			container.setAvatar("");
			
		}
		catch(Exception e){
			errors.add(BaseConstants.FATAL_KEY, new ActionMessage("errors.technical.difficulty"));
			saveMessages(request, errors);
			return mapping.getInputForward();		
		}
		
		errors.add(BaseConstants.INFO_KEY, new ActionMessage("message.avatarremoved"));
		saveMessages(request, errors);
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }
    
    
    public ActionForward updateMemberAvatar(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws
        Exception {

      
      ActionMessages errors = new ActionMessages();
      String fileTypes =  SystemConfigConstants.CONTENT_TYPE ;
      String avatarDir = getSysProp().getValue("AVATAR.FILEPATH");
      MyAlumniUserContainer continer = getUserContainer(request);

      int maxFileSize = 0;
      int maxHeight = 0;
      int maxWidth = 0;
      String overwrite = "false";

      if (isCancelled(request)){
        return mapping.findForward(BaseConstants.FWD_CANCEL);
      }

      MemberVO token = getCurrentLoggedInUser(request);
      MemberForm memberForm = (MemberForm)form;
      
      if (!memberSecurityCheck(request, token)){
        return mapping.findForward(BaseConstants.FWD_LOGIN);
      }
   
      //  Set Max Size
      try{
    	  maxFileSize = Integer.parseInt(getAppProp().getValue("avatar.image.size").trim());
      }
      catch(Exception e){
    	  maxFileSize = 120000;   // 120000 Bytes  = 120 KB
      }
      
      // Set Max Height
      try{
    	  maxHeight = Integer.parseInt(getAppProp().getValue("avatar.image.height").trim());
      }
      catch(Exception e){
    	  maxHeight = 200;   // 200 px 
      }
      
      // Set Max Width
      try{
    	  maxWidth = Integer.parseInt(getAppProp().getValue("avatar.image.width").trim());
      }
      catch(Exception e){
    	  maxWidth = 200;   // 200 px 
      }
      
      
      FormFile importFile = memberForm.getAvatarUpload();
      overwrite = StringUtil.safeString(memberForm.getAvatarUploadOverwrite());
      String importFileName = getCurrentLoggedInUser(request).getMemberUserName() + "." + getFileExtensionForImageReader(importFile.getFileName());
      int size = importFile.getFileSize();

    //--------------------  VALIDATE THE IMAGE -----------------------------------------
      // check width and heigh of image
      logger.debug(importFileName + " ext = " + getFileExtensionForImageReader(importFileName));
      Iterator readers = ImageIO.getImageReadersBySuffix(getFileExtensionForImageReader(importFileName));
      ImageReader reader = (ImageReader) readers.next();
   
       try {
          ImageInputStream iis = ImageIO.createImageInputStream(importFile.getInputStream());
          reader.setInput(iis, true);
          int width = reader.getWidth(0);
          int height = reader.getHeight(0);
          logger.debug(importFile.getFileName() + ": width=" + width + ", height=" + height);
          if (width > maxWidth || height >  maxHeight){
              errors.add(BaseConstants.WARN_KEY,new ActionMessage("error.dimensions", width, height, maxWidth, maxHeight ));
              saveMessages(request, errors);
              return mapping.getInputForward();        	  
          }
      } catch (IOException e) {
          System.err.println(e.getMessage() + ": can't open");
          errors.add(BaseConstants.FATAL_KEY,new ActionMessage("error.notreadable"));
          saveMessages(request, errors);
          return mapping.getInputForward();           
      }
          
      
      // check file name
      if (importFileName.indexOf(" ") > -1) {
        errors.add(BaseConstants.WARN_KEY,new ActionMessage("error.filename", importFileName));
        saveMessages(request, errors);
        return mapping.getInputForward();
      }

      //boolean validImageName = false;
/*      StringTokenizer st0 = new StringTokenizer(importFileName, ".");
      if (st0.hasMoreTokens()) {
        if (token.getMemberUserName().equals(st0.nextToken())) {
          //validImageName = true;
        }
        else{
          errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.fileusername", token.getMemberUserName(),importFileName ));
          saveMessages(request, errors);
          return mapping.getInputForward();
        }
      }*/


      File f = new File(avatarDir + importFileName);
      if ( f.exists() && (overwrite.equalsIgnoreCase("false") || overwrite.equalsIgnoreCase(""))){
        continer.setOverWriteAvatar(true);
        errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.filename.exist"));
        saveMessages(request, errors);
        return mapping.getInputForward();
      }

      if ( size > maxFileSize){
        errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.filetoobig", String.valueOf(size), String.valueOf(maxFileSize)));
        saveMessages(request, errors);
        return mapping.getInputForward();
      }

      boolean validImageExtension = false;
      StringTokenizer st = new StringTokenizer(fileTypes, ",");

      logger.debug("Current Type = " + importFile.getContentType());
      while (st.hasMoreTokens()) {
        if ( importFile.getContentType().equalsIgnoreCase(st.nextToken())){
          validImageExtension = true;
        }
      }

      // check file extension
      if (!validImageExtension){
        errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.imageext", String.valueOf(fileTypes)));
        saveMessages(request, errors);
        return mapping.getInputForward();
      }
      
      // apend the file extension 
      //avatar = avatar + "." + importFile.getContentType();



    //-------------------------------------------------------------

        if(!uploadFromLocalDrive(importFile, importFileName ,avatarDir)){
          errors.add(BaseConstants.FATAL_KEY, new ActionMessage("errors.technical.difficulty"));
          saveMessages(request, errors);
          return mapping.getInputForward();
        }


        memService.updateMemberAvatar(importFileName, token.getMemberUserName(), getLastModifiedBy(request));
        continer.setOverWriteAvatar(false);
        continer.setAvatar(importFileName);
        MemberVO memberVO = memService.getMemberProfileByUserName(token.getMemberUserName());
        BeanUtils.copyProperties(memberForm, memberVO);
        return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }    
    
    
    public ActionForward updateMemberEmail(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws
        Exception {

      if (isCancelled(request)){
        return mapping.findForward(BaseConstants.FWD_CANCEL);
      }


      MemberVO token = getCurrentLoggedInUser(request);
      // check to see if the user logged on is a member
      if (!memberSecurityCheck(request, token)){
        return mapping.findForward(BaseConstants.FWD_LOGIN);
      }

      MemberForm memberForm = (MemberForm) form;
      String email = memberForm.getEmail().toLowerCase();

      if (memService.isMemberAvailableByEmail(email, memberForm.getMemberId())) {
        ActionMessages errors = new ActionMessages();
        errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.duplicate.email"));
        saveMessages(request, errors);
           return mapping.getInputForward();
      }

      memService.updateMemberEmail(email, token.getMemberUserName(), getLastModifiedBy(request));

      // get the member profile
      MemberVO memberVO = memService.getMemberProfileByUserName(token.getMemberUserName());
      BeanUtils.copyProperties(memberForm, memberVO);
      MyAlumniUserContainer continer = getUserContainer(request);
      continer.updateTokenEmail(email);
      return mapping.findForward(BaseConstants.FWD_SUCCESS);

    }    
    
    
    public ActionForward updateMemberPassword(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws
        Exception {


      if (isCancelled(request)){
        return mapping.findForward(BaseConstants.FWD_CANCEL);
      }

      MemberVO token = getCurrentLoggedInUser(request);
      // check to see if the user logged on is a member
      if (!memberSecurityCheck(request, token)){
        return mapping.findForward(BaseConstants.FWD_LOGIN);
      }


      MemberForm memberForm = (MemberForm) form;
      String oldpassword =  memberForm.getOldMemberPassword();
      String newpassword =  memberForm.getMemberPasswordConfirm();

      String currentPassword = memService.getMemberPasswordByUserName(token.getMemberUserName());

      if (!currentPassword.equals(Encoder.getMD5_Base64(oldpassword))) {
        ActionMessages errors = new ActionMessages();
        errors.add(BaseConstants.WARN_KEY, new ActionMessage("error.password.equal"));
        saveMessages(request, errors);
           return mapping.getInputForward();
      }

      memService.updateMemberPassword(token.getMemberUserName(), newpassword, getLastModifiedBy(request));

      // get the member profile
      MemberVO memberVO = memService.getMemberProfileByUserName(token.getMemberUserName());
      BeanUtils.copyProperties(memberForm, memberVO);
      return mapping.findForward(BaseConstants.FWD_SUCCESS);

    }    
    
    
    public ActionForward updateMemberSignature(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws
        Exception {

      if (isCancelled(request)){
        return mapping.findForward(BaseConstants.FWD_CANCEL);
      }

      MemberVO token = getCurrentLoggedInUser(request);

      // check to see if the user logged on is a member
      if (!memberSecurityCheck(request, token)){
        return mapping.findForward(BaseConstants.FWD_LOGIN);
      }

      MemberForm memberForm = (MemberForm) form;
      String signature = memberForm.getSignature().toLowerCase();

      memService.updateMemberSignature(signature, token.getMemberUserName(), getLastModifiedBy(request));

      token.setSignature(signature);

      // get the member profile
      MemberVO memberVO = memService.getMemberProfileByUserName(token.getMemberUserName());
       BeanUtils.copyProperties(memberForm, memberVO);

      return mapping.findForward(BaseConstants.FWD_SUCCESS);

    }    
    
    
    public ActionForward displayMyDesktop ( ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response )
      throws Exception {

    	MemberVO token = getCurrentLoggedInUser(request);


	    // check to see if the user logged on is a member
	    if (!memberSecurityCheck(request, token)){
	      return mapping.findForward(BaseConstants.FWD_LOGIN);
	    }

    	MyAlumniUserContainer container = getUserContainer(request);
    	container.setNewMailCount(pmService.getMailCountByUserName(token.getMemberId(), BaseConstants.PM_STATUS_NEW));
    	return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }    
    
    
    public ActionForward viewMemberProfile(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws
        Exception {

      MemberVO token = getCurrentLoggedInUser(request);

      // check to see if the user logged on is a member
      if (!memberSecurityCheck(request, token)){
        return mapping.findForward(BaseConstants.FWD_LOGIN);
      }


      MemberForm memberForm = (MemberForm) form;

      MemberVO memberVO = memService.getMemberProfileByUserName(token.getMemberUserName());
      BeanUtils.copyProperties(memberForm, memberVO);
      
	  List<XlatDetailVO> selectedMessengers = messengerService.getActiveMemberMessengers(token.getMemberId());
	  memberForm.setMessengers(selectedMessengers);
	  
	  setRequestObject(request, BaseConstants.MEMBER_PROFILE, memberVO);    // to display date using fmt
      
      return mapping.findForward(BaseConstants.FWD_SUCCESS);
    }    
    
    
    public ActionForward prepareRegistration(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws
		Exception {
      
        
        List<XlatDetailVO> luAvailableIMs = xlatService.getActiveGroupDetails(BaseConstants.GROUP_INSTANT_MESSENGERS);
        List<XlatDetailVO> luSelectedIMs = new ArrayList<XlatDetailVO>();
        setSessionObject(request, BaseConstants.LU_AVAILABLE_IMS, luAvailableIMs);
        setSessionObject(request, BaseConstants.LU_SELECTED_IMS, luSelectedIMs);
        
		return mapping.findForward(BaseConstants.FWD_SUCCESS);
    } 
    
    
}

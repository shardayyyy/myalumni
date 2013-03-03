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

import net.naijatek.myalumni.controller.exceptions.BadInputException;
import net.naijatek.myalumni.controller.exceptions.CreateException;
import net.naijatek.myalumni.controller.exceptions.DuplicateEmailException;
import net.naijatek.myalumni.controller.exceptions.DuplicateMemberException;
import net.naijatek.myalumni.framework.MyAlumniUserContainer;
import net.naijatek.myalumni.entity.MemberVO;
import net.naijatek.myalumni.entity.MessengerVO;
import net.naijatek.myalumni.entity.SystemConfigVO;
import net.naijatek.myalumni.entity.XlatDetailVO;
import net.naijatek.myalumni.controller.helper.MyAlumniMessage;
import net.naijatek.myalumni.controller.helper.MyAlumniMessages;
import net.naijatek.myalumni.service.*;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.SystemConfigConstants;
import net.naijatek.myalumni.util.encryption.Encoder;
import net.naijatek.myalumni.util.mail.SendMailUtil;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;


@Controller
public class MemberController extends MyAlumniBaseController {

    @Autowired
    private IMemberService memService;

    @Autowired
    private IPrivateMessageService pmService;

    @Autowired
    private IMessageFolderService mfService;

    @Autowired
    private IXlatService xlatService;

    @Autowired
    private ISystemConfigService sysConfigSerivce;

    @Autowired
    private IMessengerService messengerService;

    private static Log logger = LogFactory.getLog(MemberController.class);


/*    public MaintainMemberAction(final IMemberService memService,
        final IPrivateMessageService pmService, final IMessageFolderService mfService, IXlatService xlatService,
        final ISystemConfigService sysConfigSerivce, IMessengerService messengerService) {
    this.memService = memService;
    this.pmService = pmService;
    this.mfService = mfService;
    this.xlatService = xlatService;
    this.sysConfigSerivce = sysConfigSerivce;
    this.messengerService = messengerService;
}*/
    
    private void appcontextdemo(){
    	ApplicationContext appCtx = MyAlumniStartupServlet.getApplicationContext();
    	MyAlumniStartupServlet ss = (MyAlumniStartupServlet) appCtx.getBean("starter"); // from the xml file
    	String xxx = ss.getSomthing();
    }


    @RequestMapping(value = "/genericAjaxSearch", method = RequestMethod.POST)
    public ModelAndView genericAjaxSearch(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        logger.debug("in genericAjaxSearch...");

        String searchCriteria = memberVO.getSearchCriteria();
        String searchWord = "";
        String ajaxFormat = memberVO.getAjaxFormat();
        ModelAndView mv = new ModelAndView(BaseConstants.FWD_SUCCESS);


        if (memberVO.getApproach() != null) {

            if (searchCriteria.equals(BaseConstants.FIRST_NAME)) {
                searchWord = memberVO.getFirstName();
            } else if (searchCriteria.equals(BaseConstants.LAST_NAME)) {
                searchWord = memberVO.getLastName();
            } else if (searchCriteria.equals(BaseConstants.MAIDEN_NAME)) {
                searchWord = memberVO.getMaidenName();
            } else if (searchCriteria.equals(BaseConstants.NICK_NAME)) {
                searchWord = memberVO.getNickName();
            } else if (searchCriteria.equals(BaseConstants.FULL_NAME)) {
                searchWord = memberVO.getMessageToUserName();
            }

            if (ajaxFormat.equals(BaseConstants.AJAX_FORMAT_STRING)) {
                List<String> _result = memService.genericAjaxSearch(searchWord, searchCriteria);
                mv.addObject("result", _result);
                request.getRequestDispatcher(BaseConstants.FWD_AJAX_JSP).forward(request, response);
            } else if (ajaxFormat.equals(BaseConstants.AJAX_FORMAT_OBJECT)) {
                List<MemberVO> _result = memService.genericAjaxSearchObjects(searchWord, searchCriteria);
                mv.addObject("result", _result);
                request.getRequestDispatcher(BaseConstants.FWD_AJAX_JSP_OBJECT).forward(request, response);
            }


            return null;
        }

        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    @RequestMapping(value = "/displayMiniProfile", method = RequestMethod.POST)
    public ModelAndView displayMiniProfile(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status) throws
            Exception {
        ModelAndView mv = new ModelAndView(BaseConstants.FWD_SUCCESS);
        String memberUserName = memberVO.getMemberUserName();
        memberVO = memService.getMemberProfileByUserName(memberUserName);
        mv.addObject("profile", memberVO);
        return mv;

    }


    @RequestMapping(value = "/prepareUpdateMemberProfile", method = RequestMethod.POST)
    public ModelAndView prepareUpdateMemberProfile(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status,
                                                   HttpServletRequest request,
                                                   HttpServletResponse response) throws
            Exception {

        ModelAndView mv = new ModelAndView();
        MemberVO token = getCurrentLoggedInUser(request);

        // check to see if the user logged on is a member
        if (!memberSecurityCheck(request, token)) {
            return new ModelAndView(BaseConstants.FWD_LOGIN);
        }

        memberVO = memService.getMemberProfileByUserName(token.getMemberUserName());

        try {
            // IM
            List<XlatDetailVO> availableMessengers = xlatService.getActiveGroupDetails(BaseConstants.GROUP_INSTANT_MESSENGERS);
            List<XlatDetailVO> selectedMessengers = messengerService.getActiveMemberMessengers(token.getMemberId());
            List<XlatDetailVO> filteredAvailableIMs = filterMessengers(availableMessengers, selectedMessengers);

            setSessionObject(request, BaseConstants.LU_AVAILABLE_IMS, filteredAvailableIMs);
            setSessionObject(request, BaseConstants.LU_SELECTED_IMS, selectedMessengers);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            MyAlumniMessages errors = new MyAlumniMessages();
            errors.add(BaseConstants.WARN_KEY, new MyAlumniMessage("core.errorcode.00709"));
            saveMessages(request, errors);
            //return new ModelAndView(BaseConstants.FWD_REDISPLAY);
            return mv;
        }


        return new ModelAndView(BaseConstants.FWD_SUCCESS);

    }


    @RequestMapping(value = "/addMember", method = RequestMethod.POST)
    public ModelAndView addMember(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
            Exception {

        MyAlumniMessages errors = new MyAlumniMessages();
        SystemConfigVO sysConfigVO = sysConfigSerivce.getSystemConfig();
        ModelAndView mv = new ModelAndView();

        try {
            memberVO = new MemberVO();

            memberVO.setLastModifiedBy(memberVO.getMemberUserName());

            // Member
            memService.createMember(memberVO, request);
            ////////
            final String memberId = memberVO.getMemberId();
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
            message.append("Thank you " + StringUtil.capitalize(memberVO.getFirstName()) + " " + StringUtil.capitalize(memberVO.getLastName()) + " for registering and Welcome to " + sysConfigVO.getOrganizationName() + "'s owns space in cyberspace.");
            message.append("Your account should be active within the next 24 hours. So please try logging into the system as soon as you get your activation confirmation email.");
            setSessionObject(request, BaseConstants.MESSAGE, message.toString());

            // send email to registrant
            try {
                SendMailUtil.sendWelcomeNotice(memberVO.getEmail(), memberVO.getMemberUserName(), sysConfigVO);
            } catch (Exception ex) {
                logger.error(ex.getMessage());
                errors.add(BaseConstants.FATAL_KEY, new MyAlumniMessage("error.mailserver"));
                saveMessages(request, errors);
                return new ModelAndView(BaseConstants.FWD_SUCCESS);
            }

            // send email to administrator about new registrant
            try {
                SendMailUtil.notifyAdminAboutNewMember(memberVO, sysConfigVO);
            } catch (Exception ex) {
                logger.error(ex.getMessage());
                errors.add(BaseConstants.FATAL_KEY, new MyAlumniMessage("error.mailserver"));
                saveMessages(request, errors);
                return new ModelAndView(BaseConstants.FWD_SUCCESS);
            }

        } catch (DuplicateMemberException e) {
            errors.add(BaseConstants.WARN_KEY, new MyAlumniMessage("error.duplicate.member"));
            saveMessages(request, errors);
            logger.info("DUPLICATE USER NAME - " + e.getMessage());
            //return new ModelAndView(BaseConstants.FWD_REDISPLAY);
            return mv;
        } catch (DuplicateEmailException e) {
            errors.add(BaseConstants.WARN_KEY, new MyAlumniMessage("error.duplicate.email"));
            saveMessages(request, errors);
            logger.info("DUPLICATE EMAIL - " + e.getMessage());
            //return new ModelAndView(BaseConstants.FWD_REDISPLAY);
            return mv;
        } catch (CreateException e) {
            errors.add(BaseConstants.WARN_KEY, new MyAlumniMessage("errors.technical.difficulty"));
            saveMessages(request, errors);
            logger.fatal("SYSTEM ERROR - " + e.getMessage());
            //return new ModelAndView(BaseConstants.FWD_REDISPLAY);
            return mv;
        } catch (Exception ex) {
            errors.add(BaseConstants.FATAL_KEY, new MyAlumniMessage("errors.technical.difficulty"));
            saveMessages(request, errors);
            logger.fatal("SYSTEM ERROR - " + ex.getStackTrace());
            //return new ModelAndView(BaseConstants.FWD_REDISPLAY);
            return mv;
        }
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    @RequestMapping(value = "/prepareDeleteMyMemberProfile", method = RequestMethod.POST)
    public ModelAndView prepareDeleteMyMemberProfile(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response) throws
            Exception {

        setSessionObject(request, "ipaddress", getCurrentIPAddress(request));
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    @RequestMapping(value = "/deleteMyMemberProfile", method = RequestMethod.POST)
    public ModelAndView deleteMyMemberProfile(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status,
                                              HttpServletRequest request,
                                              HttpServletResponse response) throws
            Exception {

        memService.softDelete(getCurrentUserId(request), getLastModifiedBy(request));
        MyAlumniMessages errors = new MyAlumniMessages();
        errors.add(BaseConstants.INFO_KEY, new MyAlumniMessage("core.errorcode.00713"));
        saveMessages(request, errors);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    @RequestMapping(value = "/validateMemberUserName", method = RequestMethod.POST)
    public ModelAndView validateMemberUserName(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status, HttpServletResponse response) throws
            Exception {

        if (memberVO.getApproach() != null) {
            boolean available = memService.isMemberAvailableByUserName(memberVO.getMemberUserName());
            boolean allowed = false;

            String memberUserName = memberVO.getMemberUserName();
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


    @RequestMapping(value = "/searchForMembers", method = RequestMethod.POST)
    public ModelAndView searchForMembers(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status, HttpServletResponse response, HttpServletRequest request) throws
            Exception {

        List<MemberVO> membersArrayList = new ArrayList<MemberVO>();
        int searchCount = 0;

        String isAdmin = BaseConstants.BOOLEAN_NO;

        membersArrayList = baseMemberSearch(memberVO, request, searchCount, memService, isAdmin);

        setRequestObject(request, BaseConstants.LIST_OF_MEMBERS, membersArrayList);

        return new ModelAndView(BaseConstants.FWD_SUCCESS);

    }


    @RequestMapping(value = "/updateMemberProfile", method = RequestMethod.POST)
    public ModelAndView updateMemberProfile(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status, HttpServletResponse response, HttpServletRequest request) throws
            Exception {

        //if (isCancelled(request)){
        //  return new ModelAndView(BaseConstants.FWD_CANCEL);
        //}

        MyAlumniMessages msgs = new MyAlumniMessages();


        MemberVO token = getCurrentLoggedInUser(request);

        // check to see if the user logged on is a member
        if (!memberSecurityCheck(request, token)) {
            return new ModelAndView(BaseConstants.FWD_LOGIN);
        }


        memberVO.setMemberUserName(token.getMemberUserName());
        memberVO.setMemberId(token.getMemberId());
        memService.updateMemberProfile(memberVO, getLastModifiedBy(request));

        // Messengers
        final String memberId = memberVO.getMemberId();

        List<MessengerVO> messengers = new ArrayList<MessengerVO>();
        MessengerVO mesgerVO = null;
        for (String str : memberVO.getLstSelectedIMs()) {
            mesgerVO = new MessengerVO();
            mesgerVO.setLastModifiedBy(getLastModifiedBy(request));
            mesgerVO.setMemberId(memberId);
            mesgerVO.setLookupCodeId(str);
            messengers.add(mesgerVO);
        }
        messengerService.saveAll(messengers, memberId);


        msgs.add(BaseConstants.INFO_KEY, new MyAlumniMessage("message.memberupdated"));
        saveMessages(request, msgs);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);

    }


    @RequestMapping(value = "/deleteAvatar", method = RequestMethod.POST)
    public ModelAndView deleteAvatar(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status, HttpServletResponse response, HttpServletRequest request) throws
            Exception {

        MemberVO token = getCurrentLoggedInUser(request);

        // check to see if the user logged on is a member
        if (!memberSecurityCheck(request, token)) {
            return new ModelAndView(BaseConstants.FWD_LOGIN);
        }


        MyAlumniMessages errors = new MyAlumniMessages();
        String avatarName = token.getAvatar();

        try {
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

        } catch (Exception e) {
            errors.add(BaseConstants.FATAL_KEY, new MyAlumniMessage("errors.technical.difficulty"));
            saveMessages(request, errors);
            return new ModelAndView(BaseConstants.FWD_REDISPLAY);
        }

        errors.add(BaseConstants.INFO_KEY, new MyAlumniMessage("message.avatarremoved"));
        saveMessages(request, errors);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    @RequestMapping(value = "/updateMemberAvatar", method = RequestMethod.POST)
    public ModelAndView updateMemberAvatar(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status, HttpServletResponse response, HttpServletRequest request) throws
            Exception {


        MyAlumniMessages errors = new MyAlumniMessages();
        String fileTypes = SystemConfigConstants.CONTENT_TYPE;
        String avatarDir = getSysProp().getValue("AVATAR.FILEPATH");
        MyAlumniUserContainer continer = getUserContainer(request);

        int maxFileSize = 0;
        int maxHeight = 0;
        int maxWidth = 0;
        String overwrite = "false";

//      if (isCancelled(request)){
//        return new ModelAndView(BaseConstants.FWD_CANCEL);
//      }

        MemberVO token = getCurrentLoggedInUser(request);

        if (!memberSecurityCheck(request, token)) {
            return new ModelAndView(BaseConstants.FWD_LOGIN);
        }

        //  Set Max Size
        try {
            maxFileSize = Integer.parseInt(getAppProp().getValue("avatar.image.size").trim());
        } catch (Exception e) {
            maxFileSize = 120000;   // 120000 Bytes  = 120 KB
        }

        // Set Max Height
        try {
            maxHeight = Integer.parseInt(getAppProp().getValue("avatar.image.height").trim());
        } catch (Exception e) {
            maxHeight = 200;   // 200 px
        }

        // Set Max Width
        try {
            maxWidth = Integer.parseInt(getAppProp().getValue("avatar.image.width").trim());
        } catch (Exception e) {
            maxWidth = 200;   // 200 px
        }


        CommonsMultipartFile importFile = memberVO.getAvatarUpload();
        overwrite = StringUtil.safeString(memberVO.getAvatarUploadOverwrite());
        String importFileName = getCurrentLoggedInUser(request).getMemberUserName() + "." + getFileExtensionForImageReader(importFile.getName());
        long size = importFile.getSize();

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
            logger.debug(importFile.getName() + ": width=" + width + ", height=" + height);
            if (width > maxWidth || height > maxHeight) {
                errors.add(BaseConstants.WARN_KEY, new MyAlumniMessage("error.dimensions", width, height, maxWidth, maxHeight));
                saveMessages(request, errors);
                return new ModelAndView(BaseConstants.FWD_REDISPLAY);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage() + ": can't open");
            errors.add(BaseConstants.FATAL_KEY, new MyAlumniMessage("error.notreadable"));
            saveMessages(request, errors);
            return new ModelAndView(BaseConstants.FWD_REDISPLAY);
        }


        // check file name
        if (importFileName.indexOf(" ") > -1) {
            errors.add(BaseConstants.WARN_KEY, new MyAlumniMessage("error.filename", importFileName));
            saveMessages(request, errors);
            return new ModelAndView(BaseConstants.FWD_REDISPLAY);
        }

        //boolean validImageName = false;
/*      StringTokenizer st0 = new StringTokenizer(importFileName, ".");
      if (st0.hasMoreTokens()) {
        if (token.getMemberUserName().equals(st0.nextToken())) {
          //validImageName = true;
        }
        else{
          errors.add(BaseConstants.WARN_KEY, new MyAlumniMessage("error.fileusername", token.getMemberUserName(),importFileName ));
          saveMessages(request, errors);
          return new ModelAndView(BaseConstants.FWD_REDISPLAY);
        }
      }*/


        File f = new File(avatarDir + importFileName);
        if (f.exists() && (overwrite.equalsIgnoreCase("false") || overwrite.equalsIgnoreCase(""))) {
            continer.setOverWriteAvatar(true);
            errors.add(BaseConstants.WARN_KEY, new MyAlumniMessage("error.filename.exist"));
            saveMessages(request, errors);
            return new ModelAndView(BaseConstants.FWD_REDISPLAY);
        }

        if (size > maxFileSize) {
            errors.add(BaseConstants.WARN_KEY, new MyAlumniMessage("error.filetoobig", String.valueOf(size), String.valueOf(maxFileSize)));
            saveMessages(request, errors);
            return new ModelAndView(BaseConstants.FWD_REDISPLAY);
        }

        boolean validImageExtension = false;
        StringTokenizer st = new StringTokenizer(fileTypes, ",");

        logger.debug("Current Type = " + importFile.getContentType());
        while (st.hasMoreTokens()) {
            if (importFile.getContentType().equalsIgnoreCase(st.nextToken())) {
                validImageExtension = true;
            }
        }

        // check file extension
        if (!validImageExtension) {
            errors.add(BaseConstants.WARN_KEY, new MyAlumniMessage("error.imageext", String.valueOf(fileTypes)));
            saveMessages(request, errors);
            return new ModelAndView(BaseConstants.FWD_REDISPLAY);
        }

        // apend the file extension
        //avatar = avatar + "." + importFile.getContentType();


        //-------------------------------------------------------------

        if (!uploadFromLocalDrive(importFile, importFileName, avatarDir)) {
            errors.add(BaseConstants.FATAL_KEY, new MyAlumniMessage("errors.technical.difficulty"));
            saveMessages(request, errors);
            return new ModelAndView(BaseConstants.FWD_REDISPLAY);
        }


        memService.updateMemberAvatar(importFileName, token.getMemberUserName(), getLastModifiedBy(request));
        continer.setOverWriteAvatar(false);
        continer.setAvatar(importFileName);
        memberVO = memService.getMemberProfileByUserName(token.getMemberUserName());
        // BeanUtils.copyProperties(memberForm, memberVO);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    @RequestMapping(value = "/updateMemberEmail", method = RequestMethod.POST)
    public ModelAndView updateMemberEmail(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status, HttpServletResponse response, HttpServletRequest request) throws
            Exception {

//      if (isCancelled(request)){
//        return new ModelAndView(BaseConstants.FWD_CANCEL);
//      }


        MemberVO token = getCurrentLoggedInUser(request);
        // check to see if the user logged on is a member
        if (!memberSecurityCheck(request, token)) {
            return new ModelAndView(BaseConstants.FWD_LOGIN);
        }

        String email = memberVO.getEmail().toLowerCase();

        if (memService.isMemberAvailableByEmail(email, memberVO.getMemberId())) {
            MyAlumniMessages errors = new MyAlumniMessages();
            errors.add(BaseConstants.WARN_KEY, new MyAlumniMessage("error.duplicate.email"));
            saveMessages(request, errors);
            return new ModelAndView(BaseConstants.FWD_REDISPLAY);
        }

        memService.updateMemberEmail(email, token.getMemberUserName(), getLastModifiedBy(request));

        // get the member profile
        memberVO = memService.getMemberProfileByUserName(token.getMemberUserName());
        MyAlumniUserContainer continer = getUserContainer(request);
        continer.updateTokenEmail(email);
        return new ModelAndView(BaseConstants.FWD_SUCCESS);

    }


    @RequestMapping(value = "/updateMemberPassword", method = RequestMethod.POST)
    public ModelAndView updateMemberPassword(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status, HttpServletResponse response, HttpServletRequest request) throws
            Exception {


//      if (isCancelled(request)){
//        return new ModelAndView(BaseConstants.FWD_CANCEL);
//      }

        MemberVO token = getCurrentLoggedInUser(request);
        // check to see if the user logged on is a member
        if (!memberSecurityCheck(request, token)) {
            return new ModelAndView(BaseConstants.FWD_LOGIN);
        }

        String oldpassword = memberVO.getOldMemberPassword();
        String newpassword = memberVO.getMemberPasswordConfirm();

        String currentPassword = memService.getMemberPasswordByUserName(token.getMemberUserName());

        if (!currentPassword.equals(Encoder.getMD5_Base64(oldpassword))) {
            MyAlumniMessages errors = new MyAlumniMessages();
            errors.add(BaseConstants.WARN_KEY, new MyAlumniMessage("error.password.equal"));
            saveMessages(request, errors);
            return new ModelAndView(BaseConstants.FWD_REDISPLAY);
        }

        memService.updateMemberPassword(token.getMemberUserName(), newpassword, getLastModifiedBy(request));

        // get the member profile
        memberVO = memService.getMemberProfileByUserName(token.getMemberUserName());

        return new ModelAndView(BaseConstants.FWD_SUCCESS);

    }


    @RequestMapping(value = "/updateMemberSignature", method = RequestMethod.POST)
    public ModelAndView updateMemberSignature(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status, HttpServletResponse response, HttpServletRequest request) throws
            Exception {

//      if (isCancelled(request)){
//        return new ModelAndView(BaseConstants.FWD_CANCEL);
//      }

        MemberVO token = getCurrentLoggedInUser(request);

        // check to see if the user logged on is a member
        if (!memberSecurityCheck(request, token)) {
            return new ModelAndView(BaseConstants.FWD_LOGIN);
        }

        String signature = memberVO.getSignature().toLowerCase();

        memService.updateMemberSignature(signature, token.getMemberUserName(), getLastModifiedBy(request));

        token.setSignature(signature);

        // get the member profile
        memberVO = memService.getMemberProfileByUserName(token.getMemberUserName());

        return new ModelAndView(BaseConstants.FWD_SUCCESS);

    }


    @RequestMapping(value = "/displayMyDesktop", method = RequestMethod.POST)
    public ModelAndView displayMyDesktop(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status, HttpServletResponse response, HttpServletRequest request) throws
            Exception {

        MemberVO token = getCurrentLoggedInUser(request);


        // check to see if the user logged on is a member
        if (!memberSecurityCheck(request, token)) {
            return new ModelAndView(BaseConstants.FWD_LOGIN);
        }

        MyAlumniUserContainer container = getUserContainer(request);
        container.setNewMailCount(pmService.getMailCountByUserName(token.getMemberId(), BaseConstants.PM_STATUS_NEW));
        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    @RequestMapping(value = "/viewMemberProfile", method = RequestMethod.POST)
    public ModelAndView viewMemberProfile(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status, HttpServletResponse response, HttpServletRequest request) throws
            Exception {

        MemberVO token = getCurrentLoggedInUser(request);

        // check to see if the user logged on is a member
        if (!memberSecurityCheck(request, token)) {
            return new ModelAndView(BaseConstants.FWD_LOGIN);
        }

        memberVO = memService.getMemberProfileByUserName(token.getMemberUserName());

        List<XlatDetailVO> selectedMessengers = messengerService.getActiveMemberMessengers(token.getMemberId());
        memberVO.setMessengers(selectedMessengers);

        setRequestObject(request, BaseConstants.MEMBER_PROFILE, memberVO);    // to display date using fmt

        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


    @RequestMapping(value = "/prepareRegistration", method = RequestMethod.POST)
    public ModelAndView prepareRegistration(@ModelAttribute("member") MemberVO memberVO, BindingResult result, SessionStatus status, HttpServletResponse response, HttpServletRequest request) throws
            Exception {


        List<XlatDetailVO> luAvailableIMs = xlatService.getActiveGroupDetails(BaseConstants.GROUP_INSTANT_MESSENGERS);
        List<XlatDetailVO> luSelectedIMs = new ArrayList<XlatDetailVO>();
        setSessionObject(request, BaseConstants.LU_AVAILABLE_IMS, luAvailableIMs);
        setSessionObject(request, BaseConstants.LU_SELECTED_IMS, luSelectedIMs);

        return new ModelAndView(BaseConstants.FWD_SUCCESS);
    }


}

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
package net.naijatek.myalumni.framework.extensions;

import net.naijatek.myalumni.framework.exceptions.BadInputException;
import net.naijatek.myalumni.modules.common.domain.ClassNewsVO;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.domain.XlatDetailVO;
import net.naijatek.myalumni.modules.common.helper.MyAlumniMessage;
import net.naijatek.myalumni.modules.common.helper.MyAlumniMessages;
import net.naijatek.myalumni.modules.common.helper.PrivateMessageHelper;
import net.naijatek.myalumni.modules.common.service.IClassNewsService;
import net.naijatek.myalumni.modules.common.service.IMemberService;
import net.naijatek.myalumni.modules.common.service.IPrivateMessageService;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.SystemConfigConstants;
import net.naijatek.myalumni.util.utilities.AppProp;
import net.naijatek.myalumni.util.utilities.FileUtil;
import net.naijatek.myalumni.util.utilities.StringUtil;
import net.naijatek.myalumni.util.utilities.SystemProp;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public abstract class MyAlumniBaseController extends AbstractController {

    private static Log logger = LogFactory.getLog(MyAlumniBaseController.class);

    //private static MessageResources defaultResource;
    private static MessageSource defaultResource;

    private static AppProp ap = AppProp.getInstance();

    private static SystemProp sysProp = SystemProp.getInstance();


    public ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ModelAndView mav = new ModelAndView("hello");
        mav.addObject("message", "Hello World!");
        return mav;
    }

    /*
      * //================================================ PUBLIC UTILITY METHODS
      *
      *
      * //--------------------------------------------------------------------------
      * //-- //-- P R O T E C T E D M E T H O D S //--
      * //--------------------------------------------------------------------------
      *
      * returns an instance of the application Properties file
      *
      * @return MessageResources
      */
    //ResourceBundleMessageSource
/*
	protected MessageSource getDefaultResource(HttpServletRequest request) {
		try {
            //defaultResource = getMessageSourceAccessor();
            defaultResource = (MessageSource)getServletContext().get
			defaultResource = getResources(request);
		} catch (Exception ex) {
			logger.error("in getDefaultResource, exception is thrown - " + ex);
		}
		return defaultResource;
	}
*/


    // --------------------------------------------------------------------------------------

    /**
     * Retrieve a request object based on the request and the attribute name.
     *
     * @param req      HttpServletRequest
     * @param attrName String
     * @return Object
     */
    protected Object getRequestObject(final HttpServletRequest req,
                                      final String attrName) {
        Object requestObj = null;
        if (req != null) {
            requestObj = req.getAttribute(attrName);
        }
        return requestObj;
    }

    // -----------------------------------------------------------------------------------

    /**
     * MEMBER METHODS ***********************************
     */
    protected boolean memberSecurityCheck(HttpServletRequest request) {
        MemberVO token = getCurrentLoggedInUser(request);
        return memberSecurityCheck(request, token);
    }

    // -----------------------------------------------------------------------------------

    /**
     * Checks to_email see if the user logged on is an administrator
     *
     * @param request HttpServletRequest
     * @return boolean
     */
    protected boolean memberSecurityCheck(HttpServletRequest request, final MemberVO token) {
        MyAlumniMessages errors = new MyAlumniMessages();
        boolean status = true;

        if (token == null) {
            errors.add(BaseConstants.WARN_KEY, new MyAlumniMessage(
                    "error.pleaselogin"));
            saveMessages(request, errors);
            status = false;
        }

        if (status) {
            if (token.getIsAdmin().equals(null)
                    || (!token.getIsAdmin().equalsIgnoreCase(
                    BaseConstants.BOOLEAN_YES) && !token.getIsAdmin()
                    .equalsIgnoreCase(BaseConstants.BOOLEAN_NO))) {
                errors.add(BaseConstants.WARN_KEY, new MyAlumniMessage(
                        "error.pleaselogin"));
                saveMessages(request, errors);
                status = false;
            }
        }
        return status;
    }

    // -----------------------------------------------------------------------------------

    protected boolean memberActivationCheck(final String memberUserName,
                                            final HttpServletRequest req) {
        String unActivatePattern = sysProp.getValue("DEFAULT_USERNAME_PATTERN");

        boolean validName = true;
        StringTokenizer st = new StringTokenizer(unActivatePattern, ",");
        while (st.hasMoreTokens()) {
            if (memberUserName.startsWith(st.nextToken())) {
                validName = false;
            }
        }

        return validName;

    }


    /**
     * ADMIN METHODS ***********************************
     */
    protected boolean adminSecurityCheck(HttpServletRequest request) {
        MemberVO token = getCurrentLoggedInUser(request);
        return adminSecurityCheck(request, token);
    }

    /**
     * Checks to_email see if the user logged on is an administrator
     *
     * @param request HttpServletRequest
     * @return boolean
     */
    protected boolean adminSecurityCheck(HttpServletRequest request,
                                         final MemberVO token) {
        MyAlumniMessages errors = new MyAlumniMessages();
        boolean status = true;

        if (token == null) {
            errors.add(BaseConstants.WARN_KEY, new MyAlumniMessage(
                    "error.pleaselogin"));
            saveMessages(request, errors);
            status = false;
        }

        if (status) {
            if (!token.getIsAdmin().equals(BaseConstants.BOOLEAN_YES)) {
                errors.add(BaseConstants.WARN_KEY, new MyAlumniMessage(
                        "error.pleaselogin"));
                saveMessages(request, errors);
                status = false;
            }
        }
        return status;
    }


    /**
     * @param importFile FormFile
     * @param destDir    String
     * @return String
     * @throws Exception
     */
    protected boolean uploadFromLocalDrive(CommonsMultipartFile importFile, String importFileName, String destDir)
            throws Exception {
        boolean valid = true;
        //String importFileName = importFile.getFileName();
        String destFile = destDir + importFileName;

        setupUploadDestDir(destDir);

        try {
            InputStream stream = importFile.getInputStream();
            OutputStream fos = new FileOutputStream(destFile);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            fos.close();
            stream.close();
        } catch (Exception fnfe) {
            logger.debug("Exception - Exception with Upload");
            valid = false;
        }

        return valid;
    }

    // --------------------------------------------------------------------------
    // --
    // -- P U B L I C M E T H O D S
    // --
    // --------------------------------------------------------------------------

    /**
     * Sets up other tasks such as loading the Advertisements into session and
     * annoucements
     *
     * @param request HttpServletRequest
     * @throws Exception
     */
    protected void setupAdminDesktop(final HttpServletRequest request,
                                     final IMemberService memService,
                                     final IClassNewsService classNewsService,
                                     final IPrivateMessageService pmService) throws Exception {

        // removeAdminSessionObject(request);

        // NEW MEMBERS - get all new members
        List<MemberVO> newMembers = memService.getMemberToAdminister();
        setSessionObject(request, "NEW_MEMBERS", newMembers);

        //NEW CLASSNEWS - get all new classnews
        List<ClassNewsVO> newClassNewsVO = classNewsService.findAllByStatus(BaseConstants.APPROVAL_NEEDED);
        setSessionObject(request, "NEW_CLASSNEWS", newClassNewsVO);

        MyAlumniUserContainer container = (MyAlumniUserContainer) request
                .getSession().getAttribute(BaseConstants.USER_CONTAINER);
        PrivateMessageHelper pmHelper = pmService.getAdminMessageCenter(
                BaseConstants.ADMIN_USERNAME_ID, BaseConstants.FOLDER_INBOX,
                container);
        setSessionObject(request, BaseConstants.MESSAGE_CENTER, pmHelper);
    }

    // -----------------------------------------------------------------------------------


    protected List<MemberVO> baseMemberSearch(final MemberVO objVO,
                                              final HttpServletRequest req, int searchCount,
                                              final IMemberService memService, final String isAdmin)
            throws Exception {

        int rowsToReturn = 0;
        List<MemberVO> membersArrayList = new ArrayList<MemberVO>();

        String searchCategory = StringUtil.safeString(objVO.getSearchCategory());
        String firstName = StringUtil.safeString(objVO.getFirstName()).toLowerCase();
        String lastName = StringUtil.safeString(objVO.getLastName()).toLowerCase();
        String dorm = StringUtil.safeString(objVO.getDormitoryId());
        String gender = StringUtil.safeString(objVO.getGender());
        String yearIn = StringUtil.safeString(objVO.getYearIn().toString());
        String yearOut = StringUtil.safeString(objVO.getYearOut().toString());
        String marriageName = StringUtil.safeString(objVO.getLastName()).toLowerCase();
        String nickName = StringUtil.safeString(objVO.getNickName()).toLowerCase();
        String maidenName = StringUtil.safeString(objVO.getMaidenName()).toLowerCase();
        int offset = 0;
        String partialNameSearch = objVO.getPartialNameSearch();
        String userName = StringUtil.safeString(objVO.getMemberUserName());
        String email = StringUtil.safeString(objVO.getEmail());

        if (searchCategory.equalsIgnoreCase(BaseConstants.FULL_SEARCH)) {

            membersArrayList = memService.searchFullSearchOnMembers(firstName,
                    lastName, dorm, gender, yearIn, yearOut, marriageName,
                    nickName, maidenName, partialNameSearch, offset,
                    rowsToReturn, isAdmin);
        } else if (searchCategory.equalsIgnoreCase(BaseConstants.FIRST_NAME)) {
            membersArrayList = memService.searchFirstName(firstName,
                    partialNameSearch, offset, rowsToReturn, isAdmin);
        } else if (searchCategory.equalsIgnoreCase(BaseConstants.LAST_NAME)) {
            membersArrayList = memService.searchLastName(lastName,
                    partialNameSearch, offset, rowsToReturn, isAdmin);
        } else if (searchCategory.equalsIgnoreCase(BaseConstants.DORMITORY)) {
            membersArrayList = memService.searchDormitory(dorm, offset,
                    rowsToReturn, isAdmin);
        } else if (searchCategory.equalsIgnoreCase(BaseConstants.YEAR_IN)) {
            membersArrayList = memService.searchYearIn(yearIn, offset,
                    rowsToReturn, isAdmin);
        } else if (searchCategory.equalsIgnoreCase(BaseConstants.YEAR_OUT)) {
            membersArrayList = memService.searchYearOut(yearOut, offset,
                    rowsToReturn, isAdmin);
        } else if (searchCategory.equalsIgnoreCase(BaseConstants.NICK_NAME)) {
            membersArrayList = memService.searchNickName(nickName,
                    partialNameSearch, offset, rowsToReturn, isAdmin);
        } else if (searchCategory.equalsIgnoreCase(BaseConstants.MARRIED_NAME)) {
            membersArrayList = memService.searchLastName(marriageName,
                    partialNameSearch, offset, rowsToReturn, isAdmin);
        } else if (searchCategory.equalsIgnoreCase(BaseConstants.MAIDEN_NAME)) {
            membersArrayList = memService.searchMaidenName(maidenName,
                    partialNameSearch, offset, rowsToReturn, isAdmin);
        } else if (searchCategory.equalsIgnoreCase(BaseConstants.GENDER)) {
            membersArrayList = memService.searchGender(gender, offset,
                    rowsToReturn, isAdmin);
        } else if (searchCategory.equalsIgnoreCase(BaseConstants.AVATAR)) {
            membersArrayList = memService.searchAvatar(offset, rowsToReturn,
                    isAdmin);
        } else if (searchCategory.equalsIgnoreCase(BaseConstants.USERNAME)) {
            membersArrayList = memService.searchUserName(userName, partialNameSearch, offset,
                    rowsToReturn, isAdmin);
        } else if (searchCategory.equalsIgnoreCase(BaseConstants.EMAIL)) {
            membersArrayList = memService.searchEmail(email, partialNameSearch, offset,
                    rowsToReturn, isAdmin);
        }

        return membersArrayList;
    }

    // ************************* MY ALUMNI
    // **********************************************

    // --------------------------------------------------------------------------
    // --
    // -- P R O T E C T E D M E T H O D S
    // --
    // --------------------------------------------------------------------------

    /**
     * Retrieve a session object based on the request and the attribute label.
     *
     * @param req      HttpServletRequest
     * @param attrName String
     * @return Object
     */
    protected Object getSessionObject(HttpServletRequest req, String attrName) {
        Object sessionObj = null;
        HttpSession session = req.getSession(false);
        if (session != null) {
            sessionObj = session.getAttribute(attrName);
        }
        return sessionObj;
    }

    /**
     * Set a session object based on the request and the attribute label.
     *
     * @param req      HttpServletRequest
     * @param attrName String
     * @param obj      Object
     */
    protected void setSessionObject(HttpServletRequest req, String attrName,
                                    Object obj) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.setAttribute(attrName, obj);
        }
    }

    /**
     * Set a request object based on the request and the attribute label.
     *
     * @param req      HttpServletRequest
     * @param attrName String
     * @param obj      Object
     */
    protected void setRequestObject(HttpServletRequest req, String attrName,
                                    Object obj) {
        req.setAttribute(attrName, obj);
    }

    /**
     * Places the user's container in the session
     *
     * @param request
     * @param container
     */
    protected void setSessionUserContainer(HttpServletRequest request,
                                           MyAlumniUserContainer container) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute(BaseConstants.USER_CONTAINER, container);
        }
    }

    /**
     * Retrieve the users session container.
     *
     * @param req HttpServletRequest
     * @return MyAlumniUserContainer
     */
    protected MyAlumniUserContainer getUserContainer(HttpServletRequest req) {
        MyAlumniUserContainer sessionObj = null;
        HttpSession session = req.getSession(false);
        if (session != null) {
            sessionObj = (MyAlumniUserContainer) session.getAttribute(BaseConstants.USER_CONTAINER);
        }
        return sessionObj;
    }

    /**
     * Retrieve the users currently logged in .
     *
     * @param req HttpServletRequest
     * @return MemberVO
     */
    protected MemberVO getCurrentLoggedInUser(HttpServletRequest req) {
        MyAlumniUserContainer sessionObj = getUserContainer(req);
        MemberVO userVO = null;

        if (sessionObj != null) {
            userVO = sessionObj.getToken();
        }
        return userVO;
    }

    /**
     * Retrieve the id of the user that is currently logged in.
     *
     * @param req HttpServletRequest
     * @return MyAlumniUserContainer
     */
    protected String getCurrentUserId(HttpServletRequest req) {
        MyAlumniUserContainer sessionObj = null;
        HttpSession session = req.getSession(false);
        String memberId = null;
        if (session != null) {
            sessionObj = (MyAlumniUserContainer) session
                    .getAttribute(BaseConstants.USER_CONTAINER);
            memberId = sessionObj.getToken().getMemberId();
        }
        return memberId;
    }

    /**
     * Retrieve the full value of the user that is currently logged in.
     *
     * @param req HttpServletRequest
     * @return MyAlumniUserContainer
     */
    protected String getCurrentUserFullName(HttpServletRequest req) {
        MyAlumniUserContainer sessionObj = null;
        HttpSession session = req.getSession(false);
        String fullName = null;
        if (session != null) {
            sessionObj = (MyAlumniUserContainer) session
                    .getAttribute(BaseConstants.USER_CONTAINER);
            fullName = sessionObj.getToken().getFirstName() + " "
                    + sessionObj.getToken().getLastName();
        }
        return fullName;
    }

    /**
     * Retrieve the username of the user that is currently logged in.
     *
     * @param req HttpServletRequest
     * @return MyAlumniUserContainer
     */
    protected String getLastModifiedBy(HttpServletRequest req) {
        MyAlumniUserContainer sessionObj = null;
        HttpSession session = req.getSession(false);
        String userName = null;
        if (session != null) {
            sessionObj = (MyAlumniUserContainer) session
                    .getAttribute(BaseConstants.USER_CONTAINER);
            userName = sessionObj.getToken().getMemberUserName();
        }
        return userName;
    }

    /**
     * Retrieve the email of the user that is currently logged in.
     *
     * @param req HttpServletRequest
     * @return MyAlumniUserContainer
     */
    protected String getCurrentUserEmail(HttpServletRequest req) {
        MyAlumniUserContainer sessionObj = null;
        HttpSession session = req.getSession(false);
        String email = null;
        if (session != null) {
            sessionObj = (MyAlumniUserContainer) session
                    .getAttribute(BaseConstants.USER_CONTAINER);
            email = sessionObj.getToken().getEmail();
        }
        return email;
    }

    /**
     * Remove a session object based on the request and the attribute label.
     *
     * @param req      HttpServletRequest
     * @param attrName String
     */
    protected void removeSessionObject(HttpServletRequest req, String attrName) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute(attrName);
        }
    }

    /**
     * Store an object in the servlet context scope by its name. This is a convience
     * method.
     *
     * @param attrName
     * @param obj
     */
    protected void setServletContextObject(HttpServletRequest request, String attrName, Object obj) {
        request.getSession().getServletContext().setAttribute(attrName, obj);
    }


    /**
     * Retrieve an object from the servlet context scope by its name. This is a
     * convience method.
     *
     * @param attrName
     */
    protected Object getServletContextObject(HttpServletRequest request, String attrName) {
        return request.getSession().getServletContext().getAttribute(attrName);
    }

    /**
     * @param request        HttpServletRequest
     * @param actionMessages MyAlumniMessages
     */
    protected void saveMessages(HttpServletRequest request,
                                MyAlumniMessages actionMessages) {

        MyAlumniMessages existingMyAlumniMessages = (MyAlumniMessages) request.getAttribute(BaseConstants.MESSAGE_KEY);

        if (existingMyAlumniMessages != null && !existingMyAlumniMessages.isEmpty()) {

            Iterator aeprops = actionMessages.properties();
            while (aeprops.hasNext()) {
                String prop = (String) aeprops.next();

                Iterator msgs = actionMessages.get(prop);
                while (msgs.hasNext()) {
                    MyAlumniMessage am = (MyAlumniMessage) msgs.next();
                    existingMyAlumniMessages.add(prop, am);
                }
            }
            super.saveMessages(request, existingMyAlumniMessages);
        } else {
            super.saveMessages(request, actionMessages);
        }
    }

    /**
     * Get current IP Address
     *
     * @param request
     * @return
     */
    protected String getCurrentIPAddress(final HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    // ---------------------------------------------------------------------------

    /**
     * @param path String
     */
    private static void setupUploadDestDir(final String path) {
        String destDir = FileUtil.fixDir(path);

        // Create sub-directory,
        File f = new File(destDir);

        if (!f.exists()) {
            f.mkdirs();
        }
    }

    // --------------------------------------------------------------------------
    // --
    // -- P U B L I C M E T H O D S
    // --
    // --------------------------------------------------------------------------

    protected MyAlumniMessages validateUploadFile(HttpServletRequest request,
                                                  CommonsMultipartFile uploadedFile, String fileAllowedTypes, int maxFileSize,
                                                  boolean validateHeight, int maxHeight, boolean validateWidth, int maxWidth) {

        MyAlumniMessages msgs = new MyAlumniMessages();

        String fileName = uploadedFile.getOriginalFilename();
        long fileSize = uploadedFile.getSize();


        if (fileName == null || fileName.length() == 0) {
            msgs.add(BaseConstants.WARN_KEY, new MyAlumniMessage("error.notreadable"));
        } else {
            // Check for space in file name
            if (fileName.indexOf(" ") > -1) {
                msgs.add(BaseConstants.WARN_KEY, new MyAlumniMessage(
                        "error.filename", fileName));
            }

            // check for file size
            if (fileSize > maxFileSize) {
                msgs.add(BaseConstants.WARN_KEY, new MyAlumniMessage(
                        "error.filetoobig", String.valueOf(fileSize), String
                        .valueOf(maxFileSize)));
            }

            boolean validExtension = false;
            StringTokenizer st = new StringTokenizer(fileAllowedTypes, ",");
            while (st.hasMoreTokens()) {
                if (uploadedFile.getContentType().equalsIgnoreCase(st.nextToken())) {
                    validExtension = true;
                }
            }

            if (!validExtension) {
                msgs.add(BaseConstants.WARN_KEY, new MyAlumniMessage("error.imageext", SystemConfigConstants.CONTENT_TYPE));
            } else {
                logger.debug(fileName + " ext = " + getFileExtensionForImageReader(fileName));
                Iterator readers = ImageIO.getImageReadersBySuffix(getFileExtensionForImageReader(fileName));
                ImageReader reader = (ImageReader) readers.next();

                try {
                    ImageInputStream iis = ImageIO.createImageInputStream(uploadedFile.getInputStream());
                    reader.setInput(iis, true);
                    int width = reader.getWidth(0);
                    int height = reader.getHeight(0);
                    logger.debug(uploadedFile.getOriginalFilename() + ": width=" + width + ", height=" + height);

                    if (validateHeight) {
                        if (height > maxHeight) {
                            msgs.add(BaseConstants.WARN_KEY, new MyAlumniMessage("error.heightdimensions", height, maxHeight));
                        }
                    }

                    if (validateWidth) {
                        if (width > maxWidth || height > maxHeight) {
                            msgs.add(BaseConstants.WARN_KEY, new MyAlumniMessage("error.widthdimensions", width, maxWidth));
                        }
                    }
                } catch (IOException e) {
                    msgs.add(BaseConstants.FATAL_KEY, new MyAlumniMessage("error.notreadable"));
                }
            }
        }
        return msgs;
    }

    /**
     * returns an instance of the application Properties file
     *
     * @return net.naijatek.core.util.AppProp
     */

    protected AppProp getAppProp() {
        try {
            ap = AppProp.getInstance();
        } catch (Exception ex) {
            logger.error("in getAppProp, exception is thrown - " + ex);
        }
        return ap;
    }

    // --------------------------------------------------------------------------------------

    /**
     * returns an instance of the system Properties file
     *
     * @return net.naijatek.core.util.SystemProp
     */
    protected SystemProp getSysProp() {
        try {
            sysProp = SystemProp.getInstance();
        } catch (Exception ex) {
            logger.error("in getSysProp, exception is thrown - " + ex);
        }
        return sysProp;
    }

    // --------------------------------------------------------------------------------------

    protected static void checkGoodEmail(final String input)
            throws BadInputException {
        if (input == null) {
            throw new BadInputException(
                    "Sorry, null string is not a good email.");
        }
        int atIndex = input.indexOf('@');
        int dotIndex = input.lastIndexOf('.');
        if (atIndex == -1 || dotIndex == -1 || atIndex >= dotIndex) {
            throw new BadInputException("Error: '" + input
                    + "' is not a valid email value. Please try again.");
        }
        // now check for content of the string
        byte[] s = input.getBytes();
        int length = s.length;
        byte b = 0;

        for (int i = 0; i < length; i++) {
            b = s[i];
            if (b >= 'a' && b <= 'z') {
                // lower char
            } else if (b >= 'A' && b <= 'Z') {
                // upper char
            } else if (b >= '0' && b <= '9' && i != 0) {
                // numeric char
            } else if ((b == '_' || b == '-' || b == '.' || b == '@') && i != 0) {
                // _ char
            } else {
                // not good char, throw an BadInputException
                throw new BadInputException(input
                        + " is not a valid email. Reason: character '"
                        + (char) b + "' is not accepted in an email.");
            }
        }// for

        // last check
        try {
            new javax.mail.internet.InternetAddress(input);
        } catch (Exception ex) {
            logger.error("Error when running checkGoodEmail", ex);
            throw new BadInputException(
                    "Assertion: dont want to occur in Util.checkGoodEmail");
        }
    }

    protected String getFileExtensionForImageReader(String fileName) {
        String ret = "jpeg";
        if (fileName != null) {
            File f = new File(fileName);
            String s = f.getName().substring(f.getName().lastIndexOf('.') + 1);

            if (s.equalsIgnoreCase("jpg")) {
                ret = "jpeg";
            } else if (s.equalsIgnoreCase("gif") || s.equalsIgnoreCase("png")) {
                ret = s;
            }
        }
        return ret;
    }

    // ------------------------------------------------------------------------------------------

    protected List<XlatDetailVO> filterMessengers(List<XlatDetailVO> availableMessengers, List<XlatDetailVO> selectedMessengers) {
        List<XlatDetailVO> trimmedAvailableList = new ArrayList<XlatDetailVO>();
        boolean found = false;

        // remove all selected users from available users
        for (XlatDetailVO oneAvailableMessenger : availableMessengers) {

            // take the one selected Messenger and run thru the list of selected Messengers, in order to remove if a match is found
            for (XlatDetailVO oneSelectedMessenger : selectedMessengers) {
                if (oneSelectedMessenger.getLookupCodeId().equals(oneAvailableMessenger.getLookupCodeId())) {  // if match
                    found = true;
                }
            }

            if (!found) {
                if (!trimmedAvailableList.contains(oneAvailableMessenger)) {  // and its not already in the trimmed list
                    trimmedAvailableList.add(oneAvailableMessenger);    // add it to the trimmed list
                }
            }
            found = false;
        }
        return trimmedAvailableList;
    }

    // -------------------------------------------------------------------------------------------


}

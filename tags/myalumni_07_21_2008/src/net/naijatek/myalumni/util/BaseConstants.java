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
package net.naijatek.myalumni.util;


public interface BaseConstants {
    
    
  //==================================================================
//   DO NOT CHANGE ANYTHING UNDER THIS
//==================================================================
  public final static String ADMIN_USERNAME = "sysadmin";
  public final static String GUEST_USERNAME = "guest";
  public final static String ADMIN_USERNAME_ID = "999999999999999999999999999999";
  public final static String GUEST_USERNAME_ID = "888888888888888888888888888888";
  public final static String DEFAULT_DISPLAY_SIZE = "20";


  public final static String MESSAGE = "MESSAGE";
  public final static String LOG_CONTENT = "logContent";


  // Approval Status
  public final static String APPROVE_IT = "APPROVE IT";
  public final static String DECLINE_IT = "DECLINE IT";
  public final static String DEACTIVATE_IT = "DEACTIVATE IT";
  public final static String DELETE_IT = "DELETE IT";
  public final static String MODIFY_IT = "FWD_MODIFY IT";



  // aDmin listing members
  public final static String ADMIN_LIST_ALL = "ADMIN_LIST_ALL";
  public final static String ADMIN_LIST_ONE = "ADMIN_LIST_ONE";

  // SESSION VARIABLES
  public final static String IS_ONLINE = "isOnline";
  public final static String HAS_DORMITORY = "hasDorm";


  // variables used both in the JSP's and the action classes
  public final static String FULL_SEARCH = "fullSearch";
  public final static String FIRST_NAME = "firstName";
  public final static String LAST_NAME = "lastName";
  public final static String GENDER = "gender";
  public final static String MALE = "MALE";
  public final static String FEMALE = "FEMALE";
  public final static String DORMITORY = "dormitory";
  public final static String YEAR_IN = "yearIn";
  public final static String YEAR_OUT = "yearOut";
  public final static String MARRIED_NAME = "marriageName";
  public final static String MAIDEN_NAME = "maidenName";
  public final static String NICK_NAME = "nickName";
  public final static String AVATAR = "avatar";
  public final static String USERNAME = "memberUserName";
  public final static String EMAIL = "email";
  public final static String SYSTEM_ERROR = "systemError";

  // member
  public final static String MEMBER_PROFILE = "profile";

  // Image TagLib Builder types
  public final static String TAGLIB_TYPE_ADVERTISEMENT = "advertisement";
  public final static String TAGLIB_TYPE_AVATAR = "avatar";
  public final static String TAGLIB_TYPE_EDITABLE_AVATAR = "editableavatar";
    public final static String TAGLIB_TYPE_IMAGE = "image";

  public final static String ADMIN_ACTION_DEACTIVATE = "Deactivate Account";
  public final static String ADMIN_ACTION_MODIFY = "Modify Account";
  public final static String ADMIN_ACTION_DELETE = "Delete Account";
  public final static String ADMIN_ACTION_ACTIVATE = "Activate Account";
  public final static String ADMIN_ACTION_LOCK = "Lock Account";

  
   //--------------------------------------------------------------------------------------------
   //---------------------------------- MYALUMNI CONSTANTS --------------------------------------
   //--------------------------------------------------------------------------------------------
   
  public static final String CONTACT_SUBJECT = " would like to make contact.";  
  public static final String ORGANIZATION_NAME = "ORGANIZATION_NAME";
  public static final String ORGANIZATION_SHORT_NAME = "ORGANIZATION_SHORT_NAME";
  public static final String ORG_EMAIL = "ORG_EMAIL";
  public static final String ORGANIZATION_ABOUTUS = "ORGANIZATION_ABOUTUS";
  public static final String ALBUM_URL = "ALBUM_URL";
  public static final String FORUM_URL = "FORUM_URL";
  public static final String SERVER_URL = "SERVER_URL";
  public static final String FIRST_STARTUP = "FIRST_STARTUP";
  
  
  
  /**
   * ACTION FORWARDS
   */
  public final static String FWD_MODIFY = "modify";
  public final static String FWD_LOGIN = "login";
  public final static String FWD_ADMIN_LOGIN = "adminLogin";
  public final static String FWD_LIST_DETAILS = "list_details";
  public final static String FWD_LIST_MINI = "mini";
  public final static String FWD_ALL_MEMBERS = "FWD_ALL_MEMBERS";
  public final static String FWD_ACTIVATE_ACCOUNT = "activate_account";
  public static final String FWD_SUCCESS = "success";
  public static final String FWD_INVALID_TOKEN = "token";    
  public static final String FWD_EXPIRED_PASSWORD = "promptChange";
  public static final String FWD_CANCEL = "cancel";
  public static final String FWD_MEMBER = "memberErrorPage";
  public static final String FWD_ADMIN = "adminErrorPage";
  public static final String FWD_DASHBOARD_MODULE = "dashboard";
  public static final String FWD_MEMBERS_MODULE = "members";
  public static final String FWD_SECURITY_MODULE = "security";
  public static final String FWD_GENERAL_MODULE = "general";
  public static final String FWD_SYSTEM_MODULE = "system";
  public static final String FWD_ADMIN_MODULE = "admin";
  
  public static final String SC_INSURFICIENT_PRIV_700 = "700";
  public static final String SC_SESSION_EXPIRED_701 = "701";

   // Gender
   public final static String GENDER_MALE = "M";
   public final static String GENDER_FEMALE = "F";
   public final static String GENDER_UNKNOWN = "U";
   
   // Boolean
   public final static String BOOLEAN_YES = "Y";
   public final static String BOOLEAN_NO = "N";
   
   // Common user actions.
   public static final String ADDED = "A";
   public static final String UPDATED = "U";
   public static final String SOFT_DELETED = "D";
   
   // MEMBER Account Status
   public final static String ACCOUNT_ACTIVE = "A";  // active member
   public final static String ACCOUNT_LOCKED = "L";  // member locked from system
   public final static String ACCOUNT_UNAPPROVED = "U"; // registered by the member, but unapproved yet
   public final static String ACCOUNT_DEACTIVATED = "D";  // member account deavtivated
 
   // message priorities
   public final static String PRIORITY_NORMAL = "N";
   public final static String PRIORITY_HIGH = "H";
   public final static String PRIORITY_LOW = "L";

   // Mail Folders
   public final static String FOLDER_INBOX = "Inbox" ;
   public final static String FOLDER_SENT = "Sent" ;
   public final static String FOLDER_TRASH = "Trash" ;
   public final static String FOLDER_STORAGE = "Storage" ;
   public final static String MESSAGE_CENTER = "MESSAGE_CENTER";
   
   // Mail status
   public final static String PM_STATUS_DELETED = "D";
   public final static String PM_STATUS_NEW = "N";
   public final static String PM_STATUS_READ = "R";
   
   // Constants
   public static final String USER_CONTAINER = "USER_CONTAINER";
   public static final String LOGIN_FAIL = "F";
   public static final String LOGIN_PASS = "P";
   public static final String INFO_KEY = "INFO_KEY";
   public static final String WARN_KEY = "WARN_KEY";
   public static final String ERROR_KEY = "ERROR_KEY";
   public static final String FATAL_KEY = "FATAL_KEY";
   
   // Lists
   public static final String LIST_OF_MEMBERS = "LIST_OF_MEMBERS";
   public static final String LIST_OF_ACCESS_HISTORY = "listOfAccessHistory";
   public static final String LIST_OF_CLASSNEWS = "listOfClassNews";
   public static final String LIST_OF_REMINISCE = "listOfReminisce";
   public static final String LIST_OF_ROLES = "LIST_OF_ROLES";
   public static final String LIST_OF_USERS_LOGIN_HISTORY = "listOfUserLoginHistory";
   public static final String LIST_OF_USERS = "listOfUsers";
   public static final String LIST_OF_USERS_COUNT = "listOfUsersCount";
   public static final String OBJECT_VO = "ObjectVO"; 
   public static final String SCROLL_VO = "luScrollVO";
   public static final String LIST_OF_SCROLLS = "luAllScrolls";
   public static final String LIST_OF_OBJECT_VO = "listOfObjectVO";
   public static final String ASSIGN_ROLE_AVAILABLE_USERS = "luAssignRoleAvailableUsers";
   public static final String ASSIGN_ROLE_SELECTED_USERS = "luAssignRoleSelectedUsers";
   public static final String LIST_OF_ERROR_LOGS = "listOfErrorLogs";   
   public static final String LIST_OF_CODES_FROM_GROUP = "listOfLookupCodes";
   public static final String LIST_OF_LOOKUP_GROUPS = "listOfLookupGroups"; 
   public static final String LIST_OF_ADMIN_SEARCH_OPTIONS = "luAdminSearchCategory";
   public static final String LIST_OF_MEMBER_SEARCH_OPTIONS = "luSearchCategory";
   public static final String LIST_OF_GENDER_OPTIONS = "luGender";
   public static final String LIST_OF_ADMIN_OPTIONS  = "luAdminAction";
   public static final String LIST_OF_APPROVAL_OPTIONS  = "luApprovalAction";
   public static final String LIST_OF_IPHONE_MEMBERS = "listOfIphoneMembers";
   public static final String IPHONE_MEMBER_PROFILE = "profile";
   public static final String LIST_OF_IPHONE_DORMS = "listOfIphoneDorms";
   
   
   // ROLES
   public static final String IS_ADMIN = "isAdmin";
   
   
   // Modules
   public static final String ADMIN_MODULE = "ADM";
   public static final String MEMBER_MODULE = "MEM";
   public static final String GUEST_MODULE = "GST";
   
   
	
	// Drop Down Menu Constants
	public static final String SERVICE_XLAT_LOOKUP = "xlatService";
	public static final String SERVICE_CLASSNEWS_LOOKUP = "classNewsService";
	public static final String SERVICE_MEMBER_LOOKUP = "memberService";
	public static final String SERVICE_SYSTEM_TASK_LOOKUP = 	"systemTaskService";
	public static final String SERVICE_ERRORLOGGER_LOOKUP = 	"errorLoggerService";
	public static final String SERVICE_SYSTEM_CONFIG = "systemConfigService";
	
	
	
	
	public static final String DROPDOWN_MEMBER = "luMember" ;
	public static final String DROPDOWN_TYPE_GENERIC = "luGeneric" ;
	public static final String DROPDOWN_TYPE_SPECIFIC = "luSpecific";
	public static final String DROPDOWN_YEARIN = "YEARIN";
	public static final String DROPDOWN_YEAROUT = "YEAROUT";
	public static final String DROPDOWN_CLASSYEAR = "CLASSYEAR";
	
	
	// Date Time Format
    public static final String LOOKUP_DATE_FORMAT_PATTERN = "dateFormatPattern";
    public static final String LOOKUP_DATE_TIME_FORMAT_PATTERN = "dateTimeFormatPattern";
    

    
    // Active and Inactive status    
    public static final String ACTIVE = "A";
    public static final String INACTIVE = "I";
    public static final String APPROVAL_NEEDED = "X";
        
    // Lookups
    public static final String LU_AVAILABLE_IMS = "luAvailableIMs";
    public static final String LU_SELECTED_IMS = "luSelectedIMs";
    public static final String LOOKUP_STATUS = "luStatus";
    public static final String LOOKUP_ACCOUNT_STATUS = "luAccountStatus";
    
    
    // Lookup Groups
    public static final String GROUP_INSTANT_MESSENGERS = "IMS";
    public static final String GROUP_CAREER = "CAR";
    public static final String GROUP_DEGREE = "DEG";
    public static final String GROUP_COLOR = "COL";
    public static final String GROUP_TITLE = "TIT";
    public static final String GROUP_COUNTRY = "COU";
  
    // Folder Names
    public static final String UPLOAD_DIR_NAME="upload";
    public static final String ADS_UPLOAD_DIR_NAME="ads";
    public static final String AVATAR_UPLOAD_DIR_NAME="memberavatars";    

	
	
   
}

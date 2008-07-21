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
package net.naijatek.myalumni.framework.struts;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import net.naijatek.myalumni.framework.exceptions.BadInputException;
import net.naijatek.myalumni.modules.admin.presentation.form.SystemConfigForm;
import net.naijatek.myalumni.modules.common.presentation.form.ClassNewsForm;
import net.naijatek.myalumni.modules.common.presentation.form.LoginForm;
import net.naijatek.myalumni.modules.common.presentation.form.MemberForm;
import net.naijatek.myalumni.modules.common.presentation.form.PrivateMessageForm;
import net.naijatek.myalumni.modules.common.presentation.form.ReminisceForm;
import net.naijatek.myalumni.modules.common.presentation.form.ScrollForm;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.utilities.AppProp;
import net.naijatek.myalumni.util.utilities.ParamUtil;
import net.naijatek.myalumni.util.utilities.StringUtil;
import net.naijatek.myalumni.util.utilities.SystemProp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.ValidatorAction;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;

public class MyAlumniValidator extends MyAlumniDispatchAction implements
		Serializable {

	private static Log logger = LogFactory.getLog(MyAlumniValidator.class);

	/**
	 * System Properties
	 */
	SystemProp sysProp = SystemProp.getInstance();

	/**
	 * Application Properties
	 */
	AppProp appProp = AppProp.getInstance();

	// --------------------------------------------------------------------------
	// --
	// -- P U B L I C M E T H O D S
	// --
	// --------------------------------------------------------------------------

	
	
	/**
	 * 
	 */
	public boolean orgFirstYear(Object bean,
			ValidatorAction va, Field field, ActionMessages msgs,
			HttpServletRequest request) {

		SystemConfigForm sysConfigForm = (SystemConfigForm)bean;
		
		String orgYear = sysConfigForm.getOrgFirstYear();
		int intOrgYear = Integer.parseInt(orgYear);
		
        GregorianCalendar ct = new GregorianCalendar();
        int currentYear = ct.get(Calendar.YEAR);		

		if (intOrgYear > currentYear) {			
				msgs.add(field.getKey(), new ActionMessage("error.orgfirstyear", String.valueOf(currentYear)));
				saveErrors(request, msgs);
		}
		return msgs.isEmpty();
	}	
	
	
	/**
	 * Compares both the password and confirmation password be the same
	 * 
	 * @param bean
	 *            Object
	 * @param va
	 *            ValidatorAction
	 * @param field
	 *            Field
	 * @param msgs
	 *            ActionMessages
	 * @param request
	 *            HttpServletRequest
	 * @return boolean
	 */
	public boolean common_compareLoginPasswords(Object bean,
			ValidatorAction va, Field field, ActionMessages msgs,
			HttpServletRequest request) {

		LoginForm loginForm = (LoginForm)bean;
		
		String memberPassword = loginForm.getMemberPassword();
		String memberPasswordConfirm = loginForm.getMemberPasswordConfirm();;

		if (memberPassword != null && memberPassword.length() > 0
				&& memberPasswordConfirm != null
				&& memberPasswordConfirm.length() > 0) {
			if (!memberPassword.equals(memberPasswordConfirm)) {
				msgs.add(field.getKey() + "1", new ActionMessage(
						"errors.password.notequal"));
				saveErrors(request, msgs);
			}
		}
		return msgs.isEmpty();
	}

	/**
	 * Compares the Primary Email and confirmation email and validates them
	 * to_email be the same
	 * 
	 * @param bean
	 *            Object
	 * @param va
	 *            ValidatorAction
	 * @param field
	 *            Field
	 * @param messages
	 *            ActionMessages
	 * @param request
	 *            HttpServletRequest
	 * @return boolean
	 */
	public boolean compareEmails(Object bean, ValidatorAction va, Field field,
			ActionMessages messages, HttpServletRequest request) {

		MemberForm memForm = (MemberForm)bean;
		String email = memForm.getEmail();
		String emailConfirm = memForm.getEmailConfirm();

		if (email != null && email.length() > 0 && emailConfirm != null
				&& emailConfirm.length() > 0) {
			if (!email.equals(emailConfirm)) {
				messages.add(field.getKey(), Resources.getActionMessage(
						request, va, field));
			}
		}
		return messages.isEmpty();
	}

	// ================================================================================================
	/**
	 * Compares the Arrival year and departure years. Makes sure that the
	 * departure year is not earlier than the arrival year
	 * 
	 * @param bean
	 *            Object
	 * @param va
	 *            ValidatorAction
	 * @param field
	 *            Field
	 * @param messages
	 *            ActionMessages
	 * @param request
	 *            HttpServletRequest
	 * @return boolean
	 */
	public boolean compareYear(Object bean, ValidatorAction va, Field field,
			ActionMessages messages, HttpServletRequest request) {
		int intArrival = 0;
		int intDepart = 0;
		MemberForm memForm = (MemberForm)bean;
		
		String strArrival = StringUtil.safeString(memForm.getYearIn());
		String strDepart = StringUtil.safeString(memForm.getYearOut());

		if (strArrival.length() == 0) {
			messages.add(field.getKey(), new ActionMessage("messages.required",
					"Arrival Year"));
		}

		if (strDepart.length() == 0) {
			messages.add(field.getKey(), new ActionMessage("messages.required",
					"Departure Year"));
		}

		if (!messages.isEmpty()) {
			messages.isEmpty();
		}

		if (strArrival.length() == 0 && strDepart.length() == 0) {
			intArrival = Integer.parseInt(strArrival);
			intDepart = Integer.parseInt(strDepart);
		}

		if (intDepart < intArrival) {
			messages.add(field.getKey(), Resources.getActionMessage(request,
					va, field));
		}
		return messages.isEmpty();
	}

	// ================================================================================================
	
	/**
	 * Compares the FROM year and TO years. Makes sure that the
	 * TO year is not earlier than the FROM year
	 * 
	 * @param bean
	 *            Object
	 * @param va
	 *            ValidatorAction
	 * @param field
	 *            Field
	 * @param messages
	 *            ActionMessages
	 * @param request
	 *            HttpServletRequest
	 * @return boolean
	 */
	public boolean compareClassNewsYear(Object bean, ValidatorAction va, Field field,
			ActionMessages messages, HttpServletRequest request) {

		ClassNewsForm classForm = (ClassNewsForm)bean;

		int fromClassYear = 0;
		int toClassYear = 0;
		
		String strFromClassYear = StringUtil.safeString(classForm.getFromClassYear());
		String strToClassYear = StringUtil.safeString(classForm.getToClassYear());


		fromClassYear = Integer.parseInt(strFromClassYear);
		toClassYear = Integer.parseInt(strToClassYear);


		if (toClassYear < fromClassYear) {
			messages.add(field.getKey(), Resources.getActionMessage(request,
					va, field));		
		}
		
		return messages.isEmpty();
	}

	// ================================================================================================
	
	/**
	 * Compares the FROM year and TO years. Makes sure that the
	 * TO year is not earlier than the FROM year
	 * 
	 * @param bean
	 *            Object
	 * @param va
	 *            ValidatorAction
	 * @param field
	 *            Field
	 * @param messages
	 *            ActionMessages
	 * @param request
	 *            HttpServletRequest
	 * @return boolean
	 */
	public boolean compareReminisceYear(Object bean, ValidatorAction va, Field field,
			ActionMessages messages, HttpServletRequest request) {

		ReminisceForm reminisceForm = (ReminisceForm)bean;

		int fromClassYear = 0;
		int toClassYear = 0;
		
		String strFromClassYear = StringUtil.safeString(reminisceForm.getFromYear());
		String strToClassYear = StringUtil.safeString(reminisceForm.getToYear());


		fromClassYear = Integer.parseInt(strFromClassYear);
		toClassYear = Integer.parseInt(strToClassYear);


		if (toClassYear < fromClassYear) {
			messages.add(field.getKey(), Resources.getActionMessage(request,
					va, field));		
		}
		
		return messages.isEmpty();
	}

	// ================================================================================================	
	/**
	 * Make use the user selects a valid name as a username
	 * 
	 * @param bean
	 *            Object
	 * @param va
	 *            ValidatorAction
	 * @param field
	 *            Field
	 * @param messages
	 *            ActionMessages
	 * @param request
	 *            HttpServletRequest
	 * @return boolean
	 */
	public boolean checkGoodName(Object bean, ValidatorAction va, Field field,
			ActionMessages messages, HttpServletRequest request) {

		MemberForm memForm = (MemberForm)bean;
		String username = memForm.getMemberUserName();


		try {
			StringUtil.checkGoodName(username);
		} catch (BadInputException e) {
			messages.add(field.getKey(), Resources.getActionMessage(request,
					va, field));
			logger.error(e.getMessage());
		}

		String unActivatePattern = sysProp.getValue("DEFAULT_USERNAME_PATTERN");

		boolean validName = true;
		StringTokenizer st = new StringTokenizer(unActivatePattern, ",");
		while (st.hasMoreTokens()) {
			if (username.startsWith(st.nextToken())) {
				validName = false;
			}
		}

		if (!validName) {
			messages.add(field.getKey(), Resources.getActionMessage(request,
					va, field));
		}

		return messages.isEmpty();
	}

	// ================================================================================================
	/**
	 * Validate a URL
	 * 
	 * @param bean
	 *            Object
	 * @param va
	 *            ValidatorAction
	 * @param field
	 *            Field
	 * @param messages
	 *            ActionMessages
	 * @param request
	 *            HttpServletRequest
	 * @return boolean
	 */
	public boolean validateUrlHomePage(Object bean, ValidatorAction va,
			Field field, ActionMessages messages, HttpServletRequest request) {

		MemberForm memForm = (MemberForm)bean;
		String url = memForm.getWebsite();

		try {
			if (url != null && url.length() > 0) {
				ParamUtil.getParameterUrl(url);
			}
		} catch (BadInputException e) {
			messages.add(field.getKey(), Resources.getActionMessage(request,
					va, field));
		}

		return messages.isEmpty();
	}

	// ================================================================================================
	/**
	 * Validate a URL
	 * 
	 * @param bean
	 *            Object
	 * @param va
	 *            ValidatorAction
	 * @param field
	 *            Field
	 * @param messages
	 *            ActionMessages
	 * @param request
	 *            HttpServletRequest
	 * @return boolean
	 */
	public boolean validateUrlCoolLink1(Object bean, ValidatorAction va,
			Field field, ActionMessages messages, HttpServletRequest request) {

		MemberForm memForm = (MemberForm)bean;
		String url = memForm.getFavUrl2();

		try {
			if (url != null && url.length() > 0) {
				ParamUtil.getParameterUrl(url);
			}
		} catch (BadInputException e) {
			messages.add(field.getKey(), Resources.getActionMessage(request,
					va, field));
		}

		return messages.isEmpty();
	}

	// ================================================================================================
	/**
	 * Validate a URL
	 * 
	 * @param bean
	 *            Object
	 * @param va
	 *            ValidatorAction
	 * @param field
	 *            Field
	 * @param messages
	 *            ActionMessages
	 * @param request
	 *            HttpServletRequest
	 * @return boolean
	 */
	public boolean validateUrlCoolLink2(Object bean, ValidatorAction va,
			Field field, ActionMessages messages, HttpServletRequest request) {

		MemberForm memForm = (MemberForm)bean;
		String url = memForm.getFavUrl2();

		try {
			if (url != null && url.length() > 0) {
				ParamUtil.getParameterUrl(url);
			}
		} catch (BadInputException e) {
			messages.add(field.getKey(), Resources.getActionMessage(request,
					va, field));
		}

		return messages.isEmpty();
	}

	// ================================================================================================
	/**
	 * Makes sure if the maiden name is entered, that the gender is Female
	 * 
	 * @param bean
	 *            Object
	 * @param va
	 *            ValidatorAction
	 * @param field
	 *            Field
	 * @param messages
	 *            ActionMessages
	 * @param request
	 *            HttpServletRequest
	 * @return boolean
	 */
	public boolean genderMaiden(Object bean, ValidatorAction va, Field field,
			ActionMessages messages, HttpServletRequest request) {

		MemberForm memForm = (MemberForm)bean;
		
		String gender = memForm.getGender();
		String maiden = memForm.getMaidenName();

		if (maiden != null && maiden.length() > 0) {
			if (gender != null && gender.length() > 0 && gender.equals(BaseConstants.GENDER_MALE)) {
				messages.add(field.getKey(), Resources.getActionMessage(
						request, va, field));
			}
		}

		return messages.isEmpty();
	}

	// --------------------------------------------------------------------------------------
	/**
	 * Compares both the password and confirmation password to_email be the same
	 * 
	 * @param bean
	 *            Object
	 * @param va
	 *            ValidatorAction
	 * @param field
	 *            Field
	 * @param messages
	 *            ActionMessages
	 * @param request
	 *            HttpServletRequest
	 * @return boolean
	 */
	public boolean comparePasswordReset(Object bean, ValidatorAction va,
			Field field, ActionMessages messages, HttpServletRequest request) {
		MemberForm memForm = (MemberForm)bean;
		
		String password = memForm.getMemberPassword();
		String passwordConfirm =  memForm.getMemberPasswordConfirm();

		if (password != null && password.length() > 0
				&& passwordConfirm != null && passwordConfirm.length() > 0) {
			if (!password.equals(passwordConfirm)) {
				messages.add(field.getKey(), Resources.getActionMessage(
						request, va, field));
			}
		}
		return messages.isEmpty();
	}

	// ================================================================================================

	/**
	 * Compares both the password and confirmation password to_email be the same
	 * 
	 * @param bean
	 *            Object
	 * @param va
	 *            ValidatorAction
	 * @param field
	 *            Field
	 * @param messages
	 *            ActionMessages
	 * @param request
	 *            HttpServletRequest
	 * @return boolean
	 */
	public boolean searchCategory(Object bean, ValidatorAction va, Field field,
			ActionMessages messages, HttpServletRequest request) {
		boolean condition = true;
		
		MemberForm memForm = (MemberForm)bean;
		String searchCategory = StringUtil.safeString(memForm.getSearchCategory());
		String userName = StringUtil.safeString(memForm.getMemberUserName());		
		String firstName = StringUtil.safeString(memForm.getFirstName());
		String lastName = StringUtil.safeString(memForm.getLastName());
		String dorm = StringUtil.safeString(memForm.getDormitoryId());
		String gender = StringUtil.safeString(memForm.getGender());
		String yearIn = StringUtil.safeString(memForm.getYearIn());
		String yearOut = StringUtil.safeString(memForm.getYearOut());
		String marriageName = StringUtil.safeString(memForm.getLastName());
		String nickName = StringUtil.safeString(memForm.getNickName());
		String maidenName = StringUtil.safeString(memForm.getMaidenName());

		if (searchCategory.equalsIgnoreCase(BaseConstants.FULL_SEARCH)) {
			if (firstName.length() == 0 && lastName.length() == 0
					&& dorm.length() == 0 && gender.length() == 0
					&& yearIn.length() == 0 && yearOut.length() == 0
					&& marriageName.length() == 0 && nickName.length() == 0/*
																			 * &&
																			 * maidenName.length() ==
																			 * 0
																			 */) {

				condition = false;
			}
		} else if (searchCategory.equalsIgnoreCase(BaseConstants.FIRST_NAME)) {
			if (firstName.length() == 0) {
				condition = false;
			}
		} else if (searchCategory.equalsIgnoreCase(BaseConstants.USERNAME)) {
			if (userName.length() == 0) {
				condition = false;
			}
		}else if (searchCategory.equalsIgnoreCase(BaseConstants.LAST_NAME)) {
			if (lastName.length() == 0) {
				condition = false;
			}
		} else if (searchCategory.equalsIgnoreCase(BaseConstants.DORMITORY)) {
			if (dorm.length() == 0) {
				condition = false;
			}
		} else if (searchCategory.equalsIgnoreCase(BaseConstants.YEAR_IN)) {
			if (yearIn.length() == 0) {
				condition = false;
			}
		} else if (searchCategory.equalsIgnoreCase(BaseConstants.YEAR_OUT)) {
			if (yearOut.length() == 0) {
				condition = false;
			}
		} else if (searchCategory.equalsIgnoreCase(BaseConstants.NICK_NAME)) {
			if (nickName.length() == 0) {
				condition = false;
			}
		} else if (searchCategory.equalsIgnoreCase(BaseConstants.MARRIED_NAME)) {
			if (marriageName.length() == 0) {
				condition = false;
			}
		} else if (searchCategory.equalsIgnoreCase(BaseConstants.MAIDEN_NAME)) {
			if (maidenName.length() == 0) {
				condition = false;
			}
		} else if (searchCategory.equalsIgnoreCase(BaseConstants.GENDER)) {
			if (gender.length() == 0) {
				condition = false;
			}
		}

		if (!condition) {
			messages.add(field.getKey(), Resources.getActionMessage(request,
					va, field));
		}

		return messages.isEmpty();
	}

	// --------------------------------------------------------------------------------------
	public boolean requiresDeleteId(Object bean, ValidatorAction va,
			Field field, ActionMessages messages, HttpServletRequest request) {
		String[] mailArray = new String[0];
		
		PrivateMessageForm pmForm = (PrivateMessageForm)bean;
		//mailArray = request.getParameterValues(appProp.getValue("var.privMsgsId"));  messageId
		// TODO: find a way not to hard code this variable, add string[] to form
		mailArray = request.getParameterValues("messageId");  

		if (mailArray == null) {
			messages.add(field.getKey(), Resources.getActionMessage(request,
					va, field));
		}
		return messages.isEmpty();
	}

	// ---------------------------------------------------------------------------------------

	/**
	 * 
	 * @param bean
	 *            Object
	 * @param va
	 *            ValidatorAction
	 * @param field
	 *            Field
	 * @param messages
	 *            ActionMessages
	 * @param request
	 *            HttpServletRequest
	 * @return boolean
	 */
	public boolean memberComments(Object bean, ValidatorAction va, Field field,
			ActionMessages messages, HttpServletRequest request) {
		int maxLength = 0;
		int memberComment = 0;

		MemberForm memForm = (MemberForm)bean;
		maxLength = Integer.parseInt((String) appProp.getValue("comments.length"));
		memberComment = memForm.getComments().length();

		if (maxLength < memberComment) {
			messages.add(field.getKey(), Resources.getActionMessage(request,
					va, field));
		}
		return messages.isEmpty();
	}

	// ---------------------------------------------------------------------------------------

	/**
	 * 
	 * @param bean
	 *            Object
	 * @param va
	 *            ValidatorAction
	 * @param field
	 *            Field
	 * @param messages
	 *            ActionMessages
	 * @param request
	 *            HttpServletRequest
	 * @return boolean
	 */
	public boolean adminComments(Object bean, ValidatorAction va, Field field,
			ActionMessages messages, HttpServletRequest request) {
		int maxLength = 0;
		int adminComment = 0;
		
		MemberForm memForm = (MemberForm)bean;
		maxLength = Integer.parseInt((String) appProp.getValue("comments.length"));
		adminComment = memForm.getAdminComments().length();

		if (maxLength < adminComment) {
			messages.add(field.getKey(), Resources.getActionMessage(request,va, field));
		}
		return messages.isEmpty();
	}

	// ---------------------------------------------------------------------------------------

	/**
	 * 
	 * @param bean
	 *            Object
	 * @param va
	 *            ValidatorAction
	 * @param field
	 *            Field
	 * @param messages
	 *            ActionMessages
	 * @param request
	 *            HttpServletRequest
	 * @return boolean
	 */
	public boolean signature(Object bean, ValidatorAction va, Field field,
			ActionMessages messages, HttpServletRequest request) {

		int maxLength = 0;
		int memberSignature = 0;
		MemberForm memForm = (MemberForm)bean;
		maxLength = Integer.parseInt((String) appProp
				.getValue("signature.length"));
		memberSignature = memForm.getSignature().length();

		if (maxLength < memberSignature) {
			messages.add(field.getKey(), Resources.getActionMessage(request,
					va, field));
		}
		return messages.isEmpty();

	}

	// ---------------------------------------------------------------------------------------

	/**
	 * 
	 * @param bean
	 *            Object
	 * @param va
	 *            ValidatorAction
	 * @param field
	 *            Field
	 * @param messages
	 *            ActionMessages
	 * @param request
	 *            HttpServletRequest
	 * @return boolean
	 */
	public boolean goodNameCheck(Object bean, ValidatorAction va, Field field,
			ActionMessages messages, HttpServletRequest request) {

		MemberForm memForm = (MemberForm)bean;
		
		String username = memForm.getMemberUserName();

		try {
			StringUtil.checkGoodName(username);
		} catch (BadInputException e) {
			messages.add(field.getKey(), Resources.getActionMessage(request,
					va, field));
			logger.error(e.getMessage());
		}

		String unActivatePattern = sysProp.getValue("DEFAULT_USERNAME_PATTERN");

		boolean validName = true;
		StringTokenizer st = new StringTokenizer(unActivatePattern, ",");
		while (st.hasMoreTokens()) {
			if (username.startsWith(st.nextToken())) {
				validName = false;
			}
		}

		if (!validName) {
			messages.add(field.getKey(), Resources.getActionMessage(request,
					va, field));
		}

		return messages.isEmpty();

	}

	// ---------------------------------------------------------------------------------------

	/**
	 * 
	 * @param bean
	 *            Object
	 * @param va
	 *            ValidatorAction
	 * @param field
	 *            Field
	 * @param messages
	 *            ActionMessages
	 * @param request
	 *            HttpServletRequest
	 * @return boolean
	 */
	public boolean migrationCheck(Object bean, ValidatorAction va, Field field,
			ActionMessages messages, HttpServletRequest request) {

		MemberForm memForm = (MemberForm)bean;
		
		String username = memForm.getMemberUserName();
		String unActivatePattern = sysProp.getValue("DEFAULT_USERNAME_PATTERN");

		boolean validName = true;
		StringTokenizer st = new StringTokenizer(unActivatePattern, ",");
		while (st.hasMoreTokens()) {
			if (username.startsWith(st.nextToken())) {
				validName = false;
			}
		}

		if (!validName) {
			messages.add(field.getKey(), Resources.getActionMessage(request,
					va, field));
		}

		return messages.isEmpty();

	}

	// --------------------------------------------------------------------------

	public boolean emailWebmasterEmailCheck(Object bean, ValidatorAction va,
			Field field, ActionMessages messages, HttpServletRequest request) {

		PrivateMessageForm pmForm = (PrivateMessageForm)bean;
		
		String email = pmForm.getGuestEmail();
		String memberFromUserId = pmForm.getMessageFromUserId();
		email = StringUtil.safeString(email);

		if (memberFromUserId.equals(BaseConstants.GUEST_USERNAME_ID)) {
			if (email.length() == 0) {
				messages.add(field.getKey(), Resources.getActionMessage(request, va, field));
			}
		}

		return messages.isEmpty();

	}

	// -------------------------------------------------------------------------------------------------

	public boolean adminMaintainScroll(Object bean, ValidatorAction va,
			Field field, ActionMessages messages, HttpServletRequest request) {

		ScrollForm scrollForm = (ScrollForm)bean;
		String scrollType = StringUtil.safeString(scrollForm.getType());
		String scrollText = StringUtil.safeString(scrollForm.getScrollText());
		String scrollid = StringUtil.safeString(scrollForm.getScrollId());

		if (!scrollType.equalsIgnoreCase("list")) {
			if (scrollType.equalsIgnoreCase("new") && scrollText.equalsIgnoreCase("")) {
				messages.add(field.getKey(), Resources.getActionMessage( request, va, field));
			} else if (scrollType.equalsIgnoreCase("update") && scrollid.equalsIgnoreCase("")) {
				messages.add(field.getKey(), Resources.getActionMessage( request, va, field));
			}
		}
		return messages.isEmpty();
	}

	/**
	 * Compares both the password and confirmation password to_email be the same
	 * 
	 * @param bean
	 *            Object
	 * @param va
	 *            ValidatorAction
	 * @param field
	 *            Field
	 * @param messages
	 *            ActionMessages
	 * @param request
	 *            HttpServletRequest
	 * @return boolean
	 */
	public boolean comparePassword(Object bean, ValidatorAction va,
			Field field, ActionMessages messages, HttpServletRequest request) {

		// SystemProp sysprop = SystemProp.getInstance();
		// int maxlength =
		// Integer.parseInt(sysprop.getValue("password.maxlenght"));
		// int minlength =
		// Integer.parseInt(sysprop.getValue("password.minlenght"));

		MemberForm memForm = (MemberForm)bean;
		
		String newpassword = memForm.getMemberPassword();		
		String newpasswordConfirm = memForm.getMemberPasswordConfirm();

		if (!newpassword.equals(newpasswordConfirm)) {
			messages.add(BaseConstants.WARN_KEY, new ActionMessage("errors.password.notequal"));
			saveMessages(request, messages);
		}
		// }
		return messages.isEmpty();
	}

	/**
	 * ----------------------------------------------
	 * 
	 * P R I V A T E M E T H O D S
	 * 
	 * -------------------------------------------------
	 */

	protected boolean validatePasswordMinLenght(final String password) {
		boolean status = true;
		SystemProp sysprop = SystemProp.getInstance();

		try {
			int minlength = Integer.parseInt(sysprop
					.getValue("password.minlenght"));

			if (password.length() < minlength) {
				status = false;
			}
		} catch (NumberFormatException e) {
			status = false;
		}
		return status;
	}

	protected boolean validatePasswordMaxLenght(final String password) {
		boolean status = true;
		SystemProp sysprop = SystemProp.getInstance();

		try {
			int maxlength = Integer.parseInt(sysprop
					.getValue("password.maxlenght"));

			if (password.length() > maxlength) {
				status = false;
			}
		} catch (NumberFormatException e) {
			status = false;
		}
		return status;
	}
	
	
}

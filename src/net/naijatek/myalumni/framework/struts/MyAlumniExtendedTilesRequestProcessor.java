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

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.presentation.action.ErrorAction;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.SystemConfigConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.util.RequestUtils;
import org.springframework.web.struts.DelegatingTilesRequestProcessor;

/**
 * A customized RequestProcessor that checks the user's preferred Locale from
 * the request each time. If a Locale is not in the session or the one in the
 * session doesn't match the request, the Locale in the request is set to the
 * session.
 * 
 */
public class MyAlumniExtendedTilesRequestProcessor extends
		DelegatingTilesRequestProcessor {

	private static Log logger = LogFactory.getLog(ErrorAction.class);

	/**
	 * Ensures the user locale is identified and stored in the session as
	 * appropriate.
	 * 
	 * @param request
	 *            The servlet request we are processing.
	 * @param response
	 *            The servlet response we are creating.
	 */
	protected void processLocale(HttpServletRequest request,
			HttpServletResponse response) {

		// Are we configured to select the Locale automatically?
		if (!moduleConfig.getControllerConfig().getLocale()) {
			return;
		}

		// Get the Locale (if any) that is stored in the user's session
		HttpSession session = request.getSession();
		Locale sessionLocale = (Locale) session
				.getAttribute(Globals.LOCALE_KEY);

		// Get the user's preferred Locale from the request
		Locale requestLocale = request.getLocale();

		// If there was never a Locale in the session or it has changed, set it
		if (sessionLocale == null || (sessionLocale != requestLocale)) {
			if (logger.isDebugEnabled()) {
				logger.debug(" Setting user locale '" + requestLocale + "'");
			}
			// Set the new Locale into the user's session
			session.setAttribute(Globals.LOCALE_KEY, requestLocale);
			session.setAttribute("localeKey", requestLocale.getLanguage()); // for
																			// displaytag
		}
	}

	/**
	 * Populate the properties of the specified ActionForm instance from the
	 * request parameters included with this request. In addition, it updates
	 * the cached copy of the current data object bean in the session with the
	 * new actionform clone.
	 * 
	 * @param request
	 *            The servlet request we are processing
	 * @param response
	 *            The servlet response we are creating
	 * @param form
	 *            The ActionForm instance we are populating
	 * @param mapping
	 *            The ActionMapping we are using
	 * 
	 * @exception ServletException
	 *                if thrown by RequestUtils.populate()
	 */
	protected void processPopulate(HttpServletRequest request,
			HttpServletResponse response, ActionForm form, ActionMapping mapping)
			throws ServletException {

		if (form == null) {
			return;
		}

		// Populate the bean properties of this ActionForm instance
		if (logger.isDebugEnabled()) {
			logger.debug(" Populating bean properties from this request");
		}
		form.setServlet(this.servlet);
		form.reset(mapping, request);
		if (mapping.getMultipartClass() != null) {
			request.setAttribute(Globals.MULTIPART_KEY, mapping
					.getMultipartClass());
		}
		RequestUtils.populate(form, mapping.getPrefix(), mapping.getSuffix(),
				request);

		// Set the cancellation request attribute if appropriate
		if ((request.getParameter(Constants.CANCEL_PROPERTY) != null)
				|| (request.getParameter(Constants.CANCEL_PROPERTY_X) != null)) {
			request.setAttribute(Globals.CANCEL_KEY, Boolean.TRUE);
		}
	}

	/**
	 * General process method for handling system specific actions required
	 * before continuing processing.
	 * 
	 * @return boolean true or false.
	 * 
	 * @param request
	 *            The servlet request we are processing.
	 * @param response
	 *            The servlet response we are creating.
	 */
	protected boolean processPreprocess(HttpServletRequest request,
			HttpServletResponse response) {

		boolean continueProcessing = true;

		MemberVO token = null;
		MyAlumniUserContainer container = null;

		//String requestedPath = request.getPathInfo();

		// We check to see if the user is loggin in
		// String actionTok =
		// requestedPath.substring(requestedPath.lastIndexOf("/")+1);

		// Get the session object for the user.
		HttpSession session = request.getSession(false);
		if (session != null) {
			Object userObj = session.getAttribute(BaseConstants.USER_CONTAINER);
			if (userObj != null) {
				container = (MyAlumniUserContainer) userObj;
				token = container.getToken();
			}
		} else {
			try {
				response.setStatus(Integer.parseInt(BaseConstants.SC_SESSION_EXPIRED_701));
				response.sendError(Integer.parseInt(BaseConstants.SC_SESSION_EXPIRED_701), SystemConfigConstants.EXPIRED_SESSION_MSG);
			} catch (IOException e) {
				log.error("problem dispatching error message for illegal caller ..");
			}

			// discontinue ...
			return false;
		}

		if (token != null) {
			// At this point, user is logged on
			String requestPathInfo = request.getPathInfo(); // request path
															// info.

			// At this point, we make sure user is allowed to perform the
			// action.

			boolean userIsAuthorized = isUserActionAllowed(requestPathInfo, token.getIsAdmin());
			if (!userIsAuthorized) {

				try {
					response.setStatus(Integer.parseInt(BaseConstants.SC_INSURFICIENT_PRIV_700));
					response.sendError(Integer.parseInt(BaseConstants.SC_INSURFICIENT_PRIV_700), SystemConfigConstants.UNAUTHORIZED_MSG);
				} catch (IOException e) {
					log.error("problem dispatching error message for illegal caller ..");
				}

				// discontinue ...
				return false;
			}
		}

		return continueProcessing;
	}

	/**
	 * Confirms if user action can be allowed based on the permissions granted
	 * to the user.
	 * 
	 * @param requestedPath
	 *            user request path.
	 * @param isAdmin
	 *            user permission.
	 * 
	 * @return boo true or false
	 */
	private boolean isUserActionAllowed(String requestedPath, String isAdmin) {

		boolean allowedOrNot = true;
		
		// Do some kind of security check if need be

		return allowedOrNot;
	}

	/**
	 * Finds a matching module key using the path info.
	 * 
	 * @param path
	 *            path info
	 * 
	 * @return key module key.
	 */
	public static String matchPathWithModuleKey(String path) {

		String key = null;
		String[] modPaths = new String[] { "/admin", "/member" };

		String[] modKeys = new String[] { BaseConstants.ADMIN_MODULE,
				BaseConstants.MEMBER_MODULE };

		int s = modPaths.length;
		for (; --s >= 0;) {

			if (path.startsWith(modPaths[s])) {
				key = modKeys[s];
				break;
			}
		}

		return key;
	}
}

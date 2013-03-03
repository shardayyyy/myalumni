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
package net.naijatek.myalumni.framework;

import java.util.Locale;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import net.naijatek.myalumni.entity.MemberVO;
import net.naijatek.myalumni.controller.helper.OnlineUserManager;
import net.naijatek.myalumni.service.IUserAccountService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class is used to store information about a specific user. It is used so
 * that the information is not scattered throughout the HttpSession object. Only
 * this object is stored in the session for the user.
 * 
 * The HttpSessionBindingListener interface is implemented so that it can be
 * notified of session timeout and perform the proper cleanup.
 * 
 */
public class MyAlumniUserContainer implements HttpSessionBindingListener {

	private IUserAccountService securityService;

	private static Log logger = LogFactory.getLog(MyAlumniUserContainer.class);

	private MemberVO token = null; // Data about the user that is cached

	private Locale locale; // user locale

	private int newMailCount = 0;

	private int oldMailCount = 0;

	public int getNewMailCount() {
		return newMailCount;
	}

	public void setNewMailCount(int newMailCount) {
		this.newMailCount = newMailCount;
	}

	public int getOldMailCount() {
		return oldMailCount;
	}

	public void setOldMailCount(int oldMailCount) {
		this.oldMailCount = oldMailCount;
	}

	public MyAlumniUserContainer(IUserAccountService securityService) {
		this.securityService = securityService;
		initialize();
	}

	/**
	 * Default Constructor
	 */
	public MyAlumniUserContainer() {
		super();
		initialize();
	}

	/**
	 * Initialize all of the required resources
	 */
	private void initialize() {

	}

	/**
	 * The container calls this method when it is being unbound from the
	 * session.
	 */
	public void valueUnbound(HttpSessionBindingEvent event) {
		// Perform resource cleanup
		logger.info("Session expires... Cleaning up");
		cleanUp();
	}

	/**
	 * Set the locale for the user.
	 */
	public void setLocale(Locale aLocale) {
		locale = aLocale;
	}

	/**
	 * Retrieve the locale for the user.
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * The container calls this method when it is being bound to the session.
	 */
	public void valueBound(HttpSessionBindingEvent event) {
		// Don't need to do anything, but still have to implement the
		// interface method.
	}

	/**
	 * Retrieves the user details.
	 * 
	 * @return <b> token </b> user object.
	 */
	public MemberVO getToken() {
		return token;
	}

	/**
	 * Sets the user details.
	 */
	public void setToken(MemberVO token) {
		this.token = token;
	}

	/**
	 * Clean up any open resources.
	 */
	public void cleanUp() {
		logger.info("==> Auto logout. Cleaning up Session ojects...");

		OnlineUserManager manager = OnlineUserManager.getInstance();
		manager.removeOnlineUser(token.getMemberUserName());

		if (token != null) {
			securityService.logout(token.getMemberUserName());
		}

		this.setToken(null);
		this.setLocale(null);
	}

	public void updateTokenEmail(String email) {
		this.token.setEmail(email);
	}

	public void setOverWriteAvatar(boolean overWriteAvatar) {
		this.token.setOverWriteAvatar(overWriteAvatar);
	}

	public void setAvatar(String avatar) {
		this.token.setAvatar(avatar);
	}

}

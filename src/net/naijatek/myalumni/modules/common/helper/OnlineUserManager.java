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
package net.naijatek.myalumni.modules.common.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.naijatek.myalumni.framework.exceptions.UserAccountException;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.domain.MyAlumniBaseVO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OnlineUserManager extends MyAlumniBaseVO {

	private static final int REMOVE_INTERVAL = 60000; // update every 60
														// second

	private static Log logger = LogFactory.getLog(OnlineUserManager.class);

	// static variable
	private static final OnlineUserManager instance = new OnlineUserManager();

	// instance variable
	private final Map<String, MemberVO> userMap = new TreeMap<String, MemberVO>();

	private long timeOfLastRemoveAction = 0;

	int currentOnlineUserCount = 0;

	private OnlineUserManager() {
	}

	public static OnlineUserManager getInstance() {
		return instance;
	}

	/**
	 * 
	 * @param user
	 *            MemberVO
	 * @throws UserAccountException
	 */
	public void addOnlineUser(final MemberVO user, final int sessionTimeout)
			throws UserAccountException {

		long currentTime = System.currentTimeMillis();
		if (currentTime - timeOfLastRemoveAction > REMOVE_INTERVAL) {// update
																		// every
																		// minute
			removeTimeoutUsers(sessionTimeout);
		}
		setOnlineUser(user);
		currentOnlineUserCount = userMap.size();

		String memberList = new String("[");
		List members = getOnlineUsers(sessionTimeout);
		MemberVO onlineuser = new MemberVO();
		for (int i = 0; i < members.size(); i++) {
			onlineuser = (MemberVO) members.get(i);
			memberList += onlineuser.getMemberUserName() + ", ";
		}
		memberList += "]";
		logger.debug("Members Online = " + currentOnlineUserCount + " "
				+ memberList);
	}

	/**
	 * 
	 * @param memberUserName
	 *            String
	 */
	public void removeOnlineUser(final String memberUserName) {

		if (memberUserName != null) {
			userMap.remove(memberUserName);
		}
	}

	/**
	 * 
	 * @param username
	 *            String
	 * @return boolean
	 */
	public synchronized boolean isUserOnline(final String username) {
		Collection collection = userMap.values();
		Iterator iterator = collection.iterator();
		while (iterator.hasNext()) {
			MemberVO onlineUser = (MemberVO) iterator.next();
			String currentUser = onlineUser.getMemberUserName();
			if (username.equalsIgnoreCase(currentUser)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @return java.util.List
	 */
	public synchronized List<MemberVO> getOnlineUsers(int sessionTimeOut) {
		removeTimeoutUsers(sessionTimeOut);
		Collection collection = userMap.values();
		Iterator iterator = collection.iterator();
		List<MemberVO> users = new ArrayList<MemberVO>();
		MemberVO userVO = null;

		while (iterator.hasNext()) {
			userVO = new MemberVO();
			MemberVO tmp = (MemberVO) iterator.next();
			userVO.setFirstName(tmp.getFirstName());
			userVO.setLastName(tmp.getLastName());
			userVO.setMemberId(tmp.getMemberId());
			userVO.setYearOut(tmp.getYearOut());
			userVO.setMemberUserName(tmp.getMemberUserName());
			users.add(userVO);
		}
		return users;
	}

	/**
	 * private methods
	 * 
	 * @param user
	 *            FGCAuthBean
	 */
	private synchronized void setOnlineUser(final MemberVO user) {
		if (null == user) {
			userMap.remove(user.getMemberUserName());
		} else {
			userMap.put(user.getMemberUserName(), user);
		}
	}

	private synchronized void removeTimeoutUsers(final int sessionTimeOut) {

		long currentTimeMillis = System.currentTimeMillis();
		long sessionTime = sessionTimeOut; // in seconds

		// try to resolve problem with synchronization on the class-varible :
		// timeOfLastRemoveAction
		if (currentTimeMillis - timeOfLastRemoveAction < REMOVE_INTERVAL) {
			return;
		}
		// ok now, go ahead
		timeOfLastRemoveAction = currentTimeMillis;

		Date currentTime = new Date();

		Collection collection = userMap.values();
		Iterator iterator = collection.iterator();

		while (iterator.hasNext()) {
			MemberVO onlineUser = (MemberVO) iterator.next();
			// OnlineUserAction onlineUserAction =
			// onlineUser.getOnlineUserAction();
			long duration = currentTime.getTime()
					- onlineUser.getLastRequestTime().getTime();
			if (duration > sessionTime * 1000) { // convert seconds to
													// millisecs
				iterator.remove();
			}
			// logger.debug("currentTimeMillis = " + currentTimeMillis);
			// logger.debug("sessionTime = " + sessionTime*1000);
			// logger.debug("currentTime = " + currentTime);
			// logger.debug("currentTime_getTime = " + currentTime.getTime());
			// logger.debug("OnlineUser TIme = " +
			// onlineUser.getLastRequestTime().getTime());
			// logger.debug("duration = " + duration);
			// logger.debug("");
		}
	}

}

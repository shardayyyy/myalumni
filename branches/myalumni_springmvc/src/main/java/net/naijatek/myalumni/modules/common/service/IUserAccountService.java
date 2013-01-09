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
package net.naijatek.myalumni.modules.common.service;

import javax.servlet.http.HttpServletRequest;

import net.naijatek.myalumni.framework.exceptions.UserAccountException;
import net.naijatek.myalumni.modules.common.domain.LoginHistoryVO;
import net.naijatek.myalumni.modules.common.domain.MemberVO;




/**
 * This is an interface to the user account service implementation. It
 * extends the authentication interface, and defines other methods for user
 * profile management.
 *
 * @author Folashade Adeyosoye
 * @version 1.0
 */

public interface IUserAccountService {



	    /**
	    * Emails the password for a specified user.  This method is invoked when the
	    * user clicks on "Forgot Password" link.
	    *
	    * @param <b>userName</b> - userName of the user whose password has to be notified.
	    * @param <b>langId</b> - Languague used by the user.
	    *
	    * @throws <b>UserAccountException</b> if the user cannot be notified.
	    */
	    public void notifyPassword(String userName,HttpServletRequest request) throws UserAccountException;

	    /**
	    * Changes the password for the user.
	    *
	    * @param <b>userName</b> - username specified by the user during login
	    * @param <b>oldPassword</b> - old password value
	    * @param <b>newPassword</b> - New password value
	    *
	    * @throws <b>UserAccountException</b> if the password cannot be updated.
	    */
	    public void changePassword(String userName,
	                               String oldPassword, String newPassword) throws UserAccountException;   
	                               
	                               
	    /**
	    * Changes the password for the user based on the temp apssword provided.
	    *
	    * @param <b>userName</b> - username specified by the user during login
	    * @param <b>newPassword</b> - New password value
	    * @param <b>tempPassword</b> - Temp password value
	    *
	    * @throws <b>UserAccountException</b> if the password cannot be updated.
	    */
	    public void updateExpiredPassword(String userName, String newPassword, String tempPassword) throws UserAccountException;
	    
	    /**
	     * Log the user out of the system.
	     * 
	     * @param username user id.
	     * @param organizationId organization id.
	     */
	      public void logout(String username);

	     /**
	     * Authenticates the user's credentials and either returns a SecurityToken for the
	     * user or throw a security exception.
	     * 
	     * @throws <b> UserAccountException </b> if user cannot be validated.
	     * 
	     * @return <b> token </b> authentication object.
	     * 
	     * @param <b> username </b> username .
	     * @param <b> password </b> password.
	     */
	     public MemberVO login(String username, String password, String ipAddress) throws UserAccountException;

	     /**
	     * Logs the system access for the user.
	     * 
	     * @param <b> access </b> access history object.
	     */
	     public void addAccessTrail(LoginHistoryVO access);
	     
	     /**
	     * Locks user account
	     * 
	     * @param <b> access </b> access history object.
	     */
	     public boolean lockMemberAccount(String username);
	     
	     
	     
		    /**
		    * Emails the username for a specified user.  This method is invoked when the
		    * user clicks on "Forgot UserName" link.
		    *
		    * @param <b>email</b> - email of the user whose username has to be notified.
		    * @param <b>langId</b> - Languague used by the user.
		    *
		    * @throws <b>UserAccountException</b> if the user cannot be notified.
		    */
		    public void notifyUserName(String email,HttpServletRequest request) throws UserAccountException;	     

}

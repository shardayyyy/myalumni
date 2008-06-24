/*
 * ====================================================================
 * Copyright (C) 1997-2008 by Naijatek.com
 *
 * All copyright notices regarding MyAlumni Board MUST remain 
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
 * <p>Title: MyAlumni Board </p>
 * <p>Description: This system helps keep alive the line of communications between alumni/alumnus</p>
 * <p>Copyright: Copyright (c) 1997-2008</p>
 * <p>Company: Naijatek Solutions (http://www.naijatek.com)</p>
 * @author Folashade Adeyosoye (shardayyy@naijatek.com)
 * @version 1.0
 */
package net.naijatek.myalumni.modules.common.persistence.iface;

import java.util.Date;

import net.naijatek.myalumni.modules.common.domain.LoginHistoryVO;
import net.naijatek.myalumni.modules.common.domain.MemberVO;


/**
 * This is an interface to the user account dao implementation.
 * This provides a clean separation of the persitence and business logic
 * layers.
 *
 * @author Folashade Adeyosoye
 * @version 1.0
 */
public interface UserAccountDao {

    /**
     * Retrieves user account details.
     * 
     * @return <b> MemberVO </b> authentication object.
     * 
     * @param <b> email </b> email of the user.
     */
    public MemberVO getUserAccountByEmail(String email);
    
    
    /**
     * Retrieves user account details.
     * 
     * @return <b> MemberVO </b> authentication object.
     * 
     * @param <b> userName </b> id of the user.
     */
    public MemberVO getUserAccountByUserName(String userName);

    /**
     * Updates a user password.
     * 
     * @param <b> password </b> password.
     * @param <b> userName </b> user id.
     */
    public void changeUserPassword(String userName, String password);

    /**
     * Resets a user password, and user is prompted to change this
     * on next logon.
     * 
     * @param <b> password </b> password.
     * @param <b> userName </b> user id.
     */
    public void resetUserPassword(String userName, String password);
    

    /**
    * Adds an access trail object.
    * 
    * @param <b> history </b> access history.
    */
    public void addAccessTrail(LoginHistoryVO history);
    
    
    public void updateExpiredPassword(String userName, String password); 
    
    
    public void updateUserLogonStatus(String userName, String logonStatus);
    
    
    public boolean lockMemberAccount(String username);
    
    
    /**
     * Updates the member last logon date and time
     * 
     */
    public void updateLastLogonDate(String memberId, Date lastLogonDate);
}

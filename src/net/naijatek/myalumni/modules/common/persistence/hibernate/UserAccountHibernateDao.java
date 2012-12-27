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
package net.naijatek.myalumni.modules.common.persistence.hibernate;


import java.util.Date;

import net.naijatek.myalumni.modules.common.domain.LoginHistoryVO;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.persistence.BaseHibernateDao;
import net.naijatek.myalumni.modules.common.persistence.iface.UserAccountDao;
import net.naijatek.myalumni.util.BaseConstants;



/**
 * This class implements the data access for the user account object. It
 * implements the user account dao interface and inherits the base dao class.
 *
 * @author Folashade Adeyosoye
 * @version 1.0.
 */
public class UserAccountHibernateDao extends BaseHibernateDao implements UserAccountDao {
    
	    
    /**
     * Retrieves user account details.
     * 
     * @return <b> MemberVO </b> authentication object.
     * 
     * @param <b> email </b> email of the user.
     */
    public MemberVO getUserAccountByEmail(String email){
	        
	        MemberVO token =  (MemberVO) getSession().getNamedQuery("members.byemail")
	    	.setParameter("email", email)
			.uniqueResult();
	    	
	        return token;
	    }
	    
	    
   /**
    * Retrieves user account details 
    * 
    * @param <b> userName </b> id of the user.
    * @param <b> deleteFlag </b> delete flag.
    */
    public MemberVO getUserAccountByUserName(String memberUserName) {
        
        MemberVO token =  (MemberVO) getSession().getNamedQuery("members.byusername")
    	.setParameter("memberUserName", memberUserName)
		.uniqueResult();
    	
        return token;
    }

    /**
    * Updates a user password.
    * 
    * @param <b> password </b> password.
    * @param <b> userId </b> user id.
    */
    public void changeUserPassword(String memberUserName, String password) {

        MemberVO token = getUserAccountByUserName(memberUserName);       
    	token.setMemberPassword(password);
    	token.setPromptChange(BaseConstants.BOOLEAN_NO);
    	update(token);   
    }
     
    /**
    * Resets a user password, and user is prompted to change this
    * on next logon.
    * 
    * @param <b> password </b> password.
    * @param <b> userName </b> user id.
    */
    public void resetUserPassword(String memberUserName, String password) {
    	
    	MemberVO token = getUserAccountByUserName(memberUserName); 
        token.setMemberPassword(password);
        token.setPromptChange(BaseConstants.BOOLEAN_YES);  
        update(token);
    }
    
    /**
    * Adds an access history record.
    * 
    * @param <b> history </b> access history.
    */
    public void addAccessTrail(LoginHistoryVO history) {
    	add(history);
    }
    
    
    public void updateExpiredPassword(String memberUserName, String password){
    	MemberVO token = getUserAccountByUserName(memberUserName);
    	token.setPromptChange(BaseConstants.BOOLEAN_NO);        
    	token.setMemberPassword(password);
        update(token);
    }


	@SuppressWarnings("unchecked")
	public void updateUserLogonStatus(String userName, String logonStatus) {
			//		 Do nothing with this for now, but we might want to include it in our auditable actions.
	}
	
	
	
	public boolean lockMemberAccount(String memberUserName){
    	MemberVO token = getUserAccountByUserName(memberUserName);
    	if(token == null)
    		return false;
          
    	token.setMemberStatus(BaseConstants.ACCOUNT_LOCKED);
        update(token);
        return true;
	}
             
	
	
	public void updateLastLogonDate(String memberId, Date lastLogonDate, String lastIPAddress){
		MemberVO token = (MemberVO) get(MemberVO.class, memberId);
		token.setLastLogonDate(lastLogonDate);
		token.setLastIPAddress(lastIPAddress);
		update(token);
	}
         
}

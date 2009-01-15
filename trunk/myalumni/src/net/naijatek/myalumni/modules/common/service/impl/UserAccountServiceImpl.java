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
package net.naijatek.myalumni.modules.common.service.impl;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.naijatek.myalumni.framework.exceptions.NotLoginException;
import net.naijatek.myalumni.framework.exceptions.UserAccountException;
import net.naijatek.myalumni.modules.common.domain.LoginHistoryVO;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.domain.SystemConfigVO;
import net.naijatek.myalumni.modules.common.persistence.iface.SystemConfigDao;
import net.naijatek.myalumni.modules.common.persistence.iface.UserAccountDao;
import net.naijatek.myalumni.modules.common.service.IUserAccountService;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.encryption.Encoder;
import net.naijatek.myalumni.util.encryption.PasswordGenerator;
import net.naijatek.myalumni.util.mail.SendMailUtil;





/**
  * This class is an implementation of the user account management services.
  *
  * @author Folashade Adeyosoye.
  * @version 1.0.
  */
public class UserAccountServiceImpl implements IUserAccountService {    

    private UserAccountDao userAccountDao;  
    private SystemConfigDao sysConfigDao;

    
    public UserAccountServiceImpl(UserAccountDao userAccountDao, SystemConfigDao sysConfigDao) {
        super();
        this.userAccountDao = userAccountDao;
        this.sysConfigDao = sysConfigDao;
    }
    
    /**
    * This business method logs the user out of the system.
    * 
    * @param username user id.
    * @param organizationId organization id.
    */
    public void logout(String username) {
        // Do nothing with this for now, but we might want to include it in our auditable actions.
        
        userAccountDao.updateUserLogonStatus(username, BaseConstants.BOOLEAN_NO);

    }
    
    /**
      * This business method authenticates the user's credentials and either returns 
      * a SecurityToken for the user or throw a security exception.
      * 
      * 
      * @throws UserAccountException if user cannot be validated.
      * 
      * @return token SecurityToken object.
      * 
      * @param username username 
      * @param password password 
      */     
    public MemberVO login(String username, String password, String lastIPAddress) throws UserAccountException{
        
    	MemberVO token = userAccountDao.getUserAccountByUserName(username);  
          
        // Check if user account exists
        if (token == null){
            throw new UserAccountException(NotLoginException.WRONG_USERNAME);
              
        } else {
            // encrypt the password and compare to what the user had.
            String encPasswd = Encoder.getMD5_Base64(password);      
            // If the user passwords match, then check the account status.
            if (token.getMemberStatus().equalsIgnoreCase(BaseConstants.ACCOUNT_LOCKED)){
                throw new UserAccountException(NotLoginException.ACCOUNT_LOCKED);
            }
            
            if (token.getMemberStatus().equalsIgnoreCase(BaseConstants.ACCOUNT_DEACTIVATED)){
                throw new UserAccountException(NotLoginException.ACCOUNT_DEACTIVATED);
            }

            if (token.getMemberStatus().equalsIgnoreCase(BaseConstants.ACCOUNT_UNAPPROVED)){
                throw new UserAccountException(NotLoginException.ACCOUNT_UNAPPROVED);
            }
            
            if (!encPasswd.equals(token.getMemberPassword())) {
                throw new UserAccountException(NotLoginException.WRONG_PASSWORD);
            }
            
        
        }
        
        Date lastLogonDate = new Date();
        userAccountDao.updateLastLogonDate(token.getMemberId(), lastLogonDate, lastIPAddress);
        token.setLastIPAddress(lastIPAddress) ;  
        return token;         
    }
    
 
    
    
    /**
    * This business method logs the system access for the user.
    * 
    * @param <b> access </b> access history object.
    */
    public void addAccessTrail(LoginHistoryVO access) {
        
        userAccountDao.addAccessTrail(access);
    }
    
    /**
    * This business method emails the password for a specified user.  This method is invoked when the 
    * user clicks on "Forgot Password" link.
    *
    * @param <b>userName</b> - user id of the user whose password has to be notified.
    * @param <b>langId</b> - Languague used by the user.
    *
    * @throws <b>UserAccountException</b> if the user cannot be notified. 
    */
    public void notifyPassword(String userName, HttpServletRequest request) throws UserAccountException {
                               
        // Lookup the user record, and check if user account exists.  
    	MemberVO auth = userAccountDao.getUserAccountByUserName(userName);     
        if (auth == null) {
            throw new UserAccountException(NotLoginException.USER_NOT_FOUND);
        }
        
        // Locked
        if (auth.getMemberStatus().equals(BaseConstants.ACCOUNT_LOCKED)){
        	throw new UserAccountException(NotLoginException.ACCOUNT_LOCKED);
        }
        
        // deactivated
        if (auth.getMemberStatus().equals(BaseConstants.ACCOUNT_DEACTIVATED)){
        	throw new UserAccountException(NotLoginException.ACCOUNT_DEACTIVATED);
        }
        
        //unapproved
        if (auth.getMemberStatus().equals(BaseConstants.ACCOUNT_UNAPPROVED)){
        	throw new UserAccountException(NotLoginException.ACCOUNT_UNAPPROVED);
        }
        
        
        SystemConfigVO sysConfigVO = sysConfigDao.getSystemConfig();
        try {
            // For security reasons, we generate a new random password for the user. 
            // User should be forced to change this on next logon.
            String newPasswd =  PasswordGenerator.createPassword(8);
            
            // then use an email template to send the notification.        
             SendMailUtil.sendPasswordReminderMail(auth.getEmail(), auth.getFullName(), newPasswd, sysConfigVO);
            
            // update the system with the new user password(encrypted), 
            // and insert a trail for that.
            userAccountDao.resetUserPassword(userName, Encoder.getMD5_Base64(newPasswd));
        } catch (Exception ex) {            
            throw new UserAccountException(ex.getMessage());
        }                 
    }
    
    
    /**
     * This business method emails the username for a specified user.  This method is invoked when the 
     * user clicks on "Forgot UserName" link.
     *
     * @param <b>email</b> - email of the user whose username has to be notified.
     * @param <b>langId</b> - Languague used by the user.
     *
     * @throws <b>UserAccountException</b> if the user cannot be notified. 
     */
     public void notifyUserName(String email, HttpServletRequest request) throws UserAccountException {
                                
         // Lookup the user record, and check if user account exists.  
     	MemberVO auth = userAccountDao.getUserAccountByEmail(email);  
     	
         if (auth == null) {
             throw new UserAccountException(NotLoginException.USER_NOT_FOUND);
         }
         
         // Locked
         if (auth.getMemberStatus().equals(BaseConstants.ACCOUNT_LOCKED)){
         	throw new UserAccountException(NotLoginException.ACCOUNT_LOCKED);
         }
         
         // deactivated
         if (auth.getMemberStatus().equals(BaseConstants.ACCOUNT_DEACTIVATED)){
         	throw new UserAccountException(NotLoginException.ACCOUNT_DEACTIVATED);
         }
         
         //unapproved
         if (auth.getMemberStatus().equals(BaseConstants.ACCOUNT_UNAPPROVED)){
         	throw new UserAccountException(NotLoginException.ACCOUNT_UNAPPROVED);
         }
         
         
         SystemConfigVO sysConfigVO = sysConfigDao.getSystemConfig();
         try {
        	 
             // then use an email template to send the notification.        
              SendMailUtil.sendUserNameReminderMail(auth.getEmail(), auth.getFullName(), auth.getMemberUserName(), sysConfigVO);
             
         } catch (Exception ex) {            
             throw new UserAccountException(ex.getMessage());
         }                 
     }
     
     
    /**
    * This business method changes the password for the user.
    *
    * @param <b>userId</b> - user id specified by the user during login
    * @param <b>oldPassword</b> - old password value
    * @param <b>newPassword</b> - New password value
    *
    * @throws <b>UserAccountException</b> if the password cannot be updated.
    */
    public void changePassword(String userName,
                               String oldPassword,
                               String newPassword) throws UserAccountException {
        
        // Lookup the user record, and check if user account exists.  
    	MemberVO auth = userAccountDao.getUserAccountByUserName(userName);     
        if (auth == null) {
            throw new UserAccountException(NotLoginException.WRONG_USERNAME);
        }
        
        // encrypt the password and compare to what the user had.
        String encPasswd = Encoder.getMD5_Base64(oldPassword);
        if (!encPasswd.equals(auth.getMemberPassword())) {
            throw new UserAccountException(NotLoginException.WRONG_PASSWORD);
        }
        
        if (oldPassword.equals(newPassword)){
        	throw new UserAccountException(NotLoginException.SAME_PASSWORD);
        }
        
        //Else Update the user password(encrypted) and insert a trail for that.
        userAccountDao.changeUserPassword(userName, Encoder.getMD5_Base64(newPassword));                  
    }    

    public void updateExpiredPassword(String userName, String newPassword, String tempPassword) throws UserAccountException { 
        // Lookup the user record, and check if user account exists.  
    	MemberVO auth = userAccountDao.getUserAccountByUserName(userName);     
        if (auth == null) {
            throw new UserAccountException(NotLoginException.WRONG_USERNAME);
        }
        
        // encrypt the password and compare to what the user had.
        String encPasswd = auth.getMemberPassword();
        if (!encPasswd.equals(Encoder.getMD5_Base64(tempPassword))) {
            throw new UserAccountException(NotLoginException.WRONG_PASSWORD);
        }
        
        if (newPassword.equals(tempPassword)){
        	throw new UserAccountException(NotLoginException.SAME_PASSWORD);
        }
            
        userAccountDao.updateExpiredPassword(userName, Encoder.getMD5_Base64(newPassword));                  
    }     
    
    
    /**
     * Locks user account
     * 
     * @param <b> access </b> access history object.
     */
     public boolean lockMemberAccount(String username){
    	 return userAccountDao.lockMemberAccount(username);
     }
     

}

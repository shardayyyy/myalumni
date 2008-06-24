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
package net.naijatek.myalumni.util.mail;


public interface IMailConstants {

    public static String TEMPLATE_EXTENSION = "ftl";
    
    //Password Reminder
    public static String TEMPLATE_PASSWORD_REMINDER_BODY = "PasswordReminderBody";
    public static String TEMPLATE_PASSWORD_REMINDER_SUBJECT = "PasswordReminderSubject";
    
    //UserName Reminder
    public static String TEMPLATE_USERNAME_REMINDER_BODY = "UserNameReminderBody";
    public static String TEMPLATE_USERNAME_REMINDER_SUBJECT = "UserNameReminderSubject";
    
    // Profile changed     
    public static String TEMPLATE_PROFILE_CHANGED_BODY = "ProfileChangedNotificationBody";
    public static String TEMPLATE_PROFILE_CHANGED_SUBJECT = "ProfileChangedNotificationSubject";   
    
    // Support Email     
    public static String TEMPLATE_SUPPORT_NOTIFICATION_BODY = "SupportNotificationBody";
    public static String TEMPLATE_SUPPORT_NOTIFICATION_SUBJECT = "SupportNotificationSubject";   
    
    // Requestor Support Email     
    public static String TEMPLATE_REQUESTOR_SUPPORT_NOTIFICATION_BODY = "RequestorSupportNotificationBody";
    public static String TEMPLATE_REQUESTOR_SUPPORT_NOTIFICATION_SUBJECT = "RequestorSupportNotificationSubject";  
    
    // New Member Welcome Message     
    public static String TEMPLATE_NEW_MEMBER_WELCOME_NOTIFICATION_BODY = "EmailWelcomeNoticeBody";
    public static String TEMPLATE_NEW_MEMBER_WELCOME_NOTIFICATION_SUBJECT = "EmailWelcomeNoticeSubject";  
    
    // Email Member
    public static String TEMPLATE_EMAIL_MEMBER_BODY = "EmailMemberBody";
    public static String TEMPLATE_EMAIL_MEMBER_SUBJECT = "EmailMemberSubject";
    
    // New message Notification
    public static String TEMPLATE_NEW_MESSAGE_BODY = "NewMessageNotificationBody";
    public static String TEMPLATE_NEW_MESSAGE_SUBJECT = "NewMessageNotificationSubject"; 
    
    // New admin message Notification
    public static String TEMPLATE_NEW_ADMIN_MESSAGE_BODY = "NewAdminMessageNotificationBody";
    public static String TEMPLATE_NEW_ADMIN_MESSAGE_SUBJECT = "NewAdminMessageNotificationSubject";      
    
    // Happy Birthday Wish Notification
    public static String TEMPLATE_BIRTHDAY_WISH_BODY = "BirthdayWishBody";
    public static String TEMPLATE_BIRTHDAY_WISH_SUBJECT = "BirthdayWishSubject"; 
    
    
}

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
package net.naijatek.myalumni.util.mail;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;

import net.naijatek.myalumni.framework.exceptions.MailServerException;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.domain.PrivateMessageVO;
import net.naijatek.myalumni.modules.common.domain.SystemConfigVO;
import net.naijatek.myalumni.util.utilities.StringUtil;

import freemarker.template.Configuration;


public class SendMailUtil extends HttpServlet implements IMailConstants{

	private static Log logger = LogFactory.getLog(SendMailUtil.class);
    
    private static JavaMailSender mailSender;
    private static FreeMarkerConfigurer freeMarker ;
    private static Configuration configuration;

    
    public SendMailUtil() {
       	
    }
   
    public SendMailUtil(JavaMailSender _mailSender, FreeMarkerConfigurer _freeMarker) {
    	super();   	
    	mailSender = _mailSender;
    	freeMarker = _freeMarker;
    	configuration = freeMarker.getConfiguration();
    }    
    
    /** @spring.property ref="mailSender" */
    public void setMailSender(JavaMailSender _mailSender) {
        mailSender = _mailSender;
    }

    /** @spring.property ref="freemarkerConfig" */
    public void setFreeMarker(FreeMarkerConfigurer _freeMarker) {
    	configuration = _freeMarker.getConfiguration();
    	freeMarker = _freeMarker;
    }
    
// ------------------------------------------------------------------------------------------------------------------------
    
    public static void sendProfileChangeNotificationMail(String recipient, String fullName, String langId, SystemConfigVO sysConfig, 
    		String reasonForUpdate) throws MailServerException {
        
        try {                           
            Map<String, String> map = new HashMap<String, String>();
            
            map.put("fullName", fullName);                        
  		  	map.put("serverName", StringUtil.safeString(sysConfig.getServerUrl()));
  		  	map.put("adminSignature", StringUtil.safeString(sysConfig.getAdminSignature()));
            map.put("reasonForUpdate", reasonForUpdate);
            
            String subjectTemplatePrefix = TEMPLATE_PROFILE_CHANGED_SUBJECT + "_" + langId + "-text." + TEMPLATE_EXTENSION;
            String bodyTemplatePrefix = TEMPLATE_PROFILE_CHANGED_BODY + "_" + langId + "-text." + TEMPLATE_EXTENSION;
            new FreeMarkerTemplateMailerImpl(mailSender, configuration).mail(recipient, map, bodyTemplatePrefix, subjectTemplatePrefix);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("sendProfileChangeNotificationMail: " + e.getMessage());
            throw new MailServerException("Error encountered when sending email to remind user of password " + e.getCause().toString());
        }        
    }

//  ------------------------------------------------------------------------------------------------------------------------ 

  /**
     * Send welcome notice to_email user
     * 
     * @param email String
     * @param userName String
     * @throws MessagingException
     * @throws Exception
     */
    // Done
  public static void sendWelcomeNotice(final String email, final String userName, String langId, SystemConfigVO sysConfig)
         throws Exception{
      try {                           
          Map<String, String> map = new HashMap<String, String>();
                                    
          map.put("userName", userName);         
          map.put("schoolName", StringUtil.safeString(sysConfig.getOrganizationName()));
		  map.put("serverName", StringUtil.safeString(sysConfig.getServerUrl()));
		  map.put("adminSignature", StringUtil.safeString(sysConfig.getAdminSignature()));

          String subjectTemplatePrefix = TEMPLATE_NEW_MEMBER_WELCOME_NOTIFICATION_SUBJECT + "_" + langId + "-text." + TEMPLATE_EXTENSION;
          String bodyTemplatePrefix = TEMPLATE_NEW_MEMBER_WELCOME_NOTIFICATION_BODY + "_" + langId + "-text." + TEMPLATE_EXTENSION;
          new FreeMarkerTemplateMailerImpl(mailSender, configuration).mail(email, map, bodyTemplatePrefix, subjectTemplatePrefix);
      }
      catch (Exception e) {
          e.printStackTrace();
          logger.error("sendWelcomeNotice: " + e.getMessage());
          throw new MailServerException("Error encountered sendWelcomeNotice " + e.getCause().toString());
      }  
    }

//------------------------------------------------------------------------------------------------------------------------    

    public static void sendBirthdayWish(final MemberVO memberVO, SystemConfigVO sysConfig)
           throws Exception{

    	String langId = "en";
        try {                           
            Map<String, String> map = new HashMap<String, String>();
                                      
            map.put("fullName", memberVO.getFullName());
            map.put("memberUserName", memberVO.getMemberUserName()); 
            map.put("schoolName", StringUtil.safeString(sysConfig.getOrganizationName()));
  		  	map.put("serverName", StringUtil.safeString(sysConfig.getServerUrl()));
  		  	map.put("adminSignature", StringUtil.safeString(sysConfig.getAdminSignature()));

            String subjectTemplatePrefix = TEMPLATE_BIRTHDAY_WISH_SUBJECT + "_" + langId + "-text." + TEMPLATE_EXTENSION;
            String bodyTemplatePrefix = TEMPLATE_BIRTHDAY_WISH_BODY + "_" + langId + "-text." + TEMPLATE_EXTENSION;
            new FreeMarkerTemplateMailerImpl(mailSender, configuration).mail(memberVO.getEmail(), map, bodyTemplatePrefix, subjectTemplatePrefix);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("sendBirthdayWish: " + e.getMessage());
            throw new MailServerException("Error encountered sendBirthdayWish " + e.getCause().toString());
        } 
      }
      
//  ------------------------------------------------------------------------------------------------------------------------  
    // Done
    public static void sendUserNameReminderMail(String email, String fullName, String memberUserName, String langId, SystemConfigVO sysConfig)
    throws Exception{
    	
        try {                           
            Map<String, String> map = new HashMap<String, String>();
                                      
            map.put("fullName", fullName);
            map.put("memberUserName", memberUserName); 
            map.put("schoolName", StringUtil.safeString(sysConfig.getOrganizationName()));
  		  	map.put("serverName", StringUtil.safeString(sysConfig.getServerUrl()));
  		  	map.put("adminSignature", StringUtil.safeString(sysConfig.getAdminSignature()));

            String subjectTemplatePrefix = TEMPLATE_USERNAME_REMINDER_SUBJECT + "_" + langId + "-text." + TEMPLATE_EXTENSION;
            String bodyTemplatePrefix = TEMPLATE_USERNAME_REMINDER_BODY + "_" + langId + "-text." + TEMPLATE_EXTENSION;
            new FreeMarkerTemplateMailerImpl(mailSender, configuration).mail(email, map, bodyTemplatePrefix, subjectTemplatePrefix);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("sendPasswordReminderMail: " + e.getMessage());
            throw new MailServerException("Error encountered sendPasswordReminderMail " + e.getCause().toString());
        } 
        
    }

//  ------------------------------------------------------------------------------------------------------------------------
    
    // Done
    public static void sendPasswordReminderMail(String email, String fullName, String newPasswd, String langId, SystemConfigVO sysConfig)
    throws Exception{
    	
        try {                           
            Map<String, String> map = new HashMap<String, String>();
                                      
            map.put("fullName", fullName);  
            map.put("newPasswd", newPasswd);  
            map.put("schoolName", StringUtil.safeString(sysConfig.getOrganizationName()));
  		  	map.put("serverName", StringUtil.safeString(sysConfig.getServerUrl()));
  		  	map.put("adminSignature", StringUtil.safeString(sysConfig.getAdminSignature()));

            String subjectTemplatePrefix = TEMPLATE_PASSWORD_REMINDER_SUBJECT + "_" + langId + "-text." + TEMPLATE_EXTENSION;
            String bodyTemplatePrefix = TEMPLATE_PASSWORD_REMINDER_BODY + "_" + langId + "-text." + TEMPLATE_EXTENSION;
            new FreeMarkerTemplateMailerImpl(mailSender, configuration).mail(email, map, bodyTemplatePrefix, subjectTemplatePrefix);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("sendPasswordReminderMail: " + e.getMessage());
            throw new MailServerException("Error encountered sendPasswordReminderMail " + e.getCause().toString());
        } 
        
    }

//  ------------------------------------------------------------------------------------------------------------------------

  /**
     * Send welcome notice to_email user
     * 
     * @param email String
     * @throws MessagingException
     * @throws Exception
     */
  public static void sendActivationInstructions(final String email, final String tempUserName)
           throws Exception{

//         //String tempUserName = "";
//         String activationCode = "";
//
//         //tempUserName = api.getMemberUserNameByEmail(email);
//         activationCode = Encoder.getMD5_Base64(email);
//
//         StringBuffer activationUrl = new StringBuffer(256);
//         activationUrl.append(SERVER_NAME);
//         //activationUrl.append(sysProp.getValue("CONTEXT_PATH"));
//         activationUrl.append("/jsp/myalumni/activationProcess.jsp?");
//         //activationUrl.append("?memberActivateCode=" + activationCode);
//         activationUrl.append("memberUserName=" + tempUserName);
//         activationUrl.append("&memberEmail=" + email);
//
//         Map map = new HashMap();
//         map.put("tempUserName", tempUserName);
//         map.put("activationCode", activationCode);
//         map.put("email", email);
//         map.put("activationUrl", activationUrl.toString());
//         map.put("adminSignature", adminSignature);
//
//         String body = getActivationInstructions(map);
//
//         String subject = preSubject + "Account Activation Instructions.";
//
//         //logger.debug("subject = " + subject);
//         //logger.debug("body = " + body);
//
//         try {
//           mailer.sendMail(WEBMASTER_EMAIL, email /*to*/, ""/*cc*/, "" /*bcc*/, subject, body);
//         }
////         catch (MessagingException mex) {
////           throw mex;
////         }
//         catch (Exception ex) {
//           //throw ex;
//            logger.fatal("Email Problem...");
//         }
       }
//--------------------------------------------------------------------------------------------------

  /**
   *
   * @param pm PrivateMessage
   * @throws MessagingException
   * @throws Exception
   */
  public static void sendEmail(final PrivateMessageVO pm, final SystemConfigVO sysConfigVO , String langId )
            throws Exception{

	    try {		
		    Map<String, String> map = new HashMap<String, String>();
		    map.put("subject", pm.getSubject());
		    map.put("body", pm.getMessageText());
		    map.put("serverName", StringUtil.safeString(sysConfigVO.getServerUrl()));
		    map.put("adminSignature", StringUtil.safeString(sysConfigVO.getAdminSignature()));

		    String subjectTemplatePrefix = TEMPLATE_EMAIL_MEMBER_SUBJECT + "_" + langId + "-text." + TEMPLATE_EXTENSION;
		    String bodyTemplatePrefix = TEMPLATE_EMAIL_MEMBER_BODY + "_" + langId + "-text." + TEMPLATE_EXTENSION;
		    new FreeMarkerTemplateMailerImpl(mailSender, configuration).mail(pm.getGuestEmail(), map, bodyTemplatePrefix, subjectTemplatePrefix);
	    }
	    catch (Exception ex) {
	        ex.printStackTrace();
	        logger.error("sendEmail: " + ex.getMessage());
	        throw new MailServerException("Error encountered when sending sendEmail " + ex.getCause().toString());
	    }
   }
  
  
     //-----------------------------------------------------------------------------------------
  /**
     * Notifies the member that they have an email to_email view.
     * 
     * @param pm PrivateMessage
     * @param to String
     * @throws MessagingException
     * @throws Exception
     */
  public static void memberNewMessageNotification(final PrivateMessageVO pm, final SystemConfigVO sysConfig, final String toEmail, String langId)throws  Exception{ //PROOF READ
	    try {		
		    Map<String, String> map = new HashMap<String, String>();
		    map.put("firstName", pm.getMessageToMember().getFirstName());
		    map.put("lastName", pm.getMessageToMember().getLastName());
		    map.put("orgName", StringUtil.safeString(sysConfig.getOrganizationName()));
		    map.put("serverName", StringUtil.safeString(sysConfig.getServerUrl()));
		    map.put("adminSignature", StringUtil.safeString(sysConfig.getAdminSignature()));

		    String subjectTemplatePrefix = TEMPLATE_NEW_MESSAGE_SUBJECT + "_" + langId + "-text." + TEMPLATE_EXTENSION;
		    String bodyTemplatePrefix = TEMPLATE_NEW_MESSAGE_BODY + "_" + langId + "-text." + TEMPLATE_EXTENSION;
		    new FreeMarkerTemplateMailerImpl(mailSender, configuration).mail(toEmail, map, bodyTemplatePrefix, subjectTemplatePrefix);
	    }
	    catch (Exception ex) {
	        ex.printStackTrace();
	        logger.error("memberNewMessageNotification: " + ex.getMessage());
	        throw new MailServerException("Error encountered when sending memberNewMessageNotification " + ex.getCause().toString());
	    }

  }

       //--------------------------------------------------------------------------------------------------

		  /**
		   * Notifies the member that they have an email to_email view.
		   * 
		   * @param pm PrivateMessage
		   * @param to String
		   * @throws MessagingException
		   * @throws Exception
		   */
		public static void adminNewMessageNotification(final SystemConfigVO sysConfig, final String toEmail, String langId)throws  Exception{ //PROOF READ
			    try {		
				    Map<String, String> map = new HashMap<String, String>();
				    map.put("orgName", StringUtil.safeString(sysConfig.getOrganizationName()));
				    map.put("serverName", StringUtil.safeString(sysConfig.getServerUrl()));
				    map.put("adminSignature", StringUtil.safeString(sysConfig.getAdminSignature()));
		
				    String subjectTemplatePrefix = TEMPLATE_NEW_ADMIN_MESSAGE_SUBJECT + "_" + langId + "-text." + TEMPLATE_EXTENSION;
				    String bodyTemplatePrefix = TEMPLATE_NEW_ADMIN_MESSAGE_BODY + "_" + langId + "-text." + TEMPLATE_EXTENSION;
				    new FreeMarkerTemplateMailerImpl(mailSender, configuration).mail(toEmail, map, bodyTemplatePrefix, subjectTemplatePrefix);
			    }
			    catch (Exception ex) {
			        ex.printStackTrace();
			        logger.error("adminNewMessageNotification: " + ex.getMessage());
			        throw new MailServerException("Error encountered when sending adminNewMessageNotification " + ex.getCause().toString());
			    }
		
		}
  


}

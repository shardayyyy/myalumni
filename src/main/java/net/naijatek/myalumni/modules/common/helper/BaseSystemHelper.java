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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.naijatek.myalumni.util.BaseConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseSystemHelper {
	
	private Log logger = LogFactory.getLog(this.getClass());
	
    public BaseSystemHelper() {
    }
    
    public ActionMessages validateSystemConfig() {

		// perform any extra tasks to display page
		ActionMessages errors = new ActionMessages();
		String SPRING_PROPERTY_FILENAME = "/spring.properties";
		String LOG4J_PROPERTY_FILENAME = "/log4j.properties";
		String MAIL_PROPERTY_FILENAME = "/mail.properties";
		String SYSTEM_PROPERTY_FILENAME = "/system.properties";
		
		Properties springProperties = new Properties();
		Properties log4jProperties = new Properties();
		Properties mailProperties = new Properties();
		Properties systemProperties = new Properties();
		
		InputStream is = null;
		String key = new String();
		
		//
		//   SPRING PROPERTIES
		//  =====================
		//driver=
		//dbip=
		//dbname=
		//dbport=
		//username=
		//password=				
		//dbpath=
		try {
		      is = getClass().getResourceAsStream(SPRING_PROPERTY_FILENAME);
		      springProperties.load(is);
		      logger.info("Loaded the Spring Property file... " + SPRING_PROPERTY_FILENAME);
		      
		      key = "driver";			      
		      if (springProperties.getProperty(key).trim() == null || springProperties.getProperty(key).trim().equals("")){
		    	  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00901"));
		      }
		      
		      key = "dbip";			      
		      if (springProperties.getProperty(key).trim() == null || springProperties.getProperty(key).trim().equals("")){
		    	  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00902"));
		      }

		      key = "dbname";			      
		      if (springProperties.getProperty(key).trim() == null || springProperties.getProperty(key).trim().equals("")){
		    	  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00903"));
		      }

		      key = "dbport";			      
		      if (springProperties.getProperty(key).trim() == null || springProperties.getProperty(key).trim().equals("")){
		    	  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00904"));
		      }

		      key = "username";			      
		      if (springProperties.getProperty(key).trim() == null || springProperties.getProperty(key).trim().equals("")){
		    	  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00905"));
		      }

		      key = "password";			      
		      if (springProperties.getProperty(key).trim() == null || springProperties.getProperty(key).trim().equals("")){
		    	  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00906"));
		      }

		      key = "dbpath";			      
		      if (springProperties.getProperty(key).trim() == null || springProperties.getProperty(key).trim().equals("")){
		    	  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00907"));
		      }
		      
		}
		catch (Exception ex) {
		      logger.fatal("Can not read the properties file. " + SPRING_PROPERTY_FILENAME);
		}
		finally {
		    try {
		        if (is != null) {
		          is.close();
		          is = null;
		        }
		      }
		      catch (IOException exception) {
		        // ignored
		      }
		 }			


		//
		//   LOG4J PROPERTIES
		//  =====================
		// log4j.rootLogger=
		// log4j.appender.FILE.File=
		try {
		      is = getClass().getResourceAsStream(LOG4J_PROPERTY_FILENAME);
		      log4jProperties.load(is);
		      logger.info("Loaded the Log4J Property file... " + LOG4J_PROPERTY_FILENAME);
		      
		      key = "log4j.rootLogger";			      
		      if (log4jProperties.getProperty(key).trim() == null || log4jProperties.getProperty(key).trim().equals("")){
		    	  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00911"));
		      }
		      
		      key = "log4j.appender.FILE.File";			      
		      if (log4jProperties.getProperty(key).trim() == null || log4jProperties.getProperty(key).trim().equals("")){
		    	  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00912"));
		      }			      
		}
		catch (Exception ex) {
		      logger.fatal("Can not read the properties file. " + LOG4J_PROPERTY_FILENAME);
		}
		finally {
		    try {
		        if (is != null) {
		          is.close();
		          is = null;
		        }
		      }
		      catch (IOException exception) {
		        // ignored
		      }
		 }	
		

		//
		//   MAIL PROPERTIES
		//  =====================
		//mail.smtp.username=
		//mail.smtp.password=
		//mail.smtp.host=
		//mail.smtp.default.from=
		//mail.smtp.port=
		//mail.smtp.socketFactory.port=
		//mail.smtp.socketFactory.class= 
		//mail.smtp.socketFactory.fallback=	
		try {
		      is = getClass().getResourceAsStream(MAIL_PROPERTY_FILENAME);
		      mailProperties.load(is);
		      logger.info("Loaded the mail Property file... " + MAIL_PROPERTY_FILENAME);
		      
		      key = "mail.smtp.username";			      
		      if (mailProperties.getProperty(key).trim() == null || mailProperties.getProperty(key).trim().equals("")){
		    	  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00921"));
		      }	
		      
		      key = "mail.smtp.password";			      
		      if (mailProperties.getProperty(key).trim() == null || mailProperties.getProperty(key).trim().equals("")){
		    	  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00922"));
		      }	
		      
		      key = "mail.smtp.host";			      
		      if (mailProperties.getProperty(key).trim() == null || mailProperties.getProperty(key).trim().equals("")){
		    	  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00923"));
		      }	
		      
		      key = "mail.smtp.default.from";			      
		      if (mailProperties.getProperty(key).trim() == null || mailProperties.getProperty(key).trim().equals("")){
		    	  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00924"));
		      }	
		      
		      key = "mail.smtp.port";			      
		      if (mailProperties.getProperty(key).trim() == null || mailProperties.getProperty(key).trim().equals("")){
		    	  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00925"));
		      }	
		      
		      key = "mail.smtp.socketFactory.port";			      
		      if (mailProperties.getProperty(key).trim() == null || mailProperties.getProperty(key).trim().equals("")){
		    	  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00926"));
		      }	
		      
		      key = "mail.smtp.socketFactory.class";			      
		      if (mailProperties.getProperty(key).trim() == null || mailProperties.getProperty(key).trim().equals("")){
		    	  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00927"));
		      }	
		      
		      key = "mail.smtp.socketFactory.fallback";			      
		      if (mailProperties.getProperty(key).trim() == null || mailProperties.getProperty(key).trim().equals("")){
		    	  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00928"));
		      }				      
		}
		catch (Exception ex) {
		      logger.fatal("Can not read the properties file. " + MAIL_PROPERTY_FILENAME);
		}
		finally {
		    try {
		        if (is != null) {
		          is.close();
		          is = null;
		        }
		      }
		      catch (IOException exception) {
		        // ignored
		      }
		 }				
		
	

		//
		//   SYSTEM PROPERTIES
		//  =====================
		//APP.HOMEPATH=
		//AVATAR.FILEPATH=
		//LOGFILE.FILEPATH=
		//BACKUP.FILEPATH=	
		try {
		      is = getClass().getResourceAsStream(SYSTEM_PROPERTY_FILENAME);
		      systemProperties.load(is);
		      logger.info("Loaded the system Property file... " + SYSTEM_PROPERTY_FILENAME);
		      
		      
		      key = "APP.HOMEPATH";			      
		      if (systemProperties.getProperty(key).trim() == null || systemProperties.getProperty(key).trim().equals("")){
		    	  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00931"));
		      }	
		      
		      
		      key = "AVATAR.FILEPATH";			      
		      if (systemProperties.getProperty(key).trim() == null || systemProperties.getProperty(key).trim().equals("")){
		    	  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00932"));
		      }	
		      
		      
		      key = "LOGFILE.FILEPATH";			      
		      if (systemProperties.getProperty(key).trim() == null || systemProperties.getProperty(key).trim().equals("")){
		    	  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00933"));
		      }	
		      
		      
		      key = "BACKUP.FILEPATH";			      
		      if (systemProperties.getProperty(key).trim() == null || systemProperties.getProperty(key).trim().equals("")){
		    	  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00934"));
		      }		
		      
		      key = "LOGO.FILEPATH";			      
		      if (systemProperties.getProperty(key).trim() == null || systemProperties.getProperty(key).trim().equals("")){
		    	  errors.add(BaseConstants.WARN_KEY, new ActionMessage("core.errorcode.00935"));
		      }			      
		}
		catch (Exception ex) {
		      logger.fatal("Can not read the properties file. " + SYSTEM_PROPERTY_FILENAME);
		}
		finally {
		    try {
		        if (is != null) {
		          is.close();
		          is = null;
		        }
		      }
		      catch (IOException exception) {
		        // ignored
		      }
		 }			
		

        return errors;
    }
        
}

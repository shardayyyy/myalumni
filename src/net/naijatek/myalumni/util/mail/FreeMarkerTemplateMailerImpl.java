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


import net.naijatek.myalumni.framework.exceptions.MailServerException;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.mail.MailPreparationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.MessagingException;
import javax.mail.Message;
import java.util.Map;
import java.util.Iterator;
import java.util.List;
import java.io.*;

/**
 * @spring.bean id="templateMailer"
 */
public class FreeMarkerTemplateMailerImpl extends Thread implements ITemplateMailer {
	
    private JavaMailSender mailSender;
    private Configuration configuration ;
    private static final Log logger = LogFactory.getLog(FreeMarkerTemplateMailerImpl.class);

    
    public FreeMarkerTemplateMailerImpl(JavaMailSender mailSender, Configuration configuration) {
        super();
        this.mailSender = mailSender;
        this.configuration = configuration;        
    }
        
    public FreeMarkerTemplateMailerImpl() {
        super();        
    }    
    
    
    public void mail(final String email, final Map map, final String bodyTemplatePrefix, final String subjectTemplatePrefix) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws MessagingException, IOException {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));                

                //
                // Get the subject
                //
                //BodyPart subjectPart = new MimeBodyPart();
                Template subjectTextTemplate = configuration.getTemplate(subjectTemplatePrefix);
                final StringWriter subjectTextWriter = new StringWriter();
                
                try {
                	subjectTextTemplate.process(map, subjectTextWriter);
                } catch (TemplateException e) {
                    throw new MailPreparationException("Can't generate Subject Text", e);
                }         
                mimeMessage.setSubject(subjectTextWriter.toString());      
                
                //
                // Create a "text" Multipart message
                //
                
                Template bodyTextTemplate = configuration.getTemplate(bodyTemplatePrefix);
                final StringWriter bodyTextWriter = new StringWriter();
                
                
                try {
                	bodyTextTemplate.process(map, bodyTextWriter);
                } catch (TemplateException e) {
                    throw new MailPreparationException("Can't generate Body Text", e);
                }
                mimeMessage.setText(bodyTextWriter.toString()); 
                

/*                // @TODO - This part handles sending an attachement
                textPart.setDataHandler(new DataHandler(new DataSource() {
                    public InputStream getInputStream() throws IOException {
                        return new StringBufferInputStream(bodyTextWriter.toString());
                    }
                    public OutputStream getOutputStream() throws IOException {
                        throw new IOException("Read-only data");
                    }
                    public String getContentType() {
                        return "text/plain";
                    }
                    public String getName() {
                        return "main";
                    }
                }));
                mp.addBodyPart(textPart);*/
                
                
     
                

/*                // Create a "HTML" Multipart message
                Multipart htmlContent = new MimeMultipart("related");
                BodyPart htmlPage = new MimeBodyPart();
                Template htmlTemplate = configuration.getTemplate(templatePrefix + "-html.ftl");
                final StringWriter htmlWriter = new StringWriter();
                try {
                    htmlTemplate.process(map, htmlWriter);
                } catch (TemplateException e) {
                    throw new MailPreparationException("Can't generate HTML subscription mail", e);
                }
                htmlPage.setDataHandler(new DataHandler(new DataSource() {
                    public InputStream getInputStream() throws IOException {
                        return new StringBufferInputStream(htmlWriter.toString());
                    }
                    public OutputStream getOutputStream() throws IOException {
                        throw new IOException("Read-only data");
                    }
                    public String getContentType() {
                        return "text/html";
                    }
                    public String getName() {
                        return "main";
                    }
                }));
                htmlContent.addBodyPart(htmlPage);
                BodyPart htmlPart = new MimeBodyPart();
                htmlPart.setContent(htmlContent);
                mp.addBodyPart(htmlPart);

                mimeMessage.setContent(mp);*/
            }
        };
        mailSender.send(preparator);
    }

    public void massMail(final List emails, final List contexts, final String bodyTemplatePrefix, final String subjectTemplatePrefix) {
        int i = 0;
        for (Iterator iterator = emails.iterator(); iterator.hasNext(); i++) {
            String email = (String) iterator.next();
            mail(email, (Map) contexts.get( i ), bodyTemplatePrefix, subjectTemplatePrefix);
        }
    }

    /** @spring.property ref="mailSender" */
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /** @spring.property ref="freemarkerConfig" */
    public void setConfiguration(FreeMarkerConfigurer freeMarkerConfigurer) {
        this.configuration = freeMarkerConfigurer.getConfiguration();
    }
    
    private static void checkGoodEmail(final String input) throws MailServerException {
        if (input == null) {
			throw new MailServerException("Sorry, null string is not a good email.");
		}
        int atIndex = input.indexOf('@');
        int dotIndex = input.lastIndexOf('.');
        if (atIndex == -1 || dotIndex == -1 || atIndex >= dotIndex) {
			throw new MailServerException("Error: '" + input + "' is not a valid email value. Please try again.");
		}
        // now check for content of the string
        byte[] s = input.getBytes();
        int length = s.length;
        byte b = 0;

        for (int i = 0; i < length; i++) {
            b = s[i];
            if (b >= 'a' && b <= 'z') {
                // lower char
            } else if (b >= 'A' && b <= 'Z') {
                // upper char
            } else if (b >= '0' && b <= '9' && i != 0) {
                // numeric char
            } else if ( ( b=='_' || b=='-' || b=='.' || b=='@' ) && i != 0 ) {
                // _ char
            } else {
                // not good char, throw an BadInputException
                throw new MailServerException(input + " is not a valid email. Reason: character '" + (char)b + "' is not accepted in an email.");
            }
        }// for

        // last check
        try {
            new javax.mail.internet.InternetAddress(input);
        }
        catch (Exception ex) {
                   logger.error("Error when running checkGoodEmail", ex);
                   throw new MailServerException("Assertion: dont want to occur in Util.checkGoodEmail");
               }        
        
    }
    
}
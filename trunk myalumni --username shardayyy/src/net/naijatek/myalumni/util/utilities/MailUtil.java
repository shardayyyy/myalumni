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
package net.naijatek.myalumni.util.utilities;

import java.util.StringTokenizer;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import net.naijatek.myalumni.framework.exceptions.BadInputException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoaderServlet;


public class MailUtil extends ContextLoaderServlet{

    private static Log logger = LogFactory.getLog(MailUtil.class);
    

    public MailUtil() {// prevent instantiation
    }


    public static void checkGoodEmail(final String input) throws BadInputException {
        if (input == null) {
			throw new BadInputException("Sorry, null string is not a good email.");
		}
        int atIndex = input.indexOf('@');
        int dotIndex = input.lastIndexOf('.');
        if (atIndex == -1 || dotIndex == -1 || atIndex >= dotIndex) {
			throw new BadInputException("Error: '" + input + "' is not a valid email value. Please try again.");
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
                throw new BadInputException(input + " is not a valid email. Reason: character '" + (char)b + "' is not accepted in an email.");
            }
        }// for

        // last check
        try {
            new javax.mail.internet.InternetAddress(input);
        } catch (Exception ex) {
            logger.error("Error when running checkGoodEmail", ex);
            throw new BadInputException("Assertion: dont want to occur in Util.checkGoodEmail");
        }
    }

  /**
   * This method trim the email variable, so if it contains only spaces, then
   * it will be empty string, then we have 0 token :-) The returned value is
   * never null
   *
* @param email String
* @throws BadInputException
* @return String[]
   */
  private static String[] getEmails(String email) throws BadInputException {
        if (email == null) {
			email = "";
		}
        email = email.trim();// very important
        StringTokenizer t = new StringTokenizer(email, ";");
        String[] ret = new String[t.countTokens()];
        int index = 0;
        while(t.hasMoreTokens()) {
            String mail = t.nextToken().trim();
            checkGoodEmail(mail);
            ret[index] = mail;
            //log.debug(ret[index]);
            index++;
        }
        return ret;
    }

    private static String[] getEmails(final String to, final String cc, final String bcc) throws BadInputException {
        String[] toMail = getEmails(to);
        String[] ccMail = getEmails(cc);
        String[] bccMail= getEmails(bcc);
        String[] ret = new String[toMail.length + ccMail.length + bccMail.length];
        int index = 0;
        for (String element : toMail) {
            ret[index] = element;
            index++;
        }
        for (String element : ccMail) {
            ret[index] = element;
            index++;
        }
        for (String element : bccMail) {
            ret[index] = element;
            index++;
        }
        return ret;
    }

  /**
   * This method will return null if there is not any email
   *
* @param email String
* @return javax.mail.internet.InternetAddress[]
* @throws BadInputException
* @throws AddressException
   */
  private static InternetAddress[] getInternetAddressEmails(final String email)
        throws BadInputException, AddressException {
        String[] mails = getEmails(email);
        if (mails.length == 0) {
			return null;// must return null, not empty array
		}

        //log.debug("to = " + mails);
        InternetAddress[] address = new InternetAddress[mails.length];
        for (int i = 0; i < mails.length; i++) {
            address[i] = new InternetAddress(mails[i]);
            //log.debug("to each element = " + mails[i]);
        }
        return address;
    }
}

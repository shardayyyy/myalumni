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

import java.util.List;
import java.util.Map;

import net.naijatek.myalumni.framework.exceptions.MailServerException;


public interface ITemplateMailer {

    /**
     * Send a mail with both a text and a HTML version.
     * @param email the email address where to send the email
     * @param context a {@link Map} of objects to expose to the template engine
     * @param templatePrefix the prefix of the templates to use
     */
    public void mail(String email, Map map, String bodyTemplatePrefix, String subjectTemplatePrefix) throws MailServerException;

    /**
     * Send a mass-mailing with both a text anda HTML version.
     * @param emails a {@link List} of email addresses where to send emails
     * @param contexts a {@link List} of {@link Map}s of objects to expose to the template engine
     * @param templatePrefix the prefix of the templates to us
     */
    public void massMail(List emails, List maps, String bodyTemplatePrefix, String subjectTemplatePrefix) throws MailServerException;
}
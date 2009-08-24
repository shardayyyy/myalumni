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
package net.naijatek.myalumni.framework;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.displaytag.localization.LocaleResolver;

public class MyAlumniLocaleResolver implements LocaleResolver {

	/**
	 * Resolves the current user locale.
	 * 
	 * @return <b> requestLocale </b> user locale.
	 * @param <b>
	 *            request </b> Http request.
	 */
	public Locale resolveLocale(HttpServletRequest request) {

		// Get the Locale (if any) that is stored in the user's session
		HttpSession session = request.getSession();
		Locale sessionLocale = (Locale) session
				.getAttribute(Globals.LOCALE_KEY);

		// Get the user's preferred Locale from the request
		Locale requestLocale = request.getLocale();
		// If was never a Locale in the session or it has changed, set it
		if (sessionLocale == null || (sessionLocale != requestLocale)) {
			// Set the new Locale into the user's session
			session.setAttribute(Globals.LOCALE_KEY, requestLocale);
		}

		return requestLocale;
	}
}
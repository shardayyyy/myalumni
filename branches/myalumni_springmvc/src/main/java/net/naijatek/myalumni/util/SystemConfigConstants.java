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
package net.naijatek.myalumni.util;

public interface SystemConfigConstants {

	// file extension types
	// By default, the javax.imageio package can read GIF, PNG, and JPEG
	// images and can write PNG and JPEG images. See @ ImageUtil.java
	public final static String CONTENT_TYPE = "image/pjpeg,image/gif,image/jpeg,image/png";

	// M E S S A G E F O L D E R S
	public final static String MESSAGE_FOLDERS = "Inbox,Sent,Trash,Storage";

	public final static int NumOfLatestMembers = 15;

	// Default timeout time in seconds
	public static final int DEFAULT_SESSION_TIMEOUT = 900;

	// Mailbox quota
	public static final int MAIL_QUOTA = 200;
	
	// Logo
	public static final int LOGO_MAX_SIZE = 30000;
	public static final int LOGO_HEIGHT = 109; 
	public static final int LOGO_WIDTH = 0;

	// Messages
	public static final String UNAUTHORIZED_MSG = "Insufficient Privilege. User needs to be authorized to perform this controllers.";

	public static final String EXPIRED_SESSION_MSG = "Your Session have expired.";

	public static final String DATE_FORMAT_JSP = "%Y-%m-%d";

	public static final String DATE_TIME_FORMAT_JSP = "%Y-%m-%d %H:%M";

}

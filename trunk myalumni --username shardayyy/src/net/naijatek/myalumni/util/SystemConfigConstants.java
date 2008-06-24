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

	// Messages
	public static final String UNAUTHORIZED_MSG = "Insufficient Privilege. User needs to be authorized to perform this action.";

	public static final String EXPIRED_SESSION_MSG = "Your Session have expired.";

	public static final String DATE_FORMAT_JSP = "%Y-%m-%d";

	public static final String DATE_TIME_FORMAT_JSP = "%Y-%m-%d %H:%M";

}

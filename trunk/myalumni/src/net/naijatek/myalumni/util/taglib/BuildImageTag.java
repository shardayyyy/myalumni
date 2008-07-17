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
package net.naijatek.myalumni.util.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.SystemConfigConstants;
import net.naijatek.myalumni.util.utilities.AppProp;
import net.naijatek.myalumni.util.utilities.SystemProp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class BuildImageTag extends BodyTagSupport {

	private static Log logger = LogFactory.getLog(BuildImageTag.class);

	private String imageUrl = null;

	private String imageType = null;

	private HttpServletRequest request = null;

	private final AppProp app = AppProp.getInstance();

	private final SystemProp prop = SystemProp.getInstance();

	/**
	 * Includes the body of the tag if the page attribute equals the value set
	 * in the 'match' attribute.
	 * 
	 * @return SKIP_BODY if equalsAttribute body content does not equal the
	 *         value of the match attribute, EVAL_BODY_include if it does
	 * @throws JspException
	 */
	@Override
	public final int doStartTag() throws JspException {
		request = (HttpServletRequest) pageContext.getRequest();
		return EVAL_BODY_BUFFERED;
	}

	/**
	 * Process the end of this tag.
	 * 
	 * @throws JspException
	 *             if a JSP exception has occurred
	 * @return int
	 */
	@Override
	public final int doEndTag() throws JspException {

		request = (HttpServletRequest) pageContext.getRequest();

		String uploadDir = BaseConstants.UPLOAD_DIR_NAME;
		String adsUploadDir = BaseConstants.ADS_UPLOAD_DIR_NAME;
		String avatarUploadDir = BaseConstants.AVATAR_UPLOAD_DIR_NAME;
		String seperator = "/";
		String width_ad = app.getValue("image.width");

		String width_avatar = app.getValue("avatar.image.width");
		String height_avatar = app.getValue("avatar.image.height");

		String rootContext = request.getContextPath();

		StringBuffer sb = new StringBuffer();
		if (imageType.equalsIgnoreCase(BaseConstants.TAGLIB_TYPE_ADVERTISEMENT)) {
			if (imageUrl.length() > 0) {
				sb.append("<img src=\"" + rootContext.trim() + seperator
						+ uploadDir + seperator + adsUploadDir + seperator
						+ imageUrl.trim() + "\" border=\"0\" width=\""
						+ width_ad + "\">");
			} else {
				sb
						.append("<img src=\""
								+ rootContext.trim()
								+ seperator
								+ "images"
								+ seperator
								+ "no_picture.gif"
								+ "\" border=\"0\" width=\"150\" height=\"150\" align=\"ABSMIDDLE\">");
			}
			// logger.debug("Advertisement url = " + sb.toString());
		} 
		else if (imageType.equalsIgnoreCase(BaseConstants.TAGLIB_TYPE_EDITABLE_AVATAR)) {
			if (imageUrl.length() > 0) {
				sb.append("<img src=\"" + rootContext.trim() + seperator
						+ uploadDir + seperator + avatarUploadDir + seperator
						+ imageUrl.trim() + "\" border=\"0\" width=\""
						+ width_avatar + "\" " + "height=\"" + height_avatar
						+ "\" align=\"absmiddle\">");
			} else {
				sb.append("<img src=\"" + rootContext.trim() + seperator
						+ "images" + seperator + "no_picture.gif"
						+ "\" border=\"0\" width=\"150\" height=\"150\" align=\"absmiddle\">");
				
			}
		}
		else if (imageType.equalsIgnoreCase(BaseConstants.TAGLIB_TYPE_AVATAR)) {
			if (imageUrl.length() > 0) {
				sb.append("<img src=\"" + rootContext.trim() + seperator
						+ uploadDir + seperator + avatarUploadDir + seperator
						+ imageUrl.trim() + "\" border=\"0\" width=\""
						+ width_avatar + "\" " + "height=\"" + height_avatar
						+ "\" align=\"absmiddle\">");
			} else {
				sb.append("<img src=\"" + rootContext.trim() + seperator
						+ "images" + seperator + "no_picture.gif"
						+ "\" border=\"0\" width=\"150\" height=\"150\" align=\"absmiddle\">");
				
			}
			// logger.debug("Avatar url = " + sb.toString());
		} else if (imageType.equalsIgnoreCase(BaseConstants.TAGLIB_TYPE_IMAGE)) { // Display
																					// a
																					// regular
																					// image
			// int quotaRatio = 0 ;
			int quota = 0;

			try {
				quota = Integer.parseInt(imageUrl);
			} catch (NumberFormatException e) {
				quota = 0;
			}

			double imageWidth = ((quota * 100) / SystemConfigConstants.MAIL_QUOTA) / .3;
			double usedQuotaPercent = quota
					/ (SystemConfigConstants.MAIL_QUOTA / 100);

			if (imageUrl.length() > 0) {
				if (usedQuotaPercent < 70) {
					sb
							.append("<img align=\"left\" width=\""
									+ imageWidth
									+ "\" height=\"15\" src=\""
									+ rootContext.trim()
									+ seperator
									+ "images"
									+ seperator
									+ "icon"
									+ seperator
									+ "percent_low.gif"
									+ "\" vspace=\"0\" hspace=\"0\" alt=\"Low Mail Percentage\"/>");
				} else if (usedQuotaPercent <= 85) {
					sb
							.append("<img align=\"left\" width=\""
									+ imageWidth
									+ "\" height=\"15\" src=\""
									+ rootContext.trim()
									+ seperator
									+ "images"
									+ seperator
									+ "icon"
									+ seperator
									+ "percent_med.gif"
									+ "\" vspace=\"0\" hspace=\"0\" alt=\"Medium Mail Percentage\"/>");
				} else {
					sb
							.append("<img align=\"left\" width=\""
									+ imageWidth
									+ "\" height=\"15\" src=\""
									+ rootContext.trim()
									+ seperator
									+ "images"
									+ seperator
									+ "icon"
									+ seperator
									+ "percent_high.gif"
									+ "\" vspace=\"0\" hspace=\"0\" alt=\"High Mail Percentage\"/>");
				}
			}
		}
		try {
			pageContext.getOut().print(sb.toString());
		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new JspException("IO Problem in BuildImageTag "
					+ e.getMessage());
		}

		return EVAL_PAGE;
	}

	@Override
	public int doAfterBody() throws JspException, JspTagException {
		BodyContent bc = getBodyContent();
		setImageUrl(bc.getString()); // get body string
		bc.clearBody(); // clean up
		return SKIP_BODY;
	}

	/**
	 * Release any acquired resources.
	 */
	@Override
	public void release() {
		super.release();
		imageUrl = null;
	}

	/**
	 * 
	 * @return java.lang.String
	 */
	public String getImageUrl() {
		return imageUrl.trim();
	}

	/**
	 * 
	 * @param imageUrl
	 *            String
	 */
	public void setImageUrl(final String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * 
	 * @return java.lang.String
	 */
	public String getImageType() {
		return imageType;
	}

	/**
	 * 
	 * @param imageType
	 *            String
	 */
	public void setImageType(final String imageType) {
		this.imageType = imageType;
	}
}

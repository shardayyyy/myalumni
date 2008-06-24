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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.service.IMemberService;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.utilities.StringUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * <p>
 * Title: Federal government College System (FeGoCoId System)
 * </p>
 * <p>
 * Description: FeGoCoId System
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Naijatek Solutions
 * </p>
 * 
 * @author Folashade Adeyosoye (shardayyy@naijatek.com)
 * @version 1.0
 */
public class BirthdayListTag extends BodyTagSupport {

	private static Log logger = LogFactory.getLog(BirthdayListTag.class);

	private HttpServletRequest request = null;

	private String tableWidth = null;

	private IMemberService memService;

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

		WebApplicationContext wac = WebApplicationContextUtils
				.getWebApplicationContext(pageContext.getServletContext());
		memService = (IMemberService) wac
				.getBean(BaseConstants.SERVICE_MEMBER_LOOKUP);
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
		StringBuffer sb = new StringBuffer();
		Calendar now = new GregorianCalendar();
		String seperator = "/";
		MemberVO user = new MemberVO();
		List<MemberVO> bdays = new ArrayList<MemberVO>();
		int today = now.get(Calendar.DATE);
		String rowCss = new String();
		String rootContext = request.getContextPath();
		String rootContextName = StringUtil.trimRootContext(request
				.getContextPath());

		try {
			bdays = memService.getBirthdayListOfTheMonth(String.valueOf((now
					.get(Calendar.MONTH) + 1)));

			sb
					.append("<table width=\""
							+ this.getTableWidth()
							+ "\" border=\"0\" cellspacing=\"1\" cellpadding=\"3\" align=\"center\"  class=\"tborder\">");
			sb.append("<tr>");
			sb
					.append("<td height=\"30\" class=\"bg0\" colspan=\"3\">Happy Birthday this month of "
							+ StringUtil.convertToAlphaMonth(now
									.get(Calendar.MONTH) + 1)
							+ " to the following Alumni's.</td>");
			sb.append("</tr>");
			sb.append("<tr  class=\"portlet-section-body\">");
			sb.append("<td height=\"30\" class=\"bg0\">Alumni Name</td>");
			sb.append("<td height=\"30\" class=\"bg0\">Departure Year</td>");
			sb.append("<td height=\"30\" class=\"bg0\">Birthday</td>");
			sb.append("</tr>");

			int size = bdays.size();
			for (int i = 0; i < size; i++) {
				user = (MemberVO) bdays.get(i);
				DateTime dt = new DateTime(user.getDob());
				if (today == dt.getDayOfMonth()) {
					rowCss = "bgH";
				} else {
					rowCss = "portlet-section-body";
				}
				// sb.append("<tr bgcolor=\" + rowCss + \">");
				sb.append("<tr class=\"");
				sb.append(rowCss);
				sb.append("\">");
				sb.append("<td class=\"fieldlabel\">");

				if (today == dt.getDayOfMonth()) {
					sb.append("<img src=\"" + rootContext.trim() + seperator
							+ "/images/icon/bday.jpg\" align=\"absmiddle\">");
				}
				sb.append("<a href=\"/");
				sb.append(rootContextName);
				sb
						.append("/action/member/displayMiniProfile?action=displayMiniProfile&memberUserName="
								+ user.getMemberUserName()
								+ "\" + onclick=\"newPopup(this.href,'name');return false\" title=\"View "
								+ user.getFirstName()
								+ " "
								+ user.getLastName()
								+ " details\">"
								+ user.getFirstName()
								+ " "
								+ user.getLastName() + "</td>");
				sb.append("<td class=\"fieldlabel\">" + user.getYearOut()
						+ "</td>");
				sb.append("<td class=\"fieldlabel\"> "
						+ StringUtil.dobPostfix(dt.getDayOfMonth())
						+ " of "
						+ StringUtil.convertToAlphaMonth(now
								.get(Calendar.MONTH) + 1) + " </td>");
				sb.append("</tr>");
			}
			if (size == 0) {
				rowCss = "portlet-section-body";
				sb.append("<tr class=\"");
				sb.append(rowCss);
				sb.append("\">");
				sb.append("<td class=\"fieldlabel\" colspan=\"3\">None.</td>");
				sb.append("</tr>");
			}
			sb.append("</table>");
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new JspException("IO Problem in BirthdayListTag "
					+ ex.getMessage());
		}

		try {
			pageContext.getOut().print(sb.toString());
		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new JspException("IO Problem in BirthdayListTag "
					+ e.getMessage());
		}

		return EVAL_PAGE;
	}

	@Override
	public int doAfterBody() throws JspException, JspTagException {
		BodyContent bc = getBodyContent();
		bc.clearBody(); // clean up
		return SKIP_BODY;
	}

	/**
	 * Release any acquired resources.
	 */
	@Override
	public void release() {
		super.release();
	}

	/**
	 * 
	 * @return java.lang.String
	 */
	public String getTableWidth() {
		return tableWidth;
	}

	/**
	 * 
	 * @param tableWidth
	 *            String
	 */
	public void setTableWidth(final String tableWidth) {
		this.tableWidth = tableWidth;
	}

}

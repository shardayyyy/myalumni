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
package net.naijatek.myalumni.util.taglib;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;


import net.naijatek.myalumni.framework.struts.MyAlumniUserContainer;
import net.naijatek.myalumni.modules.common.domain.ClassNewsVO;
import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.domain.SystemConfigVO;
import net.naijatek.myalumni.modules.common.service.IClassNewsService;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.utilities.AppProp;


import java.net.URL;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;



public class DisplayClassNewsTag extends BodyTagSupport {

/**
   * LOGGER
   */
  private static Log logger = LogFactory.getLog(DisplayClassNewsTag.class);
  private IClassNewsService classNewsService;
  private HttpServletRequest request = null;
  private HttpSession session = null;
  private MyAlumniUserContainer container = null;
  
  
  /**
   * Includes the body of the tag if the page attribute equals the value set
   * in the 'match' attribute.
   *
   * @return SKIP_BODY if equalsAttribute body content does not equal the
   *   value of the match attribute, EVAL_BODY_include if it does
   * @throws JspException
   */
  @Override
public final int doStartTag() throws JspException
    {
      	request = (HttpServletRequest) pageContext.getRequest();
        session = request.getSession();
        container = (MyAlumniUserContainer)session.getAttribute(BaseConstants.USER_CONTAINER);
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
        classNewsService = (IClassNewsService) wac.getBean(BaseConstants.SERVICE_CLASSNEWS_LOOKUP);
        return EVAL_BODY_BUFFERED;
    }

  /**
   * Process the end of this tag.
   *
   * @throws JspException if a JSP exception has occurred
   * @return int
   */
  @Override
public final int doEndTag() throws JspException {

	   request = (HttpServletRequest) pageContext.getRequest();
       StringBuffer sb = new StringBuffer();
       AppProp ap = AppProp.getInstance();

		
		MemberVO token = null;

		if (container != null) {
			token = container.getToken();
		}
    	   	
    	   	try {        
    	   		List<ClassNewsVO> list = classNewsService.findAllByYearOutAndSystemNews(token.getYearOut());
    	   		request.setAttribute(BaseConstants.LIST_OF_CLASSNEWS, list);
           }
           catch (Exception e) {
               sb.append(ap.getValue("core.errorcode.00708"));//Problem encountered while retrieving class news
           } 

      return EVAL_PAGE;
    }


    @Override
	public int doAfterBody() throws JspException, JspTagException {
      BodyContent bc = getBodyContent();
      bc.clearBody();      // clean up
      return SKIP_BODY;
    }


    /**
     * Release any acquired resources.
     */
    @Override
	public void release() {
        super.release();
    }



  }

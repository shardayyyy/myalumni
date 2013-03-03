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
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;


import net.naijatek.myalumni.entity.SystemConfigVO;
import net.naijatek.myalumni.service.ISystemConfigService;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.utilities.AppProp;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.net.URL;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;



public class DisplayRSSTag extends BodyTagSupport {

/**
   * LOGGER
   */
  private static Log logger = LogFactory.getLog(DisplayRSSTag.class);
  private ISystemConfigService sysConfigService;
  private HttpServletRequest request = null;
  private String maxNumberToDisplay = null;
  
  
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
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
	    sysConfigService = (ISystemConfigService) wac.getBean(BaseConstants.SERVICE_SYSTEM_CONFIG);
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


    	   	SystemConfigVO feed = sysConfigService.getRssFeedSource();
    	   	int limit ;
    	   	try{
    	   		limit = Integer.parseInt(getMaxNumberToDisplay());
    	   	}
    	   	catch(NumberFormatException nfe){
    	   		limit = 5;
    	   	}

    	   	
    	   	try {        
               // Next, we read the fed input.
               URL feedUrl = new URL(feed.getRssUrl());
               //System.out.println(feedUrl);
               SyndFeedInput input = new SyndFeedInput();
              
               XmlReader reader = new XmlReader(feedUrl);
               SyndFeed feeder = input.build(reader);
                         
               
               List feederEntries = feeder.getEntries();
               int end = feederEntries.size();
               end = (end < limit) ? end : limit;
              
               List selectedNewsEntries = feederEntries.subList(0, end);
               
               // We then set this selected news in our feed source.
               request.setAttribute("readNews", feederEntries);
               request.setAttribute("rssHeader", feed.getRssHeader());
           }
           catch (Exception e) {
               sb.append(ap.getValue("core.errorcode.00707"));//Problem encountered while retrieving rss feeds
               System.out.println(e.getMessage());
           } 
           


      return EVAL_PAGE;
    }


    @Override
	public int doAfterBody() throws JspException, JspTagException {
      //System.out.println("doAfterBody()");
      BodyContent bc = getBodyContent();
      //setTableWidth(bc.getString());  // get body string
      bc.clearBody();      // clean up
      return SKIP_BODY;
    }


    /**
     * Release any acquired resources.
     */
    @Override
	public void release() {
        //System.out.println("release()");
        super.release();
    }

	public String getMaxNumberToDisplay() {
		return maxNumberToDisplay;
	}

	public void setMaxNumberToDisplay(String maxNumberToDisplay) {
		this.maxNumberToDisplay = maxNumberToDisplay;
	}


  }

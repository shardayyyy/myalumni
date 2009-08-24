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



import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import net.naijatek.myalumni.modules.common.domain.MemberVO;
import net.naijatek.myalumni.modules.common.domain.XlatDetailVO;
import net.naijatek.myalumni.modules.common.service.IMemberService;
import net.naijatek.myalumni.modules.common.service.ISystemConfigService;
import net.naijatek.myalumni.modules.common.service.IXlatService;
import net.naijatek.myalumni.util.BaseConstants;
import net.naijatek.myalumni.util.utilities.AppProp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


 


public class BuildDropdownTag extends BodyTagSupport {
    private String fieldName = null;
    private String group = null;
    private String objectValue = null;
    private String onChange = null;
    private String type = null;
    private String firstOptionBlank = null;
    private String titleKey = null;
    private IXlatService xlatService;
    private IMemberService memberService;
    private ISystemConfigService systemConfigService;
    private AppProp apProp = AppProp.getInstance();
    



    private Log logger = LogFactory.getLog(this.getClass());

//--------------------------------------------------------------------------
//--
//--                   P U B L I C   M E T H O D S
//--
//--------------------------------------------------------------------------

    /**
     * Set the required tag attribute <b>fieldName</b>.
     *
     * @param str
     *     list
     */
    public final void setFieldName(String str){
      fieldName = str;
    }
    /**
     * @return String
     */
    public final String getFieldName(){
      return fieldName;
    }


    /**
     * doStartTag
     *
     * @exception JspException
     * @return int
     */
    public final int doStartTag() throws JspException
    {
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
        xlatService = (IXlatService) wac.getBean(BaseConstants.SERVICE_XLAT_LOOKUP);
        memberService = (IMemberService) wac.getBean(BaseConstants.SERVICE_MEMBER_LOOKUP);
        systemConfigService = (ISystemConfigService) wac.getBean(BaseConstants.SERVICE_SYSTEM_CONFIG);

        return EVAL_BODY_BUFFERED;
    }

    /**
     * Save the associated label from the body content.
     *
     * @exception JspException if a JSP exception has occurred
     * @return int
     */
    public int doAfterBody() throws JspException {
        BodyContent bc = getBodyContent();
        setObjectValue(bc.getString());
      return (SKIP_BODY);
    }

    /**
     * Process the end of this tag.
     *
     * @exception JspException if a JSP exception has occurred
     * @return int
     */

    public final int doEndTag() throws JspException {
       StringBuffer sbContent = new StringBuffer();
       String value = null;

        value = renderSelectStartElement();
        if (value != null)
            sbContent.append(value);
        sbContent.append(renderListOptions());
        sbContent.append("</select>");

         try{
           pageContext.getOut().print(sbContent.toString());
         }catch(Exception e){
           logger.debug(e.getMessage());
           throw new JspException("IO Problem in BuildDropdownTag " + e.getMessage());
         }
      return EVAL_PAGE;
    }


//--------------------------------------------------------------------------
//--
//--                 P R O T E C T E D   M E T H O D S
//--
//--------------------------------------------------------------------------

    /**
     * Create an appropriate select option element based on our parameters.
     * @return
     * @throws JspException
     */
     protected String renderListOptions() throws JspException {
        StringBuffer sb = new StringBuffer();

        // GENERIC i.e XlatDetailVO
        if (type.equals(BaseConstants.DROPDOWN_TYPE_GENERIC)){
            sb.append(renderGenericDropDown());
        }
        // SPECIFIC
        else if (type.equalsIgnoreCase(BaseConstants.DROPDOWN_TYPE_SPECIFIC)){
            //other drop down menus
            if (group.equalsIgnoreCase(BaseConstants.DROPDOWN_MEMBER)){
                sb.append(renderMemberDropDown());
            }
            else if (group.equalsIgnoreCase(BaseConstants.DROPDOWN_YEAROUT)){
            	sb.append(renderYearOutDropDown());
            }
            else if (group.equalsIgnoreCase(BaseConstants.DROPDOWN_YEARIN)){
            	sb.append(renderYearInDropDown());
            }
            else if (group.equalsIgnoreCase(BaseConstants.DROPDOWN_CLASSYEAR)){
            	sb.append(renderClassYearDropDown());
            }
            
            else{
                     sb.append("Drop Down Group Not Found.");
             }
        }
        return sb.toString();
     }


    /**
     * Creates the drop down for a generic drop down
     * @return
     */
    protected String renderGenericDropDown(){
        StringBuffer sb = new StringBuffer();
        List luList = xlatService.getActiveGroupDetails(group);
        XlatDetailVO detail = new XlatDetailVO();

        if (getFirstOptionBlank() != null && getFirstOptionBlank().equalsIgnoreCase(BaseConstants.BOOLEAN_YES)){
        	sb.append("<option value=\"\">-- Select --</option>");
        }
        for (int i = 0 ; i < luList.size() ; i++){
            detail = (XlatDetailVO)luList.get(i);

            sb.append("<option value=\"");
            sb.append(detail.getLookupCodeId().trim());
            sb.append("\"");
            if (getObjectValue()!= null && getObjectValue().trim().length()>0 && getObjectValue().equals(detail.getLookupCodeId().trim())) {
               sb.append(" selected");
            }
            sb.append(">");
            sb.append(detail.getLabel().trim());
            sb.append("</option>");
        }
        return sb.toString();
    }

    


    protected String renderYearOutDropDown(){
    	
        int startYear =  systemConfigService.getFirstYearofSchool();  // Integer.parseInt(sysProp.getValue("years.start"));
        GregorianCalendar ct = new GregorianCalendar();
        int currentYear = ct.get(Calendar.YEAR);
        
        StringBuffer sb = new StringBuffer();

        sb.append("<option value=\"\">-- Select --</option>");
        for (int i = currentYear + 6; i > startYear - 1; i--) { // 6 year after year in will give us year out, why 6, i dont know, sound resonable
            sb.append("<option value=\"");
            sb.append(String.valueOf(i));
            sb.append("\"");
            if (getObjectValue()!= null && getObjectValue().trim().length()>0 && getObjectValue().equals(String.valueOf(i))) {
               sb.append(" selected");
            }
            sb.append(">");
            sb.append(String.valueOf(i));
            sb.append("</option>");
        }
        return sb.toString();
    }
    
    

    protected String renderYearInDropDown(){
        int startYear =  systemConfigService.getFirstYearofSchool();  // Integer.parseInt(sysProp.getValue("years.start"));
        GregorianCalendar ct = new GregorianCalendar();
        int currentYear = ct.get(Calendar.YEAR);
        StringBuffer sb = new StringBuffer();
             
        sb.append("<option value=\"\">-- Select --</option>");
        for (int i = currentYear; i > startYear - 1; i--) {

            sb.append("<option value=\"");
            sb.append(String.valueOf(i));
            sb.append("\"");
            if (getObjectValue()!= null && getObjectValue().trim().length()>0 && getObjectValue().equals(String.valueOf(i))) {
               sb.append(" selected");
            }
            sb.append(">");
            sb.append(String.valueOf(i));
            sb.append("</option>");
        }
        return sb.toString();
    }
    
    
    protected String renderClassYearDropDown(){
        int startYear =  systemConfigService.getFirstYearofSchool();  // Integer.parseInt(sysProp.getValue("years.start"));
        GregorianCalendar ct = new GregorianCalendar();
        int currentYear = ct.get(Calendar.YEAR);
        StringBuffer sb = new StringBuffer();
             
        sb.append("<option value=\"\">-- Select --</option>");
        for (int i = currentYear; i > startYear - 1; i--) {

            sb.append("<option value=\"");
            sb.append(String.valueOf(i));
            sb.append("\"");
            if (getObjectValue()!= null && getObjectValue().trim().length()>0 && getObjectValue().equals(String.valueOf(i))) {
               sb.append(" selected");
            }
            sb.append(">");
            sb.append(String.valueOf(i));
            sb.append("</option>");
        }
        return sb.toString();
    }
    
    
    protected String renderMemberDropDown(){
        StringBuffer sb = new StringBuffer();
        List luList = memberService.findAll();
        MemberVO member = new MemberVO();


        sb.append("<option value=\"\">-- Select --</option>");
        for (int i = 0 ; i < luList.size() ; i++){
            member = (MemberVO)luList.get(i);

            sb.append("<option value=\"");
            sb.append(member.getMemberId().trim());
            sb.append("\"");
            if (getObjectValue()!= null && getObjectValue().trim().length()>0 && getObjectValue().equals(member.getMemberId().trim())) {
               sb.append(" selected");
            }
            sb.append(">");
            sb.append(member.getFirstName().trim() + " - " + member.getLastName().trim());
            sb.append("</option>");
        }
        return sb.toString();
    }

    /**
     * Create an appropriate select start element based on our parameters.
     *
     * @exception JspException
     * @return String
     */
    private String renderSelectStartElement() throws JspException {
      StringBuffer results = new StringBuffer("<select");
      String title = null;

      if (titleKey == null || titleKey.length() == 0){
        title = titleKey;
      }
      else{
    	  title = apProp.getValue(titleKey);
      }

      results.append(" name=\"");
      results.append(fieldName);
      results.append("\"");

      results.append(" title=\"");
      results.append(title);
      results.append("\"");

      if (onChange != null && onChange.length() > 0){
          results.append(" onChange=\"");
          results.append(onChange);
          results.append("\"");
      }

      results.append(">");

      logger.debug("in renderSelectStartElement(), select - " + results.toString());
      return results.toString();
    }


    /**
     * Release any acquired resources.
     */
    public void release() {
      super.release();
      fieldName = null;
      group = null;
      onChange = null;
      type = null;
      titleKey = null;
      firstOptionBlank = null;
    }


    public void setGroup(String groupId) {
        this.group = groupId;
    }

    public String getGroup() {
        return group;
    }

    public void setObjectValue(String objectValue) {
        this.objectValue = objectValue;
    }

    public String getObjectValue() {
        return objectValue;
    }

    public final void setOnChange(String onChange) {
        this.onChange = onChange;
    }

    public final String getOnChange() {
        return onChange;
    }

    public final void setType(String type) {
        this.type = type;
    }

    public final String getType() {
        return type;
    }
	public final String getFirstOptionBlank() {
		return firstOptionBlank;
	}
	public final void setFirstOptionBlank(String firstOptionBlank) {
		this.firstOptionBlank = firstOptionBlank;
	}
	public final String getTitleKey() {
		return titleKey;
	}
	public final void setTitleKey(String titleKey) {
		this.titleKey = titleKey;
	}
	
	
}

<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>


<html:form  action="/admin/maintainScroll?action=maintainScroll">
      <table width="100%" cellspacing="1" cellpadding="3" class="tborder">
	<tr class="bg0">
	  <td height="30" colspan="3" align="left">Manage System Page Scroll </td>
	</tr>
	<tr class="portlet-section-body">
	  <td colspan="2" nowrap><strong> Current Scroll: </strong></td>
	  <td>	 	
			<tiles:insert name="/jsp/layout/common/scroll.jsp"/>
       </td>
	</tr>
	<tr class="portlet-section-body">
	  <td nowrap><strong>Add Scrolls:</strong></td>
	  <td nowrap><html:radio property="type" value="new"/></td>
	  <td><html:text property="scrollText" size="100" maxlength="200" onclick="document.forms[0].type[0].checked=true;"/></td>
	</tr>

	<tr class="portlet-section-body">
	  <td width="104" nowrap><strong>Previous Scrolls:</strong></td>
	  <td width="19" nowrap><html:radio property="type" value="update"/></td>
	  <td>

			<html:select property="scrollId" titleKey="label.scrolls" onclick="document.forms[0].type[1].checked=true;">
						<%--<option value="" selected>-- Select a scroll to display --</option> --%>
						<html:options collection="luAllScrolls" property="scrollId" labelProperty="scrollText"/>
			</html:select>
			Select a scroll to display
	  </td>
	</tr>
	<tr class="portlet-section-body">
	  <td nowrap><strong>Important Message:</strong></td>
	  <td nowrap><html:checkbox property="priority" value="Y" title="Is this an important announcement?"/></td>
	  <td>&nbsp;</td>
	</tr>
	<tr align="center" class="portlet-section-body">
	  <td colspan="3" valign="top" nowrap>
		<html:submit styleClass="button">
			  <bean:message key="button.updateaddscroll"/>
		</html:submit>
         &nbsp;&nbsp;
           <html:link page="/jsp/admin/system/index.jsp" onmouseover="window.status=''; return true">
               <html:img page="/images/cancel.png" border="0" align="absbottom"/>    
           </html:link>		
	  </td>
	</tr>
      </table>
    </html:form>


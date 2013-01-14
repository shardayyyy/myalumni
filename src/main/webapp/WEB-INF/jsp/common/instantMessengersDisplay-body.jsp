<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>




<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
          <tr>
            <td height="30" colspan="2" class="bg0"><bean:message key="table.title.instantMessengers"/></td>
          </tr>
          <tr class="portlet-section-body">
            <td width="25%" class="fieldlabel" nowrap="nowrap"><bean:message key="label.instantmessengers"/>:&nbsp;<html:img page="/images/icon/messengers.png" align="absmiddle"/></td>
            <td width="75%">

            	<logic:notEmpty name="memberForm" property="messengers">
                	<logic:iterate id="im" name="memberForm" property="messengers" indexId="recIdx">
			            <c:out value="${im.label}"/>,
                	</logic:iterate>
                </logic:notEmpty>
           </td>
          </tr>
</table>

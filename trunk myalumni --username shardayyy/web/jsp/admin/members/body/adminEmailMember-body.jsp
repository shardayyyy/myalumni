<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<html:form action="/admin/emailMember?action=emailMember">
  <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
    <tr>
      <td height="30" colspan="2" class="bg0">Email Member</td>
    </tr>
    <tr class="portlet-section-body">
      <td width="10%" class="fieldlabel">To:</td>
      <td width="90%"><html:text property="guestEmail" size="60" maxlength="60"/></td>
    </tr>
    <tr class="portlet-section-body">
      <td width="10%" class="fieldlabel">Subject:</td>
      <td width="90%"><html:text property="subject" size="60" maxlength="60"/></td>
    </tr>
    <tr class="portlet-section-body">
      <td valign="top" class="fieldlabel">Message:</td>
      <td><html:textarea property="messageText" cols="60" rows="10"/></td>
    </tr>
    <tr class="portlet-section-body">
      <td>&nbsp;</td>
      <td>
      <html:submit styleClass="button">
      		<bean:message key="button.submit"/>
      </html:submit>
      	&nbsp;
       <html:reset styleClass="button">
      		<bean:message key="button.reset"/>
      </html:reset>
     </td>
    </tr>
  </table>
</html:form>

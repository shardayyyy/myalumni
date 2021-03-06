<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<html:form action="/admin/emailMember?action=emailMember">
  <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
    <tr>
      <td height="30" colspan="2" class="bg0"><bean:message key="label.admin.member.emailmember"/></td>
    </tr>
    <tr class="portlet-section-body">
      <td width="10%" class="fieldlabel"><bean:message key="label.admin.member.to"/>:</td>
      <td width="90%">
			<html:text property="guestEmail" titleKey="label.admin.member.to"/>                                  
      </td>
    </tr>
    <tr class="portlet-section-body">
      <td width="10%" class="fieldlabel"><bean:message key="label.admin.member.subject"/>:</td>
      <td width="90%"><html:text property="subject" size="60" maxlength="60" titleKey="label.admin.member.subject"/></td>
    </tr>
    <tr class="portlet-section-body">
      <td valign="top" class="fieldlabel"><bean:message key="label.admin.member.message"/>:</td>
      <td><html:textarea property="messageText" cols="60" rows="10" titleKey="label.admin.member.message"/></td>
    </tr>
    <tr class="portlet-section-body">
      <td>&nbsp;</td>
      <td>
      <html:submit>
      		<bean:message key="button.submit"/>
      </html:submit>
      	&nbsp;
       <html:reset>
      		<bean:message key="button.reset"/>
      </html:reset>
     </td>
    </tr>
  </table>
</html:form>

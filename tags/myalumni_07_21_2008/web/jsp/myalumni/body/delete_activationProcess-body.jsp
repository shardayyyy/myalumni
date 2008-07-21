<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/myalumni-taglibs.tld" prefix="myalumni" %>



<html:form action="/memberActivateAccount?action=memberActivateAccount">
  <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
    <tr>
      <td height="30" colspan="2" class="bg0"><bean:message key="table.title.activateaccount"/> </td>
    </tr>
    <tr class="portlet-section-body">
      <td width="24%" class="fieldlabel"><bean:message key="label.activatempusername"/>:</td>
      <td width="76%"><html:text property="memberUserName" size="60" maxlength="60" titleKey="label.username"/></td>
    </tr>
    <tr class="portlet-section-body">
      <td class="fieldlabel"><bean:message key="label.activationcode"/>:</td>
      <td><html:text property="memberActivateCode" size="60" maxlength="60" titleKey="label.activationcode"/></td>
    </tr>
    <tr class="portlet-section-body">
      <td class="fieldlabel"><bean:message key="label.primaryemail"/>:</td>
      <td><html:text property="email" size="60" maxlength="60" titleKey="label.email"/></td>
    </tr>
    <tr align="center" class="portlet-section-body">
      <td colspan="2"><html:submit styleClass="button"><bean:message key="button.submit"/></html:submit></td>
    </tr>
  </table>
</html:form>

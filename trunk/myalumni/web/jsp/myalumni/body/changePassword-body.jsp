<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>



<html:form action="/member/updatePassword?action=updateMemberPassword">
<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
  <tr>
    <td height="20" colspan="2" class="bg0"><bean:message key="table.title.changepassword"/> </td>
  </tr>
  <tr class="portlet-section-body">
    <td width="25%" class="fieldlabel"><bean:message key="label.oldpassword"/>:</td>
    <td width="75%"><html:password property="oldMemberPassword" size="25" maxlength="25" titleKey="label.oldpassword"/></td>
  </tr>
  <tr class="portlet-section-body">
    <td class="fieldlabel"><bean:message key="label.newpassword"/>:</td>
    <td><html:password property="memberPassword" size="25" maxlength="25" titleKey="label.password"/></td>
  </tr>
  <tr class="portlet-section-body">
    <td class="fieldlabel"><bean:message key="label.confirmnewpassword"/>:</td>
    <td><html:password property="memberPasswordConfirm" size="25" maxlength="25" titleKey="label.passwordconfirm"/></td>
  </tr>
  <tr align="center" class="portlet-section-body">
    <td colspa:submit styleClass="button">
    		<bean:message key="button.submit"/>
    	</html:submit>
    	&nbsp;html:cancel styleClass="button">
    		<bean:message key="button.cancel"/>
    	</html:cancel>

    </td>
    </tr>

</table>
</html:form>

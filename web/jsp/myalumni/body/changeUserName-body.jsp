<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>





<html:form action="/member/finalizeAccountActivation?action=finalizeAccountActivation">
    	<html:hidden property="memberActivateCode"/>
    	<html:hidden property="email"/>
    	<html:hidden property="memberTempUserName"/>
<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
  <tr>
    <td height="30" colspan="2" class="bg0"><bean:message key="table.title.activateaccount"/></td>
  </tr>
      <tr class="portlet-section-body">
        <td width="21%"><span class="fieldlabel"><bean:message key="label.yournewusername"/>:<font color="#cc0000">*</font></span>
        </td>
        <td width="79%"><html:text property="memberUserName" size="30" maxlength="30" titleKey="label.username"/>   Please enter your new username.</td>
      </tr>
  <tr class="portlet-section-body">
    <td><span class="fieldlabel"><bean:message key="label.password"/>:<font color="#cc0000">*</font></span></td>
    <td><html:password property="password" size="30" maxlength="30" titleKey="label.password"/>
    </td>
  </tr>
  <tr class="portlet-section-body">
    <td><span class="fieldlabel"><bean:message key="label.retypepassword"/>:<font color="#cc0000">*</font></span></td>
    <td><html:password property="passwordConfirm" size="30" maxlength="30" titleKey="label.passwordconfirm"/>
    </td>
  </tr>
  <tr align="center" class="portlet-section-body">
    <td colspan="2" valign="top">

      	<html:submit styleClass="button">
      		<bean:message key="button.submit"/>
      	</html:submit>
    	&nbsp;&nbsp;&nbsp;
    	<html:cancel styleClass="button">
    		<bean:message key="button.cancel"/>
    	</html:cancel>
     </td>
    </tr>
</table>
</html:form>



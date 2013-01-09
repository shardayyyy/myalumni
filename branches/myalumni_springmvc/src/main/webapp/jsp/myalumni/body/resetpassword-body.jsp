<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

DELETE THIS PAGE

<html:form action="/resetpassword?action=resetpassword">
    <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
      <tr>
        <td height="30" colspan="2" class="bg0">Reset Password </td>
      </tr>
      <tr class="portlet-section-body">
        <td width="21%"><span class="fieldlabel"><bean:message key="label.username"/>:<font color="#cc0000">*</font></span>
        </td>
        <td width="79%"><html:text property="memberUserName" size="30" maxlength="30" titleKey="label.username"/></td>
      </tr>
      <tr class="portlet-section-body">
        <td width="21%"><span class="fieldlabel"><bean:message key="label.primaryemail"/>:<font color="#cc0000">*</font></span>
        </td>
        <td width="79%"><html:text property="email" size="60" maxlength="60" titleKey="label.email"/></td>
      </tr>
      <tr class="portlet-section-body">
        <td width="21%"><span class="fieldlabel"><bean:message key="label.password"/>:<font color="#cc0000">*</font></span>
        </td>
        <td width="79%"><html:password property="memberPassword" size="30" maxlength="30" titleKey="label.password"/></td>
      </tr>
      <tr class="portlet-section-body">
        <td><span class="fieldlabel"><bean:message key="label.retypepassword"/>:<font color="#cc0000">* </font></span> </td>
        <td><html:password property="memberPasswordConfirm" size="30" maxlength="30" titleKey="label.passwordconfirm"/></td>
      </tr>
      <tr class="portlet-section-body">
        <td><span class="fieldlabel"><bean:message key="label.activationcode"/>:<font color="#cc0000">* </font></span> </td>
        <td><html:text property="memberActivateCode" size="50" maxlength="50" titleKey="label.memberactivationcode"/></td>
      </tr>
      <tr class="portlet-section-body">
        <td align="center" valign="middle"><br>
   <td>	<html:submit styleClass="button">
                    <bean:message key="button.submit"/>
                </html:submit>
        </td>
      </tr>
    </table>
  </html:form>

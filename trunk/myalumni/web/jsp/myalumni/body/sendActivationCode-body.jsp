<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>




<html:form  action="/member/forgotPassword?action=forgotPassword">
  <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
    <tr>
      <td height="30" colspan="2" class="bg0"> Get your account activation and password change instruction </td>
    </tr>
    <tr class="portlet-section-body">
      <td width="21%" align="right" class="fieldlabel"><bean:message key="label.username"/>:<font color="#cc0000">*</font> </td>
      <td width="79%"><html:text property="memberUserName" size="25" maxlength="25" titleKey="label.username"/></td>
    </tr>
    <tr class="portlet-section-body">
      <td align="right" class="fieldlabel"><bean:message key="label.primaryemail"/>:<font color="#cc0000">*</font></td>
      <td><html:text property="email" size="60" maxlength="60" titleKey="label.email"/>
                  <html:img page="/images/email.gif" titleKey="label.primaryemail" width="15" height="15" align="absmiddle"/></td>
    </tr>
    <tr align="center" class="portlet-section-body">
<td colspan="2">
      
    	<html:submit styleClass="button">
    		<bean:message key="button.activationInstructions"/>
    	</html:submit>
      </td>
         
    </tr>
  </table>
</html:form>

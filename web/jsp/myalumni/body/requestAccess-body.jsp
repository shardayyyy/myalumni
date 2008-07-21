<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<html:form action="/getActivationInstructions?action=getActivationInstructions">
  <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
    <tr>
      <td height="30" colspan="2" class="bg0"><bean:message key="table.title.activateaccount"/></td>
    </tr>
    <tr class="portlet-section-body">
      <td colspan="2"><p><strong>IP Address:</strong> <strong class="graysmall">logged... </strong></p></td>
      </tr>
    <tr class="portlet-section-body">
      <td colspan="2"><br>
      	<ul>
      	<li>Your registered email is required to activate your account.</li>
      	<li>Please enter your email below, and an email would be sent to you on further instructions on how to activate your account.</li>
      	<li>For further help, please send am email to <c:out value="${ORG_EMAIL}" default="[ORGANIZATION EMAIL NOT CONFIGURED]"/> from your email account.</li>
      	</ul>
      </td>
      </tr>
    <tr class="portlet-section-body">
      <td width="13%" class="fieldlabel" align="right"><bean:message key="label.primaryemail"/>:<font color="#cc0000">*</font> </td>
      <td width="87%"><html:text property="email" size="60" maxlength="60" titleKey="label.email"/></td>
    </tr>
    <tr class="portlet-section-body">
      <td colspan="2">
      	<html:submit styleClass="button">
      		<bean:message key="button.submit"/>
      	</html:submit>
      	&nbsp;&nbsp;
      	<html:cancel styleClass="button">
      		<bean:message key="button.cancel"/>
      	</html:cancel>
      	</td>
      </tr>
  </table>
  </html:form>

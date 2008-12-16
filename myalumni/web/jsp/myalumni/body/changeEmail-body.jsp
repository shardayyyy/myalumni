<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<html:form action="/member/updateEmail?action=updateMemberEmail">
<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
  <tr>
    <td height="30" colspan="2" class="bg0"><bean:message key="table.title.changeemailaddress"/> </td>
  </tr>
  <tr class="portlet-section-body">
    <td width="25%" class="fieldlabel"><bean:message key="label.currentemail"/>:</td>
    <td width="75%"><c:out value="${USER_CONTAINER.token.email}"/></td>
  </tr>
  <tr class="portlet-section-body">
    <td class="fieldlabel"><bean:message key="label.entercurrentemail"/>:<font color="#cc0000">*</font></td>
    <td><html:text property="email" size="60" maxlength="60" titleKey="label.email"/></td>
  </tr>
  <tr class="portlet-section-body">
    <td class="fieldlabel"><bean:message key="label.entercurrentemailconfirm"/>:<font color="#cc0000">*</font></td>
    <td><html:text property="emailConfirm" size="60" maxlength="60" titleKey="label.emailconfirm"/></td>
  </tr>
  <tr align="center" class="portlet-section-body">
    <td colspansubmit styleClass="button">
    		<bean:message key="button.submit"/>
    	</html:submit>
    	&nbsp;&tml:cancel styleClass="button">
    		<bean:message key="button.cancel"/>
    	</html:cancel>

    </td>
    </tr>
</table>
</html:form>

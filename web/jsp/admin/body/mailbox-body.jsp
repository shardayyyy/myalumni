<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>


<tiles:insert name="/jsp/common/mailQuota-body.jsp" />
<table width="100%"  border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td width="150" valign="top">
      <%-- Message Folders --%>
      <tiles:insert name="/jsp/common/mailFolder-body.jsp?MODULE=ADMIN"/>
    </td>
    <td valign="top" align="left">
    <%-- Mail Body --%>
    <html:form  action="/admin/deleteMail?action=deleteMail">
	    <html:hidden property="type" value="reply"/>
	    <html:hidden property="roleType" value="ADMIN"/>
	    <input type="hidden" name="privAdminMove" value="yes">
	    <input type="hidden" name="privAdminDelete" value="yes">
	    <html:hidden property="privMsgsAction"/>	   
	    <tiles:insert name="/jsp/common/mailboxContent-body.jsp?MODULE=ADMIN"/>
    </html:form>

    </td>
  </tr>
</table>
<p></p>

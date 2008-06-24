<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>


<tiles:insert name="/jsp/common/mailQuota-body.jsp" />
<table width="99%"  border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td width="150" valign="top">
      <%-- Message Folders --%>
      <tiles:insert name="/jsp/common/mailFolder-body.jsp"/>
    </td>
    <td valign="top" align="left">
    <%-- Mail Body --%>
    <html:form  action="/member/deleteMail?action=deleteMail">
            <html:hidden property="privMsgsAction"/>
            <tiles:insert name="/jsp/common/mailboxContent-body.jsp"/>
    </html:form>

    </td>
  </tr>
</table>
<p></p>






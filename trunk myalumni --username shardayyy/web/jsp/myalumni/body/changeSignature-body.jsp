<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>




<html:form action="/member/updateSignature?action=updateMemberSignature">
<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
  <tr>
    <td height="30" colspan="2" class="bg0"><bean:message key="table.title.changesignature"/></td>
  </tr>
  <tr class="portlet-section-body">
    <td align="right" valign="top" class="fieldlabel"><bean:message key="label.signature"/>:</td>
    <td><c:out value="${USER_CONTAINER.token.signature}"/><br><br>
    <html:textarea property="signature" cols="40" rows="5" titleKey="label.signature"/>&nbsp;Maximum Length = <bean:message key="signature.length"/></td>
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



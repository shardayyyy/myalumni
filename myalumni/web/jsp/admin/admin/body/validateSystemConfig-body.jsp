<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>


<br><br>
<table width="300" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
          <tr>
            <td height="30" class="bg0">Validating System Confuration...</td>
          </tr>
          <tr class="portlet-section-body">
            <td align="center"><br><p>

		<html:form action="/admin/validateSystemConfig">
		        <html:hidden property="action" value="validateSystemConfig"/>
					    <html:submit><bean:message key="button.validate"/></html:submit>
		</html:form>


                </p>

            </td>
          </tr>
</table>
<br><br><br><br>



<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:url var="listlogfiles" value="/action/admin/listSystemLogs?action=listSystemLogs"/>
<c:url var="backupsystemlog" value="/action/test"/>



<script language="javaScript" type="text/Javascript">
function submitMe(button)
{
  document.systemForm.submit();
}
</script>

<html:form  action="/admin/viewSystemLogs?action=viewSystemLogs">
<html:hidden property="logFileName"/>
<table class="tborder" width="98%" cellspacing="1" cellpadding="3">
  <tr class="bg0">
    <td>Log file Information:</td>
    <td align="right">   &laquo;&laquo; <a href="<c:out value="${listlogfiles}"/>">Back to List of Log Files</a>&nbsp;&nbsp;</td>
  </tr>
  <tr class="portlet-section-body">
    <td width="20%">File log name:</td>
    <td width="80%"><bean:write name="systemForm" property="logFileName"/></td>
  </tr>
  <tr class="portlet-section-body">
    <td>File log size:</td>
    <td><bean:write name="systemForm" property="logFileHumanSize"/> (<bean:write name="systemForm" property="logFileSize"/> bytes)</td>
  </tr><tr class="portlet-section-body">
    <td>How many lines do you want to view?</td>
    <td>
      <html:select property="lineCount" onchange="submitMe();">
        <html:option value="25">25</html:option>
        <html:option value="50">50</html:option>
        <html:option value="100">100</html:option>
        <html:option value="200">200</html:option>
        <html:option value="400">400</html:option>
        <html:option value="800">800</html:option>
        <html:option value="1000">1000</html:option>
        <html:option value="1200">1200</html:option>
        <html:option value="1400">1400</html:option>
      </html:select> Before filter
      <html:select property="logType" onchange="submitMe();">
        <html:option value="ALL">All</html:option>
        <html:option value="TRACE">Only Trace</html:option>
        <html:option value="DEBUG">Only Debug</html:option>
        <html:option value="INFO">Only Info</html:option>
        <html:option value="WARN">Only Warn</html:option>
        <html:option value="ERROR">Only Error</html:option>
        <html:option value="FATAL">Only Fatal</html:option>
      </html:select>
    </td>
  </tr>

</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr class="portlet-section-body">
    <td colspan="2">

      <iframe src="logFrame.jsp?linecount=50&amp;logType=<bean:write name="systemForm" property="logType"/>" align="middle"
        frameborder="0" height="400" width="100%" marginheight="0" marginwidth="0" scrolling="auto"></iframe>

    </td>
  </tr>
</table>
</html:form>




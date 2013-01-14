<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/string-taglib.tld" prefix="str" %>

<%--<bean:write name="systemForm" property="logType"/>
--%><table width="98%" border="0" cellspacing="0" cellpadding="0">
  <tr class="bg0" >
    <td height="30" width="100">Line</td>
    <td>Content</td>
  </tr>
  <tr class="portlet-section-body">
    <td colspan="2">

      <%
      String[] lines = (String[])session.getAttribute("logContent");
      String logType = (String)request.getParameter("logType");
      %>
      <b>Log Level = <%=logType%></b>
      <table width="100%" border="0" cellspacing="0" cellpadding="5">
        <%
        int index = 0;
        for ( int i = 0 ; i < lines.length ; i++ ) {%>
          <tr>
            <td class="bg4" width="20"><%=index++ +1%></td>
            <td nowrap>
                <%  if ((lines[i].indexOf("WARN") >= 0 ) || (lines[i].indexOf("ERROR") >= 0) || (lines[i].indexOf("FATAL") >= 0)) {%>
                      <span class="redsmall"><%=lines[i]%></span>
                  <%} else if ((lines[i].indexOf("INFO") >= 0 )) {%>
                       <span class="greensmall"><%=lines[i]%></span>
                  <%} else {%>
                       <span class="blacksmall"><%=lines[i]%></span>
                  <%}%>
            </td>
          </tr>
          <%}//for
          %>
      </table>
    </td>
  </tr>
</table>

<c:remove var="logContent" scope="session"/>

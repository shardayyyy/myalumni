<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>


<%-- related path --%>
<c:url var="PRIVACY" value="/jsp/privacy.jsp" />

<table width="100%" border="0" cellpadding="2" cellspacing="1">
      <tr class="bg0">
        <td  height="30" align="center" nowrap> <div align="right"> 
        <a href="<c:out value="${PRIVACY}"/>">Privacy Policy </a>&nbsp;</div></td>
        </tr>
    </table>

<tiles:insert name="/jsp/layout/common/copyright.jsp"/>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>






<table width="75%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
  <tr>
    <td height="30" class="bg0">About <c:out value="${ORGANIZATION_NAME}"/></td>
  </tr>
  <tr class="portlet-section-body">
    <td>
			<p>
					
					<c:choose>
						<c:when test="${ORGANIZATION_ABOUTUS eq '' or ORGANIZATION_ABOUTUS eq NULL}">
								<center>Your About Us has not been configured yet.</center>
						</c:when>
						<c:otherwise>
							<c:out value="${ORGANIZATION_ABOUTUS}" escapeXml="false"/>
						</c:otherwise>
					</c:choose>
			        
			</p>	

    </td>
  </tr>
</table>
<p></p>

	<%-- Begining of Head boys and girls and principles
	<tiles:insert name="/jsp/common/frontpage/principlesandprefects-body.jsp" flush="true"/> --%>

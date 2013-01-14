<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<c:choose>
    <c:when test="${USER_CONTAINER.token.isAdmin == 'Y'}">
		<tiles:insert name="/jsp/admin/common/cpanel-body.jsp"/>
  </c:when>
  <c:otherwise>
		<tiles:insert name="/jsp/body/permissionDenied-body.jsp"/>
  </c:otherwise>
</c:choose>





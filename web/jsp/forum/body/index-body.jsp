<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<p>
		<%-- http://groups.yahoo.com/group/fgcidoani/ --%>
		<c:choose>
			<c:when test="${FORUM_URL eq '' or FORUM_URL eq NULL}">
					<center>Your Forum has not been configured yet.</center>
			</c:when>
			<c:otherwise>
				<iframe src="<c:out value="${FORUM_URL}"/>" align="middle" frameborder="0" height="100%" width="100%" marginheight="0" marginwidth="0" scrolling="auto"></iframe>
			</c:otherwise>
		</c:choose>
        
</p>
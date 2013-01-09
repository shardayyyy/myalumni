<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<p>
		<%-- http://picasaweb.google.com/fgcidoani/ --%>
		<c:choose>
			<c:when test="${ALBUM_URL eq '' or ALBUM_URL eq NULL}">
					<center>Your Album has not been configured yet.</center>
			</c:when>
			<c:otherwise>
				<iframe src="<c:out value="${ALBUM_URL}"/>" align="middle" frameborder="0" height="100%" width="100%" marginheight="0" marginwidth="0" scrolling="auto"></iframe>		
			</c:otherwise>
		</c:choose>
        
</p>
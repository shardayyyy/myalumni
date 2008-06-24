<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>


<%-- Reference Website http://www.wtv-zone.com/bluefox/marquee.html--%>
<table width="100%" align="center">
	  <tr>
	    <td>
	    	<c:choose>
				<c:when test="${luScrollVO.lastModifiedDate != null}">
	                            <c:choose>
	                                    <c:when test="${luScrollVO.priority == 'Y'}">
	
	                                        <font color="red"><b><marquee scrollamount=2><fmt:formatDate value="${luScrollVO.lastModifiedDate}" dateStyle="long"/> - <c:out value="${luScrollVO.scrollText}"/></marquee></b></font>
	                                    </c:when>
	                                    <c:otherwise>
	                                        <marquee scrollamount=2><fmt:formatDate value="${luScrollVO.lastModifiedDate}" dateStyle="long"/> - <c:out value="${luScrollVO.scrollText}"/>  </marquee>
	                                        
	                                    </c:otherwise>                            
	                            </c:choose>
				</c:when>
				<c:otherwise>
					&nbsp;
				</c:otherwise>
	    	</c:choose>
	    </td>
	</tr>
</table>

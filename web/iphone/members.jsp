<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<tiles:insert definition="myalumni.iphone" flush="true">
  <c:choose>
  	<c:when test="${sortType eq 'firstName' }">
  		<tiles:put name="title" value="Members First Name" />
  	</c:when>
  	<c:when test="${sortType eq 'lastName' }">
  		<tiles:put name="title" value="Members Last Name" />
  	</c:when> 
  	<c:otherwise>
  		<tiles:put name="title" value="Members" />
  	</c:otherwise> 	
  </c:choose>
  
  <tiles:put name="body" value="/iphone/body/members-body.jsp" />
</tiles:insert>


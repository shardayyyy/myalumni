<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="menuTab" scope="session" value="home"/>


  <c:choose>
  	<c:when test="${ FIRST_STARTUP eq 'Y'}">
		<tiles:insert definition="myalumni.setup" flush="true">
		  <tiles:put name="title" value="Setup" />
		  <tiles:put name="body" value="/jsp/setup/body/index-body.jsp" />
		</tiles:insert>	
  	</c:when>
  	<c:otherwise>
		<tiles:insert definition="myalumni.base" flush="true">
		  <tiles:put name="title" value="Welcome" />
		  <tiles:put name="body" value="/jsp/body/index-body.jsp" />
		</tiles:insert>
  	</c:otherwise>
  </c:choose>
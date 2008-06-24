<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="isHome" scope="request" value="true"/>


<tiles:insert definition="myalumni.iphone" flush="true">
  <tiles:put name="title" value="Login" />
  <tiles:put name="body" value="/iphone/body/login-body.jsp" />
</tiles:insert>


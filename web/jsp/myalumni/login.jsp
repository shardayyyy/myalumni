<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="menuTab" scope="session" value="myfegocoid"/>

<tiles:insert definition="myalumni.login" flush="true">
  <tiles:put name="title" value="Member Login" />
  <tiles:put name="body" value="/jsp/body/login-body.jsp" />
</tiles:insert>
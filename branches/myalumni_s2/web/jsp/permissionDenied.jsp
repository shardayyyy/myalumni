<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="menuTab" scope="session" value="home"/>

<tiles:insert definition="myalumni.base" flush="true">
  <tiles:put name="title" value="Permission Denied" />
  <tiles:put name="body" value="/jsp/body/permissionDenied.-body.jsp" />
</tiles:insert>
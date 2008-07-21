<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="menuTab" scope="session" value="myfegocoid"/>
<c:set var="popup" scope="session" value="false"/>



<tiles:insert definition="myalumni.base" flush="true">
  <tiles:put name="title" value="Deny Access" />
  <tiles:put name="body" value="/jsp/myalumni/body/denyAccess-body.jsp" />
</tiles:insert>
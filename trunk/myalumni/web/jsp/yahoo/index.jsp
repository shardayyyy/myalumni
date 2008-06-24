<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="menuTab" scope="session" value="yahoo"/>
<c:remove var="subMenu" />

<tiles:insert definition="myalumni.base" flush="true">
  <tiles:put name="title" value="Yahoo Groups" />
  <tiles:put name="body" value="/jsp/yahoo/body/index-body.jsp" />
</tiles:insert>
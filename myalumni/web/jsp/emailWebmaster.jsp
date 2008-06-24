<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="menuTab" scope="session" value="home"/>
<c:set var="subMenu" scope="session" value="emailwebmaster"/>

<tiles:insert definition="myalumni.base" flush="true">
  <tiles:put name="title" value="Email Webmaster" />
  <tiles:put name="body" value="/jsp/body/emailWebmaster-body.jsp" />
</tiles:insert>
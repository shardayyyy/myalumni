<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="menuTab" scope="session" value="home"/>

<tiles:insert definition="myalumni.base" flush="true">
  <tiles:put name="title" value="Thank You" />
  <tiles:put name="body" value="/jsp/body/thanks-body.jsp" />
</tiles:insert>
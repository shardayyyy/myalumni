<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="menuTab" scope="session" value="myalumni"/>

<tiles:insert definition="myalumni.base" flush="true">
  <tiles:put name="title" value="View ClassNews" />
  <tiles:put name="body" value="/jsp/myalumni/body/viewClassNews-body.jsp" />
</tiles:insert>
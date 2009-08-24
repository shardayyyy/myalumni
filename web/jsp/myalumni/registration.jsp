<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="menuTab" scope="session" value="myalumni"/>
<c:set var="actionTask" scope="request" value="add"/>

<tiles:insert definition="myalumni.base" flush="true">
  <tiles:put name="title" value="Registration" />
  <tiles:put name="body" value="/jsp/myalumni/body/registration-body.jsp" />
</tiles:insert>
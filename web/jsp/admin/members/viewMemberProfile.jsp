<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="adminTab" scope="session" value="members"/>
<c:set var="MODULE" scope="request" value="ADMIN"/>

<tiles:insert definition="myalumni.admin" flush="true">
  <tiles:put name="title" value="Viewing My Profile" />
  <tiles:put name="body" value="/jsp/myalumni/body/viewProfile-body.jsp" />
</tiles:insert>
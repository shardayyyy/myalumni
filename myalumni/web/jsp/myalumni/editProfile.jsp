<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="menuTab" scope="session" value="myfegocoid"/>
<c:set var="actionTask" scope="request" value="update"/>

<tiles:insert definition="myalumni.base" flush="true">
  <tiles:put name="title" value="Edit My Profile" />
  <tiles:put name="body" value="/jsp/myalumni/body/editProfile-body.jsp" />
</tiles:insert>
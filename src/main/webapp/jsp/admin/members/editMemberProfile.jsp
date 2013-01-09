<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="MODULE" scope="request" value="ADMIN"/>
<c:set var="adminTab" scope="session" value="members"/>

<tiles:insert definition="myalumni.admin" flush="true">
  <tiles:put name="title" value="Edit Profile" />
  <tiles:put name="body" value="/jsp/admin/members/body/editMemberProfile-body.jsp" />
</tiles:insert>
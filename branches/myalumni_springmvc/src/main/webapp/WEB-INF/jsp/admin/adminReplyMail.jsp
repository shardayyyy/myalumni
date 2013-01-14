<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="adminTab" scope="session" value="desktop"/>
<c:set var="MODULE" scope="request" value="ADMIN"/>
<c:set var="mailType" scope="request" value="reply"/>

<tiles:insert definition="myalumni.admin" flush="true">
  <tiles:put name="title" value="Admin Reply to Private Message" />
  <tiles:put name="body" value="/jsp/admin/body/adminReplyMail-body.jsp" />
</tiles:insert>
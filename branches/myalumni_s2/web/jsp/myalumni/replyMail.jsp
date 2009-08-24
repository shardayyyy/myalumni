<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="menuTab" scope="session" value="myalumni"/>
<c:set var="mailType" scope="request" value="reply"/>

<tiles:insert definition="myalumni.base" flush="true">
  <tiles:put name="title" value="Reply to Private Message" />
  <tiles:put name="body" value="/jsp/myalumni/body/replyMail-body.jsp" />
</tiles:insert>
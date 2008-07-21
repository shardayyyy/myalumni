<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="adminTab" scope="session" value="admin"/>
<c:set var="MODULE" scope="request" value="ADMIN"/>

<tiles:insert definition="myalumni.admin" flush="true">
  <tiles:put name="title" value="JCapture Stats" />
  <tiles:put name="body" value="/jsp/admin/admin/body/jcaptcha_stats-body.jsp" />
</tiles:insert>
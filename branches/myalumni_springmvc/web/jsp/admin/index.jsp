<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="adminTab" scope="session" value="desktop"/>

<tiles:insert definition="myalumni.login" flush="true">
  <tiles:put name="title" value="Admin Login" />
  <tiles:put name="body" value="/jsp/admin/body/index-body.jsp" />
</tiles:insert>
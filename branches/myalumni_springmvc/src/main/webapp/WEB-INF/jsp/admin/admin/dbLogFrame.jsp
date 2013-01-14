<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="adminTab" scope="session" value="admin"/>

<tiles:insert definition="myalumni.blank" flush="true">
  <tiles:put name="title" value="Viewing Database Backup" />
  <tiles:put name="body" value="/jsp/admin/admin/body/dbLogFrame-body.jsp" />
</tiles:insert>

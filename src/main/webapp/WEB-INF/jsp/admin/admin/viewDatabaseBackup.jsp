<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="adminTab" scope="session" value="admin"/>

<tiles:insert definition="myalumni.admin" flush="true">
  <tiles:put name="title" value="Viewing Database Backup" />
  <tiles:put name="body" value="/jsp/admin/admin/body/viewDatabaseBackup-body.jsp" />
</tiles:insert>
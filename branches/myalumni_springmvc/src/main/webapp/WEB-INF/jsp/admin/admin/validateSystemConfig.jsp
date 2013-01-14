<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="adminTab" scope="session" value="admin"/>

<tiles:insert definition="myalumni.admin" flush="true">
  <tiles:put name="title" value="Validate System Config" />
  <tiles:put name="body" value="/jsp/admin/admin/body/validateSystemConfig-body.jsp" />
</tiles:insert>
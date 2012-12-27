<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="adminTab" scope="session" value="system"/>

<tiles:insert definition="myalumni.admin" flush="true">
  <tiles:put name="title" value="Upload Logo" />
  <tiles:put name="body" value="/jsp/admin/system/body/uploadLogo-body.jsp" />
</tiles:insert>
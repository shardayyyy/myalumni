<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="adminTab" scope="session" value="system"/>

<tiles:insert definition="myalumni.blank" flush="true">
  <tiles:put name="title" value="Viewing Log Files" />
  <tiles:put name="body" value="/jsp/admin/system/body/logFrame-body.jsp" />
</tiles:insert>

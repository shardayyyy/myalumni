<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="adminTab" scope="session" value="system"/>

<tiles:insert definition="myalumni.admin" flush="true">
  <tiles:put name="title" value="Maintain Scroll" />
  <tiles:put name="body" value="/jsp/admin/system/body/maintainScroll-body.jsp" />
</tiles:insert>
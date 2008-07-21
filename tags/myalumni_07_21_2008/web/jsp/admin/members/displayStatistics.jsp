<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="adminTab" scope="session" value="members"/>

<tiles:insert definition="myalumni.admin" flush="true">
  <tiles:put name="title" value="Viewing System Statistics" />
  <tiles:put name="body" value="/jsp/admin/members/body/displayStatistics-body.jsp" />
</tiles:insert>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="menuTab" scope="session" value="myalumni"/>

<tiles:insert definition="myalumni.blank" flush="true">
  <tiles:put name="title" value="Viewing Mini Profile" />
  <tiles:put name="body" value="/jsp/common/miniProfileDetails-body.jsp" />
</tiles:insert>
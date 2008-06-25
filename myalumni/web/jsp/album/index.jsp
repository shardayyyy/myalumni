<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="menuTab" scope="session" value="album"/>

<tiles:insert definition="myalumni.base" flush="true">
  <tiles:put name="title" value="Album" />
  <tiles:put name="body" value="/jsp/album/body/index-body.jsp" />
</tiles:insert>
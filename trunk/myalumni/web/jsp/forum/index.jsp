<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="menuTab" scope="session" value="forum"/>


<tiles:insert definition="myalumni.base" flush="true">
  <tiles:put name="title" value="Discussion Group" />
  <tiles:put name="body" value="/jsp/forum/body/index-body.jsp" />
</tiles:insert>
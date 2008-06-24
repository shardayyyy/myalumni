<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="menuTab" scope="session" value="myfegocoid"/>
<c:remove var="subMenu" />

<tiles:insert definition="myalumni.base" flush="true">
  <tiles:put name="title" value="MyFeGoCoId" />
  <tiles:put name="body" value="/jsp/myalumni/body/index-body.jsp" />
</tiles:insert>
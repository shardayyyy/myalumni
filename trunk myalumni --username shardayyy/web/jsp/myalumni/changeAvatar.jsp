<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="menuTab" scope="session" value="myfegocoid"/>

<tiles:insert definition="myalumni.base" flush="true">
  <tiles:put name="title" value="Change Avatar" />
  <tiles:put name="body" value="/jsp/myalumni/body/changeAvatar-body.jsp" />
</tiles:insert>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="menuTab" scope="session" value="myfegocoid"/>

<tiles:insert definition="myalumni.base" flush="true">
  <tiles:put name="title" value="Viewing Private Message" />
  <tiles:put name="body" value="/jsp/myalumni/body/readMessage-body.jsp" />
</tiles:insert>
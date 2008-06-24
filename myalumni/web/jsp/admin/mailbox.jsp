<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="adminTab" scope="session" value="desktop"/>
<c:set var="MODULE" scope="request" value="ADMIN"/>

<tiles:insert definition="myalumni.admin" flush="true">
  <tiles:put name="title" value="My Mail Box" />
  <tiles:put name="body" value="/jsp/admin/body/mailbox-body.jsp" />
</tiles:insert>
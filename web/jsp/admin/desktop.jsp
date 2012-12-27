<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="adminTab" scope="session" value="desktop"/>


<tiles:insert definition="myalumni.admin" flush="true">
  <tiles:put name="title" value="Administration Desktop" />
  <tiles:put name="body" value="/jsp/admin/body/desktop-body.jsp" />
</tiles:insert>
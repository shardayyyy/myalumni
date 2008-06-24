<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="menuTab" scope="session" value="contact"/>
<c:remove var="subMenu" />

<tiles:insert definition="myalumni.base" flush="true">
  <tiles:put name="title" value="Contacts" />
  <tiles:put name="body" value="/jsp/contact/body/index-body.jsp" />
</tiles:insert>
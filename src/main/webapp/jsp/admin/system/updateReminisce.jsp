<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<c:set var="adminTab" scope="session" value="system"/>
<c:set var="MODULE" scope="request" value="ADMIN"/>


<tiles:insert definition="myalumni.admin" flush="true">
  <tiles:put name="title" value="Administration - System (Update Reminisce)" />
  <tiles:put name="body" value="/jsp/admin/system/body/updateReminisce-body.jsp" />
</tiles:insert>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<c:set var="adminTab" scope="session" value="general"/>



<tiles:insert definition="myalumni.admin" flush="true">
  <tiles:put name="title" value="Administration - General (Update Front Page Link)" />
  <tiles:put name="body" value="/jsp/admin/general/body/updateFrontPageLink-body.jsp" />
</tiles:insert>
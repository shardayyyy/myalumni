<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<c:set var="adminTab" scope="session" value="security"/>


<tiles:insert definition="myalumni.admin" flush="true">
  <tiles:put name="title" value="Administration - Security" />
  <tiles:put name="body" value="/jsp/admin/common/security-portlet.jsp" />
</tiles:insert>
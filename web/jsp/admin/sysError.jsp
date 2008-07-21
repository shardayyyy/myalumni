<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>


<c:set var="MODULE" scope="request" value="ADMIN"/>

<tiles:insert definition="myalumni.admin" flush="true">
  <tiles:put name="title" value="Admin System Error" />
  <tiles:put name="body" value="/jsp/common/sysError-body.jsp" />
</tiles:insert>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="adminTab" scope="session" value="desktop"/>
<c:set var="MODULE" scope="request" value="ADMIN"/>

<tiles:insert definition="myalumni.blank" flush="true">
  <tiles:put name="title" value="ADMIN::Viewing Mini Profile" />
  <tiles:put name="body" value="/jsp/admin/members/common/adminMiniProfileDetails-body.jsp" />
</tiles:insert>
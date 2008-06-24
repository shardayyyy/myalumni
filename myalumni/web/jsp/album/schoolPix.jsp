<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="menuTab" scope="session" value="album"/>
<c:set var="subMenu" scope="session" value="schoolpix"/>


<tiles:insert definition="myalumni.base" flush="true">
  <tiles:put name="title" value="Album - School Pictures" />
  <tiles:put name="body" value="/jsp/album/body/schoolPix-body.jsp" />
</tiles:insert>
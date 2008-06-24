<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="menuTab" scope="session" value="reminisce"/>
<c:set var="subMenu" scope="session" value="playlet"/>


<tiles:insert definition="myalumni.base" flush="true">
  <tiles:put name="title" value="Reminisce - Playlet" />
  <tiles:put name="body" value="/jsp/reminisce/body/playlet-body.jsp" />
</tiles:insert>
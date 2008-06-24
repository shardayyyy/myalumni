<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="menuTab" scope="session" value="reminisce"/>
<c:set var="subMenu" scope="session" value="schooljokes"/>


<tiles:insert definition="myalumni.base" flush="true">
  <tiles:put name="title" value="Reminisce - The OtherSide to Idoani Life" />
  <tiles:put name="body" value="/jsp/reminisce/body/schoolJokes-body.jsp" />
</tiles:insert>
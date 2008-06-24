<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<c:set var="menuTab" scope="session" value="aboutus"/>
<c:set var="subMenu" scope="session" value="coollinks"/>

<tiles:insert definition="myalumni.base" flush="true">
  <tiles:put name="title" value="About Us - Cool Links" />
  <tiles:put name="body" value="/jsp/aboutus/body/coolLinks-body.jsp" />
</tiles:insert>
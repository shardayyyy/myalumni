<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>

<tiles:insert definition="myalumni.login" flush="true">
  <tiles:put name="title" value="Login" />
  <tiles:put name="body" value="/jsp/body/login-body.jsp" />
</tiles:insert>
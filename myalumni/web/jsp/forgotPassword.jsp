<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>


<tiles:insert definition="myalumni.login" flush="true">
  <tiles:put name="title" value="Request Forgot Password" />
  <tiles:put name="body" value="/jsp/body/forgotPassword-body.jsp" />
</tiles:insert>
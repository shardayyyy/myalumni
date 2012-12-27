<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>


<tiles:insert definition="myalumni.login" flush="true">
  <tiles:put name="title" value="Request Forgot UserName" />
  <tiles:put name="body" value="/jsp/body/forgotUserName-body.jsp" />
</tiles:insert>
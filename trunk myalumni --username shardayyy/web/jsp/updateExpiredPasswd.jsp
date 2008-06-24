<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>


<tiles:insert definition="myalumni.login" flush="true">
  <tiles:put name="title" value="Update Expired Password" />
  <tiles:put name="body" value="/jsp/body/updateExpiredPasswd-body.jsp" />
</tiles:insert>